Feature: Addition 
	As a user of the calculator application, I want to be able to add two numbers
	and view the result

Background:
	Given I am a valid user
	
Scenario Outline: Addition of two positive numbers 
	When I add two positive numbers <Number1> and <Number2>
	Then The result should be positive 
	And The result should be equal to <ExpectedResult>
	
	Examples:
	|Number1	|Number2	|ExpectedResult	|
	|5			|6			|11				|
	
Scenario: Addition of two negative numbers 
	When I add two negative numbers -5 and -6 
	Then The result should be negative 
	And The result should be equal to -11 
	
Scenario: Addition of a small positive number and a large negative number 
	When I add a positive number 5 and a negative number -6 
	And The positive number 5 is smaller in value than the negative number -6 
	Then The result should be negative 
	And The result should be equal to -1 
	
Scenario: Addition of a large positive number and a small negative number 
	When I add a positive number 6 and a negative number -5
	And The positive number 6 is larger in value than the negative number -5 
	Then The result should be positive 
	And The result should be equal to 1