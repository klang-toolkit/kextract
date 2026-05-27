import org.apache.tools.ant.taskdefs.condition.Os
import java.net.URI
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.nio.file.attribute.PosixFilePermission

plugins {
    java
    id("org.jetbrains.kotlin.jvm") version "2.3.21"
}

kotlin {
    jvmToolchain(25)
}

fun checkPath(p: String) {
    if (!Files.exists(Path.of(p))) {
        throw IllegalArgumentException("Error: the path $p does not exist")
    }
}

val llvmVersion = "22.1.6"
val explicitLlvmHome = (project.findProperty("llvm_home") as String?)
    ?.takeIf { it.isNotBlank() && Files.exists(Path.of(it)) }
val autoLlvmDir = layout.buildDirectory.dir("llvm/$llvmVersion").get().asFile.absolutePath
val llvm_home: String = explicitLlvmHome ?: autoLlvmDir
val jdk_home = project.property("jdk_home") as String
checkPath(jdk_home)
if (explicitLlvmHome != null) {
    checkPath("$llvm_home/lib/clang")
}

val clang_version: String by lazy {
    val clangDir = File("$llvm_home/lib/clang/")
    if (!clangDir.exists()) {
        throw GradleException(
            "LLVM not found at $llvm_home. Run './gradlew downloadLLVM' first, " +
            "or pass -Pllvm_home=<path> (e.g. \$(brew --prefix llvm))."
        )
    }
    val versions = clangDir.list()
    if (versions.isNullOrEmpty()) {
        throw GradleException(
            "Could not detect clang version. Make sure $llvm_home/lib/clang/<VERSION> exists."
        )
    }
    versions[0]
}

val buildDirectory = layout.buildDirectory.get()
@Suppress("UNUSED_VARIABLE")
val kextract_version = "23"
val kextract_jmod_inputs = "$buildDirectory/jmod_inputs"
val kextract_app_dir = "$buildDirectory/kextract"
val kextract_runtime_dir = "$kextract_app_dir/runtime"
val kextract_bin_dir = "$kextract_app_dir/bin"
val os_lib_dir = if (Os.isFamily(Os.FAMILY_WINDOWS)) "bin" else "lib"
val os_script_extension = if (Os.isFamily(Os.FAMILY_WINDOWS)) ".bat" else ""
val os_exe_suffix = if (Os.isFamily(Os.FAMILY_WINDOWS)) ".exe" else ""
val libclang_dir = "$llvm_home/$os_lib_dir"

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

        // Cache the archive in the Gradle user home so it survives `./gradlew clean`
        val archiveCacheDir = File(gradle.gradleUserHomeDir, "kextract-llvm")
        archiveCacheDir.mkdirs()
        val archive = File(archiveCacheDir, assetName)

        if (!archive.exists()) {
            logger.lifecycle("Downloading $url (one-time, ~1.5 GB)")
            val tmp = File(archiveCacheDir, "$assetName.part")
            URI(url).toURL().openStream().use { input ->
                tmp.outputStream().use { output -> input.copyTo(output) }
            }
            tmp.renameTo(archive)
        } else {
            logger.lifecycle("Using cached LLVM archive at $archive")
        }

        delete(targetDir)
        targetDir.mkdirs()
        logger.lifecycle("Extracting $assetName to $targetDir")
        val proc = ProcessBuilder(
            "tar", "--strip-components=1",
            "-xf", archive.absolutePath,
            "-C", targetDir.absolutePath
        ).inheritIO().start()
        val exit = proc.waitFor()
        if (exit != 0) throw GradleException("tar extraction failed with exit code $exit")

        // Trim down: keep libclang + libLLVM (which libclang depends on) + builtin headers.
        val keepPatterns = listOf(
            Regex("^$os_lib_dir/libclang\\..*"),
            Regex("^$os_lib_dir/libLLVM\\..*"),
            Regex("^lib/clang/[^/]+/include/.*"),
        )
        val sizeBefore = targetDir.walkBottomUp().filter { it.isFile }.sumOf { it.length() }
        targetDir.walkBottomUp()
            .filter { it.isFile }
            .filter { f ->
                val rel = f.toRelativeString(targetDir).replace('\\', '/')
                keepPatterns.none { it.matches(rel) }
            }
            .forEach { it.delete() }
        var changed = true
        while (changed) {
            changed = false
            targetDir.walkBottomUp()
                .filter { it.isDirectory && it != targetDir && (it.list()?.isEmpty() == true) }
                .forEach { it.delete(); changed = true }
        }
        val sizeAfter = targetDir.walkBottomUp().filter { it.isFile }.sumOf { it.length() }
        logger.lifecycle("Trimmed LLVM: ${sizeBefore / 1024 / 1024} MB → ${sizeAfter / 1024 / 1024} MB")

        marker.writeText("ok\n")
    }
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf("src/main/java"))
        }
        kotlin {
            setSrcDirs(emptyList<String>())
        }
        resources {
            setSrcDirs(emptyList<String>())
        }
    }
    val kmain by creating {
        kotlin {
            setSrcDirs(listOf("src/main/kotlin"))
        }
        resources {
            setSrcDirs(listOf("src/main/resources"))
        }
        compileClasspath += sourceSets["main"].output + sourceSets["main"].compileClasspath
        runtimeClasspath += sourceSets["main"].output + sourceSets["main"].runtimeClasspath
    }
    test {
        kotlin {
            setSrcDirs(listOf("src/test/kotlin"))
            exclude("ManualKotlinGen.kt")
        }
        compileClasspath += sourceSets["kmain"].output
        runtimeClasspath += sourceSets["kmain"].output
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.3.21")
    "kmainImplementation"("org.jetbrains.kotlin:kotlin-stdlib:2.3.21")
    "kmainImplementation"("com.github.ajalt.clikt:clikt:5.0.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.14.4")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.14.4")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.jetbrains.kotlin:kotlin-test:2.3.21")

    // Kotest
    val kotestVersion = "6.1.11"
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
}

