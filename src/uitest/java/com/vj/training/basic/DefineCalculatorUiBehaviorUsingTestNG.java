package com.vj.training.basic;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.vj.training.support.Browser;
import com.vj.training.support.Util;
import com.vj.training.support.WebDriverFactory;

import static org.assertj.core.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * TestNG based Test class for the Calculator UI
 * @author vj
 */
public class DefineCalculatorUiBehaviorUsingTestNG {
	
	private WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		// To start with, instantiate the WebDriver object...
		// ... depending on the browser you choose for your tests
		// In this example, we will use the Chrome browser
		
		// Note the use of the "Factory" design pattern...
		// ...to simplify the creation of the WebDriver object
		// In a nutshell, a factory class is used to "manufacture" specific objects...
		// ...depending on the user's requirements
		// In this example, the WebDriverFactory "manufactures" the WebDriver object...
		// ...depending on the choice of browser specified
		
		// Also note the usage of Java enumerations...
		// ...to specify the list of supported browsers
		driver = WebDriverFactory.getDriver(Browser.CHROME);
		driver.manage().window().maximize();
		
		// Use the "get()" function to navigate to the application under test
		driver.get("http://web2.0calc.com/");
	}
	
	@Test
	public void sumOfTwoPositiveNumbersShouldBePositive() {
		int num1 = 5;
		int num2 = 6;
		
		// Use WebDriver's "WebElement" object to work with individual UI objects as required
		// A WebElement is identified using the "findElement()" function...
		// ...used in conjunction with a suitable locator (ID, Name, XPath, etc.)
		// It is a good idea to add a prefix/suffix to your WebElement object's name...
		// ...to indicate the kind of UI element that you are interacting with
		WebElement num1Btn = driver.findElement(By.id("Btn" + num1));
		num1Btn.click();
		
		// It is possible to "chain" together the 2 lines of code shown above...
		// ... into a single line for improved readability (see below)
		driver.findElement(By.id("BtnPlus")).click();
		driver.findElement(By.id("Btn" + num2)).click();
		driver.findElement(By.id("BtnCalc")).click();
		Util.waitFor(1000);
		
		// WebDriver provides special API's to work with UI elements such as drop-down lists, radio-buttons, etc.
		// Use these API's when the generic WebElement API does not cut it (code sample below)
		//Select myDropDownList = new Select(driver.findElement(By.id("mydropdownlist")));
		//myDropDownList.selectByIndex(1);
		
		// Use TestNG's Reporter.log() method to send custom messages to the test results
		// You can include HTML tags if required for additional formatting
		Reporter.log("Adding 2 positive numbers: " + num1 + ", " + num2 + "<br>");
		
		int sum = Integer.parseInt(driver.findElement(By.id("input")).getAttribute("value"));
		Reporter.log("Actual result: " + sum + "<br>");
		
		assertThat(num1).as("num1 should be positive").isGreaterThan(0);
		assertThat(num2).as("num2 should be positive").isGreaterThan(0);
		assertThat(sum).as("Sum of num1 and num2 should be positive").isGreaterThan(0);
		assertThat(sum).as("Sum of num1 and num2 should be accurate").isEqualTo(11);
	}
	
	@Test
	public void sumOfTwoNegativeNumbersShouldBeNegative() {
		int num1 = -5;
		int num2 = -6;
		
		driver.findElement(By.id("BtnMinus")).click();
		driver.findElement(By.id("Btn" + Math.abs(num1))).click();
		driver.findElement(By.id("BtnPlus")).click();
		driver.findElement(By.id("BtnMinus")).click();
		driver.findElement(By.id("Btn" + Math.abs(num2))).click();
		driver.findElement(By.id("BtnCalc")).click();
		Util.waitFor(1000);
		Reporter.log("Adding 2 negative numbers: " + num1 + ", " + num2 + "<br>");
		
		int sum = Integer.parseInt(driver.findElement(By.id("input")).getAttribute("value"));
		Reporter.log("Actual result: " + sum + "<br>");
		
		assertThat(num1).as("num1 should be negative").isLessThan(0);
		assertThat(num2).as("num2 should be negative").isLessThan(0);
		assertThat(sum).as("Sum of num1 and num2 should be negative").isLessThan(0);
		assertThat(sum).as("Sum of num1 and num2 should be accurate").isEqualTo(-11);
	}
	
	@Test
	public void subtractingSmallNumFromBigNumShouldReturnPositive() {
		int bigNum = 7;
		int smallNum = 4;
		
		driver.findElement(By.id("Btn" + bigNum)).click();
		driver.findElement(By.id("BtnMinus")).click();
		driver.findElement(By.id("Btn" + smallNum)).click();
		driver.findElement(By.id("BtnCalc")).click();
		Util.waitFor(1000);
		Reporter.log("Subtracting small number from big number: " + bigNum + ", " + smallNum + "<br>");
		
		int diff = Integer.parseInt(driver.findElement(By.id("input")).getAttribute("value"));
		Reporter.log("Actual result: " + diff + "<br>");
		
		assertThat(bigNum).as("num1 should be greater than num2").isGreaterThan(smallNum);
		assertThat(diff).as("Difference between num1 and num2 should be positive").isGreaterThan(0);
		assertThat(diff).as("Difference between num1 and num2 should be accurate").isEqualTo(3);
	}
	
	@Test
	public void divisionByZeroShouldThrowArithmeticException() {
		driver.findElement(By.id("Btn5")).click();
		driver.findElement(By.id("BtnDiv")).click();
		driver.findElement(By.id("Btn0")).click();
		driver.findElement(By.id("BtnCalc")).click();
		Util.waitFor(1000);
		Reporter.log("Dividing a number by zero<br>");
		
		String errorMessage = driver.findElement(By.id("input")).getAttribute("value");
		Reporter.log("Actual result: " + errorMessage + "<br>");
		
		assertThat(errorMessage).as("Division by zero should throw an error").isEqualTo("Error: DivByZero");
	}
	
	@AfterMethod
	public void cleanUp() {
		// Use the "quit()" function to close the browser once your test is complete
		driver.quit();
	}
}