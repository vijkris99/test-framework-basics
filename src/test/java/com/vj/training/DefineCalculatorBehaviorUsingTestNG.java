package com.vj.training;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

// Static imports to improve readability of asserts and matchers
import static org.assertj.core.api.Assertions.*;

/**
 * Improved TestNG based Test class for the Calculator class
 * @author vj
 */
public class DefineCalculatorBehaviorUsingTestNG {
	private Calculator calculator;
	
	// Use the @BeforeSuite annotation to perform setup activities...
	// ...that need to executed only once for all test methods in this test suite
	// It is recommended to use @BeforeSuite to initialize objects that can be shared across all your test methods
	@BeforeSuite
	public void setup() {
		calculator = new Calculator();
	}
	
	// Note the improved readability of the test names in this class
	// This draws upon ideas from Behavior Driven Development (BDD):
	// 	Test names should be sentences
	// 	Get the words right -> Use "should" instead of "test"
	@Test
	public void sumOfTwoPositiveNumbersShouldBePositive() {
		int num1 = 5;
		int num2 = 6;
		
		int sum = calculator.add(num1, num2);
		
		// Another improvement inspired from BDD: "fluent" assertions using AssertJ
		// Use "assertThat", combined with matchers to greatly improve the readability of your assertions
		assertThat(num1).as("num1 should be positive").isGreaterThan(0);
		assertThat(num2).as("num2 should be positive").isGreaterThan(0);
		assertThat(sum).as("Sum of num1 and num2 should be positive").isGreaterThan(0);
		assertThat(sum).as("Sum of num1 and num2 should be accurate").isEqualTo(11);
	}
	
	@Test
	public void sumOfTwoNegativeNumbersShouldBeNegative() {
		int num1 = -5;
		int num2 = -6;
		
		int sum = calculator.add(num1, num2);
		
		// Note yet another improvement here: The usage of "static imports" (look at the top of this class)
		// This enables you to avoid specifying the class name for your asserts, further improving readability
		// This means that you can directly call the "assertThat()" method, instead of writing "Assertions.assertThat()"
		assertThat(num1).as("num1 should be negative").isLessThan(0);
		assertThat(num2).as("num2 should be negative").isLessThan(0);
		assertThat(sum).as("Sum of num1 and num2 should be negative").isLessThan(0);
		assertThat(sum).as("Sum of num1 and num2 should be accurate").isEqualTo(-11);
	}
	
	@Test
	public void subtractingSmallerNumFromBiggerNumShouldReturnPositiveNum() {
		int num1 = 17;
		int num2 = 4;
		
		int diff = calculator.subtract(17, 4);
		
		assertThat(num1).as("num1 should be greater than num2").isGreaterThan(num2);
		assertThat(diff).as("Difference between num1 and num2 should be positive").isGreaterThan(0);
		assertThat(diff).as("Difference between num1 and num2 should be accurate").isEqualTo(13);
	}
	
	@Test(expectedExceptions = ArithmeticException.class)
	public void divisionByZeroShouldThrowArithmeticException() {
		calculator.divide(5, 0);
	}
}