tasks.withType<Test>().configureEach {
    dependsOn("downloadLLVM")
    useJUnitPlatform()
    jvmArgs("--enable-native-access=ALL-UNNAMED")
    systemProperty("java.library.path", "$llvm_home/$os_lib_dir")
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 25
    if (name.contains("Java")) {
        options.isFork = true
        options.forkOptions.executable = "$jdk_home/bin/javac$os_exe_suffix"
    }
}

tasks.named<JavaCompile>("compileTestJava") {
    options.release = 25
}

tasks.named<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile>("compileTestKotlin") {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_25)
    }
}

tasks.named<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile>("compileKmainKotlin") {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_25)
    }
}

tasks.named<Jar>("jar") {
    archiveBaseName = "org.graphiks.kextract"
    archiveVersion = project.version.toString()
    from(sourceSets["kmain"].output)
}

tasks.register("copyLibClang") {
    dependsOn("downloadLLVM")
    outputs.dir("$buildDirectory/jmod_inputs")
    doLast {
        val outDir = file("$buildDirectory/jmod_inputs")
        val libsDir = File(outDir, "libs").apply { deleteRecursively(); mkdirs() }
        val confDir = File(outDir, "conf/kextract").apply { deleteRecursively(); mkdirs() }

        val isAix = Os.isName("AIX") || Os.isName("aix")
        val isUnixNotMac = Os.isFamily(Os.FAMILY_UNIX) && !Os.isFamily(Os.FAMILY_MAC)
        val clangPathInclude = when {
            isAix -> "libclang.a"
            isUnixNotMac -> "libclang.so.$clang_version"
            Os.isFamily(Os.FAMILY_WINDOWS) -> "libclang.dll"
            else -> "libclang.dylib"
        }

        val srcLibDir = File(libclang_dir)
        if (!srcLibDir.isDirectory) {
            throw GradleException("libclang directory not found at $srcLibDir")
        }

        srcLibDir.listFiles { f -> f.isFile }?.forEach { src ->
            val n = src.name
            if (n == "clang.exe") return@forEach
            val keep = n == clangPathInclude || n.startsWith("libLLVM.")
            if (!keep) return@forEach
            val targetName = if (n.startsWith("libclang.so.")) "libclang.so" else n
            Files.copy(
                src.toPath(),
                File(libsDir, targetName).toPath(),
                StandardCopyOption.REPLACE_EXISTING
            )
        }

        val includeDir = File("$llvm_home/lib/clang/$clang_version/include")
        if (!includeDir.isDirectory) {
            throw GradleException("Clang builtin headers not found at $includeDir")
        }
        includeDir.listFiles { f -> f.isFile && f.name.endsWith(".h") }?.forEach { src ->
            Files.copy(
                src.toPath(),
                File(confDir, src.name).toPath(),
                StandardCopyOption.REPLACE_EXISTING
            )
        }
    }
}

