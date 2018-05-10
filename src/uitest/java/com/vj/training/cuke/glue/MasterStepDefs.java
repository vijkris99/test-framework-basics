package com.vj.training.cuke.glue;

import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;

/**
 * Master step definitions class containing global variables shared across multiple steps
 * @author vj
 */
public abstract class MasterStepDefs {
	protected static WebDriver driver;
	protected static Scenario currentScenario;
}