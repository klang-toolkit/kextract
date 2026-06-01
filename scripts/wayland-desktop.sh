#!/usr/bin/env bash
# Launch an interactive Wayland desktop (Weston + RDP backend) running the hello-compose sample,
# so the Wayland window events (Resized, CloseRequested) can be exercised with a real mouse from
# a macOS dev box.
#
#   scripts/wayland-desktop.sh
#   → then connect an RDP client (Microsoft Remote Desktop) to localhost:3389
#
# The repo is mounted at /work; a named volume persists ~/.gradle across runs.
set -euo pipefail

REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
IMAGE=kadre-wayland-desktop

docker build -t "$IMAGE" "$REPO_ROOT/docker/wayland-desktop"

TTY_FLAG=""
[ -t 1 ] && TTY_FLAG="-t"

docker run --rm $TTY_FLAG \
  -p 3389:3389 \
  -v "$REPO_ROOT":/work \
  -v kadre-gradle-cache:/root/.gradle \
  "$IMAGE"
