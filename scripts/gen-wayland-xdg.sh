#!/usr/bin/env bash
# (Re)generate the Wayland xdg-shell FFM bindings for Kadre, from a macOS dev box, via Docker.
#
# Chain (all inside a Linux container — see docker/wayland-codegen/Dockerfile):
#   1. build kextract (submodule)            → build/kextract/bin/kextract
#   2. wayland-scanner xdg-shell.xml         → header + protocol.c
#   3. gcc protocol.c                        → libkadre-xdg.so  (exports xdg_*_interface)
#   4. kextract header                       → Kotlin FFM bindings
#
# Outputs are written into the mounted repo so they can be committed:
#   kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/generated/*.kt
#   kadre-wayland/src/jvmMain/resources/native/linux-<arch>/libkadre-xdg.so
#
# Usage: scripts/gen-wayland-xdg.sh
set -euo pipefail

REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
IMAGE=kadre-wayland-codegen

docker build -t "$IMAGE" "$REPO_ROOT/docker/wayland-codegen"

TTY_FLAG=""
[ -t 1 ] && TTY_FLAG="-t"

# A named volume persists ~/.gradle (kextract build deps) across runs.
docker run --rm $TTY_FLAG \
  -v "$REPO_ROOT":/work \
  -v kadre-gradle-cache:/root/.gradle \
  "$IMAGE" bash /work/docker/wayland-codegen/generate.sh
