package com.vj.training.cuke.glue;

import org.openqa.selenium.By;

import com.vj.training.support.Util;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.*;

/**
 * Cucumber step definitions for the Calculator UI tests
 * @author vj
 */
public class CalculatorStepDefs extends MasterStepDefs {
	
	private int result;
	private String errorMessage;
	
	@Given("^I am a valid user$")
	public void i_am_a_valid_user() {
		driver.get("http://web2.0calc.com/");
		
		/*if(driver.findElement(By.cssSelector("button.close")).isDisplayed()) {
			driver.findElement(By.cssSelector("button.close")).click();
		}*/
		
		Boolean validUser = true;
		assertTrue(validUser);
	}
	
	@When("^I add two positive numbers (.*) and (.*)$")
	public void i_add_two_positive_numbers(int a, int b) {
		assertTrue(a>0);
		assertTrue(b>0);
		
		driver.findElement(By.id("Btn" + a)).click();
		driver.findElement(By.id("BtnPlus")).click();
		driver.findElement(By.id("Btn" + b)).click();
		driver.findElement(By.id("BtnCalc")).click();
		Util.waitFor(1000);
		result = Integer.parseInt(driver.findElement(By.id("input")).getAttribute("value"));
	}
	
	@When("^I add two negative numbers (.*) and (.*)$")
	public void i_add_two_negative_numbers(int a, int b) {
		assertTrue(a<0);
		assertTrue(b<0);
		
		driver.findElement(By.id("BtnMinus")).click();
		driver.findElement(By.id("Btn" + Math.abs(a))).click();
		driver.findElement(By.id("BtnPlus")).click();
		driver.findElement(By.id("BtnMinus")).click();
		driver.findElement(By.id("Btn" + Math.abs(b))).click();
		driver.findElement(By.id("BtnCalc")).click();
		Util.waitFor(1000);
		result = Integer.parseInt(driver.findElement(By.id("input")).getAttribute("value"));
	}
	
	@When("^I add a positive number (.*) and a negative number (.*)$")
	public void i_add_a_positive_number_and_a_negative_number(int a, int b) {
		assertTrue(a>0);
		assertTrue(b<0);
		
		driver.findElement(By.id("Btn" + a)).click();
		driver.findElement(By.id("BtnPlus")).click();
		driver.findElement(By.id("BtnMinus")).click();
		driver.findElement(By.id("Btn" + Math.abs(b))).click();
		driver.findElement(By.id("BtnCalc")).click();
		Util.waitFor(1000);
		result = Integer.parseInt(driver.findElement(By.id("input")).getAttribute("value"));
	}
	
	@When("^I divide two positive numbers (.*) and (.*)$")
	public void i_divide_two_positive_numbers(int a, int b) {
		driver.findElement(By.id("Btn" + a)).click();
		driver.findElement(By.id("BtnDiv")).click();
		driver.findElement(By.id("Btn" + b)).click();
		driver.findElement(By.id("BtnCalc")).click();
		Util.waitFor(1000);
		result = Integer.parseInt(driver.findElement(By.id("input")).getAttribute("value"));
	}
	
	@When("^I divide any number by zero$")
	public void i_divide_any_number_by_zero() {
		driver.findElement(By.id("Btn5")).click();
		driver.findElement(By.id("BtnDiv")).click();
		driver.findElement(By.id("Btn0")).click();
		driver.findElement(By.id("BtnCalc")).click();
		Util.waitFor(1000);
		errorMessage = driver.findElement(By.id("input")).getAttribute("value");
	}
	
	@And("^The positive number (.*) is smaller in value than the negative number (.*)$")
	public void positive_number_is_smaller_in_value_than_negative_number(int a, int b) {
		assertTrue(Math.abs(a) < Math.abs(b));
	}
	
	@And("^The positive number (.*) is larger in value than the negative number (.*)$")
	public void positive_number_is_larger_in_value_than_negative_number(int a, int b) {
		assertTrue(Math.abs(a) > Math.abs(b));
	}
	
	@Then("^The result should be positive")
	public void the_result_should_be_positive() {
		assertTrue(result>0);
	}
	
	@Then("^The result should be negative")
	public void the_result_should_be_negative() {
		assertTrue(result<0);
	}
	
	@Then("^The application should throw an exception$")
	public void the_application_should_throw_an_exception() {
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		
		assertTrue(errorMessage.equals("Error: DivByZero"));
	}
	
	@And("^The result should be equal to (.*)$")
	public void the_result_should_be_equal_to(int expectedSum) {
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		
		assertEquals(expectedSum, result);
	}
}