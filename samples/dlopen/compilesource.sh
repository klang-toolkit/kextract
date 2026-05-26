cc --shared -o libhello.dylib hello.c

kextract --output src -t org.unix "<dlfcn.h>"

javac --source=22 -d . src/org/unix/*.java
