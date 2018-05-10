package com.vj.training;

import org.junit.BeforeClass;
import org.junit.Test;

// Static imports to improve readability of asserts and matchers
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Improved JUnit based Test class for the Calculator class
 * @author vj
 */
public class DefineCalculatorBehaviorUsingJUnit {
	
	private static Calculator calculator;
	
	// Use the @BeforeClass annotation to perform setup activities...
	// ...that need to executed only once for all test methods in this class
	// It is recommended to use @BeforeClass to initialize objects that can be shared across all your test methods
	// Note that this requires the method as well as the objects initialized by it to be static
	@BeforeClass
	public static void setupClass() {
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
		
		// Another improvement inspired from BDD: "fluent" assertions
		// Use "assertThat", combined with Hamcrest matchers to greatly improve the readability of your assertions
		assertThat("num1 should be positive", num1, is(greaterThan(0)));
		assertThat("num2 should be positive", num2, greaterThan(0));
		assertThat("Sum of num1 and num2 should be positive", sum, greaterThan(0));
		assertThat("Sum of num1 and num2 should be accurate", sum, equalTo(11));
	}
	
	@Test
	public void sumOfTwoNegativeNumbersShouldBeNegative() {
		int num1 = -5;
		int num2 = -6;
		
		int sum = calculator.add(num1, num2);
		
		// Note yet another improvement here: The usage of "static imports" (look at the top of this class)
		// This enables you to avoid specifying the class name for your asserts, further improving readability
		// This means that you can directly call the "assertThat()" method, instead of writing "Assert.assertThat()"
		assertThat("num1 should be negative", num1, is(lessThan(0)));
		assertThat("num2 should be negative", num2, lessThan(0));
		assertThat("Sum of num1 and num2 should be negative", sum, lessThan(0));
		assertThat("Sum of num1 and num2 should be accurate", sum, is(-11));
	}
	
	@Test
	public void subtractingSmallNumFromBigNumShouldReturnPositive() {
		int num1 = 17;
		int num2 = 4;
		
		int diff = calculator.subtract(17, 4);
		
		assertThat("num1 should be greater than num2", num1, greaterThan(num2));
		assertThat("Difference between num1 and num2 should be positive", diff, greaterThan(0));
		assertThat("Difference between num1 and num2 should be accurate", diff, is(13));
	}
	
	@Test(expected = ArithmeticException.class)
	public void divisionByZeroShouldThrowArithmeticException() {
		calculator.divide(5, 0);
	}
}