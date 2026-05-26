#!/bin/bash
# Test script for Kotlin integration with kextract
# Usage: ./test_kotlin_integration.sh [header_file]

set -e  # Exit on error

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Get script directory
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && pwd)"
PROJECT_ROOT="$(dirname "$(dirname "$(dirname "$(dirname "$SCRIPT_DIR")")")")"

# Default header
HEADER_FILE="${SCRIPT_DIR}/resources/test_structs.h"

# Override with argument if provided
if [ $# -ge 1 ]; then
    HEADER_FILE="$1"
fi

# Check if header file exists
if [ ! -f "$HEADER_FILE" ]; then
    echo -e "${RED}Error: Header file not found: $HEADER_FILE${NC}"
    echo "Available test headers:"
    ls -1 "${SCRIPT_DIR}/resources/" 2>/dev/null || echo "  (none)"
    exit 1
fi

# Create temp directory
TEMP_DIR=$(mktemp -d -t kextract_kotlin_test.XXXXXX)
echo -e "${YELLOW}Using temp directory: $TEMP_DIR${NC}"

# Run kextract with Kotlin target
OUTPUT_DIR="${TEMP_DIR}/output"
mkdir -p "$OUTPUT_DIR"

echo -e "${YELLOW}Running kextract on $HEADER_FILE...${NC}"

# Build the kextract command
# Note: kextract is installed via brew, so it should be in PATH
KEXTRACT_CMD="kextract"

# Check if kextract is available
if ! command -v "$KEXTRACT_CMD" &> /dev/null; then
    echo -e "${RED}Error: kextract not found in PATH${NC}"
    echo "Make sure kextract is installed via brew: brew install kextract"
    exit 1
fi

# Run kextract
"$KEXTRACT_CMD" \
    "$HEADER_FILE" \
    --output "$OUTPUT_DIR" \
    -t "org.openjdk.kextract.test" \
    -l "testlib" \
    --target-language kotlin \
    --use-system-load-library

# Check if generation succeeded
if [ $? -ne 0 ]; then
    echo -e "${RED}Error: kextract failed to generate Kotlin code${NC}"
    rm -rf "$TEMP_DIR"
    exit 1
fi

# Check for generated .kt files
KT_FILES=$(find "$OUTPUT_DIR" -name "*.kt" 2>/dev/null)

if [ -z "$KT_FILES" ]; then
    echo -e "${RED}Error: No .kt files generated${NC}"
    echo "Contents of $OUTPUT_DIR:"
    ls -la "$OUTPUT_DIR" || true
    rm -rf "$TEMP_DIR"
    exit 1
fi

echo -e "${GREEN}✓ Kotlin files generated:${NC}"
for file in $KT_FILES; do
    echo "  - $file"
done

# Try to compile the generated Kotlin files
echo -e "\n${YELLOW}Checking Kotlin syntax...${NC}"

KOTLINC_CMD="kotlinc"
if ! command -v "$KOTLINC_CMD" &> /dev/null; then
    echo -e "${YELLOW}Warning: kotlinc not found, skipping syntax check${NC}"
    echo "Install with: brew install kotlin"
else
    # Find all .kt files and check syntax
    SYNTAX_ERRORS=0
    for kt_file in $KT_FILES; do
        echo -n "  Checking $kt_file... "
        if kotlinc -d "${TEMP_DIR}/dummy.jar" "$kt_file" \
            -cp "$(find ~/.gradle/caches -name "kotlin-stdlib*.jar" | head -1 || echo "")" \
            2>&1 | grep -q "error:"; then
            echo -e "${RED}FAIL${NC}"
            SYNTAX_ERRORS=$((SYNTAX_ERRORS + 1))
        else
            echo -e "${GREEN}OK${NC}"
        fi
    done
    
    if [ $SYNTAX_ERRORS -gt 0 ]; then
        echo -e "\n${RED}Error: $SYNTAX_ERRORS syntax errors found${NC}"
        rm -rf "$TEMP_DIR"
        exit 1
    fi
    
    echo -e "\n${GREEN}✓ All Kotlin files are syntactically valid${NC}"
fi

# Show a sample of generated code
echo -e "\n${YELLOW}Sample of generated code:${NC}"
SAMPLE_FILE=$(find "$OUTPUT_DIR" -name "*.kt" | head -1)
if [ -n "$SAMPLE_FILE" ]; then
    echo "--- $SAMPLE_FILE ---"
    head -50 "$SAMPLE_FILE"
fi

# Cleanup
echo -e "\n${YELLOW}Cleaning up...${NC}"
rm -rf "$TEMP_DIR"
echo -e "${GREEN}✓ All tests passed!${NC}"
