package org.graphiks.kadre.ffi.wayland

import java.io.File

object WaylandXdgLib {

    val loaded: Boolean by lazy { tryLoad() }

    private fun tryLoad(): Boolean = runCatching {
        val arch = when (val a = System.getProperty("os.arch")?.lowercase()) {
            "amd64", "x86_64" -> "x86_64"
            "aarch64", "arm64" -> "aarch64"
            else -> a ?: return@runCatching false
        }
        val resource = "/native/linux-$arch/libkadre-xdg.so"
        val stream = WaylandXdgLib::class.java.getResourceAsStream(resource)
        if (stream == null) {
            System.err.println("[kadre-wayland] libkadre-xdg.so not found on classpath: $resource")
            return@runCatching false
        }

        val tmp = File.createTempFile("libkadre-xdg", ".so").apply { deleteOnExit() }
        stream.use { input -> tmp.outputStream().use { output -> input.copyTo(output) } }

        System.load(tmp.absolutePath)
        true
    }.getOrElse { e ->
        System.err.println("[kadre-wayland] failed to load libkadre-xdg.so: $e")
        false
    }
}
