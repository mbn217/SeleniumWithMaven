
	On Error Resume Next
	Dim objSysInfo, objUser
	Set objSysInfo = CreateObject("ADSystemInfo")
	' Currently logged in User
	Set objUser = GetObject("LDAP://" & objSysInfo.UserName)
	path ="C:\Users\" & objUser.givenName & "."& objUser.sn &"\workspace\projectName\ExtentReport\reportDemo.html"   
   
   
   Set objOutlook = CreateObject("Outlook.Application")
   Set objMail = objOutlook.CreateItem(0)
   objMail.Display   'To display message
   objMail.Recipients.Add ("dummy@dummy.com")
   'objMail.Recipients.Add ("dummy@dummy.com")
   'objMail.Recipients.Add ("dummy@dummy.com")
   objMail.Subject = "Mail Subject"
   objMail.Body = "This is Email Body"
   objMail.Attachments.Add(path)   'Make sure attachment exists at given path. Then uncomment this line.
   objMail.Send   'I intentionally commented this line
   'objOutlook.Quit
   'Set objMail = Nothing
   'Set objOutlook = Nothing