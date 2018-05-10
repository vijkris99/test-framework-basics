* This project includes sample code for white box as well as black box tests
* Start with the white box tests under "src/test/java":
	* Recommended sequence for learning: TestsFromStoneAge -> TestsUsingJUnit -> TestsUsingTestNG -> DefineCalculatorBehaviorUsingJUnit -> DefineCalculatorBehaviorUsingTestNG
* Once done with the unit tests, look at the browser based UI tests under "src/uitest/java":
	* This example uses Cucumber-JVM and Selenium-WebDriver
	* The Cucumber feature files (gherkin) are located under the "src/uitest/resources" folder
	* The webdriver browser drivers for Chrome and IE should also be located within the resources folder. Download them and place them under the "browser-drivers" folder within resources.
	* To execute the tests, use the JUnit or TestNG runner, under the "cuketests" package