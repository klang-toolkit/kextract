kextract --output src -t org.kextract -lcurl "<curl/curl.h>"

javac --source=22 -d . src/org/kextract/*.java
