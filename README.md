# SeleniumWithMaven

This is a basic selenium framework that uses Maven and testNG as the core test framework

Tech used:

Selenium Webdriver

TestNG

ExtentReport

Log4j


#To run the test simply use :

mvn clean test -Dxml=src\test\resources\TestSuites\SmokeTest\SmokeTestLocal.xml

If you are setting up jenkins to run on different server, you can use SmokeTestGrid.xml and make the necessary changes into Driver Class
to run on you configured server URL where the GRID is configured

Happy Testing :)
