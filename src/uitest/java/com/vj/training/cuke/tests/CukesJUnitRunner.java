package com.vj.training.cuke.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {
				"src/uitest/resources/cuke/features"
				},
		glue = {
				"com.vj.training.cuke.glue"
				},
		monochrome = true,	// "true" = better formatting of console output
		plugin = {
				"pretty",	// prints pretty report on command line
				"pretty:target/cuke-reports/pretty.txt",
				"html:target/cuke-reports/cucumber-htmlreport",
				"junit:target/cuke-reports/cucumber-junitreport.xml",
				"usage:target/cuke-reports/usage.json"
				},
		strict = true
		)
public class CukesJUnitRunner {
	
}