import org.apache.tools.ant.taskdefs.condition.Os
import org.gradle.api.tasks.PathSensitivity
import java.net.URI

plugins {
    java
    id("org.jetbrains.kotlin.jvm")
}

kotlin { jvmToolchain(25) }

// ── LLVM / JDK configuration ─────────────────────────────────────────────────

val llvmVersion = "22.1.6"
val explicitLlvmHome = (project.findProperty("llvm_home") as String?)
    ?.takeIf { it.isNotBlank() && File(it).exists() }
val autoLlvmDir = layout.buildDirectory.dir("llvm/$llvmVersion").get().asFile.absolutePath
val llvm_home: String = explicitLlvmHome ?: autoLlvmDir
val jdk_home = (project.findProperty("jdk_home") as? String)
    ?.takeIf { File(it).exists() }
    ?: System.getProperty("java.home")
    ?: error("jdk_home not set and java.home not available")
if (explicitLlvmHome != null) require(File("$llvm_home/lib/clang").exists()) {
    "llvm_home/lib/clang not found: $llvm_home/lib/clang"
}

val clang_version: String by lazy {
    val dir = File("$llvm_home/lib/clang")
    when {
        !dir.exists() -> throw GradleException(
            "LLVM not found at $llvm_home. Run './gradlew downloadLLVM' first, " +
            "or pass -Pllvm_home=<path> (e.g. \$(brew --prefix llvm))."
        )
        else -> dir.list()?.firstOrNull()
            ?: throw GradleException("No clang version found under $dir")
    }
}

// ── Path constants ────────────────────────────────────────────────────────────

val buildDirectory   = layout.buildDirectory.get()
val kextract_inputs  = "$buildDirectory/jmod_inputs"
val kextract_app_dir = "$buildDirectory/kextract"
val kextract_rt_dir  = "$kextract_app_dir/runtime"
val kextract_bin_dir = "$kextract_app_dir/bin"
val os_lib_dir       = if (Os.isFamily(Os.FAMILY_WINDOWS)) "bin" else "lib"
val os_script_ext    = if (Os.isFamily(Os.FAMILY_WINDOWS)) ".bat" else ""
val os_exe_suffix    = if (Os.isFamily(Os.FAMILY_WINDOWS)) ".exe" else ""
val libclang_dir     = "$llvm_home/$os_lib_dir"

// ── downloadLLVM ──────────────────────────────────────────────────────────────

tasks.register("downloadLLVM") {
    description = "Downloads and extracts the LLVM $llvmVersion binary distribution for the current OS/arch"
    onlyIf { explicitLlvmHome == null }

    val targetDir = File(autoLlvmDir)
    val marker = File(targetDir, ".extracted-$llvmVersion")
    outputs.file(marker)

    doLast {
        val osArch = System.getProperty("os.arch")
        val isArm = osArch == "aarch64" || osArch == "arm64"
        val assetName = when {
            Os.isFamily(Os.FAMILY_WINDOWS) ->
                "clang+llvm-$llvmVersion-x86_64-pc-windows-msvc.tar.xz"
            Os.isFamily(Os.FAMILY_MAC) -> {
                if (!isArm) throw GradleException(
                    "No upstream LLVM $llvmVersion binary for macOS x86_64. " +
                    "Pass -Pllvm_home=<path> (e.g. \$(brew --prefix llvm))."
                )
                "LLVM-$llvmVersion-macOS-ARM64.tar.xz"
            }
            else ->
                if (isArm) "LLVM-$llvmVersion-Linux-ARM64.tar.xz"
                else "LLVM-$llvmVersion-Linux-X64.tar.xz"
        }
        val url = "https://github.com/llvm/llvm-project/releases/download/llvmorg-$llvmVersion/$assetName"

        val archiveCacheDir = File(gradle.gradleUserHomeDir, "kextract-llvm").apply { mkdirs() }
        val archive = File(archiveCacheDir, assetName)

        if (!archive.exists()) {
            logger.lifecycle("Downloading $url (one-time, ~1.5 GB)")
            val tmp = File(archiveCacheDir, "$assetName.part")
            URI(url).toURL().openStream().use { input ->
                tmp.outputStream().use { input.copyTo(it) }
            }
            tmp.renameTo(archive)
        } else {
            logger.lifecycle("Using cached LLVM archive at $archive")
        }

        delete(targetDir)
        targetDir.mkdirs()
        logger.lifecycle("Extracting $assetName to $targetDir")
        val exit = ProcessBuilder(
            "tar", "--strip-components=1", "-xf", archive.absolutePath, "-C", targetDir.absolutePath
        ).inheritIO().start().waitFor()
        if (exit != 0) throw GradleException("tar extraction failed with exit code $exit")

        // Trim: keep libclang + libLLVM (which libclang depends on) + builtin headers
        val keepPatterns = listOf(
            Regex("^$os_lib_dir/libclang\\..*"),
            Regex("^$os_lib_dir/libLLVM\\..*"),
            Regex("^lib/clang/[^/]+/include/.*"),
        )
        val sizeBefore = targetDir.walkBottomUp().filter { it.isFile }.sumOf { it.length() }
        targetDir.walkBottomUp()
            .filter { it.isFile }
            .filter { f -> keepPatterns.none { it.matches(f.toRelativeString(targetDir).replace('\\', '/')) } }
            .forEach { it.delete() }
        var changed = true
        while (changed) {
            changed = false
            targetDir.walkBottomUp()
                .filter { it.isDirectory && it != targetDir && it.list()?.isEmpty() == true }
                .forEach { it.delete(); changed = true }
        }
        val sizeAfter = targetDir.walkBottomUp().filter { it.isFile }.sumOf { it.length() }
        logger.lifecycle("Trimmed LLVM: ${sizeBefore / 1024 / 1024} MB → ${sizeAfter / 1024 / 1024} MB")
        marker.writeText("ok\n")
    }
}

