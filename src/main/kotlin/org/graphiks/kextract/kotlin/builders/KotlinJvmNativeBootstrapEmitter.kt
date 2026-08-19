package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.kotlin.KotlinJvmNativeBundleIndex
import org.graphiks.kextract.kotlin.KotlinJvmNativePlatformBundle
import org.graphiks.kextract.pipeline.Options
import java.security.MessageDigest

internal class KotlinJvmNativeBootstrapEmitter(
    private val libraries: List<Options.Library>,
    private val bundleIndex: KotlinJvmNativeBundleIndex,
    private val bootstrapName: String,
    private val delegateResolverName: String,
) {
    fun emit(builder: SourceBuilder) {
        val bundledPlatforms = bundleIndex.platforms.filter(::hasDeclaredLibrary)
        builder.appendLine("private object $bootstrapName {")
        builder.indent()
        builder.appendLine("@kotlin.jvm.Volatile private var loaded: kotlin.Boolean = false")
        builder.appendBlankLine()
        builder.appendLine("fun resolve(name: kotlin.String): kotlin.Long {")
        builder.indent()
        builder.appendLine("load()")
        builder.appendLine("return $delegateResolverName(name)")
        builder.unindent()
        builder.appendLine("}")
        builder.appendBlankLine()
        emitLoadController(builder)
        if (bundledPlatforms.isNotEmpty()) {
            builder.appendBlankLine()
            emitBundleSupport(builder, bundledPlatforms)
        }
        builder.unindent()
        builder.appendLine("}")
        builder.appendBlankLine()
    }

    private fun emitLoadController(builder: SourceBuilder) {
        val hasNamedLibraries = libraries.any { it.specKind == Options.Library.SpecKind.NAME }
        val hasBundledLibraries = bundleIndex.platforms.any(::hasDeclaredLibrary)

        builder.appendLine("private fun load() {")
        builder.indent()
        builder.appendLine("if (loaded) return")
        builder.appendLine("kotlin.synchronized(this) {")
        builder.indent()
        builder.appendLine("if (loaded) return")
        if (hasNamedLibraries && hasBundledLibraries) {
            builder.appendLine("val platform = currentPlatform()")
            builder.appendLine("val bundle = bundles[platform]")
            builder.appendLine("val bundleDirectory = bundle?.let { extractBundle(platform, it) }")
        }
        libraries.forEachIndexed { index, library ->
            when (library.specKind) {
                Options.Library.SpecKind.PATH -> builder.appendLine(
                    "java.lang.System.load(java.nio.file.Path.of(${quote(library.libSpec)}).toAbsolutePath().normalize().toString())",
                )
                Options.Library.SpecKind.NAME -> {
                    if (hasBundledLibraries) {
                        builder.appendLine("val libraryPath$index = bundle?.libraryPaths?.get(${quote(library.libSpec)})")
                        builder.appendLine("if (bundleDirectory != null && libraryPath$index != null) {")
                        builder.indent()
                        builder.appendLine(
                            "java.lang.System.load(bundleDirectory.resolve(libraryPath$index).toAbsolutePath().normalize().toString())",
                        )
                        builder.unindent()
                        builder.appendLine("} else {")
                        builder.indent()
                        builder.appendLine("java.lang.System.loadLibrary(${quote(library.libSpec)})")
                        builder.unindent()
                        builder.appendLine("}")
                    } else {
                        builder.appendLine("java.lang.System.loadLibrary(${quote(library.libSpec)})")
                    }
                }
            }
        }
        builder.appendLine("loaded = true")
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
    }

    private fun emitBundleSupport(
        builder: SourceBuilder,
        platforms: List<KotlinJvmNativePlatformBundle>,
    ) {
        builder.appendLine(
            "private data class Resource(val path: kotlin.String, val sha256: kotlin.String)",
        )
        builder.appendLine(
            "private data class Bundle(" +
                "val key: kotlin.String, " +
                "val resources: kotlin.collections.List<Resource>, " +
                "val libraryPaths: kotlin.collections.Map<kotlin.String, kotlin.String>" +
                ")",
        )
        builder.appendBlankLine()
        builder.appendLine(
            "private val bundles: kotlin.collections.Map<kotlin.String, Bundle> = kotlin.collections.mapOf(",
        )
        builder.indent()
        platforms.forEach { platform ->
            builder.appendLine("${quote(platform.id)} to Bundle(")
            builder.indent()
            builder.appendLine("key = ${quote(contentKey(platform))},")
            builder.appendLine("resources = kotlin.collections.listOf(")
            builder.indent()
            platform.resources.forEach { resource ->
                builder.appendLine(
                    "Resource(${quote(resource.relativePath)}, ${quote(resource.sha256)}),",
                )
            }
            builder.unindent()
            builder.appendLine("),")
            builder.appendLine("libraryPaths = kotlin.collections.mapOf(")
            builder.indent()
            libraries.filter { it.specKind == Options.Library.SpecKind.NAME }.forEach { library ->
                bundleIndex.resourcePath(platform.id, library)?.let { relativePath ->
                    builder.appendLine("${quote(library.libSpec)} to ${quote(relativePath)},")
                }
            }
            builder.unindent()
            builder.appendLine("),")
            builder.unindent()
            builder.appendLine("),")
        }
        builder.unindent()
        builder.appendLine(")")
        builder.appendBlankLine()
        emitCurrentPlatform(builder)
        builder.appendBlankLine()
        emitExtractBundle(builder)
        builder.appendBlankLine()
        emitCopyResource(builder)
        builder.appendBlankLine()
        emitSha256(builder)
    }

    private fun emitCurrentPlatform(builder: SourceBuilder) {
        builder.appendLine("private fun currentPlatform(): kotlin.String {")
        builder.indent()
        builder.appendLine(
            "val os = java.lang.System.getProperty(\"os.name\").lowercase(java.util.Locale.ROOT)",
        )
        builder.appendLine(
            "val architecture = java.lang.System.getProperty(\"os.arch\").lowercase(java.util.Locale.ROOT)",
        )
        builder.appendLine("val osId = when {")
        builder.indent()
        builder.appendLine("os.contains(\"mac\") || os.contains(\"darwin\") -> \"darwin\"")
        builder.appendLine("os.contains(\"linux\") -> \"linux\"")
        builder.appendLine("os.contains(\"windows\") -> \"win32\"")
        builder.appendLine("else -> os.replace(kotlin.text.Regex(\"[^a-z0-9]+\"), \"-\").trim('-')")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine("val architectureId = when (architecture) {")
        builder.indent()
        builder.appendLine("\"aarch64\", \"arm64\" -> \"aarch64\"")
        builder.appendLine("\"amd64\", \"x86_64\", \"x64\" -> \"x86-64\"")
        builder.appendLine("else -> architecture.replace(kotlin.text.Regex(\"[^a-z0-9]+\"), \"-\").trim('-')")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine("return \"\$osId-\$architectureId\"")
        builder.unindent()
        builder.appendLine("}")
    }

    private fun emitExtractBundle(builder: SourceBuilder) {
        builder.appendLine(
            "private fun extractBundle(platform: kotlin.String, bundle: Bundle): java.nio.file.Path {",
        )
        builder.indent()
        builder.appendLine(
            "val configuredCache = java.lang.System.getProperty(\"kextract.native.cache.dir\")",
        )
        builder.appendLine("val cacheRoot = if (configuredCache.isNullOrBlank()) {")
        builder.indent()
        builder.appendLine(
            "java.nio.file.Path.of(java.lang.System.getProperty(\"java.io.tmpdir\"), \"kextract-native\")",
        )
        builder.unindent()
        builder.appendLine("} else {")
        builder.indent()
        builder.appendLine("java.nio.file.Path.of(configuredCache)")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine("java.nio.file.Files.createDirectories(cacheRoot)")
        builder.appendLine("val bundleDirectory = cacheRoot.resolve(bundle.key).toAbsolutePath().normalize()")
        builder.appendLine("val lockPath = cacheRoot.resolve(\"\${bundle.key}.lock\")")
        builder.appendLine("val processLock = lockPath.toAbsolutePath().normalize().toString().intern()")
        builder.appendLine("kotlin.synchronized(processLock) {")
        builder.indent()
        builder.appendLine(
            "java.nio.channels.FileChannel.open(" +
                "lockPath, java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.WRITE" +
                ").use { channel ->",
        )
        builder.indent()
        builder.appendLine("channel.lock().use {")
        builder.indent()
        builder.appendLine("java.nio.file.Files.createDirectories(bundleDirectory)")
        builder.appendLine("bundle.resources.forEach { resource ->")
        builder.indent()
        builder.appendLine("val destination = bundleDirectory.resolve(resource.path).normalize()")
        builder.appendLine("if (!destination.startsWith(bundleDirectory)) {")
        builder.indent()
        builder.appendLine(
            "throw java.io.IOException(\"Native resource escapes cache directory: \${resource.path}\")",
        )
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine("if (!java.nio.file.Files.isRegularFile(destination) || sha256(destination) != resource.sha256) {")
        builder.indent()
        builder.appendLine("copyResource(platform, resource, destination)")
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine("return bundleDirectory")
        builder.unindent()
        builder.appendLine("}")
    }

    private fun emitCopyResource(builder: SourceBuilder) {
        builder.appendLine(
            "private fun copyResource(platform: kotlin.String, resource: Resource, destination: java.nio.file.Path) {",
        )
        builder.indent()
        builder.appendLine("java.nio.file.Files.createDirectories(destination.parent)")
        builder.appendLine(
            "val temporary = java.nio.file.Files.createTempFile(destination.parent, \".\${destination.fileName}.\", \".tmp\")",
        )
        builder.appendLine("try {")
        builder.indent()
        builder.appendLine("val resourceName = \"\$platform/\${resource.path}\"")
        builder.appendLine("val classLoader = $bootstrapName::class.java.classLoader")
        builder.appendLine("val candidates = if (classLoader == null) {")
        builder.indent()
        builder.appendLine("java.lang.ClassLoader.getSystemResources(resourceName)")
        builder.unindent()
        builder.appendLine("} else {")
        builder.indent()
        builder.appendLine("classLoader.getResources(resourceName)")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine("var candidateCount = 0")
        builder.appendLine("var matched = false")
        builder.appendLine("while (candidates.hasMoreElements()) {")
        builder.indent()
        builder.appendLine("candidateCount += 1")
        builder.appendLine("candidates.nextElement().openStream().use { input ->")
        builder.indent()
        builder.appendLine(
            "java.nio.file.Files.copy(input, temporary, java.nio.file.StandardCopyOption.REPLACE_EXISTING)",
        )
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine("if (sha256(temporary) == resource.sha256) {")
        builder.indent()
        builder.appendLine("matched = true")
        builder.appendLine("break")
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine("if (!matched) {")
        builder.indent()
        builder.appendLine("if (candidateCount == 0) {")
        builder.indent()
        builder.appendLine(
            "throw java.io.FileNotFoundException(\"Native resource not found: /\$resourceName\")",
        )
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine(
            "throw java.io.IOException(" +
                "\"No native resource candidate matched SHA-256 \${resource.sha256}: /\$resourceName\"" +
                ")",
        )
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine("try {")
        builder.indent()
        builder.appendLine(
            "java.nio.file.Files.move(" +
                "temporary, destination, " +
                "java.nio.file.StandardCopyOption.ATOMIC_MOVE, " +
                "java.nio.file.StandardCopyOption.REPLACE_EXISTING" +
                ")",
        )
        builder.unindent()
        builder.appendLine("} catch (_: java.nio.file.AtomicMoveNotSupportedException) {")
        builder.indent()
        builder.appendLine(
            "java.nio.file.Files.move(" +
                "temporary, destination, java.nio.file.StandardCopyOption.REPLACE_EXISTING" +
                ")",
        )
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("} finally {")
        builder.indent()
        builder.appendLine("java.nio.file.Files.deleteIfExists(temporary)")
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
    }

    private fun emitSha256(builder: SourceBuilder) {
        builder.appendLine("private fun sha256(path: java.nio.file.Path): kotlin.String {")
        builder.indent()
        builder.appendLine("val digest = java.security.MessageDigest.getInstance(\"SHA-256\")")
        builder.appendLine("java.nio.file.Files.newInputStream(path).use { input ->")
        builder.indent()
        builder.appendLine("val buffer = kotlin.ByteArray(8192)")
        builder.appendLine("while (true) {")
        builder.indent()
        builder.appendLine("val read = input.read(buffer)")
        builder.appendLine("if (read < 0) break")
        builder.appendLine("digest.update(buffer, 0, read)")
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine("return java.util.HexFormat.of().formatHex(digest.digest())")
        builder.unindent()
        builder.appendLine("}")
    }

    private fun hasDeclaredLibrary(platform: KotlinJvmNativePlatformBundle): Boolean =
        libraries.any { library -> bundleIndex.resourcePath(platform.id, library) != null }

    private fun contentKey(platform: KotlinJvmNativePlatformBundle): String {
        val digest = MessageDigest.getInstance("SHA-256")
        digest.update(platform.id.encodeToByteArray())
        digest.update(0.toByte())
        platform.resources.forEach { resource ->
            digest.update(resource.relativePath.encodeToByteArray())
            digest.update(0.toByte())
            digest.update(resource.sha256.encodeToByteArray())
            digest.update(0.toByte())
        }
        return digest.digest().joinToString("") { byte -> "%02x".format(byte) }
    }

    private fun quote(value: String): String = buildString {
        append('"')
        value.forEach { character ->
            when (character) {
                '\\' -> append("\\\\")
                '"' -> append("\\\"")
                '\n' -> append("\\n")
                '\r' -> append("\\r")
                '\t' -> append("\\t")
                '$' -> append("\\$")
                else -> append(character)
            }
        }
        append('"')
    }
}
