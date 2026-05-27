$ROOT = "$PSScriptRoot\.."
$LIBS = "$ROOT\lib\org.graphiks.kextract.jar"
Get-Item "$ROOT\lib\*.jar" -ErrorAction SilentlyContinue |
    Where-Object { $_.BaseName -ne "org.graphiks.kextract" } |
    ForEach-Object { $LIBS = "$LIBS;$($_.FullName)" }
& "$ROOT\runtime\bin\java" $Env:JEXTRACT_JAVA_OPTIONS --enable-native-access=ALL-UNNAMED "-Djava.library.path=$ROOT\lib" -cp $LIBS org.graphiks.kextract.pipeline.KextractTool $args
