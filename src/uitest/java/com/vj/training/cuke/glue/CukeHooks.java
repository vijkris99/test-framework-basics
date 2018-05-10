package com.vj.training.cuke.glue;

import com.vj.training.support.Browser;
import com.vj.training.support.WebDriverFactory;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Cucumber hooks for the Calculator UI tests
 * @author vj
 */
public class CukeHooks extends MasterStepDefs {
	
	@Before
	public void setUp(Scenario scenario) {
		currentScenario = scenario;
		driver = WebDriverFactory.getDriver(Browser.CHROME);
	}
	
	@After
	public void closeBrowser() {
		driver.quit();
	}
}