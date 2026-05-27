# kextract

`kextract` generates **Kotlin/JVM bindings** from C and Objective-C headers.  
It parses headers via [libclang](https://clang.llvm.org/doxygen/group__CINDEX.html) and emits idiomatic Kotlin code that calls native libraries through the [Foreign Function & Memory API](https://openjdk.org/jeps/454) (Panama FFI) — no JNI, no stub generation.

---

## Requirements

| Dependency | Version |
|---|---|
| JDK | 25+ |
| LLVM / libclang | 13+ (download from [releases.llvm.org](https://releases.llvm.org/download.html)) |
| Gradle | 9.5.1 (fetched automatically by the wrapper) |

> **macOS shortcut** — `llvm_home` can point to the Xcode toolchain or the Homebrew LLVM:
> ```sh
> $(brew --prefix llvm)
> /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr
> ```

---

## Building

```sh
./gradlew -Pjdk_home=<jdk_dir> -Pllvm_home=<llvm_dir> clean kmainClasses
```

The build produces a self-contained `kextract` distribution under `build/kextract/`.

---

## Usage

```
kextract [OPTIONS] headers...
```

### Options

| Option | Short | Description |
|---|---|---|
| `--output <dir>` | `-o` | Output directory for generated files (default: `.`) |
| `--target-package <pkg>` | `-t` | Package for generated Kotlin classes |
| `--library <lib>` | `-l` | Library to link against (prefix `:` for a path, e.g. `:/usr/lib/libfoo.so`) |
| `--include-path <dir>` | `-I` | Add a directory to the clang include path |
| `-D <NAME[=VALUE]>` | | Add a preprocessor define |
| `--clang-arg <arg>` | `-A` | Forward an arbitrary argument to clang |
| `--use-system-load-library` | | Use `System.loadLibrary` instead of `SymbolLookup.libraryLookup` |
| `--symbols-class-name <name>` | | Class name for the shared symbols object |
| `--dump-includes <file>` | | Write a reusable `--include-*` filter file |
| `--objc` | | Enable Objective-C mode (`-x objective-c -fobjc-arc`) — macOS only |
| `--include-function <name>` | | Include a specific function |
| `--include-var <name>` | | Include a specific variable |
| `--include-constant <name>` | | Include a specific constant |
| `--include-struct <name>` | | Include a specific struct |
| `--include-union <name>` | | Include a specific union |
| `--include-typedef <name>` | | Include a specific typedef |
| `--include-objc-class <name>` | | Include a specific ObjC class |
| `--include-objc-protocol <name>` | | Include a specific ObjC protocol |
| `--include-objc-category <name>` | | Include a specific ObjC category |
| `--help` | `-h` | Print help and exit |
| `--version` | `-V` | Print version and exit |

GNU-style concatenated options (`-DFOO=1`, `-I/path`) are accepted.  
Argument files (`@args.txt`) are supported — one argument per line, `#` comments allowed.

### Example

```sh
# Generate Kotlin bindings for zlib
kextract \
  -t org.example.zlib \
  -o src/generated/kotlin \
  -l z \
  /usr/include/zlib.h
```

This produces `src/generated/kotlin/org/example/zlib/zlib_h.kt` with:

```kotlin
// Memory layout for z_stream
object z_stream_h {
    val layout: StructLayout = MemoryLayout.structLayout(...)

    fun next_in(seg: MemorySegment): MemorySegment = ...
    fun avail_in(seg: MemorySegment): Int = ...
    // ...
}

// Native function bindings
fun deflate(strm: MemorySegment, flush: Int): Int = ...
fun inflate(strm: MemorySegment, flush: Int): Int = ...
```

---

## Objective-C support (macOS)

Pass `--objc` to enable ObjC parsing. `@interface`, `@protocol`, and categories are mapped to Kotlin classes / interfaces / extension functions that call the ObjC runtime via `objc_msgSend` (Panama FFI, no Kotlin/Native required).

```sh
kextract --objc -t org.example.foundation /usr/include/Foundation/NSString.h
```

---

## Project structure

```
org.graphiks.kextract          # public model — Declaration, Type, Position
org.graphiks.kextract.pipeline # extraction engine — parser, filters, name mangler, CLI
org.graphiks.kextract.clang    # low-level libclang bindings (auto-generated)
org.graphiks.kextract.kotlin   # Kotlin code generators
```

---

## Testing

```sh
./gradlew -Pjdk_home=<jdk_dir> -Pllvm_home=<llvm_dir> test
```

Tests are written with [JUnit 5](https://junit.org/junit5/) and [Kotest](https://kotest.io/).

---

## License

[MIT](LICENSE)
