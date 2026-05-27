/*
 * Copyright (c) 2022 Oracle and/or its affiliates. All rights reserved.
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
import org.openjdk.kextract.Declaration.Typedef
import org.openjdk.kextract.Declaration.Variable
import org.openjdk.kextract.Type
import org.openjdk.kextract.Type.Declared
import org.openjdk.kextract.Type.Delegated
import org.openjdk.kextract.Type.Function
import org.openjdk.kextract.DeclarationImpl.AnonymousStruct
import org.openjdk.kextract.DeclarationImpl.JavaFunctionalInterfaceName
import org.openjdk.kextract.DeclarationImpl.JavaName
import java.lang.reflect.Modifier
import java.util.concurrent.ConcurrentHashMap
import javax.lang.model.SourceVersion

class NameMangler(private val headerName: String) : Declaration.Visitor<Unit> {

    private val functionTypeDefNames = mutableMapOf<Type, String>()

    private class Scope private constructor(
        val parent: Scope?,
        name: String,
        val isStruct: Boolean
    ) {
        val className: String = NameMangler.javaSafeIdentifier(name)
        private val nestedClassNames = mutableSetOf<String>()
        private var nestedClassNameCount = 0

        private fun isEnclosedBySameName(name: String): Boolean =
            className == name || (isNested() && parent!!.isEnclosedBySameName(name))

        fun isNested(): Boolean = parent != null && parent.isStruct

        fun uniqueNestedClassName(name: String): String {
            val safe = NameMangler.javaSafeIdentifier(name)
            val notSeen = nestedClassNames.add(safe.lowercase())
            val notEnclosed = !isEnclosedBySameName(safe)
            return if (notSeen && notEnclosed) safe else "$safe\$${nestedClassNameCount++}"
        }

        fun fullName(): List<String> {
            val names = mutableListOf<String>()
            var current: Scope? = this
            while (current != null && current.isStruct) {
                names.add(0, current.className)
                current = current.parent
            }
            return names
        }

        companion object {
            fun newStruct(parent: Scope?, name: String) = Scope(parent, name, true)
            fun newHeader(name: String) = Scope(null, name, false)
        }
    }

    private lateinit var curScope: Scope
    private var currentParent: Declaration? = null

    fun scan(header: Declaration.Scoped): Declaration.Scoped {
        val javaName = javaSafeIdentifier(headerName.replace(".h", "_h"), true)
        curScope = Scope.newHeader(javaName)
        JavaName.with(header, listOf(javaName))
        header.members().forEach { it.accept(this) }
        return header
    }

    override fun visitConstant(constant: Declaration.Constant) {
        JavaName.with(constant, makeJavaName(constant))
    }

    override fun visitFunction(func: Declaration.Function) {
        JavaName.with(func, makeJavaName(func))
        var i = 0
        for (param in func.parameters()) {
            val f = param.type().asFunctionPointer()
            if (f != null) {
                val fiName = func.name() + "\$" + (if (param.name().isEmpty()) "x$i" else param.name())
                JavaFunctionalInterfaceName.with(param, fiName)
                i++
            }
            JavaName.with(param, makeJavaName(param))
            val saved = currentParent
            currentParent = func
            param.forEachNested { it.accept(this) }
            currentParent = saved
        }
        val returnFunc = func.type().returnType().asFunctionPointer()
        if (returnFunc != null) {
            JavaFunctionalInterfaceName.with(func, func.name() + "\$return")
        }
        val saved = currentParent
        currentParent = func
        func.forEachNested { it.accept(this) }
        currentParent = saved
    }

    override fun visitScoped(scoped: Declaration.Scoped) {
        val parent = currentParent   // capture the parent before we change it
        if (scoped.isEnum()) {
            val saved = currentParent
            currentParent = null
            scoped.members().forEach { it.accept(this) }
            currentParent = saved
        } else if (scoped.isStructOrUnion()) {
            if (JavaName.isPresent(scoped)) return

            val oldScope = curScope
            if (!AnonymousStruct.isPresent(scoped)) {
                val name = if (parent is Typedef && parent.type() is Declared &&
                    (parent.type() as Declared).tree().name().isEmpty()) {
                    JavaName.getOrThrow(parent)
                } else {
                    oldScope.uniqueNestedClassName(
                        if (scoped.name().isEmpty()) fallbackNameFor(parent, scoped)
                        else scoped.name()
                    )
                }
                curScope = Scope.newStruct(oldScope, name)
                JavaName.with(scoped, curScope.fullName())
            }
            val saved = currentParent
            currentParent = null
            try {
                scoped.members().forEach { it.accept(this) }
            } finally {
                curScope = oldScope
                currentParent = saved
            }
        }
    }

    override fun visitTypedef(typedef: Declaration.Typedef) {
        if (JavaName.isPresent(typedef)) return

        val javaName = curScope.uniqueNestedClassName(typedef.name())
        JavaName.with(typedef, listOf(javaName))
        val func = typedef.type().asFunctionPointer()
        if (func != null) {
            JavaFunctionalInterfaceName.with(typedef, javaName)
            functionTypeDefNames[typedef.type()] = javaName
        }
        val saved = currentParent
        currentParent = typedef
        typedef.forEachNested { it.accept(this) }
        currentParent = saved
    }

    private fun fallbackNameFor(parent: Declaration?, nested: Declaration.Scoped): String {
        var nestedName = parent?.name() ?: ""
        val func: Function? = when (parent) {
            is Declaration.Function -> parent.type()
            is Variable -> parent.type().asFunctionPointer()
            is Typedef -> parent.type().asFunctionPointer()
            else -> null
        }
        if (func != null) {
            var suffix: String? = null
            for (i in func.argumentTypes().indices) {
                val arg = func.argumentTypes()[i]
                if (arg is Declared && arg.tree() === nested) {
                    suffix = "\$x$i"
                }
            }
            if (suffix == null) suffix = "\$return"
            nestedName += suffix
        }
        return nestedName
    }

    override fun visitVariable(variable: Declaration.Variable) {
        JavaName.with(variable, makeJavaName(variable))
        val type = variable.type()
        val func = type.asFunctionPointer()
        if (func != null) {
            val declFiName = curScope.uniqueNestedClassName(variable.name())
            JavaFunctionalInterfaceName.with(variable, declFiName)
        } else if (type is Delegated) {
            val typedefName = functionTypeDefNames[type.type()]
            if (typedefName != null) {
                JavaFunctionalInterfaceName.with(variable, typedefName)
            }
        }
        val saved = currentParent
        currentParent = variable
        variable.forEachNested { it.accept(this) }
        currentParent = saved
    }

    override fun visitObjCClass(d: Declaration.ObjCClass) {
        JavaName.with(d, listOf(javaSafeIdentifier(d.name())))
    }

    override fun visitObjCProtocol(d: Declaration.ObjCProtocol) {
        JavaName.with(d, listOf(javaSafeIdentifier(d.name())))
    }

    override fun visitObjCCategory(d: Declaration.ObjCCategory) {
        // JavaName for a category is the extended class name (used to name the extension file)
        JavaName.with(d, listOf(javaSafeIdentifier(d.extendedClass())))
    }

    private fun makeJavaName(decl: Declaration): List<String> =
        if (decl.name().isEmpty()) listOf(decl.name())
        else listOf(javaSafeIdentifier(decl.name()))

    companion object {
        fun javaSafeIdentifier(name: String): String = javaSafeIdentifier(name, false)

        fun javaSafeIdentifiers(names: List<String>): List<String> = names.map { javaSafeIdentifier(it) }

        fun javaSafeIdentifier(name: String, checkAllChars: Boolean): String {
            if (checkAllChars) {
                val chars = name.toCharArray()
                return buildString {
                    append(if (Character.isJavaIdentifierStart(chars[0])) chars[0] else '_')
                    for (i in 1 until chars.size) {
                        append(if (Character.isJavaIdentifierPart(chars[i])) chars[i] else '_')
                    }
                }
            } else {
                check(SourceVersion.isIdentifier(name)) { "Not a valid Java identifier: $name" }
                return if (SourceVersion.isKeyword(name) || isRestrictedTypeName(name) || isJavaTypeName(name))
                    "${name}_" else name
            }
        }

        private fun isRestrictedTypeName(name: String): Boolean = when (name) {
            "var", "yield", "record", "sealed", "permits" -> true
            else -> false
        }

        private val WELL_KNOWN_JAVA_TYPES: MutableSet<String> = ConcurrentHashMap.newKeySet<String>().also {
            it.add("ByteOrder")
            it.add("MethodHandle")
            it.add("VarHandle")
        }

        private fun isJavaTypeName(name: String): Boolean {
            if (name.isEmpty() || name[0].isLowerCase()) return false
            if (WELL_KNOWN_JAVA_TYPES.contains(name)) return true
            return checkTypeInPackage("java.lang", name) || checkTypeInPackage("java.lang.foreign", name)
        }

        private fun checkTypeInPackage(pkgName: String, typeName: String): Boolean {
            return try {
                val mods = Class.forName("$pkgName.$typeName").modifiers
                if (Modifier.isPublic(mods)) {
                    WELL_KNOWN_JAVA_TYPES.add(typeName)
                    true
                } else false
            } catch (e: Exception) {
                false
            }
        }
    }
}
