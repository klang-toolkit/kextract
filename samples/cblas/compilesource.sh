OPENBLAS_HOME=/usr/local/opt/openblas

kextract --output src -D FORCE_OPENBLAS_COMPLEX_STRUCT \
  -l :${OPENBLAS_HOME}/lib/libopenblas.dylib -t blas \
  ${OPENBLAS_HOME}/include/cblas.h

javac --source=22 -d . src/blas/*.java
