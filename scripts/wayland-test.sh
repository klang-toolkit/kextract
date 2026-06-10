#!/usr/bin/env bash
# Run a Kadre Gradle command against a headless Weston compositor inside a Linux container,
# so the Wayland backend can be tested from a macOS dev box.
#
#   scripts/wayland-test.sh                       # default: compose/desktop --window-capture
#   scripts/wayland-test.sh ./gradlew :samples:hello-window:run --no-daemon
#
# The repo is mounted at /work; a named volume persists ~/.gradle across runs.
set -euo pipefail

REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
IMAGE=kadre-wayland

docker build -t "$IMAGE" "$REPO_ROOT/docker/wayland-test"

# Allocate a TTY only when attached to one (interactive use). In CI / piped runs a TTY
# mangles Gradle output with carriage returns, so omit -t there for clean, greppable logs.
# Plain string (not an array) so it expands safely under `set -u` on macOS bash 3.2.
TTY_FLAG=""
[ -t 1 ] && TTY_FLAG="-t"

docker run --rm $TTY_FLAG \
  -v "$REPO_ROOT":/work \
  -v kadre-gradle-cache:/root/.gradle \
  "$IMAGE" "$@"
