// test_structs.h

// Simple struct
struct Point {
    int x;
    int y;
};

// Struct with nested struct
typedef struct {
    int width;
    int height;
} Dimensions;

// Union
union IntOrFloat {
    int i;
    float f;
};

// Typedef struct
struct Color {
    unsigned char r;
    unsigned char g;
    unsigned char b;
};
typedef struct Color Color;
