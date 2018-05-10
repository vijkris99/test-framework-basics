@Echo off
REM Use the command below to see a complete list of options that can be specified while running the Selenium server from the command line
REM This includes options for running Selenium as a Selenium RC server, as a RemoteWebDriver server, or as a Selenium Grid hub/node
REM java -jar "H:\Javalibs\Selenium\selenium-server-standalone-2.48.2.jar" -help

REM Use the command below to explicitly specify the path of the browser driver exes by setting the corresponding system properties using the -D switch
REM java -Dwebdriver.ie.driver="H:\\Javalibs\\Selenium\\Browser Drivers\\IEDriverServer.exe" -Dwebdriver.chrome.driver="H:\\Javalibs\\Selenium\\Browser Drivers\\chromedriver.exe" -jar "H:\Javalibs\Selenium\selenium-server-standalone-2.48.2.jar"
REM Note that any other system properties can also be specified by using the -D switch if required

REM Use the command below if the location of the browser driver exes are included in the PATH environment variable
@Echo on
java -jar "H:\Javalibs\Selenium\selenium-server-standalone-2.48.2.jar"