tasks.register("createKextractImage") {
    val jarTask = tasks.named<Jar>("jar")
    val copyLibClangTask = tasks.named("copyLibClang")
    val writeLibClangVersionTask = tasks.named("writeLibClangVersion")
    dependsOn(jarTask, copyLibClangTask, writeLibClangVersionTask)

    inputs.file(jarTask.flatMap { it.archiveFile })
    inputs.dir(kextract_jmod_inputs)
    outputs.dir(kextract_app_dir)

    doLast {
        delete(kextract_app_dir)

        val libDir = "$kextract_app_dir/lib"
        val confDir = "$kextract_app_dir/conf/kextract"
        project.mkdir(libDir)
        project.mkdir(confDir)
        project.mkdir(kextract_bin_dir)

        // 1. jlink: minimal JRE
        val kotlinStdlib = configurations["kmainRuntimeClasspath"].files
            .find { it.name.contains("kotlin-stdlib") }
        var modulePath = "$jdk_home/jmods"
        if (kotlinStdlib != null) modulePath += File.pathSeparator + kotlinStdlib.parent

        val jlinkCmd = listOf(
            "$jdk_home/bin/jlink",
            "--module-path=$modulePath",
            "--add-modules=java.base,java.compiler,kotlin.stdlib",
            "--output=$kextract_runtime_dir",
            "--strip-debug", "--no-man-pages", "--no-header-files"
        )
        val jlinkProc = ProcessBuilder(jlinkCmd).inheritIO().start()
        val jlinkExit = jlinkProc.waitFor()
        if (jlinkExit != 0) throw GradleException("jlink failed with exit code $jlinkExit")

        // 2. App JAR + all runtime dependencies on classpath
        val jarFile = jarTask.get().archiveFile.get().asFile
        Files.copy(jarFile.toPath(), Path.of("$libDir/org.graphiks.kextract.jar"))
        configurations["kmainRuntimeClasspath"].files.forEach { dep ->
            val dest = Path.of("$libDir/${dep.name}")
            if (!Files.exists(dest)) Files.copy(dep.toPath(), dest)
        }

        // 3. Native library
        project.copy {
            from("$kextract_jmod_inputs/libs")
            into(libDir)
        }

        // 4. Clang builtin headers + version file
        project.copy {
            from("$kextract_jmod_inputs/conf/kextract")
            into(confDir)
        }

        // 5. Launcher scripts
        val unixOut = Path.of("$kextract_bin_dir/kextract")
        Files.copy(Path.of("$projectDir/src/main/kextract"), unixOut)
        if (unixOut.fileSystem.supportedFileAttributeViews().contains("posix")) {
            val perms: MutableSet<PosixFilePermission> = Files.getPosixFilePermissions(unixOut)
            perms.add(PosixFilePermission.OWNER_EXECUTE)
            perms.add(PosixFilePermission.GROUP_EXECUTE)
            perms.add(PosixFilePermission.OTHERS_EXECUTE)
            Files.setPosixFilePermissions(unixOut, perms)
        }
        Files.copy(
            Path.of("$projectDir/src/main/kextract.bat"),
            Path.of("$kextract_bin_dir/kextract.bat")
        )
        Files.copy(
            Path.of("$projectDir/src/main/kextract.ps1"),
            Path.of("$kextract_bin_dir/kextract.ps1")
        )
    }
}

tasks.named("assemble") {
    dependsOn("createKextractImage")
}

tasks.register<Exec>("verify") {
    dependsOn("createKextractImage")
    executable = "$kextract_bin_dir/kextract$os_script_extension"
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
        val examplesDir = file("examples")
        examplesDir.listFiles { f -> f.isDirectory && File(f, "run.sh").exists() }
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

tasks.register("writeLibClangVersion") {
    dependsOn("downloadLLVM")
    val confDir = file("$buildDirectory/jmod_inputs/conf/kextract")
    outputs.file("$confDir/libclang.version")
    doLast {
        val clang_major_version = clang_version.split(".")[0]
        confDir.mkdirs()
        file("$confDir/libclang.version").writeText(clang_major_version + "\n")
    }
}

tasks.named("compileKmainKotlin") {
    dependsOn("compileJava")
}
tasks.named("compileTestKotlin") {
    dependsOn("compileKmainKotlin")
}
