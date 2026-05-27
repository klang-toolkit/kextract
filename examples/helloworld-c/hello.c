#include <stdio.h>
#include "hello.h"

void hello(void) {
    printf("Hello from C via Panama FFI!\n");
}

int add(int a, int b) {
    return a + b;
}
