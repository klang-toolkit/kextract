#!/bin/bash

# Script to compile newimpl Kotlin files
# This script compiles the Kotlin files in newimpl/ package
# Usage: ./compile-newimpl.sh

set -e

# Temporarily move Kotlin files to compile Java only
echo "Temporarily moving Kotlin files..."
mkdir -p /tmp/newimpl_backup
mv src/main/kotlin/org/openjdk/kextract/newimpl /tmp/newimpl_backup/

echo "Compiling Java classes..."
./gradlew clean compileJava --no-build-cache

# Restore Kotlin files
echo "Restoring Kotlin files..."
mv /tmp/newimpl_backup/newimpl src/main/kotlin/org/openjdk/kextract/
rmdir /tmp/newimpl_backup

echo "Compiling newimpl Kotlin classes..."
KOTLIN_STDLIB=$(find ~/.gradle/caches -name "kotlin-stdlib*.jar" 2>/dev/null | head -1)
if [ -z "$KOTLIN_STDLIB" ]; then
    echo "Error: Kotlin stdlib not found"
    exit 1
fi

kotlinc -cp "build/classes/java/main:$KOTLIN_STDLIB" \
    src/main/kotlin/org/openjdk/kextract/newimpl/*.kt \
    -d build/classes/kotlin/main

echo "Compiling newimpl Kotlin test classes..."
JUNIT_JUPITER=$(find ~/.gradle/caches -name "junit-jupiter-api*.jar" 2>/dev/null | head -1)
KOTLIN_TEST=$(find ~/.gradle/caches -name "kotlin-test*.jar" 2>/dev/null | head -1)

if [ -n "$JUNIT_JUPITER" ] && [ -n "$KOTLIN_TEST" ]; then
    kotlinc -cp "build/classes/java/main:build/classes/kotlin/main:$KOTLIN_STDLIB:$JUNIT_JUPITER:$KOTLIN_TEST" \
        src/test/kotlin/org/openjdk/kextract/newimpl/*.kt \
        -d build/classes/kotlin/test
else
    echo "Warning: JUnit or Kotlin test libraries not found, skipping test compilation"
fi

echo "Compilation successful!"
