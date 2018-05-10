package com.vj.training.support;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Class to encapsulate utility functions of the framework
 * @author vj
 */
public class Util {
	private Util() {
		// To prevent external instantiation of this class
	}
	
	/**
	 * Function to get the separator string to be used for directories and files based on the current OS
	 * @return The file separator string
	 */
	public static String getFileSeparator() {
		return System.getProperty("file.separator");
		// change!
		// someone else's change
		// another change!!
	}
	
	/**
	 * Function to select the specified value from a listbox
	 * @param driver The {@link WebDriver} object
	 * @param by The {@link WebDriver} locator used to identify the listbox
	 * @param itemText The value to be selected within the listbox
	 */
	public void selectListItem(WebDriver driver, By by, String itemText) {
		Select dropDownList = new Select(driver.findElement(by));
		dropDownList.selectByVisibleText(itemText);
	}
	
	/**
	 * Function to select the specified value from a listbox
	 * @param driver The {@link WebDriver} object
	 * @param by The {@link WebDriver} locator used to identify the listbox
	 * @param itemIndex The index of the value to be selected within the listbox
	 */
	public void selectListItem(WebDriver driver, By by, int itemIndex) {
		Select dropDownList = new Select(driver.findElement(by));
		dropDownList.selectByIndex(itemIndex);
	}
	
	/**
	 * Function to take a screenshot of the current page
	 * @param driver The {@link WebDriver} object
	 * @return A byte array corresponding to the screenshot taken
	 */
	public static byte[] takeScreenshot(WebDriver driver) {
		if (driver == null) {
			throw new RuntimeException("Report.driver is not initialized!");
		}
		
		if (driver.getClass().getSimpleName().equals("HtmlUnitDriver") || 
			driver.getClass().getGenericSuperclass().toString().equals("class org.openqa.selenium.htmlunit.HtmlUnitDriver")) {
			return null;	// Screenshots not supported in headless mode
		}
		
		if (driver.getClass().getSimpleName().equals("RemoteWebDriver")) {
			Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
			if (capabilities.getBrowserName().equals("htmlunit")) {
				return null;	// Screenshots not supported in headless mode
			}
			WebDriver augmentedDriver = new Augmenter().augment(driver);
	        return ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
		} else {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		}
	}
	
	/**
	 * Function to wait for the specified period of time
	 * @param milliSeconds The wait period (in milliseconds)
	 */
	public static void waitFor(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Function to wait until the specified element is located
	 * @param driver The {@link WebDriver} object
	 * @param by The {@link WebDriver} locator used to identify the element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilElementLocated(WebDriver driver, By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds))
							.until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	/**
	 * Function to wait until the specified element is visible
	 * @param driver The {@link WebDriver} object
	 * @param by The {@link WebDriver} locator used to identify the element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilElementVisible(WebDriver driver, By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds))
							.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	/**
	 * Function to switch to the most recently opened pop-up window
	 * @param driver The {@link WebDriver} object
	 * @param nPopupsAlreadyOpen The number of pop-ups which are already open
	 * @param timeOutInSeconds The number of seconds to wait for the pop-up window to open and load
	 * @return The window handle of the parent window
	 */
	public String switchToPopup(WebDriver driver, int nPopupsAlreadyOpen, long timeOutInSeconds) {
		String mainWindowHandle = driver.getWindowHandle();
		String popupWindowHandle = getPopupWindowHandle(driver, nPopupsAlreadyOpen, timeOutInSeconds);
		driver.switchTo().window(popupWindowHandle);
		
		return mainWindowHandle;
	}
	
	private String getPopupWindowHandle(WebDriver driver, int nPopupsAlreadyOpen, long timeOutInSeconds) {
		Object[] openWindowHandles = driver.getWindowHandles().toArray();
		int milliSecondsWaited = 0;
		while(milliSecondsWaited < timeOutInSeconds*1000) {
			if(openWindowHandles.length > nPopupsAlreadyOpen+1) {
				break;
			} else {
				waitFor(100);
				milliSecondsWaited += 100;
				openWindowHandles = driver.getWindowHandles().toArray();
			}
		}
		if(openWindowHandles.length < nPopupsAlreadyOpen+2) {
			throw new RuntimeException("The pop-up window did not open as expected!");
		}
		
		return openWindowHandles[openWindowHandles.length - 1].toString();
	}
	
	/**
	 * Function to do a mouseover on top of the specified element
	 * @param driver The {@link WebDriver} object
	 * @param by The {@link WebDriver} locator used to identify the element
	 */
	public void mouseOver(WebDriver driver, By by) {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(by)).build().perform();
	}
}