#!/usr/bin/env bash
#
# Regenerates the AppKit FFM bindings using a local kextract install.
#
# Usage:
#   scripts/regen-appkit-bindings.sh /path/to/kextract/bin/kextract
#
# After running this script, apply the manual fixups documented in
# kadre-appkit/src/jvmMain/kotlin/org/graphiks/kadre/appkit/bindings/AppKit_h.kt
# (search for "Manual fixups" at the top of the file) or use the perl/sed lines
# at the bottom of this script.
#
# Requires:
#   - macOS with Xcode installed (xcrun must work)
#   - kextract v0.0.2 or newer
#
set -euo pipefail

KEXTRACT="${1:?Usage: $0 /path/to/kextract/bin/kextract}"
[ -x "$KEXTRACT" ] || { echo "kextract binary not executable: $KEXTRACT" >&2; exit 1; }

SDK=$(xcrun --sdk macosx --show-sdk-path)
APPKIT_H="$SDK/System/Library/Frameworks/AppKit.framework/Headers/AppKit.h"
OUT=$(cd "$(dirname "$0")/.." && pwd)/kadre-appkit/src/jvmMain/kotlin

echo "→ Regenerating AppKit bindings via kextract"
echo "  SDK     = $SDK"
echo "  Output  = $OUT"

"$KEXTRACT" \
    --objc \
    -A "-F$SDK/System/Library/Frameworks" \
    -A "-isysroot" -A "$SDK" \
    -o "$OUT" \
    -t org.graphiks.kadre.appkit.bindings \
    --include-objc-class NSApplication \
    --include-objc-class NSWindow \
    --include-objc-class NSView \
    --include-objc-class NSEvent \
    --include-objc-class CAMetalLayer \
    --include-objc-protocol NSApplicationDelegate \
    --include-objc-protocol NSWindowDelegate \
    "$APPKIT_H"

AKH="$OUT/org/graphiks/kadre/appkit/bindings/AppKit_h.kt"

echo "→ Applying manual fixups"

# 1. Escape Kotlin reserved keyword `object` in parameter positions.
perl -i -pe 's/\bobject(?=: MemorySegment|, |\))/`object`/g' "$AKH"

# 2. Add explicit `: Unit` to single-expression methods that throw — Kotlin can't
#    infer `Nothing` as expression-body return type when it's the actual return.
perl -i -pe 's/^(    fun \w+\([^)]*\)) =$/\1: Unit =/' "$AKH"

# 3. Strip evil shadowing typealiases that kextract v0.0.2 emits for CoreFoundation
#    legacy types: `typealias Boolean = Any` and `typealias Byte = Any` hide the
#    Kotlin builtins inside this package.
#    Upstream tracking: https://github.com/klang-toolkit/kextract/issues/28
perl -i -ne 'print unless /^typealias (Boolean|Byte) = Any\s*$/' "$AKH"

# 4. Strip `typealias NSUInteger = Any` — the unsigned-long C type has no Kotlin
#    primitive; we override it to Long in FoundationTypes.kt.
#    Upstream tracking: https://github.com/klang-toolkit/kextract/issues/29
perl -i -ne 'print unless /^typealias NSUInteger = Any\s*$/' "$AKH"

echo "✓ Done. Regenerated bindings at $AKH"
echo "  Don't forget to inspect the diff and run :kadre-appkit:jvmTest."
