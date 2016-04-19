package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class Extension extends WebDriverPage{
	
	public Extension(WebDriverProvider driverProvider) {
		super(driverProvider);		
	}

	public void open() {
		get("http://www.seleniumframework.com/add-extensions/");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

}
