kextract --dump-includes includes.txt \
  -t opengl \
  --framework GLUT \
  --framework OpenGL \
  "<GLUT/glut.h>"

kextract -t opengl \
  --framework GLUT \
  --framework OpenGL \
  "<GLUT/glut.h>"

javac --release 24 \
      -d target/generatedclasses \
      opengl/*.java

javac --release 24 \
      -cp target/generatedclasses \
      -d target/clientClasses \
      Teapot.java

java --enable-preview \
      --source 24 \
      NativeSymbolLister.java \
      target/clientClasses \
      used.txt

grep -Ff used.txt includes.txt > includes-filtered.txt

rm -rf target opengl

kextract @includes-filtered.txt -t opengl --framework GLUT \
  --framework OpenGL \
  "<GLUT/glut.h>"

javac --source=24 -d . opengl/*.java
