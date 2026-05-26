// test_functions.h

// Simple function
int add(int a, int b);

// Function with struct parameter
struct Point;
void print_point(struct Point p);

// Function with pointer
void modify_point(struct Point *p, int dx, int dy);

// Global variable
extern int global_counter;

// Constant
#define MAX_SIZE 100
#define PI 3.14159

// Function pointer typedef
typedef int (*Comparator)(const void*, const void*);
