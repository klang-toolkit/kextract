#!/usr/bin/env bash
# helloworld-c — generates Kotlin bindings from hello.h, compiles and runs.
# Usage: ./run.sh [--skip-build]
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
ROOT="$(cd "$SCRIPT_DIR/../.." && pwd)"
KEXTRACT="$ROOT/build/kextract/bin/kextract"

# ── 1. Build kextract (skippable if already built) ──────────────────────────
if [[ "${1:-}" != "--skip-build" ]]; then
    echo "▶ Building kextract…"
    cd "$ROOT"
    ./gradlew createKextractImage
    cd "$SCRIPT_DIR"
    echo "✓ kextract built"
fi

if [[ ! -x "$KEXTRACT" ]]; then
    echo "✗ kextract binary not found at $KEXTRACT" >&2
    echo "  Run without --skip-build, or run: cd $ROOT && ./gradlew createKextractImage" >&2
    exit 1
fi

# ── 2. Compile the native library ───────────────────────────────────────────
echo "▶ Compiling hello.c…"
cd "$SCRIPT_DIR"
cc -shared -fPIC -o libhello.dylib hello.c
echo "✓ libhello.dylib"

# ── 3. Generate Kotlin bindings ─────────────────────────────────────────────
echo "▶ Generating Kotlin bindings…"
rm -rf generated
"$KEXTRACT" \
    --target-package org.example.hello \
    --output generated \
    --library :libhello.dylib \
    hello.h
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