// ── Source sets ───────────────────────────────────────────────────────────────

sourceSets {
    main {
        java { setSrcDirs(listOf("src/main/java")) }
        kotlin { setSrcDirs(emptyList<String>()) }
        resources { setSrcDirs(emptyList<String>()) }
    }
    val kmain by creating {
        kotlin { setSrcDirs(listOf("src/main/kotlin")) }
        resources { setSrcDirs(listOf("src/main/resources")) }
        compileClasspath += sourceSets["main"].output + sourceSets["main"].compileClasspath
        runtimeClasspath += sourceSets["main"].output + sourceSets["main"].runtimeClasspath
    }
    test {
        kotlin {
            setSrcDirs(listOf("src/test/kotlin"))
            exclude("ManualKotlinGen.kt")
        }
        compileClasspath += sourceSets["kmain"].output + sourceSets["kmain"].compileClasspath
        runtimeClasspath += sourceSets["kmain"].output + sourceSets["kmain"].runtimeClasspath
    }
}

// ── Dependencies ──────────────────────────────────────────────────────────────

repositories { mavenCentral() }

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.3.21")
    "kmainImplementation"("org.jetbrains.kotlin:kotlin-stdlib:2.3.21")
    "kmainImplementation"("com.github.ajalt.clikt:clikt:5.0.3")
    "kmainImplementation"("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.18.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.14.4")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.14.4")
    testImplementation("org.junit.platform:junit-platform-launcher")
    testImplementation("org.jetbrains.kotlin:kotlin-test:2.3.21")
    val kotestVersion = "6.1.11"
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("net.java.dev.jna:jna:5.18.1")
    testImplementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:2.3.21")
}

// ── Compiler configuration ────────────────────────────────────────────────────

tasks.withType<Test>().configureEach {
    val completionMarker = layout.buildDirectory.file("test-plan-completion/$name.properties")
    val llvmLibraryDirectory = "$llvm_home/$os_lib_dir"
    val inheritedLibraryPath = System.getenv("LD_LIBRARY_PATH")?.takeIf(String::isNotBlank)
    val librarySearchPaths = mutableListOf<String>()

    dependsOn("downloadLLVM")
    useJUnitPlatform()
    if (Os.isName("linux")) {
        val testJdkHome = javaLauncher.get().metadata.installationPath.asFile
        val libjsig = testJdkHome.resolve("lib/libjsig.so")
        if (!libjsig.isFile) {
            throw GradleException(
                "Cannot configure Linux test signal chaining: expected the test toolchain's " +
                    "libjsig.so at $libjsig",
            )
        }
        librarySearchPaths += libjsig.parentFile.absolutePath
        val inheritedPreload = System.getenv("LD_PRELOAD")?.takeIf(String::isNotBlank)
        // glibc cannot escape spaces in LD_PRELOAD entries, so resolve the basename
        // through LD_LIBRARY_PATH instead of embedding the test JDK's absolute path.
        environment(
            "LD_PRELOAD",
            listOfNotNull(libjsig.name, inheritedPreload).joinToString(File.pathSeparator),
        )
    }
    librarySearchPaths += llvmLibraryDirectory
    inheritedLibraryPath?.let(librarySearchPaths::add)
    environment("LD_LIBRARY_PATH", librarySearchPaths.joinToString(File.pathSeparator))
    if (!Os.isFamily(Os.FAMILY_WINDOWS)) {
        environment("LIBCLANG_DISABLE_CRASH_RECOVERY", "1")
    }
    doFirst {
        completionMarker.get().asFile.delete()
    }
    systemProperty("kextract.testCompletionMarker", completionMarker.get().asFile.absolutePath)
    // --enable-native-access is required for Panama FFI in JDK 22+.
    jvmArgs("--enable-native-access=ALL-UNNAMED")
    systemProperty("java.library.path", llvmLibraryDirectory)
    // Help the OS dynamic linker resolve transitive libclang dependencies
    // (libclang.so → libLLVM.so.22) when dlopen opens libclang. java.library.path
    // only feeds System.loadLibrary(); the inner dlopen() chain uses LD_LIBRARY_PATH
    // / DYLD_LIBRARY_PATH / PATH instead.
    environment("DYLD_LIBRARY_PATH", "$llvm_home/$os_lib_dir")
    environment("PATH",              "$llvm_home/$os_lib_dir${File.pathSeparator}${System.getenv("PATH") ?: ""}")
}

