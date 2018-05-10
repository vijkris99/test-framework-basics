package com.vj.training;

/**
 * Test class from the stone age for the Calculator class
 * @author vj
 */
public class TestCalculatorFromStoneAge {
	private static Calculator calculator;
	
	// Note the use of a plain old main() method to setup and execute all your tests
	public static void main(String[] args) {
		// Initialize the objects being tested
		calculator = new Calculator();
		
		// Call all the test methods one after the other
		testAddTwoPositiveNums();
		testDivisionByZero();
	}
	
	private static void testAddTwoPositiveNums() {
		int sum = calculator.add(5, 6);
		
		// Use plain old conditional statements to validate conditions within your test
		if(sum == 11) {
			// Use plain old console output to report on test results
			System.out.println("Pass: Sum of 2 positive numbers calculated correctly");
		} else {
			System.out.println("Fail: Sum of 2 positive numbers not calculated correctly");
		}
	}
	
	private static void testDivisionByZero() {
		// Use plain old try-catch to test for exception conditions
		try {
			calculator.divide(5, 0);
			System.out.println("Fail: Division by zero does not result in arithmetic error!");
		} catch(ArithmeticException ex) {
			System.out.println("Pass: Division by zero results in arithmetic error");
		}
	}
}