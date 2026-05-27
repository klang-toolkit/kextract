import org.apache.tools.ant.taskdefs.condition.Os
import java.nio.file.Files
import java.nio.file.Path
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

val llvm_home = project.property("llvm_home") as String
val jdk_home = project.property("jdk_home") as String
checkPath(llvm_home)
checkPath("$llvm_home/lib/clang")
val clang_versions = File("$llvm_home/lib/clang/").list()
    ?: throw IllegalArgumentException("Could not list $llvm_home/lib/clang/")
if (clang_versions.isEmpty()) {
    throw IllegalArgumentException(
        "Could not detect clang version. Make sure a $llvm_home/lib/clang/<VERSION> directory exists"
    )
}
val clang_version = clang_versions[0]

val buildDirectory = layout.buildDirectory.get()
@Suppress("UNUSED_VARIABLE")
val kextract_version = "23"
val kextract_jmod_inputs = "$buildDirectory/jmod_inputs"
val kextract_app_dir = "$buildDirectory/kextract"
val kextract_runtime_dir = "$kextract_app_dir/runtime"
val kextract_bin_dir = "$kextract_app_dir/bin"
val clang_include_dir = "$llvm_home/lib/clang/$clang_version/include"
checkPath(clang_include_dir)
val os_lib_dir = if (Os.isFamily(Os.FAMILY_WINDOWS)) "bin" else "lib"
val os_script_extension = if (Os.isFamily(Os.FAMILY_WINDOWS)) ".bat" else ""
val os_exe_suffix = if (Os.isFamily(Os.FAMILY_WINDOWS)) ".exe" else ""
val libclang_dir = "$llvm_home/$os_lib_dir"
checkPath(libclang_dir)

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
    useJUnitPlatform()
    jvmArgs("--enable-native-access=ALL-UNNAMED")
    systemProperty("java.library.path", "${findProperty("llvm_home") ?: ""}/lib")
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

tasks.register<Sync>("copyLibClang") {
    into("$buildDirectory/jmod_inputs")
    val isAix = Os.isName("AIX") || Os.isName("aix")
    val isUnixNotMac = Os.isFamily(Os.FAMILY_UNIX) && !Os.isFamily(Os.FAMILY_MAC)
    val clang_path_include = when {
        isAix -> "libclang.a"
        isUnixNotMac -> "libclang.so.$clang_version"
        else -> "*clang*"
    }
    from(libclang_dir) {
        include(clang_path_include)
        include("libLLVM.*")
        exclude("clang.exe")
        into("libs")
        rename("libclang.so.*", "libclang.so")
    }
    from(clang_include_dir) {
        include("*.h")
        into("conf/kextract")
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

        // 2. App JAR + kotlin-stdlib on classpath
        val jarFile = jarTask.get().archiveFile.get().asFile
        Files.copy(jarFile.toPath(), Path.of("$libDir/org.graphiks.kextract.jar"))
        if (kotlinStdlib != null) {
            Files.copy(kotlinStdlib.toPath(), Path.of("$libDir/${kotlinStdlib.name}"))
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

tasks.register("writeLibClangVersion") {
    val confDir = file("$buildDirectory/jmod_inputs/conf/kextract")
    outputs.file("$confDir/libclang.version")
    val clang_major_version = clang_version.split(".")[0]
    doLast {
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
