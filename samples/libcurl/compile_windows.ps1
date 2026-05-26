param(
  [Parameter(Mandatory=$true, HelpMessage="The path to the lib curl installation")]
  [string]$curlpath
)

kextract `
  -I "$curlpath\include" `
  -I "$curlpath\include\curl" `
  --dump-includes 'includes_all.conf' `
  '<curl.h>'
  
Select-String -Path 'includes_all.conf' -Pattern '(curl|sockaddr )' | %{ $_.Line } | Out-File -FilePath 'includes_filtered.conf' -Encoding ascii

kextract `
  --output src `
  -t org.kextract `
  -I "$curlpath\include" `
  -I "$curlpath\include\curl" `
  -l libcurl `
  --use-system-load-library `
  '@includes_filtered.conf' `
  '<curl.h>'

javac -d classes (ls -r src/*.java)
