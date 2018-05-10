Feature: Division
	As a user of the calculator application, I want to be able to divide two numbers
	and view the result
	
Background:
	Given I am a valid user
	
Scenario: Division of two positive numbers
	When I divide two positive numbers 8 and 4
	Then The result should be positive 
	And The result should be equal to 2
	
Scenario: Division by zero
	When I divide any number by zero
	Then The application should throw an exception