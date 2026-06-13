#!/usr/bin/env bash
#
# Regenerates ObjC FFM bindings for all frameworks useful for desktop apps/games.
# Uses --split-output and --include-framework for per-class files with framework
# source-location filtering.
#
# Usage:
#   scripts/regen-objc-bindings.sh /path/to/kextract/bin/kextract
#
# Requires:
#   - macOS with Xcode installed (xcrun must work)
#   - kextract with --split-output and --include-framework support
#
set -euo pipefail

KEXTRACT="${1:?Usage: $0 /path/to/kextract/bin/kextract}"
[ -x "$KEXTRACT" ] || { echo "kextract binary not executable: $KEXTRACT" >&2; exit 1; }

SDK=$(xcrun --sdk macosx --show-sdk-path)
APPKIT_H="$SDK/System/Library/Frameworks/AppKit.framework/Headers/AppKit.h"
OUT=$(cd "$(dirname "$0")/.." && pwd)/ffi/objc/src/jvmMain/kotlin

FRAMEWORKS=(
    Foundation CoreFoundation AppKit CoreGraphics
    QuartzCore CoreImage Metal AVFoundation
    GameController ModelIO SceneKit
    UniformTypeIdentifiers PDFKit QuickLook
)

includeFwArgs=()
for fw in "${FRAMEWORKS[@]}"; do
    includeFwArgs+=("--include-framework" "$fw")
done

echo "→ Regenerating ObjC bindings for ${#FRAMEWORKS[@]} frameworks"
echo "  SDK     = $SDK"
echo "  Output  = $OUT"

"$KEXTRACT" \
    --objc \
    --split-output \
    "${includeFwArgs[@]}" \
    -A "-F$SDK/System/Library/Frameworks" \
    -A "-isysroot" -A "$SDK" \
    -o "$OUT" \
    -t org.graphiks.kffi.objc \
    "$APPKIT_H"

echo "✓ Done. Regenerated bindings at $OUT/org/graphiks/kffi/objc/"
