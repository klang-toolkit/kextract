/*
 * Copyright (c) 2024 Oracle and/or its affiliates. All rights reserved.
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
 * 2 along with this code; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package org.openjdk.kextract.newimpl

import org.openjdk.kextract.Position
import java.io.PrintWriter
import java.text.MessageFormat
import java.util.Locale
import java.util.ResourceBundle

/**
 * Logger for kextract warnings and errors with internationalization support.
 */
class Logger @JvmOverloads constructor(
    val outWriter: PrintWriter = PrintWriter(System.out, true),
    val errWriter: PrintWriter = PrintWriter(System.err, true),
    private val locale: Locale = Locale.getDefault()
) {
    var nErrors: Int = 0
        private set
    var nClangErrors: Int = 0
        private set

    private val messagesBundle: ResourceBundle by lazy {
        ResourceBundle.getBundle("org.openjdk.kextract.impl.resources.Messages", locale)
    }

    // --- Error / warning / info (key-based, no position) ---

    @JvmOverloads
    fun err(key: String, vararg args: Any, pos: Position? = null) {
        val msg = format(key, *args)
        val fullMsg = formatPos(pos)?.let { "$it: error: $msg" } ?: "error: $msg"
        synchronized(errWriter) { errWriter.println(fullMsg) }
        nErrors++
    }

    @JvmOverloads
    fun warn(key: String, vararg args: Any, pos: Position? = null) {
        val msg = format(key, *args)
        val fullMsg = formatPos(pos)?.let { "$it: warning: $msg" } ?: "warning: $msg"
        synchronized(errWriter) { errWriter.println(fullMsg) }
    }

    @JvmOverloads
    fun info(key: String, vararg args: Any, pos: Position? = null) {
        val msg = format(key, *args)
        val fullMsg = formatPos(pos)?.let { "$it: $msg" } ?: msg
        synchronized(errWriter) { errWriter.println(fullMsg) }
    }

    // --- Clang-diagnostic methods (raw message, no key lookup) ---

    fun clangErr(pos: Position?, msg: String) {
        val fullMsg = formatPos(pos)?.let { "$it: error: $msg" } ?: "error: $msg"
        synchronized(errWriter) { errWriter.println(fullMsg) }
        nClangErrors++
    }

    fun clangWarn(pos: Position?, msg: String) {
        val fullMsg = formatPos(pos)?.let { "$it: warning: $msg" } ?: "warning: $msg"
        synchronized(errWriter) { errWriter.println(fullMsg) }
    }

    fun clangInfo(pos: Position?, msg: String) {
        val fullMsg = formatPos(pos)?.let { "$it: $msg" } ?: msg
        synchronized(errWriter) { errWriter.println(fullMsg) }
    }

    // --- Utilities ---

    fun printStackTrace(t: Throwable) {
        synchronized(errWriter) { t.printStackTrace(errWriter) }
    }

    @JvmOverloads
    fun fatal(t: Throwable, msg: String = "kextract.crash", vararg args: Any) {
        synchronized(errWriter) {
            errWriter.println("fatal: ${format(msg, *args)}")
            if (isDebugEnabled()) printStackTrace(t)
        }
    }

    fun hasErrors(): Boolean = nErrors > 0
    fun hasClangErrors(): Boolean = nClangErrors > 0

    fun format(key: String, vararg args: Any): String {
        return try {
            MessageFormat(messagesBundle.getString(key)).format(args)
        } catch (_: Exception) {
            if (args.isNotEmpty()) "$key ${args.joinToString(" ")}" else key
        }
    }

    private fun formatPos(pos: Position?): String? {
        if (pos == null || pos === Position.NO_POSITION || pos.path == null) return null
        return "${pos.path.fileName}:${pos.line}:${pos.col}"
    }

    private fun isDebugEnabled(): Boolean = System.getProperty("kextract.debug") == "true"

    companion object {
        val DEFAULT: Logger = Logger()
    }
}