// All Java compilation uses the configured JDK and targets Java 25
tasks.withType<JavaCompile>().configureEach {
    options.release = 25
    options.isFork = true
    options.forkOptions.executable = "$jdk_home/bin/javac$os_exe_suffix"
}

tasks.named<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile>("compileKmainKotlin") {
    compilerOptions { jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_25) }
}

tasks.named<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile>("compileTestKotlin") {
    compilerOptions { jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_25) }
}

// ── JAR ───────────────────────────────────────────────────────────────────────

tasks.named<Jar>("jar") {
    archiveBaseName = "org.graphiks.kextract"
    archiveVersion = project.version.toString()
    from(sourceSets["kmain"].output)
}

// ── Distribution ──────────────────────────────────────────────────────────────

/**
 * Copies libclang, builtin headers, and writes the libclang version file
 * into build/jmod_inputs — the staging area consumed by createKextractImage.
 */
tasks.register("prepareInputs") {
    dependsOn("downloadLLVM")
    outputs.dir(kextract_inputs)

    // The image embeds the selected libclang and its builtin headers.  Both
    // their location and contents are task inputs: otherwise switching
    // `-Pllvm_home` can leave an image with a previous LLVM version while
    // Gradle considers this task up-to-date.
    val sourceLibDir = file(libclang_dir)
    val clangLibPattern = when {
        Os.isName("AIX") || Os.isName("aix") -> "libclang.a"
        Os.isFamily(Os.FAMILY_UNIX) && !Os.isFamily(Os.FAMILY_MAC) -> "libclang.so*"
        Os.isFamily(Os.FAMILY_WINDOWS) -> "libclang.dll"
        else -> "libclang.dylib"
    }
    val sourceBuiltinHeaders = file("$llvm_home/lib/clang/$clang_version/include")
    inputs.property("llvmHome", llvm_home)
    inputs.files(fileTree(sourceLibDir) {
        include(clangLibPattern, "libLLVM.*", "LLVM-C*")
        exclude("clang.exe")
    }).withPathSensitivity(PathSensitivity.RELATIVE)
    inputs.dir(sourceBuiltinHeaders).withPathSensitivity(PathSensitivity.RELATIVE)

    doLast {
        val libsDir = file("$kextract_inputs/libs").apply { deleteRecursively(); mkdirs() }
        val confDir = file("$kextract_inputs/conf/kextract").apply { deleteRecursively(); mkdirs() }

        // Native library — use a glob so we match whichever version filename the LLVM
        // release actually ships (e.g. libclang.so.22 vs libclang.so.22.1.6 vs the
        // unversioned symlink). The rename pattern normalises any versioned filename
        // to the unversioned form so System.loadLibrary("clang") can find it.
        val srcLibDir = sourceLibDir.also {
            if (!it.isDirectory) throw GradleException("libclang directory not found: $it")
        }
        project.copy {
            from(srcLibDir) {
                include(clangLibPattern, "libLLVM.*", "LLVM-C*")
                exclude("clang.exe")
                // Map any versioned libclang.so.X(.Y.Z) → libclang.so
                rename("libclang\\.so\\..+", "libclang.so")
            }
            into(libsDir)
            // On Linux the archive contains both libclang.so (symlink) and a
            // versioned libclang.so.X; both get renamed to libclang.so. The
            // last copy wins — both have the same content so the result is fine.
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
        }
        // Sanity-check that we actually copied libclang. On Linux/macOS the unversioned
        // file must exist; on Windows it's libclang.dll.
        val expectedClang = when {
            Os.isFamily(Os.FAMILY_WINDOWS) -> "libclang.dll"
            Os.isFamily(Os.FAMILY_MAC)     -> "libclang.dylib"
            else                           -> "libclang.so"
        }
        if (!File(libsDir, expectedClang).exists()) {
            throw GradleException(
                "prepareInputs: $expectedClang not produced under $libsDir " +
                "(source dir contents: ${srcLibDir.list()?.toList()})"
            )
        }

        // Clang builtin headers
        val includeDir = sourceBuiltinHeaders.also {
            if (!it.isDirectory) throw GradleException("Clang builtin headers not found: $it")
        }
        project.copy {
            from(includeDir) { include("*.h") }
            into(confDir)
        }

        // libclang major version (consumed by KextractTool.detectBuiltinDir)
        file("$confDir/libclang.version").writeText(clang_version.split(".")[0] + "\n")
    }
}

