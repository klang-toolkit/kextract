package org.graphiks.kextract.pipeline

import java.io.Reader
import java.io.IOException
import java.nio.file.Path
import kotlin.io.path.bufferedReader

/**
 * Utility object for processing command line arguments.
 * Supports @file expansion and environment variable processing.
 */
object CommandLine {
    private const val FORM_FEED = '\u000C'
    private const val NUL = '\u0000'
    /**
     * Process Win32-style command files for the specified command line arguments.
     * @param args The command line arguments to process
     * @return The expanded arguments list
     * @throws IOException If an I/O error occurs
     */
    @JvmStatic
    @Throws(IOException::class)
    fun parse(args: List<String>): List<String> {
        val newArgs = mutableListOf<String>()
        appendParsedCommandArgs(newArgs, args)
        return newArgs
    }

    /**
     * Process command line arguments with environment variable expansion.
     * @param envVariable The environment variable name
     * @param args The command line arguments to process
     * @return The expanded arguments list
     * @throws IOException If an I/O error occurs
     * @throws UnmatchedQuoteException If there are unmatched quotes
     */
    @JvmStatic
    @Throws(IOException::class, UnmatchedQuoteException::class)
    fun parse(envVariable: String?, args: List<String>): List<String> {
        val inArgs = mutableListOf<String>()
        appendParsedEnvVariables(inArgs, envVariable)
        inArgs.addAll(args)
        val newArgs = mutableListOf<String>()
        appendParsedCommandArgs(newArgs, inArgs)
        return newArgs
    }

    /**
     * Append parsed command arguments to the provided list.
     */
    @Throws(IOException::class)
    private fun appendParsedCommandArgs(newArgs: MutableList<String>, args: List<String>) {
        for (arg in args) {
            when {
                arg.length > 1 && arg[0] == '@' -> {
                    val fileArg = arg.substring(1)
                    if (fileArg.isNotEmpty() && fileArg[0] == '@') {
                        newArgs.add(fileArg)
                    } else {
                        loadCmdFile(fileArg, newArgs)
                    }
                }
                else -> newArgs.add(arg)
            }
        }
    }

    /**
     * Load command file and append its arguments.
     */
    @Throws(IOException::class)
    private fun loadCmdFile(name: String, args: MutableList<String>) {
        Path.of(name).bufferedReader().use { reader ->
            val tokenizer = Tokenizer(reader)
            var token: String?
            while (tokenizer.nextToken().also { token = it } != null) {
                args.add(token!!)
            }
        }
    }

    /**
     * Append parsed environment variables to the provided list.
     */
    @Throws(UnmatchedQuoteException::class)
    private fun appendParsedEnvVariables(newArgs: MutableList<String>, envVariable: String?) {
        if (envVariable.isNullOrEmpty()) return

        val inEnv = System.getenv(envVariable) ?: return
        if (inEnv.isEmpty()) return

        var pos = 0
        val len = inEnv.length
        val sb = StringBuilder()
        var quote = NUL
        var ch: Char

        while (pos < len) {
            ch = inEnv[pos]
            when (ch) {
                '"', '\'' -> {
                    quote = if (quote == NUL) ch else if (quote == ch) NUL else {
                        sb.append(ch)
                        quote
                    }
                    pos++
                }
                FORM_FEED, '\n', '\r', '\t', ' ' -> {
                    if (quote == NUL) {
                        newArgs.add(sb.toString())
                        sb.clear()
                        // Skip all whitespace
                        while (pos < len) {
                            ch = inEnv[pos]
                            if (ch != FORM_FEED && ch != '\n' && ch != '\r' && ch != '\t' && ch != ' ') {
                                break
                            }
                            pos++
                        }
                    } else {
                        sb.append(ch)
                        pos++
                    }
                }
                else -> {
                    sb.append(ch)
                    pos++
                }
            }
        }
        if (sb.isNotEmpty()) {
            newArgs.add(sb.toString())
        }
        if (quote != NUL) {
            throw UnmatchedQuoteException(envVariable)
        }
    }

    /**
     * Tokenizer for parsing command line arguments from a reader.
     */
    class Tokenizer(private val reader: Reader) {
        private var ch: Int = reader.read()

        @Throws(IOException::class)
        fun nextToken(): String? {
            skipWhite()
            if (ch == -1) return null

            val sb = StringBuilder()
            var quoteChar: Char? = null

            while (ch != -1) {
                val currentChar = ch.toChar()
                when (currentChar) {
                    ' ', '\t', FORM_FEED -> {
                        if (quoteChar == null) {
                            return sb.toString()
                        }
                        sb.append(currentChar)
                    }
                    '\n', '\r' -> {
                        return sb.toString()
                    }
                    '"', '\'' -> {
                        quoteChar = if (quoteChar == null) currentChar
                                    else if (quoteChar == currentChar) null
                                    else {
                                        sb.append(currentChar)
                                        quoteChar
                                    }
                    }
                    '\\' -> {
                        if (quoteChar != null) {
                            ch = reader.read()
                            if (ch == -1) break
                            val nextChar = ch.toChar()
                            when (nextChar) {
                                '\n', '\r' -> {
                                    // Line continuation
                                    while (ch != -1) {
                                        val c = ch.toChar()
                                        if (c != ' ' && c != '\n' && c != '\r' && c != '\t' && c != FORM_FEED) {
                                            break
                                        }
                                        ch = reader.read()
                                    }
                                    continue
                                }
                                'n' -> ch = '\n'.code
                                'r' -> ch = '\r'.code
                                't' -> ch = '\t'.code
                                'f' -> ch = FORM_FEED.code
                                else -> {
                                    sb.append(nextChar)
                                    ch = reader.read()
                                    continue
                                }
                            }
                            // After handling escape, append the resulting character
                            sb.append(ch.toChar())
                            ch = reader.read()
                            continue
                        }
                        sb.append(currentChar)
                    }
                    else -> sb.append(currentChar)
                }
                ch = reader.read()
            }
            return sb.toString()
        }

        @Throws(IOException::class)
        private fun skipWhite() {
            while (ch != -1) {
                val currentChar = ch.toChar()
                when (currentChar) {
                    ' ', '\t', '\n', '\r', FORM_FEED -> {
                        ch = reader.read()
                    }
                    '#' -> {
                        // Comment: skip to end of line
                        ch = reader.read()
                        while (ch != -1) {
                            val c = ch.toChar()
                            if (c == '\n' || c == '\r') {
                                break
                            }
                            ch = reader.read()
                        }
                    }
                    else -> return
                }
            }
        }
    }

    /**
     * Exception thrown when there are unmatched quotes in environment variable parsing.
     */
    class UnmatchedQuoteException(variableName: String) : Exception("Unmatched quote in variable: $variableName")
}
