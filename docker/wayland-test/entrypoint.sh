#!/usr/bin/env bash
# Starts a headless Weston compositor, then runs the command passed as arguments
# (defaults to a hello-compose windowed capture). Everything runs inside the container.
set -euo pipefail

mkdir -p "$XDG_RUNTIME_DIR" && chmod 700 "$XDG_RUNTIME_DIR"

echo "[wayland-test] starting headless Weston…"
weston \
  --backend=headless-backend.so \
  --renderer=pixman \
  --width=800 --height=600 \
  --socket=wayland-ci \
  --idle-time=0 \
  --log=/tmp/weston.log \
  >/tmp/weston.stdout 2>&1 &

for _ in $(seq 1 60); do
  [ -S "$XDG_RUNTIME_DIR/wayland-ci" ] && break
  sleep 0.5
done
if [ ! -S "$XDG_RUNTIME_DIR/wayland-ci" ]; then
  echo "[wayland-test] ERROR: Wayland socket never appeared" >&2
  cat /tmp/weston.log /tmp/weston.stdout 2>/dev/null || true
  exit 1
fi
export WAYLAND_DISPLAY=wayland-ci
echo "[wayland-test] Weston up (WAYLAND_DISPLAY=$WAYLAND_DISPLAY)"

if [ "$#" -eq 0 ]; then
  set -- ./gradlew :samples:hello-compose:run \
    --args="--window-capture build/wayland/hello-compose.png" \
    --no-daemon --stacktrace --console=plain
fi

echo "[wayland-test] running: $*"
exec "$@"
