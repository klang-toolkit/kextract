#!/usr/bin/env bash
# In-container generation steps. Run by scripts/gen-wayland-xdg.sh inside the
# kadre-wayland-codegen image (LLVM/libclang + wayland-scanner + gcc + JDK 25).
set -euo pipefail

JDK_HOME="${JDK_HOME:-/opt/java/openjdk}"
LLVM_HOME="${LLVM_HOME:-/usr/lib/llvm-18}"
REPO=/work
KEXTRACT_DIR="$REPO/third_party/kextract"
PROTO=/usr/share/wayland-protocols/stable/xdg-shell/xdg-shell.xml

ARCH="$(uname -m)"   # aarch64 | x86_64
GEN="$REPO/kadre-wayland/build/wayland-xdg"
OUT_KT="$REPO/kadre-wayland/src/jvmMain/kotlin"
OUT_SO_DIR="$REPO/kadre-wayland/src/jvmMain/resources/native/linux-$ARCH"

echo "[gen] arch=$ARCH  llvm=$LLVM_HOME  jdk=$JDK_HOME"
mkdir -p "$GEN" "$OUT_SO_DIR"

# ── 0. Apply Kadre's kextract evolutions (kept as patches; submodule stays pinned) ──
# kextract v0.0.2 mis-generates two things we need: aggregate-typed globals (struct
# interface symbols) and struct byteSize() helpers. Our fixes live as patches here and
# are applied idempotently before the build. See kextract-patches/.
# Use `patch`, not `git apply`: the submodule's .git points at the parent repo's modules
# dir, which is not mounted in the container, so git can't operate inside the submodule.
for p in "$REPO"/docker/wayland-codegen/kextract-patches/*.patch; do
  [ -e "$p" ] || continue
  if patch -p1 -d "$KEXTRACT_DIR" -R --dry-run -f < "$p" >/dev/null 2>&1; then
    echo "[gen] kextract patch already applied: $(basename "$p")"
  else
    patch -p1 -d "$KEXTRACT_DIR" -f < "$p" && echo "[gen] applied kextract patch: $(basename "$p")"
  fi
done

# ── 1. Build kextract (self-contained image under build/kextract/) ──────────────
echo "[gen] building kextract…"
( cd "$KEXTRACT_DIR" && ./gradlew --no-daemon -Pjdk_home="$JDK_HOME" -Pllvm_home="$LLVM_HOME" createKextractImage )
KEXTRACT="$KEXTRACT_DIR/build/kextract/bin/kextract"
"$KEXTRACT" --help >/dev/null 2>&1 && echo "[gen] kextract launcher OK"

# ── 2. wayland-scanner: protocol headers + public code ──────────────────────────
# public-code (not private-code): the interface tables must be EXPORTED symbols so the JVM can
# resolve them via dlsym/loaderLookup. private-code gives them hidden (LOCAL) visibility, which
# compiles fine but the symbols are invisible to SymbolLookup at runtime.
PROTO_DECO=/usr/share/wayland-protocols/unstable/xdg-decoration/xdg-decoration-unstable-v1.xml
echo "[gen] wayland-scanner $PROTO + $PROTO_DECO"
wayland-scanner client-header "$PROTO"      "$GEN/xdg-shell-client-protocol.h"
wayland-scanner public-code    "$PROTO"      "$GEN/xdg-shell-protocol.c"
wayland-scanner client-header "$PROTO_DECO" "$GEN/xdg-decoration-client-protocol.h"
wayland-scanner public-code    "$PROTO_DECO" "$GEN/xdg-decoration-protocol.c"

# ── 3. Compile the interface tables into a shared lib ───────────────────────────
echo "[gen] gcc → libkadre-xdg.so"
gcc -shared -fPIC -o "$OUT_SO_DIR/libkadre-xdg.so" \
    "$GEN/xdg-shell-protocol.c" "$GEN/xdg-decoration-protocol.c" -lwayland-client
nm -D "$OUT_SO_DIR/libkadre-xdg.so" | grep -E "(xdg_(wm_base|surface|toplevel)|zxdg_(decoration_manager|toplevel_decoration))" || true

# ── 4. kextract: header → Kotlin FFM bindings ───────────────────────────────────
# -ffreestanding is required: kextract's bundled libclang, in hosted mode, can't follow
# clang's stdint.h `#include_next <stdint.h>` chain into glibc in this image, so every
# sized int type (int32_t/uint32_t/intmax_t) comes out "unknown" and the parse aborts.
# Freestanding makes clang define those types itself from compiler builtins
# (__INT32_TYPE__ &c.) — verified to drop the error count to zero. The wayland headers
# only need the sized-int / size_t / va_list types, all available freestanding.
# No -l: emit loaderLookup()-based accessors. The Kotlin side System.load()s the
# bundled libkadre-xdg.so resource at init, which makes its symbols resolvable via
# loaderLookup — avoiding a hard-coded library path baked into the generated file.
"$KEXTRACT" \
  -t org.graphiks.kadre.wayland.generated \
  -o "$OUT_KT" \
  --include-var xdg_wm_base_interface \
  --include-var xdg_surface_interface \
  --include-var xdg_toplevel_interface \
  --include-var zxdg_decoration_manager_v1_interface \
  --include-var zxdg_toplevel_decoration_v1_interface \
  --include-struct wl_interface \
  --include-struct wl_message \
  -A -ffreestanding \
  -I "$GEN" \
  "$GEN/xdg-shell-client-protocol.h" \
  "$GEN/xdg-decoration-client-protocol.h"

echo "[gen] done. Generated:"
find "$OUT_KT/org/graphiks/kadre/wayland/generated" -name '*.kt' 2>/dev/null
ls -l "$OUT_SO_DIR"
