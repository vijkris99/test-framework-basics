package com.vj.training.basic;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.vj.training.support.Browser;
import com.vj.training.support.Util;
import com.vj.training.support.WebDriverFactory;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * TestNG based Test class for the Calculator UI
 * @author vj
 */
public class DefineCalculatorUiBehaviorUsingTestNGAdvanced {
	
	// Use ThreadLocal to enable running multiple tests in parallel...
	//...while avoiding sharing of the same driver object across threads
	private ThreadLocal<WebDriver> localDriver = new ThreadLocal<WebDriver>();
	
	@BeforeMethod
	public void setup() {
		WebDriver driver = WebDriverFactory.getDriver(Browser.CHROME);
		//WebDriver driver = WebDriverFactory.getDriver(Browser.CHROME_HEADLESS);
		
		// Use the set method to store the initialized WebDriver object in the ThreadLocal instance
		localDriver.set(driver);
		
		// Use implicit waits to set global object timeouts for the entire test script
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		driver.get("http://web2.0calc.com/");
	}
	
	@Test
	public void sumOfTwoPositiveNumbersShouldBePositive() throws IOException {
		int num1 = 5;
		int num2 = 6;
		
		// Use the get method to retrieve the initialized WebDriver object from the ThreadLocal instance
		WebDriver driver = localDriver.get();
		
		driver.findElement(By.id("Btn" + num1)).click();
		driver.findElement(By.id("BtnPlus")).click();
		driver.findElement(By.id("Btn" + num2)).click();
		driver.findElement(By.id("BtnCalc")).click();
		Util.waitFor(1000);
		Reporter.log("Adding 2 positive numbers: " + num1 + ", " + num2 + "<br>");
		
		int sum = Integer.parseInt(driver.findElement(By.id("input")).getAttribute("value"));
		Reporter.log("Actual result: " + sum + "<br>");
		
		// Cast the driver object to the "TakesScreenshot" object to capture screenshots during the test execution
		TakesScreenshot screenshotCaptureDriver = (TakesScreenshot) driver;
		File screenshot = screenshotCaptureDriver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("test-output//screenshots//screenshot1.png"), true);
		
		// It is possible to embed such screenshots directly into the TestNG report, as shown below
		Reporter.log("<img src='screenshots//screenshot1.png'>");
		
		assertThat(num1).as("num1 should be positive").isGreaterThan(0);
		assertThat(num2).as("num2 should be positive").isGreaterThan(0);
		assertThat(sum).as("Sum of num1 and num2 should be positive").isGreaterThan(0);
		assertThat(sum).as("Sum of num1 and num2 should be accurate").isEqualTo(11);
	}
	
	@Test
	public void sumOfTwoNegativeNumbersShouldBeNegative() {
		int num1 = -5;
		int num2 = -6;
		
		WebDriver driver = localDriver.get();
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
		
		captureScreenshot(driver, "screenshot2.png");
		Reporter.log("<img src='screenshots//screenshot2.png'>");
		
		assertThat(num1).as("num1 should be negative").isLessThan(0);
		assertThat(num2).as("num2 should be negative").isLessThan(0);
		assertThat(sum).as("Sum of num1 and num2 should be negative").isLessThan(0);
		assertThat(sum).as("Sum of num1 and num2 should be accurate").isEqualTo(-11);
	}
	
	private void captureScreenshot(WebDriver driver, String fileName) {
		TakesScreenshot screenshotCaptureDriver = (TakesScreenshot) driver;
		File screenshot = screenshotCaptureDriver.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("test-output//screenshots//" + fileName), true);
		} catch (IOException e) {
			Reporter.log("Error while copying screenshot file!");
			e.printStackTrace();
		}
	}
	
	@Test
	public void subtractingSmallNumFromBigNumShouldReturnPositive() {
		int bigNum = 7;
		int smallNum = 4;
		
		WebDriver driver = localDriver.get();
		driver.findElement(By.id("Btn" + bigNum)).click();
		driver.findElement(By.id("BtnMinus")).click();
		driver.findElement(By.id("Btn" + smallNum)).click();
		driver.findElement(By.id("BtnCalc")).click();
		Util.waitFor(1000);
		Reporter.log("Subtracting small number from big number: " + bigNum + ", " + smallNum + "<br>");
		
		int diff = Integer.parseInt(driver.findElement(By.id("input")).getAttribute("value"));
		Reporter.log("Actual result: " + diff + "<br>");
		
		captureScreenshot(driver, "screenshot3.png");
		Reporter.log("<img src='screenshots//screenshot3.png'>");
		
		assertThat(bigNum).as("num1 should be greater than num2").isGreaterThan(smallNum);
		assertThat(diff).as("Difference between num1 and num2 should be positive").isGreaterThan(0);
		assertThat(diff).as("Difference between num1 and num2 should be accurate").isEqualTo(3);
	}
	
	@Test
	public void divisionByZeroShouldThrowArithmeticException() {
		WebDriver driver = localDriver.get();
		driver.findElement(By.id("Btn5")).click();
		driver.findElement(By.id("BtnDiv")).click();
		driver.findElement(By.id("Btn0")).click();
		driver.findElement(By.id("BtnCalc")).click();
		Util.waitFor(1000);
		Reporter.log("Dividing a number by zero<br>");
		
		String errorMessage = driver.findElement(By.id("input")).getAttribute("value");
		Reporter.log("Actual result: " + errorMessage + "<br>");
		
		captureScreenshot(driver, "screenshot4.png");
		Reporter.log("<img src='screenshots//screenshot4.png'>");
		
		assertThat(errorMessage).as("Division by zero should throw an error").isEqualTo("Error: DivByZero");
	}
	
	@AfterMethod
	public void cleanUp() {
		WebDriver driver = localDriver.get();
		driver.quit();
	}
}