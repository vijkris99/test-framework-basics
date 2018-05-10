package com.vj.training;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * TestNG based Test class for the Calculator class
 * @author vj
 */
public class TestCalculatorUsingTestNG {
	
	// Define the objects being tested
	// All test methods have access to these objects, since they are defined at the class level
	private Calculator calculator;
	
	
	// Use the @Before annotation to perform setup activities...
	// ...that need to executed before each test method
	// This means that if you have 100 test methods, this setup method gets executed 100 times
	// The most typical use of @Before is to initialize objects being tested
	// Note that the use of @Before is optional, but usually recommended
	@BeforeMethod
	public void setup() {
		calculator = new Calculator();
	}
	
	// Use the @Test annotation to define individual test methods (aka test cases)
	// Use test objects initialized by your @Before method
	// Use asserts to compare actual results with expected results, and report on failures
	// TestNG provides a wide variety of asserts to handle most test conditions
	@Test
	public void testAddTwoPositiveNums() {
		int sum = calculator.add(5, 6);
		Assert.assertEquals(sum, 11);
	}
	
	// It is possible to include any number of @Test methods in a single test class
	// You can execute individual test methods, or all test methods within a class -> as required
	@Test
	public void testAddTwoNegativeNums() {
		int sum = calculator.add(-5, -6);
		Assert.assertEquals(sum, -11);
	}
	
	@Test
	public void testSubtractTwoPositiveNums() {
		int diff = calculator.subtract(17, 4);
		Assert.assertEquals(diff, 13);
	}
	
	// Use the "expectedExceptions" attribute to validate that your test throws a specific exception
	// In this example, no additional asserts were required
	@Test(expectedExceptions = ArithmeticException.class)
	public void testDivisionByZero() {
		calculator.divide(5, 0);
	}
	
	@Test
	public void testEndToEnd() {
		int result = calculator.multiply(calculator.add(3, 4), calculator.divide(10, 2));
		Assert.assertEquals(result, 35);
	}
}