# Interactive Wayland desktop for Kadre, viewable remotely over RDP.
#
# Runs a real Weston compositor (desktop-shell: floating, decorated windows) with the RDP
# backend, so a Kadre Wayland sample can be driven with a mouse/keyboard from a macOS dev box
# via Microsoft Remote Desktop. Unlike the headless harness, the compositor sends real
# xdg_toplevel.configure (resize) and xdg_toplevel.close (titlebar ✕) events — exactly what the
# Wayland backend now emits.
#
# Build:  docker build -t kadre-wayland-desktop docker/wayland-desktop
# Run:    see scripts/wayland-desktop.sh   (publishes RDP on localhost:3389)
FROM eclipse-temurin:25-jdk-noble

RUN apt-get update && apt-get install -y --no-install-recommends \
        weston \
        libwayland-client0 libwayland-egl1 libxkbcommon0 \
        libegl1 libegl-mesa0 libgl1 libgl1-mesa-dri libglx-mesa0 mesa-utils \
        libfreerdp2-2 libwinpr2-2 \
        openssl ca-certificates \
    && rm -rf /var/lib/apt/lists/*

# Software GL (llvmpipe) + force the Wayland backend.
ENV LIBGL_ALWAYS_SOFTWARE=1 \
    GALLIUM_DRIVER=llvmpipe \
    KADRE_LINUX_BACKEND=wayland \
    XDG_RUNTIME_DIR=/tmp/wl-runtime

COPY entrypoint-desktop.sh /usr/local/bin/entrypoint-desktop.sh
RUN chmod +x /usr/local/bin/entrypoint-desktop.sh

WORKDIR /work
ENTRYPOINT ["/usr/local/bin/entrypoint-desktop.sh"]
