// test_combined.h

#include "test_structs.h"
#include "test_functions.h"

// Function using previous structs
struct Point create_point(int x, int y);
int distance(struct Point a, struct Point b);

// Enum (si supporté)
typedef enum {
    RED,
    GREEN,
    BLUE
} ColorEnum;
