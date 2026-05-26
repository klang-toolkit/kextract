#!/bin/bash

KEXTRACT=${JEXTRACT:-../build/kextract/bin/kextract}

echo "Extracting libclang headers (Kotlin) with ${KEXTRACT}..."

"${KEXTRACT}" --output ../src/main/kotlin \
  -t org.openjdk.kextract.clang.libclang -lclang \
  --target-language kotlin \
  --use-system-load-library \
  -I /Applications/Xcode.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX.sdk/usr/include/ \
  -I ${LIBCLANG_HOME}/include/ \
  -I ${LIBCLANG_HOME}/include/clang-c \
  @clang.symbols \
  ${LIBCLANG_HOME}/include/clang-c/Index.h

echo "Done!"