tasks.register("createKextractImage") {
    val jarTask = tasks.named<Jar>("jar")
    dependsOn(jarTask, "prepareInputs")

    inputs.file(jarTask.flatMap { it.archiveFile })
    inputs.dir(kextract_inputs)
    outputs.dir(kextract_app_dir)

    doLast {
        delete(kextract_app_dir)
        project.mkdir("$kextract_app_dir/lib")
        project.mkdir("$kextract_app_dir/conf/kextract")
        project.mkdir(kextract_bin_dir)

        // 1. jlink: minimal JRE
        val kotlinStdlib = configurations["kmainRuntimeClasspath"].files
            .find { it.name.contains("kotlin-stdlib") }
        val modulePath = buildString {
            append("$jdk_home/jmods")
            if (kotlinStdlib != null) append(File.pathSeparator + kotlinStdlib.parent)
        }
        val jlinkExit = ProcessBuilder(
            "$jdk_home/bin/jlink",
            "--module-path=$modulePath",
            "--add-modules=java.base,java.compiler,kotlin.stdlib",
            "--output=$kextract_rt_dir",
            "--strip-debug", "--no-man-pages", "--no-header-files"
        ).inheritIO().start().waitFor()
        if (jlinkExit != 0) throw GradleException("jlink failed with exit code $jlinkExit")

        // 2. App JAR
        project.copy {
            from(jarTask.get().archiveFile.get().asFile)
            into("$kextract_app_dir/lib")
            rename { "org.graphiks.kextract.jar" }
        }

        // 3. Runtime dependencies
        project.copy {
            from(configurations["kmainRuntimeClasspath"])
            into("$kextract_app_dir/lib")
        }

        // 4. Native library + Clang builtin headers
        project.copy { from("$kextract_inputs/libs");             into("$kextract_app_dir/lib") }
        project.copy { from("$kextract_inputs/conf/kextract");    into("$kextract_app_dir/conf/kextract") }

        // 5. Launcher scripts (executable on Unix)
        project.copy {
            from("$projectDir/src/main") {
                include("kextract", "kextract.bat", "kextract.ps1")
            }
            into(kextract_bin_dir)
            filePermissions { unix("rwxr-xr-x") }
        }
    }
}

tasks.named("assemble") { dependsOn("createKextractImage") }

tasks.register<Exec>("verify") {
    dependsOn("createKextractImage")
    executable = "$kextract_bin_dir/kextract$os_script_ext"
    args = listOf("test.h", "--output", "$buildDirectory/integration_test")
}

/**
 * Builds kextract, then compiles and runs every example under examples/.
 * Requires: cc (or clang) and kotlinc on PATH.
 *
 * Usage:  ./gradlew verifyExamples
 */
tasks.register("verifyExamples") {
    dependsOn("createKextractImage")
    group = "verification"
    description = "Build kextract, generate Kotlin bindings for each example and run the result."

    doLast {
        var allPassed = true
        file("examples")
            .listFiles { f -> f.isDirectory && File(f, "run.sh").exists() }
            ?.sortedBy { it.name }
            ?.forEach { exampleDir ->
                println("\n── ${exampleDir.name} ──────────────────────────────")
                if (File(exampleDir, "PENDING").exists()) {
                    println("  PENDING — skipped (remove PENDING file once implemented)")
                    return@forEach
                }
                val proc = ProcessBuilder("bash", "run.sh", "--skip-build")
                    .directory(exampleDir)
                    .redirectErrorStream(true)
                    .start()
                val output = proc.inputStream.bufferedReader().readText()
                val exitCode = proc.waitFor()
                print(output)
                if (exitCode != 0) {
                    logger.error("✗ ${exampleDir.name} FAILED (exit $exitCode)")
                    allPassed = false
                } else {
                    println("✓ ${exampleDir.name} passed")
                }
            }
        if (!allPassed) throw GradleException("One or more examples failed — see output above.")
    }
}

// ── Task ordering ─────────────────────────────────────────────────────────────

tasks.named("compileKmainKotlin") { dependsOn("compileJava") }
tasks.named("compileTestKotlin")  { dependsOn("compileKmainKotlin") }
