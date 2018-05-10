package com.vj.training.basic;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vj.training.support.Util;

/**
 * JUnit based Test class for the Calculator UI
 * @author vj
 */
public class DefineCalculatorUiBehaviorUsingJUnit {
	
	// The WebDriver object that will be used to drive the browser
	private WebDriver driver;
	
	@Before
	public void setup() {
		// To start with, instantiate the WebDriver object using the "new" keyword...
		// ... depending on the browser you choose for your tests
		// In this example, we will use the Chrome browser
		// Note that we need to set a system property...
		// ...that points to the location of the corresponding driver executable...
		// ...BEFORE we instantiate the WebDriver object
		System.setProperty("webdriver.chrome.driver",
							"src\\uitest\\resources\\browser-drivers\\chromedriver.exe");
		driver = new ChromeDriver();
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
		int sum = Integer.parseInt(driver.findElement(By.id("input")).getAttribute("value"));
		
		assertThat("num1 should be positive", num1, is(greaterThan(0)));
		assertThat("num2 should be positive", num2, greaterThan(0));
		assertThat("Sum of num1 and num2 should be positive", sum, greaterThan(0));
		assertThat("Sum of num1 and num2 should be accurate", sum, equalTo(11));
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
		int sum = Integer.parseInt(driver.findElement(By.id("input")).getAttribute("value"));
		
		assertThat("num1 should be negative", num1, is(lessThan(0)));
		assertThat("num2 should be negative", num2, lessThan(0));
		assertThat("Sum of num1 and num2 should be negative", sum, lessThan(0));
		assertThat("Sum of num1 and num2 should be accurate", sum, is(-11));
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
		int diff = Integer.parseInt(driver.findElement(By.id("input")).getAttribute("value"));
		
		assertThat("num1 should be greater than num2", bigNum, greaterThan(smallNum));
		assertThat("Difference between num1 and num2 should be positive", diff, greaterThan(0));
		assertThat("Difference between num1 and num2 should be accurate", diff, is(3));
	}
	
	@Test
	public void divisionByZeroShouldThrowArithmeticException() {
		driver.findElement(By.id("Btn5")).click();
		driver.findElement(By.id("BtnDiv")).click();
		driver.findElement(By.id("Btn0")).click();
		driver.findElement(By.id("BtnCalc")).click();
		Util.waitFor(1000);
		
		String errorMessage = driver.findElement(By.id("input")).getAttribute("value");
		assertThat("Division by zero should throw an error",
											errorMessage, equalTo("Error: DivByZero"));
	}
	
	@After
	public void cleanUp() {
		// Use the "quit()" function to close the browser once your test is complete
		driver.quit();
	}
}