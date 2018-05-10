package com.vj.training;

/**
 * Calculator class
 * @author vj
 *
 */
public class Calculator {
	
	/**
	 * Method to add 2 integers
	 * @param a The first input
	 * @param b The second input, to be added to the first input
	 * @return The sum of the 2 integers
	 */
	public int add(int a, int b) {
		return a+b;
	}
	
	/**
	 * Method to subtract 2 integers
	 * @param a The first input
	 * @param b The second input, to be subtracted from the first input
	 * @return The difference between the 2 integers
	 */
	public int subtract(int a, int b) {
		return a-b;
	}
	
	/**
	 * Method to multiply 2 integers
	 * @param a The first input
	 * @param b The second input, to be multiplied with the first input
	 * @return The product of the 2 integers
	 */
	public int multiply(int a, int b) {
		return a*b;
	}
	
	/**
	 * Method to divide 2 integers
	 * @param a The numerator
	 * @param b The denominator
	 * @return The result of dividing the first integer by the second
	 */
	public int divide(int a,int b) {
		return a/b;
	}
	
	// The code below exists purely to demonstrate how SonarQube points out deficiencies in your code
	
	private String field;
	
	public void use() {
		System.exit(33);
	}
	
	public void useFieldForLcom4() {
		System.out.println(field);
	}
}