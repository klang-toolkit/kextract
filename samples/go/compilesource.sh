go build -o libhello.dylib  -buildmode=c-shared
kextract -l hello -t org.golang libhello.h
javac --source=22 org/golang/*.java
