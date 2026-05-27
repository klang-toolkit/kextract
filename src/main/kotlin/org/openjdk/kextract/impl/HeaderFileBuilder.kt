/*
 * Copyright (c) 2020, 2025, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package org.openjdk.kextract.impl

import org.openjdk.kextract.Declaration
import org.openjdk.kextract.Type
import org.openjdk.kextract.impl.DeclarationImpl.JavaName
import org.openjdk.kextract.newimpl.Options

import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator
import java.lang.invoke.MethodType
import java.util.stream.Collectors
import java.util.stream.IntStream

/**
 * A helper class to generate header interface class in source form.
 * After aggregating various constituents of a .java source, build
 * method is called to get overall generated source string.
 */
internal class HeaderFileBuilder(
    builder: SourceFileBuilder,
    className: String,
    superName: String?,
    runtimeHelperName: String
) : ClassSourceBuilder(builder, "public", Kind.CLASS, className, superName, null, runtimeHelperName) {

    companion object {
        private const val ASMLABEL = "AsmLabelAttr"

        private val isMacOSX: Boolean =
            System.getProperty("os.name", "unknown").contains("OS X")
    }

    private val holderClassNames = HashSet<String>()

    fun addVar(varTree: Declaration.Variable) {
        val javaName = JavaName.getOrThrow(varTree)
        appendBlankLine()
        val holderClass = emitVarHolderClass(varTree, javaName)
        if (Utils.isArray(varTree.type()) || Utils.isStructOrUnion(varTree.type())) {
            emitGlobalSegmentGetter(holderClass, javaName, varTree, "Getter for variable:")
            emitGlobalSegmentSetter(holderClass, javaName, varTree, "Setter for variable:")
            val dims = Utils.dimensions(varTree.type()).size
            if (dims > 0) {
                val indexList = IndexList.of(dims)
                emitGlobalArrayGetter(holderClass, indexList, javaName, varTree, "Indexed getter for variable:")
                emitGlobalArraySetter(holderClass, indexList, javaName, varTree, "Indexed setter for variable:")
            }
        } else if (Utils.isPointer(varTree.type()) || Utils.isPrimitive(varTree.type())) {
            emitGlobalGetter(holderClass, javaName, varTree, "Getter for variable:")
            emitGlobalSetter(holderClass, javaName, varTree, "Setter for variable:")
        } else {
            throw IllegalArgumentException("Tree type not handled: ${varTree.type()}")
        }
    }

    fun addFunction(funcTree: Declaration.Function) {
        val nativeName = funcTree.name()
        val isVarargs = funcTree.type().varargs()
        val needsAllocator = Utils.isStructOrUnion(funcTree.type().returnType())
        val parameterNames = funcTree.parameters()
            .stream()
            .map { JavaName.getOrThrow(it) }
            .toList()

        emitFunctionWrapper(
            JavaName.getOrThrow(funcTree), nativeName,
            needsAllocator, isVarargs, parameterNames, funcTree
        )
    }

    private fun lookupName(decl: Declaration): String {
        val attrs = decl.getAttribute(Declaration.ClangAttributes::class.java)
        return if (attrs != null && attrs.attributes.containsKey(ASMLABEL)) {
            val asmLabel = attrs.attributes[ASMLABEL]!![0]
            if (isMacOSX)
                asmLabel.substring(1) // skip leading "_"
            else
                asmLabel
        } else {
            decl.name()
        }
    }

    fun addConstant(constantTree: Declaration.Constant) {
        val value = constantTree.value()
        emitConstant(Utils.carrierFor(constantTree.type()), JavaName.getOrThrow(constantTree), value, constantTree)
    }

    // private generation

    private fun emitFunctionWrapper(
        javaName: String, nativeName: String, needsAllocator: Boolean,
        isVarArg: Boolean, parameterNames: List<String>, decl: Declaration.Function
    ) {
        var declType: MethodType = Utils.methodTypeFor(decl.type())
        val finalParamNames = finalizeParameterNames(parameterNames, needsAllocator, isVarArg)
        if (needsAllocator) {
            declType = declType.insertParameterTypes(0, SegmentAllocator::class.java)
        }

        val retType = declType.returnType().simpleName
        val isVoid = declType.returnType() == Void.TYPE
        val returnNoCast = if (isVoid) "" else "return "
        val returnWithCast = if (isVoid) "" else String.format("%1\$s(%2\$s)", returnNoCast, retType)
        val paramList = finalParamNames.joinToString(", ")
        val traceArgList = if (paramList.isEmpty())
            String.format("\"%1\$s\"", nativeName)
        else
            String.format("\"%1\$s\", %2\$s", nativeName, paramList)
        incrAlign()
        if (!isVarArg) {
            val holderClass = newHolderClassName(javaName)
            appendLines("""

                private static class %1${'$'}s {
                    public static final FunctionDescriptor DESC = %2${'$'}s;

                    public static final MemorySegment ADDR = SYMBOL_LOOKUP.findOrThrow("%3${'$'}s");

                    public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(ADDR, DESC);
                }
                """.trimIndent() + "\n",
                holderClass, functionDescriptorString(1, decl.type()),
                lookupName(decl))
            appendBlankLine()
            emitDocComment(decl, "Function descriptor for:")
            appendLines("""
                public static FunctionDescriptor %1${'$'}s${'$'}descriptor() {
                    return %2${'$'}s.DESC;
                }
                """.trimIndent() + "\n", javaName, holderClass)
            appendBlankLine()
            emitDocComment(decl, "Downcall method handle for:")
            appendLines("""
                public static MethodHandle %1${'$'}s${'$'}handle() {
                    return %2${'$'}s.HANDLE;
                }
                """.trimIndent() + "\n", javaName, holderClass)
            appendBlankLine()
            emitDocComment(decl, "Address for:")
            appendLines("""
                public static MemorySegment %1${'$'}s${'$'}address() {
                    return %2${'$'}s.ADDR;
                }
                """.trimIndent() + "\n", javaName, holderClass)
            appendBlankLine()
            emitDocComment(decl)
            appendLines("""
            public static %1${'$'}s %2${'$'}s(%3${'$'}s) {
                var mh${'$'} = %4${'$'}s.HANDLE;
                try {
                    if (TRACE_DOWNCALLS) {
                        traceDowncall(%5${'$'}s);
                    }
                    %6${'$'}smh${'$'}.invokeExact(%7${'$'}s);
                } catch (Error | RuntimeException ex) {
                   throw ex;
                } catch (Throwable ex${'$'}) {
                   throw new AssertionError("should not reach here", ex${'$'});
                }
            }
            """.trimIndent() + "\n",
                retType, javaName,
                paramExprs(declType, finalParamNames, isVarArg),
                holderClass, traceArgList, returnWithCast, paramList)
        } else {
            val invokerClassName = newHolderClassName(javaName)
            val paramExprs = paramExprs(declType, finalParamNames, isVarArg)
            appendBlankLine()
            emitDocComment(decl, "Variadic invoker class for:")
            appendLines("""
                public static class %1${'$'}s {
                    private static final FunctionDescriptor BASE_DESC = %2${'$'}s;
                    private static final MemorySegment ADDR = SYMBOL_LOOKUP.findOrThrow("%3${'$'}s");

                    private final MethodHandle handle;
                    private final FunctionDescriptor descriptor;
                    private final MethodHandle spreader;

                    private %4${'$'}s(MethodHandle handle, FunctionDescriptor descriptor, MethodHandle spreader) {
                        this.handle = handle;
                        this.descriptor = descriptor;
                        this.spreader = spreader;
                    }
                """.trimIndent() + "\n",
                invokerClassName, functionDescriptorString(2, decl.type()),
                lookupName(decl), invokerClassName)
            incrAlign()
            appendBlankLine()
            emitDocComment(decl, "Variadic invoker factory for:")
            appendLines("""
                public static %1${'$'}s makeInvoker(MemoryLayout... layouts) {
                    FunctionDescriptor desc${'$'} = BASE_DESC.appendArgumentLayouts(layouts);
                    Linker.Option fva${'$'} = Linker.Option.firstVariadicArg(BASE_DESC.argumentLayouts().size());
                    var mh${'$'} = Linker.nativeLinker().downcallHandle(ADDR, desc${'$'}, fva${'$'});
                    var spreader${'$'} = mh${'$'}.asSpreader(Object[].class, layouts.length);
                    return new %1${'$'}s(mh${'$'}, desc${'$'}, spreader${'$'});
                }
                """.trimIndent() + "\n", invokerClassName)
            decrAlign()
            appendLines("""

                    /**
                     * {@return the address}
                     */
                    public static MemorySegment address() {
                        return ADDR;
                    }

                    /**
                     * {@return the specialized method handle}
                     */
                    public MethodHandle handle() {
                        return handle;
                    }

                    /**
                     * {@return the specialized descriptor}
                     */
                    public FunctionDescriptor descriptor() {
                        return descriptor;
                    }

                    public %1${'$'}s apply(%2${'$'}s) {
                        try {
                            if (TRACE_DOWNCALLS) {
                                traceDowncall(%3${'$'}s);
                            }
                            %4${'$'}s spreader.invokeExact(%5${'$'}s);
                        } catch(IllegalArgumentException | ClassCastException ex${'$'})  {
                            throw ex${'$'}; // rethrow IAE from passing wrong number/type of args
                        } catch (Throwable ex${'$'}) {
                           throw new AssertionError("should not reach here", ex${'$'});
                        }
                    }
                }
                """.trimIndent() + "\n",
                retType, paramExprs, traceArgList, returnWithCast, paramList)
        }
        decrAlign()
    }

    fun emitPrimitiveTypedef(typedefTree: Declaration.Typedef, primType: Type.Primitive, name: String) {
        emitPrimitiveTypedefLayout(name, primType, typedefTree)
    }

    fun emitPointerTypedef(typedefTree: Declaration.Typedef, name: String) {
        emitPrimitiveTypedefLayout(name, Type.pointer(), typedefTree)
    }

    fun emitFirstHeaderPreamble(libraries: List<Options.Library>, useSystemLoadLibrary: Boolean) {
        val lookups = mutableListOf<String>()
        // if legacy library loading is selected, load libraries (if any) into current loader
        if (useSystemLoadLibrary) {
            appendBlankLine()
            appendIndentedLines("""

                static {
                """.trimIndent() + "\n")
            incrAlign()
            for (lib in libraries) {
                val method = if (lib.specKind == Options.Library.SpecKind.PATH) "load" else "loadLibrary"
                appendIndentedLines("System.%1\$s(\"%2\$s\");", method, Options.Library.toQuotedName(lib))
            }
            decrAlign()
            appendIndentedLines("""
                }
                """.trimIndent() + "\n")
        } else {
            // otherwise, add a library lookup per library (if any)
            libraries.stream()
                .map { l ->
                    if (l.specKind == Options.Library.SpecKind.PATH)
                        String.format("SymbolLookup.libraryLookup(\"%1\$s\", LIBRARY_ARENA)", Options.Library.toQuotedName(l))
                    else
                        String.format("SymbolLookup.libraryLookup(System.mapLibraryName(\"%1\$s\"), LIBRARY_ARENA)", Options.Library.toQuotedName(l))
                }
                .collect(Collectors.toCollection { lookups })
        }

        lookups.add("SymbolLookup.loaderLookup()") // fallback to loader lookup
        lookups.add("Linker.nativeLinker().defaultLookup()") // fallback to native lookup

        // wrap all lookups (but the first) with ".or(...)"
        val lookupCalls = mutableListOf<String>()
        var isFirst = true
        for (lookup in lookups) {
            lookupCalls.add(if (isFirst) lookup else String.format(".or(%1\$s)", lookup))
            isFirst = false
        }

        // chain all the calls together into a combined symbol lookup
        appendBlankLine()
        appendIndentedLines(lookupCalls.stream()
            .collect(Collectors.joining(
                String.format("\n%1\$s", indentString(2)),
                "static final SymbolLookup SYMBOL_LOOKUP = ",
                ";"
            )))
        appendBlankLine()
    }

    fun emitLibaryArena() {
        appendIndentedLines("""

            static final Arena LIBRARY_ARENA = Arena.ofAuto();""".trimIndent() + "\n")
    }

    fun emitRuntimeHelperMethods() {
        appendIndentedLines("""

            static final boolean TRACE_DOWNCALLS = Boolean.getBoolean("kextract.trace.downcalls");

            static void traceDowncall(String name, Object... args) {
                 String traceArgs = Arrays.stream(args)
                               .map(Object::toString)
                               .collect(Collectors.joining(", "));
                 System.out.printf("%s(%s)\n", name, traceArgs);
            }

            static MethodHandle upcallHandle(Class<?> fi, String name, FunctionDescriptor fdesc) {
                try {
                    return MethodHandles.lookup().findVirtual(fi, name, fdesc.toMethodType());
                } catch (ReflectiveOperationException ex) {
                    throw new AssertionError(ex);
                }
            }

            static MemoryLayout align(MemoryLayout layout, long align) {
                return switch (layout) {
                    case PaddingLayout p -> p;
                    case ValueLayout v -> v.withByteAlignment(align);
                    case GroupLayout g -> {
                        MemoryLayout[] alignedMembers = g.memberLayouts().stream()
                                .map(m -> align(m, align)).toArray(MemoryLayout[]::new);
                        yield g instanceof StructLayout ?
                                MemoryLayout.structLayout(alignedMembers) : MemoryLayout.unionLayout(alignedMembers);
                    }
                    case SequenceLayout s -> MemoryLayout.sequenceLayout(s.elementCount(), align(s.elementLayout(), align));
                };
            }
            """.trimIndent() + "\n")
    }

    fun emitBasicPrimitiveTypes() {
        appendIndentedLines("""

            public static final ValueLayout.OfBoolean C_BOOL = (ValueLayout.OfBoolean) Linker.nativeLinker().canonicalLayouts().get("bool");
            public static final ValueLayout.OfByte C_CHAR =(ValueLayout.OfByte)Linker.nativeLinker().canonicalLayouts().get("char");
            public static final ValueLayout.OfShort C_SHORT = (ValueLayout.OfShort) Linker.nativeLinker().canonicalLayouts().get("short");
            public static final ValueLayout.OfInt C_INT = (ValueLayout.OfInt) Linker.nativeLinker().canonicalLayouts().get("int");
            public static final ValueLayout.OfLong C_LONG_LONG = (ValueLayout.OfLong) Linker.nativeLinker().canonicalLayouts().get("long long");
            public static final ValueLayout.OfFloat C_FLOAT = (ValueLayout.OfFloat) Linker.nativeLinker().canonicalLayouts().get("float");
            public static final ValueLayout.OfDouble C_DOUBLE = (ValueLayout.OfDouble) Linker.nativeLinker().canonicalLayouts().get("double");
            public static final AddressLayout C_POINTER = ((AddressLayout) Linker.nativeLinker().canonicalLayouts().get("void*"))
                    .withTargetLayout(MemoryLayout.sequenceLayout(java.lang.Long.MAX_VALUE, C_CHAR));
            """.trimIndent() + "\n")
        if (TypeImpl.IS_WINDOWS) {
            appendIndentedLines("public static final ValueLayout.OfInt C_LONG = (ValueLayout.OfInt) Linker.nativeLinker().canonicalLayouts().get(\"long\");")
            appendIndentedLines("public static final ValueLayout.OfDouble C_LONG_DOUBLE = (ValueLayout.OfDouble) Linker.nativeLinker().canonicalLayouts().get(\"double\");")
        } else {
            appendIndentedLines("public static final ValueLayout.OfLong C_LONG = (ValueLayout.OfLong) Linker.nativeLinker().canonicalLayouts().get(\"long\");")
        }
    }

    private fun emitGlobalGetter(holderClass: String, javaName: String, decl: Declaration.Variable, docHeader: String) {
        appendBlankLine()
        incrAlign()
        emitDocComment(decl, docHeader)
        val type = Utils.carrierFor(decl.type())
        appendLines("""
            public static %1${'$'}s %2${'$'}s() {
                return %3${'$'}s.SEGMENT.get(%3${'$'}s.LAYOUT, 0L);
            }
            """.trimIndent() + "\n", type.simpleName, javaName, holderClass)
        decrAlign()
    }

    private fun emitGlobalSetter(holderClass: String, javaName: String, decl: Declaration.Variable, docHeader: String) {
        appendBlankLine()
        incrAlign()
        emitDocComment(decl, docHeader)
        val type = Utils.carrierFor(decl.type())
        appendLines("""
            public static void %1${'$'}s(%2${'$'}s varValue) {
                %3${'$'}s.SEGMENT.set(%3${'$'}s.LAYOUT, 0L, varValue);
            }
            """.trimIndent() + "\n", javaName, type.simpleName, holderClass)
        decrAlign()
    }

    private fun emitGlobalSegmentGetter(holderClass: String, javaName: String, varTree: Declaration.Variable, docHeader: String) {
        appendBlankLine()
        incrAlign()
        emitDocComment(varTree, docHeader)
        appendLines("""
            public static MemorySegment %1${'$'}s() {
                return %2${'$'}s.SEGMENT;
            }
            """.trimIndent() + "\n", javaName, holderClass)
        decrAlign()
    }

    private fun emitGlobalSegmentSetter(holderClass: String, javaName: String, varTree: Declaration.Variable, docHeader: String) {
        appendBlankLine()
        incrAlign()
        emitDocComment(varTree, docHeader)
        appendLines("""
            public static void %1${'$'}s(MemorySegment varValue) {
                MemorySegment.copy(varValue, 0L, %2${'$'}s.SEGMENT, 0L, %2${'$'}s.LAYOUT.byteSize());
            }
            """.trimIndent() + "\n", javaName, holderClass)
        decrAlign()
    }

    private fun emitGlobalArrayGetter(
        holderClass: String, indexList: IndexList,
        javaName: String, varTree: Declaration.Variable, docHeader: String
    ) {
        val elemType = Utils.typeOrElemType(varTree.type())
        val typeCls = Utils.carrierFor(elemType)
        appendBlankLine()
        incrAlign()
        emitDocComment(varTree, docHeader)
        if (Utils.isStructOrUnion(elemType)) {
            appendLines("""
                public static MemorySegment %1${'$'}s(%2${'$'}s) {
                    try {
                        return (MemorySegment)%3${'$'}s.HANDLE.invokeExact(%3${'$'}s.SEGMENT, 0L, %4${'$'}s);
                    } catch (Error | RuntimeException ex) {
                        throw ex;
                    } catch (Throwable ex${'$'}) {
                        throw new AssertionError("should not reach here", ex${'$'});
                    }
                }
                """.trimIndent() + "\n", javaName, indexList.decl, holderClass, indexList.use)
        } else {
            appendLines("""
                public static %1${'$'}s %2${'$'}s(%3${'$'}s) {
                    return (%1${'$'}s)%4${'$'}s.HANDLE.get(%4${'$'}s.SEGMENT, 0L, %5${'$'}s);
                }
                """.trimIndent() + "\n",
                typeCls.simpleName, javaName, indexList.decl,
                holderClass, indexList.use)
        }
        decrAlign()
    }

    private fun emitGlobalArraySetter(
        holderClass: String, indexList: IndexList,
        javaName: String, varTree: Declaration.Variable, docHeader: String
    ) {
        val elemType = Utils.typeOrElemType(varTree.type())
        val typeCls = Utils.carrierFor(elemType)
        appendBlankLine()
        incrAlign()
        emitDocComment(varTree, docHeader)
        if (Utils.isStructOrUnion(elemType)) {
            appendLines("""
                public static void %1${'$'}s(%2${'$'}s, MemorySegment varValue) {
                    MemorySegment.copy(varValue, 0L, %1${'$'}s(%3${'$'}s), 0L, %4${'$'}s.byteSize());
                }
                """.trimIndent() + "\n", javaName, indexList.decl, indexList.use, layoutString(elemType))
        } else {
            appendLines("""
                public static void %1${'$'}s(%2${'$'}s, %3${'$'}s varValue) {
                    %4${'$'}s.HANDLE.set(%4${'$'}s.SEGMENT, 0L, %5${'$'}s, varValue);
                }
                """.trimIndent() + "\n",
                javaName, indexList.decl, typeCls.simpleName, holderClass, indexList.use)
        }
        decrAlign()
    }

    private fun emitVarHolderClass(varDecl: Declaration.Variable, javaName: String): String {
        val varType = varDecl.type()
        val mangledName = newHolderClassName(String.format("%1\$s\$constants", javaName))
        val layoutType = Utils.layoutCarrierFor(varType).simpleName
        if (varType is Type.Array) {
            val dimensions = Utils.dimensions(varType)
            val path = IntStream.range(0, dimensions.size)
                .mapToObj { _ -> "sequenceElement()" }
                .collect(Collectors.joining(", "))
            val elemType = Utils.typeOrElemType(varType)
            val accessHandle = if (Utils.isStructOrUnion(elemType))
                "public static final MethodHandle HANDLE = LAYOUT.sliceHandle($path);"
            else
                "public static final VarHandle HANDLE = LAYOUT.varHandle($path);\n"
            val dimsString = dimensions.stream().map { obj: Long -> obj.toString() }
                .collect(Collectors.joining(", "))
            appendIndentedLines("""
                private static class %1${'$'}s {
                    public static final %2${'$'}s LAYOUT = %3${'$'}s;
                    public static final MemorySegment SEGMENT = SYMBOL_LOOKUP.findOrThrow("%4${'$'}s").reinterpret(LAYOUT.byteSize());
                %5${'$'}s
                    public static final long[] DIMS = { %6${'$'}s };
                }
                """.trimIndent() + "\n",
                mangledName, layoutType, layoutString(varType), lookupName(varDecl),
                accessHandle, dimsString)
        } else {
            appendIndentedLines("""
                private static class %1${'$'}s {
                    public static final %2${'$'}s LAYOUT = %3${'$'}s;
                    public static final MemorySegment SEGMENT = SYMBOL_LOOKUP.findOrThrow("%4${'$'}s").reinterpret(LAYOUT.byteSize());
                }
                """.trimIndent() + "\n",
                mangledName, layoutType, layoutString(varType), lookupName(varDecl))
        }
        incrAlign()
        appendBlankLine()
        emitDocComment(varDecl, "Layout for variable:")
        appendLines("""
                public static %1${'$'}s %2${'$'}s${'$'}layout() {
                    return %3${'$'}s.LAYOUT;
                }
                """.trimIndent() + "\n", layoutType, javaName, mangledName)
        if (!Utils.isStructOrUnion(varType) && !Utils.isArray(varType)) {
            appendBlankLine()
            emitDocComment(varDecl, "Segment for variable:")
            appendLines("""
                    public static MemorySegment %1${'$'}s${'$'}segment() {
                        return %2${'$'}s.SEGMENT;
                    }
                    """.trimIndent() + "\n", javaName, mangledName)
        }
        if (varType is Type.Array) {
            appendBlankLine()
            emitDocComment(varDecl, "Dimensions for array variable:")
            appendLines("""
                public static long[] %1${'$'}s${'$'}dimensions() {
                    return %2${'$'}s.DIMS;
                }
                """.trimIndent() + "\n", javaName, mangledName)
        }
        decrAlign()
        return mangledName
    }

    private fun emitConstant(javaType: Class<*>, constantName: String, value: Any, declaration: Declaration) {
        incrAlign()
        if (value is String) {
            emitDocComment(declaration)
            appendLines("""
                public static %1${'$'}s %2${'$'}s() {
                    class Holder {
                        static final %1${'$'}s %2${'$'}s
                            = %3${'$'}s.LIBRARY_ARENA.allocateFrom("%4${'$'}s");
                    }
                    return Holder.%2${'$'}s;
                }
                """.trimIndent() + "\n",
                javaType.simpleName,
                constantName,
                runtimeHelperName(),
                Utils.quote(value.toString()))
        } else {
            appendLines("""
                private static final %1${'$'}s %2${'$'}s = %3${'$'}s;
                """.trimIndent() + "\n",
                javaType.simpleName,
                constantName,
                constantValue(javaType, value))
            emitDocComment(declaration)
            appendLines("""
                public static %1${'$'}s %2${'$'}s() {
                    return %2${'$'}s;
                }
                """.trimIndent() + "\n",
                javaType.simpleName,
                constantName)
        }
        decrAlign()
    }

    private fun constantValue(type: Class<*>, value: Any): String {
        return if (type == MemorySegment::class.java) {
            String.format("MemorySegment.ofAddress(%1\$dL)", (value as Number).toLong())
        } else {
            val buf = StringBuilder()
            when {
                type == Float::class.javaPrimitiveType -> {
                    val f = (value as Number).toFloat()
                    if (java.lang.Float.isFinite(f)) {
                        buf.append(value)
                        buf.append("f")
                    } else {
                        buf.append("Float.valueOf(\"")
                        buf.append(value)
                        buf.append("\")")
                    }
                }
                type == Long::class.javaPrimitiveType -> {
                    buf.append(value.toString())
                    buf.append("L")
                }
                type == Double::class.javaPrimitiveType -> {
                    val d = (value as Number).toDouble()
                    if (java.lang.Double.isFinite(d)) {
                        buf.append(value)
                        buf.append("d")
                    } else {
                        buf.append("Double.valueOf(\"")
                        buf.append(value)
                        buf.append("\")")
                    }
                }
                type == Boolean::class.javaPrimitiveType -> {
                    val booleanValue = (value as Number).toByte() != 0.toByte()
                    buf.append(booleanValue)
                }
                value is Number -> {
                    buf.append("(${type.name})")
                    buf.append("${value.toLong()}L")
                }
                else -> throw IllegalArgumentException(
                    String.format("Unhandled type: %1\$s, or value: %2\$s", type, value)
                )
            }
            buf.toString()
        }
    }

    private fun emitPrimitiveTypedefLayout(javaName: String, type: Type, declaration: Declaration) {
        incrAlign()
        emitDocComment(declaration)
        appendLines("""
        public static final %1${'$'}s %2${'$'}s = %3${'$'}s;
        """.trimIndent() + "\n", Utils.layoutCarrierFor(type).simpleName, javaName, layoutString(type))
        decrAlign()
    }

    private fun newHolderClassName(javaName: String): String {
        var holderClassName = javaName
        while (!holderClassNames.add(holderClassName.lowercase())) {
            holderClassName += "$"
        }
        return holderClassName
    }

    private fun finalizeParameterNames(
        parameterNames: List<String>,
        needsAllocator: Boolean,
        isVarArg: Boolean
    ): List<String> {
        val result = mutableListOf<String>()

        var i = 0
        while (i < parameterNames.size) {
            var name = parameterNames[i]
            if (name.isEmpty()) {
                name = "x$i"
            }
            result.add(name)
            i++
        }

        if (isVarArg) {
            result.add("x$i")
        }

        if (needsAllocator) {
            var allocatorName = "allocator"
            while (result.contains(allocatorName)) {
                allocatorName = "_$allocatorName"
            }
            result.add(0, allocatorName)
        }

        return result
    }

    private fun paramExprs(type: MethodType, parameterNames: List<String>, isVarArg: Boolean): String {
        assert(parameterNames.size >= type.parameterCount())
        val sb = java.util.StringJoiner(", ")
        var i = 0
        while (i < type.parameterCount()) {
            val pName = parameterNames[i]
            sb.add("${type.parameterType(i).simpleName} $pName")
            i++
        }

        if (isVarArg) {
            sb.add("Object... ${parameterNames[i]}")
        }

        return sb.toString()
    }
}
