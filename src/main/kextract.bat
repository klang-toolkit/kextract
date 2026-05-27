@set ROOT=%~dp0..
@set LIBS=%ROOT%\lib\org.graphiks.kextract.jar
@for %%f in ("%ROOT%\lib\*.jar") do @if not "%%~nf"=="org.graphiks.kextract" @set LIBS=%LIBS%;%%f
@"%ROOT%\runtime\bin\java" %JEXTRACT_JAVA_OPTIONS% --enable-native-access=ALL-UNNAMED "-Djava.library.path=%ROOT%\lib" -cp "%LIBS%" org.graphiks.kextract.pipeline.KextractTool %*
