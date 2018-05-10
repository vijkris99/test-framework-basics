package com.vj.training.support;

/**
 * Enumeration to represent the browser to be used for execution
 * @author vj
 */
public enum Browser {
	CHROME("chrome"),
	CHROME_HEADLESS("chrome_headless"),
	EDGE("edge"),
	FIREFOX("marionette"),
	INTERNET_EXPLORER("internet explorer"),
	SAFARI("safari");
	
	private String value;
	
	Browser(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}