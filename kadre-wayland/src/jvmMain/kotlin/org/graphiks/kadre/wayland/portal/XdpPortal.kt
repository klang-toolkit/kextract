package org.graphiks.kadre.wayland.portal

import org.graphiks.kadre.core.capture.CapturePermission
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * xdg-desktop-portal helper for screen capture.
 * Uses dbus-send to communicate with the portal service.
 * 
 * Portal interface: org.freedesktop.portal.ScreenCast
 * Documentation: https://flatpak.github.io/xdg-desktop-portal/docs/doc-org.freedesktop.portal.ScreenCast.html
 */
internal object XdpPortal {
    
    private const val PORTAL_BUS_NAME = "org.freedesktop.portal.Desktop"
    private const val PORTAL_OBJECT_PATH = "/org/freedesktop/portal/desktop"
    private const val SCREENCAST_INTERFACE = "org.freedesktop.portal.ScreenCast"
    
    // Session state tracking
    private val activeSessions = mutableMapOf<String, XdpSessionState>()
    
    /**
     * Check if xdg-desktop-portal is available on the system.
     */
    fun isAvailable(): Boolean {
        return try {
            val process = ProcessBuilder(
                "dbus-send", 
                "--print-reply",
                "--dest=$PORTAL_BUS_NAME",
                PORTAL_OBJECT_PATH,
                "org.freedesktop.DBus.Properties.Get",
                "string:$SCREENCAST_INTERFACE",
                "string:version"
            )
            .redirectErrorStream(true)
            .start()
            
            val exitCode = process.waitFor()
            exitCode == 0
        } catch (_: Exception) {
            false
        }
    }
    
    /**
     * Check current screen capture permission status via portal.
     */
    fun checkPermissionStatus(): CapturePermission {
        if (!isAvailable()) return CapturePermission.Granted
        
        // If we have active sessions, check their state
        activeSessions.values.forEach { session ->
            if (session.started && !session.closed) {
                return CapturePermission.Granted
            }
        }
        
        return CapturePermission.Pending
    }
    
    /**
     * Create a new screencast session.
     * Returns the session handle (object path) or null on failure.
     */
    fun createSession(appId: String = "kadre"): String? {
        return try {
            val process = ProcessBuilder(
                "dbus-send",
                "--print-reply",
                "--dest=$PORTAL_BUS_NAME",
                PORTAL_OBJECT_PATH,
                "$SCREENCAST_INTERFACE.CreateSession",
                "string:$appId"
            )
            .redirectErrorStream(true)
            .start()
            
            val exitCode = process.waitFor()
            if (exitCode != 0) return null
            
            val output = process.inputStream.bufferedReader().use(BufferedReader::readText)
            val sessionPath = parseSessionHandle(output)
            
            if (sessionPath != null) {
                activeSessions[sessionPath] = XdpSessionState(created = true)
            }
            
            sessionPath
        } catch (_: Exception) {
            null
        }
    }
    
    /**
     * Select sources for the screencast session.
     * This triggers a user prompt to select which display/window to capture.
     * 
     * @param sessionHandle The session object path from createSession
     * @param types Types of sources to select: "monitor", "window", or both
     * @param multiple Allow selecting multiple sources
     * @param cursorMode How to handle cursor: "none", "embedded", "metadata"
     * @return true if successful, false otherwise
     */
    fun selectSources(
        sessionHandle: String,
        types: List<String> = listOf("monitor", "window"),
        multiple: Boolean = false,
        cursorMode: String = "embedded"
    ): Boolean {
        val typesStr = types.joinToString(",")
        
        return try {
            val process = ProcessBuilder(
                "dbus-send",
                "--print-reply",
                "--dest=$PORTAL_BUS_NAME",
                sessionHandle,
                "$SCREENCAST_INTERFACE.SelectSources",
                "string:$typesStr",
                buildOptions(mapOf(
                    "handle_token" to "s:kadre_${System.currentTimeMillis()}",
                    "multiple" to "b:$multiple",
                    "cursor_mode" to "s:$cursorMode"
                ))
            )
            .redirectErrorStream(true)
            .start()
            
            val exitCode = process.waitFor()
            if (exitCode == 0) {
                activeSessions[sessionHandle] = activeSessions[sessionHandle]?.copy(sourcesSelected = true)
                    ?: XdpSessionState(sourcesSelected = true)
            }
            exitCode == 0
        } catch (_: Exception) {
            false
        }
    }
    
