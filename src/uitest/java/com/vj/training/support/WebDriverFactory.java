package com.vj.training.support;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


/**
 * Factory class for creating the {@link WebDriver} object as required
 * @author vj
 */
public class WebDriverFactory {
	
	private WebDriverFactory() {
		// To prevent external instantiation of this class
	}
	
	/**
	 * Function to initialize the {@link WebDriver} object based on the {@link Browser} required
	 * @param browser The {@link Browser} required
	 * @return The {@link WebDriver} object
	 */
	public static WebDriver getDriver(Browser browser) {
		WebDriver driver;
		
		switch(browser) {
		case CHROME:
			System.setProperty("webdriver.chrome.driver", "src\\uitest\\resources\\browser-drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
			
		case CHROME_HEADLESS:
			System.setProperty("webdriver.chrome.driver", "src\\uitest\\resources\\browser-drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
	        options.addArguments("headless");
	        options.addArguments("window-size=1200x600");	// Seems to be required
	        //options.addArguments("disable-gpu");	// Does not seem to be required anymore
	        //options.addArguments("remote-debugging-port=9222");	// Does not work
			driver = new ChromeDriver(options);
			break;
			
		case EDGE:
			System.setProperty("webdriver.edge.driver", "src\\uitest\\resources\\browser-drivers\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			break;
			
		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", "src\\uitest\\resources\\browser-drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
			
		case INTERNET_EXPLORER:
			System.setProperty("webdriver.ie.driver", "src\\uitest\\resources\\browser-drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
			
		case SAFARI:
			driver = new SafariDriver();
			break;
			
		default:
			throw new RuntimeException("Unhandled browser!");
		}
		
		driver.manage().window().maximize();
		return driver;
	}
	
	/**
	 * Function to return the {@link RemoteWebDriver} object based on the parameters passed
	 * @param browser The {@link Browser} to be used for the test execution
	 * @param browserVersion The browser version to be used for the test execution (Specify null to skip)
	 * @param platform The {@link Platform} to be used for the test execution (Specify null to skip)
	 * @param remoteUrl The URL of the remote webdriver server to be used for the test execution
	 * @return The corresponding {@link RemoteWebDriver} object
	 */
	public static WebDriver getRemoteWebDriver(Browser browser, String browserVersion,
												Platform platform, String remoteUrl) {
		// For running RemoteWebDriver tests in Chrome and IE:
		// The browser driver exes need to be in the PATH of the remote machine
		// To set the executable path manually, use the "-D" switch:
		// java -Dwebdriver.chrome.driver=/path/to/driver -jar selenium-server-standalone.jar
		// java -Dwebdriver.ie.driver=/path/to/driver -jar selenium-server-standalone.jar
		
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		
		desiredCapabilities.setBrowserName(browser.getValue());
		if (browserVersion != null) {
			desiredCapabilities.setVersion(browserVersion);
		}
		if (platform != null) {
			desiredCapabilities.setPlatform(platform);
		}
		desiredCapabilities.setJavascriptEnabled(true);	// Pre-requisite for remote execution
		
		URL url;
		try {
			url = new URL(remoteUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return new RemoteWebDriver(url, desiredCapabilities);
	}
}