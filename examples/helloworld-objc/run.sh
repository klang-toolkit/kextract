#!/usr/bin/env bash
# helloworld-objc — generates Kotlin bindings from Greeter.h (ObjC mode),
#                   compiles the native framework and runs everything.
# Usage: ./run.sh [--skip-build]
#
# Requires: macOS, Xcode Command Line Tools, kotlinc on PATH (brew install kotlin)
# NOTE: ObjC support in kextract must be implemented first (see the ObjC plan).
set -euo pipefail

if [[ "$(uname)" != "Darwin" ]]; then
    echo "✗ This example requires macOS (Objective-C runtime)." >&2
    exit 1
fi

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
ROOT="$(cd "$SCRIPT_DIR/../.." && pwd)"
KEXTRACT="$ROOT/build/kextract/bin/kextract"

# ── 1. Build kextract ───────────────────────────────────────────────────────
if [[ "${1:-}" != "--skip-build" ]]; then
    echo "▶ Building kextract…"
    cd "$ROOT"
    ./gradlew createKextractImage
    cd "$SCRIPT_DIR"
    echo "✓ kextract built"
fi

if [[ ! -x "$KEXTRACT" ]]; then
    echo "✗ kextract binary not found at $KEXTRACT" >&2
    exit 1
fi

# ── 2. Compile the ObjC library ─────────────────────────────────────────────
echo "▶ Compiling Greeter.m…"
cd "$SCRIPT_DIR"
clang -shared -fPIC -fobjc-arc \
    -framework Foundation \
    -o libgreeter.dylib \
    Greeter.m
echo "✓ libgreeter.dylib"

# ── 3. Generate Kotlin bindings (ObjC mode) ─────────────────────────────────
echo "▶ Generating Kotlin bindings (--objc)…"
rm -rf generated
"$KEXTRACT" \
    --objc \
    --target-package org.example.greeter \
    --output generated \
    --library :libgreeter.dylib \
    Greeter.h
echo "✓ Bindings in generated/"

# ── 4. Detect kotlinc ───────────────────────────────────────────────────────
KOTLINC=""
for candidate in \
    "${KOTLIN_HOME:-}/bin/kotlinc" \
    "$(which kotlinc 2>/dev/null || true)" \
    "/opt/homebrew/bin/kotlinc"; do
    if [[ -x "$candidate" ]]; then
        KOTLINC="$candidate"
        break
    fi
done

if [[ -z "$KOTLINC" ]]; then
    echo ""
    echo "✗ kotlinc not found. Install it with:"
    echo "    brew install kotlin"
    echo "  or set KOTLIN_HOME to your Kotlin installation."
    exit 1
fi
echo "✓ Using kotlinc: $KOTLINC"

# ── 5. Collect generated sources + Main.kt ──────────────────────────────────
SOURCES=(Main.kt)
while IFS= read -r -d '' f; do
    SOURCES+=("$f")
done < <(find generated -name "*.kt" -print0)

# ── 6. Compile ──────────────────────────────────────────────────────────────
echo "▶ Compiling Kotlin…"
rm -rf out && mkdir out
"$KOTLINC" "${SOURCES[@]}" -include-runtime -d out/app.jar 2>&1
echo "✓ out/app.jar"

# ── 7. Run ──────────────────────────────────────────────────────────────────
echo "▶ Running…"
echo ""
"$ROOT/build/kextract/runtime/bin/java" \
    --enable-native-access=ALL-UNNAMED \
    -Djava.library.path="$SCRIPT_DIR" \
    -jar out/app.jar