    /**
     * Start the screencast session.
     * 
     * @param sessionHandle The session object path
     * @param parentWindow Optional parent window handle (for positioning the prompt)
     * @return StartResult containing stream node ID and other metadata, or null on failure
     */
    fun startSession(
        sessionHandle: String,
        parentWindow: String? = null
    ): StartResult? {
        val parentArg = parentWindow?.let { "string:$it" } ?: "string:"
        
        return try {
            val process = ProcessBuilder(
                "dbus-send",
                "--print-reply",
                "--dest=$PORTAL_BUS_NAME",
                sessionHandle,
                "$SCREENCAST_INTERFACE.Start",
                parentArg,
                "a{sv}:"
            )
            .redirectErrorStream(true)
            .start()
            
            val exitCode = process.waitFor()
            if (exitCode != 0) return null
            
            val output = process.inputStream.bufferedReader().use(BufferedReader::readText)
            val result = parseStartResponse(output)
            
            if (result != null) {
                activeSessions[sessionHandle] = activeSessions[sessionHandle]?.copy(
                    started = true,
                    streamNodeId = result.streamNodeId
                ) ?: XdpSessionState(started = true, streamNodeId = result.streamNodeId)
            }
            
            result
        } catch (_: Exception) {
            null
        }
    }
    
    /**
     * Close a screencast session.
     */
    fun closeSession(sessionHandle: String): Boolean {
        return try {
            val process = ProcessBuilder(
                "dbus-send",
                "--dest=$PORTAL_BUS_NAME",
                sessionHandle,
                "$SCREENCAST_INTERFACE.Close"
            )
            .redirectErrorStream(true)
            .start()
            
            val exitCode = process.waitFor()
            if (exitCode == 0) {
                activeSessions[sessionHandle] = activeSessions[sessionHandle]?.copy(closed = true)
                    ?: XdpSessionState(closed = true)
            }
            exitCode == 0
        } catch (_: Exception) {
            false
        }
    }
    
    /**
     * Get the stream node ID for a session.
     */
    fun getStreamNodeId(sessionHandle: String): Int? {
        return activeSessions[sessionHandle]?.streamNodeId
    }
    
    /**
     * Result of the Start method containing stream information.
     */
    data class StartResult(
        val streamNodeId: Int,
        val types: Int,  // 1 = monitor, 2 = window
        val cursorMode: Int  // 0 = none, 1 = hidden, 2 = embedded, 3 = metadata
    )
    
    /**
     * Internal session state tracking.
     */
    private data class XdpSessionState(
        val created: Boolean = false,
        val sourcesSelected: Boolean = false,
        val started: Boolean = false,
        val closed: Boolean = false,
        val streamNodeId: Int? = null
    )
    
    /**
     * Parse session handle from dbus-send output.
     * Expected format: object path "/org/freedesktop/portal/desktop/session/..."
     */
    private fun parseSessionHandle(output: String): String? {
        // Look for: object path "/org/freedesktop/portal/desktop/session/abc123"
        val pattern = "object path \"(/[^\"]+)\""
        return pattern.toRegex().find(output)?.groupValues?.get(1)
    }
    
    /**
     * Parse Start response which returns an array of dict entries.
     * Expected format:
     * array [
     *   dict entry(string "stream_node_id", variant uint32 42)
     *   dict entry(string "types", variant uint32 1)
     *   dict entry(string "cursor_mode", variant uint32 2)
     * ]
     */
    private fun parseStartResponse(output: String): StartResult? {
        var streamNodeId: Int? = null
        var types: Int? = null
        var cursorMode: Int? = null
        
        // Parse all uint32 values from the output
        val uint32Pattern = "variant uint32 (\\d+)".toRegex()
        val allUInt32Values = uint32Pattern.findAll(output)
            .map { it.groupValues[1].toInt() }
            .toList()
        
        // The values should appear in order: stream_node_id, types, cursor_mode
        if (allUInt32Values.size >= 3) {
            streamNodeId = allUInt32Values[0]
            types = allUInt32Values[1]
            cursorMode = allUInt32Values[2]
        }
        
        if (streamNodeId != null && types != null && cursorMode != null) {
            return StartResult(streamNodeId, types, cursorMode)
        }
        
        return null
    }
    
    /**
     * Build D-Bus variant dict string from key-value pairs.
     * Format: a{sv}:key1 variant_type1 value1,key2 variant_type2 value2,...
     */
    private fun buildOptions(options: Map<String, String>): String {
        if (options.isEmpty()) return "a{sv}:"
        
        return "a{sv}:" + options.entries.joinToString(",") { (key, value) ->
            when (value) {
                "true" -> "$key b:true"
                "false" -> "$key b:false"
                else -> "$key s:$value"
            }
        }
    }
}
