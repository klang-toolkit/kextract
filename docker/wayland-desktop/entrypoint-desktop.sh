#!/usr/bin/env bash
# Starts a Weston compositor with the RDP backend (decorated, floating windows) and runs the
# interactive hello-compose sample inside it. Connect from the host with an RDP client
# (e.g. Microsoft Remote Desktop) to localhost:3389.
set -euo pipefail

mkdir -p "$XDG_RUNTIME_DIR" && chmod 700 "$XDG_RUNTIME_DIR"

# The RDP backend requires a TLS certificate; a throwaway self-signed one is fine (the client
# will warn about trust — accept it).
openssl req -x509 -newkey rsa:2048 -nodes -days 7 \
  -keyout /tmp/rdp-key.pem -out /tmp/rdp-cert.pem -subj "/CN=kadre" >/dev/null 2>&1

echo "[wayland-desktop] starting Weston (RDP backend) on :3389…"
weston \
  --backend=rdp-backend.so \
  --width=1280 --height=800 \
  --socket=wayland-kadre \
  --rdp-tls-cert=/tmp/rdp-cert.pem \
  --rdp-tls-key=/tmp/rdp-key.pem \
  --idle-time=0 \
  --log=/tmp/weston.log \
  >/tmp/weston.out 2>&1 &

for _ in $(seq 1 60); do
  [ -S "$XDG_RUNTIME_DIR/wayland-kadre" ] && break
  sleep 0.5
done
if [ ! -S "$XDG_RUNTIME_DIR/wayland-kadre" ]; then
  echo "[wayland-desktop] ERROR: Weston socket never appeared" >&2
  cat /tmp/weston.log /tmp/weston.out 2>/dev/null || true
  exit 1
fi
export WAYLAND_DISPLAY=wayland-kadre

echo "[wayland-desktop] Weston up — connect an RDP client to localhost:3389 (no credentials)."
echo "[wayland-desktop] Test: drag a window edge to resize (→ Resized), click the titlebar ✕ to close (→ CloseRequested)."

# Interactive sample (no --window-capture): opens a real toplevel in Weston.
# WAYLAND_DEBUG can be set by the caller to trace the protocol (attach/commit/configure).
exec ./gradlew :samples:hello-compose:run --no-daemon --console=plain --stacktrace
