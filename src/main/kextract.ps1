$ROOT = "$PSScriptRoot\.."
$LIBS = "$ROOT\lib\org.openjdk.kextract.jar"
$kotlinStdlib = Get-Item "$ROOT\lib\kotlin-stdlib-*.jar" -ErrorAction SilentlyContinue | Select-Object -First 1
if ($kotlinStdlib) { $LIBS = "$LIBS;$($kotlinStdlib.FullName)" }
& "$ROOT\runtime\bin\java" $Env:JEXTRACT_JAVA_OPTIONS --enable-native-access=ALL-UNNAMED "-Djava.library.path=$ROOT\lib" -cp $LIBS org.openjdk.kextract.newimpl.KextractTool $args
