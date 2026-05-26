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

import java.lang.invoke.MethodType
import java.util.Optional
import java.util.stream.Collectors
import java.util.stream.IntStream

internal class FunctionalInterfaceBuilder private constructor(
    builder: SourceFileBuilder,
    className: String,
    enclosing: ClassSourceBuilder?,
    runtimeHelperName: String,
    private val funcType: Type.Function,
    isNested: Boolean
) : ClassSourceBuilder(
    builder,
    if (isNested) "public final static" else "public final",
    Kind.CLASS,
    className,
    null,
    enclosing,
    runtimeHelperName
) {

    private val parameterNames: Optional<List<String>> =
        funcType.parameterNames().map { NameMangler.javaSafeIdentifiers(it) }
    private val methodType: MethodType = Utils.methodTypeFor(funcType)

    companion object {
        @JvmStatic
        fun generate(
            builder: SourceFileBuilder,
            className: String,
            enclosing: ClassSourceBuilder?,
            runtimeHelperName: String,
            parentDecl: Declaration,
            funcType: Type.Function,
            isNested: Boolean
        ) {
            val fib = FunctionalInterfaceBuilder(builder, className, enclosing, runtimeHelperName, funcType, isNested)
            fib.appendBlankLine()
            fib.emitDocComment(parentDecl)
            fib.classBegin()
            fib.emitPrivateConstructor()
            val fiName = fib.emitFunctionalInterface()
            fib.emitDescriptorDecl()
            fib.emitFunctionalFactory(fiName)
            fib.emitInvoke()
            fib.classEnd()
        }
    }

    private fun emitFunctionalInterface(): String {
        // beware of mangling!
        val fiName = if (className().lowercase() == "function") "Function\$" else "Function"
        appendIndentedLines("""

            /**
             * The function pointer signature, expressed as a functional interface
             */
            public interface %1${"$"}s {
                %2${"$"}s apply(%3${"$"}s);
            }
            """.trimIndent() + "\n",
            fiName, methodType.returnType().simpleName, paramExprs()
        )
        return fiName
    }

    private fun emitFunctionalFactory(fiName: String) {
        appendIndentedLines("""

            private static final MethodHandle UP${"$"}MH = %1${"$"}s.upcallHandle(%2${"$"}s.%3${"$"}s.class, "apply", ${"$"}DESC);

            /**
             * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
             * The lifetime of the returned segment is managed by {@code arena}
             */
            public static MemorySegment allocate(%2${"$"}s.%3${"$"}s fi, Arena arena) {
                return Linker.nativeLinker().upcallStub(UP${"$"}MH.bindTo(fi), ${"$"}DESC, arena);
            }
            """.trimIndent() + "\n", runtimeHelperName(), className(), fiName
        )
    }

    private fun emitInvoke() {
        val needsAllocator = Utils.isStructOrUnion(funcType.returnType())
        val allocParam = if (needsAllocator) ", SegmentAllocator alloc" else ""
        val allocArg = if (needsAllocator) ", alloc" else ""
        val paramStr = if (methodType.parameterCount() != 0) ", ${paramExprs()}" else ""
        appendIndentedLines("""

            private static final MethodHandle DOWN${"$"}MH = Linker.nativeLinker().downcallHandle(${"$"}DESC);

            /**
             * Invoke the upcall stub {@code funcPtr}, with given parameters
             */
            public static %1${"$"}s invoke(MemorySegment funcPtr%2${"$"}s%3${"$"}s) {
                try {
                    %4${"$"}s DOWN${"$"}MH.invokeExact(funcPtr%5${"$"}s%6${"$"}s);
                } catch (Error | RuntimeException ex) {
                    throw ex;
                } catch (Throwable ex${"$"}) {
                    throw new AssertionError("should not reach here", ex${"$"});
                }
            }
            """.trimIndent() + "\n",
            methodType.returnType().simpleName,
            allocParam,
            paramStr,
            retExpr(),
            allocArg,
            otherArgExprs()
        )
    }

    // private generation
    private fun parameterName(i: Int): String {
        var name = ""
        if (parameterNames.isPresent) {
            name = parameterNames.get()[i]
        }
        return if (name.isEmpty()) "_x$i" else name
    }

    private fun paramExprs(): String {
        val result = StringBuilder()
        var delim = ""
        for (i in 0 until methodType.parameterCount()) {
            result.append(delim).append(methodType.parameterType(i).simpleName)
            result.append(" ")
            result.append(parameterName(i))
            delim = ", "
        }
        return result.toString()
    }

    private fun retExpr(): String {
        return if (methodType.returnType() != Void.TYPE) {
            "return (${methodType.returnType().simpleName})"
        } else {
            ""
        }
    }

    private fun otherArgExprs(): String {
        return if (methodType.parameterCount() > 0) {
            ", " + IntStream.range(0, methodType.parameterCount())
                .mapToObj { this.parameterName(it) }
                .collect(Collectors.joining(", "))
        } else {
            ""
        }
    }

    private fun emitDescriptorDecl() {
        appendIndentedLines("""

            private static final FunctionDescriptor ${"$"}DESC = %1${"$"}s;

            /**
             * The descriptor of this function pointer
             */
            public static FunctionDescriptor descriptor() {
                return ${"$"}DESC;
            }
            """.trimIndent() + "\n", functionDescriptorString(0, funcType)
        )
    }
}
