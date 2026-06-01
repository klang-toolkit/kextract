# Wayland xdg-shell binding codegen environment for Kadre.
#
# Builds the kextract submodule (Kotlin/JVM FFM binding generator) and runs the full
# generation chain inside a Linux container, so the Wayland xdg-shell bindings can be
# (re)generated from a macOS dev box:
#
#   wayland-scanner  xdg-shell.xml  →  xdg-shell-client-protocol.h + xdg-shell-protocol.c
#   gcc              protocol.c     →  libkadre-xdg.so   (exports xdg_*_interface symbols)
#   kextract         protocol.h     →  Kotlin FFM bindings (xdg_shell_h.kt)
#
# Build:  docker build -t kadre-wayland-codegen docker/wayland-codegen
# Run:    see scripts/gen-wayland-xdg.sh
FROM eclipse-temurin:25-jdk-noble

# LLVM/libclang (for kextract), wayland-scanner + protocol XML, and a C toolchain.
RUN apt-get update && apt-get install -y --no-install-recommends \
        clang-18 llvm-18-dev libclang-18-dev \
        libwayland-bin libwayland-dev wayland-protocols \
        gcc libc6-dev \
        patch \
        ca-certificates \
    && rm -rf /var/lib/apt/lists/*

ENV JDK_HOME=/opt/java/openjdk \
    LLVM_HOME=/usr/lib/llvm-18

WORKDIR /work
