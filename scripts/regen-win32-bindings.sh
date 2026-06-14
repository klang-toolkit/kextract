#!/usr/bin/env bash
#
# Regenerates Win32 FFM bindings for DLLs needed by Kadre on Windows.
# Uses --win32 and --dll-map to generate per-DLL bindings from <windows.h>.
#
# Usage:
#   scripts/regen-win32-bindings.sh /path/to/kextract/bin/kextract
#
# Requires:
#   - Windows SDK (cl.exe/clang-cl must work)
#   - kextract with --win32 and --dll-map support
#
set -euo pipefail

KEXTRACT="${1:?Usage: $0 /path/to/kextract/bin/kextract}"
[ -x "$KEXTRACT" ] || { echo "kextract binary not executable: $KEXTRACT" >&2; exit 1; }

# Only runs on Windows — <windows.h> is Windows SDK only
case "$(uname -s)" in
    MINGW*|MSYS*|CYGWIN*) ;;
    *)
        echo "This script can only run on Windows (requires the Windows SDK for <windows.h>)." >&2
        echo "Current OS: $(uname -s)" >&2
        exit 0
        ;;
esac

SCRIPT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
DLL_MAP_DIR="$SCRIPT_DIR/ffi/win32"
OUT_DIR="$DLL_MAP_DIR/src/jvmMain/kotlin/org/graphiks/kadre/ffi/win32/generated"
PACKAGE="org.graphiks.kadre.ffi.win32.generated"
HEADER="<windows.h>"

DLLS=(user32 kernel32 gdi32 dwmapi)

echo "→ Regenerating Win32 bindings for ${#DLLS[@]} DLLs"
echo "  Output  = $OUT_DIR"

mkdir -p "$OUT_DIR"

for dll in "${DLLS[@]}"; do
    yaml="$DLL_MAP_DIR/${dll}.yaml"
    echo "  Processing $dll..."

    # Extract function names from the YAML mapping (lines under "functions:" until next section)
    IFS=$'\n' read -r -d '' -a functions < <(
        sed -n '/^    functions:/,/^    structs:/{
            /^    functions:/d
            /^    structs:/d
            /^      - /{
                s/^      - //
                p
            }
        }' "$yaml"
    )

    kextractArgs=(
        --win32
        --dll-map "$yaml"
        -o "$OUT_DIR"
        -t "$PACKAGE"
    )

    for fn in "${functions[@]}"; do
        kextractArgs+=(--include-function "$fn")
    done
    kextractArgs+=("$HEADER")

    "$KEXTRACT" "${kextractArgs[@]}"
done

echo "✓ Done. Regenerated bindings at $OUT_DIR/"
