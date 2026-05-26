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
import org.openjdk.kextract.JavaSourceFile
import org.openjdk.kextract.Type
import org.openjdk.kextract.impl.DeclarationImpl.JavaFunctionalInterfaceName
import org.openjdk.kextract.impl.DeclarationImpl.JavaName
import org.openjdk.kextract.newimpl.Options

import java.lang.constant.ClassDesc

/**
 * A helper class to generate header interface class in source form.
 * After aggregating various constituents of a .java source, build
 * method is called to get overall generated source string.
 */
internal class ToplevelBuilder(
    packageName: String,
    headerClassName: String,
    libs: List<Options.Library>,
    useSystemLoadLibrary: Boolean,
    sharedClassName: String?
) : Builder {

    companion object {
        private val DECLS_PER_HEADER_CLASS = Integer.getInteger("kextract.decls.per.header", 1000)
        const val PREV_SUFFIX = "#{PREV_SUFFIX}"
        private const val SUFFIX = "#{SUFFIX}"
    }

    private val headerDesc: ClassDesc = ClassDesc.of(packageName, headerClassName)
    private val shared: String = sharedClassName ?: (headerDesc.displayName() + "\$shared")

    private var declCount = 0
    private val headerBuilders = mutableListOf<SourceFileBuilder>()
    private val otherBuilders = mutableListOf<SourceFileBuilder>()
    private var lastHeader: HeaderFileBuilder

    init {
        lastHeader = initSharedClass()
        initFirstHeader(libs, useSystemLoadLibrary)
    }

    private fun initSharedClass(): HeaderFileBuilder {
        val sfb = SourceFileBuilder.newSourceFile(packageName(), shared)
        val sharedHeader = initHeader(sfb, shared, null, null)
        otherBuilders.add(sfb)
        sharedHeader.emitBasicPrimitiveTypes()
        sharedHeader.emitRuntimeHelperMethods()
        sharedHeader.classEnd()
        return sharedHeader
    }

    private fun initFirstHeader(libs: List<Options.Library>, useSystemLoadLibrary: Boolean) {
        val base = headerDesc.displayName()
        val sfb = SourceFileBuilder.newSourceFile(packageName(), base)
        headerBuilders.add(sfb)

        lastHeader = initHeader(
            sfb,
            base + SUFFIX,
            shared,
            base
        )
        lastHeader.emitLibaryArena()
        lastHeader.emitFirstHeaderPreamble(libs, useSystemLoadLibrary)
    }

    /**
     * Shared boilerplate: blank line + classBegin + default constructor
     */
    private fun initHeader(
        sfb: SourceFileBuilder,
        classNameWithSuffix: String,
        superClass: String?,
        extendsClass: String?
    ): HeaderFileBuilder {
        val hfb = HeaderFileBuilder(sfb, classNameWithSuffix, superClass, extendsClass ?: classNameWithSuffix)
        hfb.appendBlankLine()
        hfb.classBegin()
        hfb.emitDefaultConstructor()
        return hfb
    }

    /**
     * Creates a new header file chunk
     */
    private fun newHeaderChunk(): HeaderFileBuilder {
        val base = headerDesc.displayName()
        val sfb = SourceFileBuilder.newSourceFile(packageName(), base)
        val hfb = initHeader(
            sfb,
            base + SUFFIX,
            base + PREV_SUFFIX,
            base
        )
        headerBuilders.add(sfb)
        return hfb
    }

    fun toFiles(): List<JavaSourceFile> {
        lastHeader.classEnd()

        val files = mutableListOf<JavaSourceFile>()

        if (headerBuilders.size == 1) {
            files.add(headerBuilders.first().toFile { s -> s.replace(SUFFIX, "") })
        } else {
            // adjust suffixes so that the last header class becomes the main header class,
            // and extends all the other header classes
            val totalHeaders = headerBuilders.size
            for (i in 0 until totalHeaders) {
                val header = headerBuilders[i]
                val isMainHeader = (i == totalHeaders - 1) // last header is the main header
                val currentSuffix = if (isMainHeader)
                    "" // main header class, drop the suffix
                else
                    String.format("_%d", totalHeaders - i - 1)
                val preSuffix = String.format("_%d", totalHeaders - i)
                val className = headerBuilders.first().className()
                val modifier = if (isMainHeader) "public " else ""

                files.add(header.toFile(currentSuffix) { s ->
                    s.replace("public class $className", "${modifier}class $className")
                        .replace(SUFFIX, currentSuffix)
                        .replace(PREV_SUFFIX, preSuffix)
                })
            }
        }
        // add remaining builders
        files.addAll(otherBuilders.stream()
            .map { it.toFile() }.toList())
        return files
    }

    fun mainHeaderClassName(): String = headerDesc.displayName()

    fun packageName(): String = headerDesc.packageName()

    override fun addVar(varTree: Declaration.Variable) {
        nextHeader().addVar(varTree)
    }

    override fun addFunction(funcTree: Declaration.Function) {
        nextHeader().addFunction(funcTree)
    }

    override fun addConstant(constantTree: Declaration.Constant) {
        nextHeader().addConstant(constantTree)
    }

    override fun addTypedef(typedefTree: Declaration.Typedef, superClass: String?, type: Type) {
        val javaName = JavaName.getOrThrow(typedefTree)
        if (type is Type.Primitive) {
            // primitive
            nextHeader().emitPrimitiveTypedef(typedefTree, type, javaName)
        } else if ((type as TypeImpl).isPointer()) {
            // pointer typedef
            nextHeader().emitPointerTypedef(typedefTree, javaName)
        } else {
            val sfb = SourceFileBuilder.newSourceFile(packageName(), javaName)
            TypedefBuilder.generate(sfb, sfb.className(), superClass, mainHeaderClassName(), typedefTree)
            otherBuilders.add(sfb)
        }
    }

    override fun addStruct(structTree: Declaration.Scoped): StructBuilder {
        val sfb = SourceFileBuilder.newSourceFile(packageName(), JavaName.getOrThrow(structTree))
        otherBuilders.add(sfb)
        val structBuilder = StructBuilder(sfb, "public", sfb.className(), null, mainHeaderClassName(), structTree)
        structBuilder.begin()
        return structBuilder
    }

    override fun addFunctionalInterface(parentDecl: Declaration, funcType: Type.Function) {
        val sfb = SourceFileBuilder.newSourceFile(packageName(), JavaFunctionalInterfaceName.getOrThrow(parentDecl))
        otherBuilders.add(sfb)
        FunctionalInterfaceBuilder.generate(sfb, sfb.className(), null, mainHeaderClassName(), parentDecl, funcType, false)
    }

    private fun nextHeader(): HeaderFileBuilder {
        if (declCount == DECLS_PER_HEADER_CLASS) {
            lastHeader.classEnd()
            lastHeader = newHeaderChunk()
            declCount = 1
        } else {
            declCount++
        }
        return lastHeader
    }
}
