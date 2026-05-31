# Local Wayland test environment for Kadre (mirrors the linux-compose-visual CI).
#
# Runs a headless Weston compositor + the JVM sample inside this Linux container, so the
# Wayland backend can be exercised from a macOS dev box via Docker (no host Wayland client
# needed — everything runs in the container). Software Mesa GL (llvmpipe), no GPU required.
#
# Build:  docker build -t kadre-wayland docker/wayland-test
# Run:    see scripts/wayland-test.sh
FROM eclipse-temurin:25-jdk-noble

RUN apt-get update && apt-get install -y --no-install-recommends \
        weston \
        libwayland-client0 libwayland-egl1 libxkbcommon0 \
        libegl1 libegl-mesa0 libgl1 libgl1-mesa-dri libglx-mesa0 \
        mesa-utils \
        ca-certificates \
    && rm -rf /var/lib/apt/lists/*

# Software GL (llvmpipe) + force the Wayland backend.
ENV LIBGL_ALWAYS_SOFTWARE=1 \
    GALLIUM_DRIVER=llvmpipe \
    KADRE_LINUX_BACKEND=wayland \
    XDG_RUNTIME_DIR=/tmp/wl-runtime

COPY entrypoint.sh /usr/local/bin/entrypoint.sh
RUN chmod +x /usr/local/bin/entrypoint.sh

WORKDIR /work
ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]
