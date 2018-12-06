
strProgramName = "firefox.exe"

Dim objshell
Set objshell=CreateObject("WScript.Shell")
objshell.Run "TASKKILL /F /IM "& strProgramName
Set objshell=nothing