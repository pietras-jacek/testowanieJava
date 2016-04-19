package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class Home extends WebDriverPage {

	public Home(WebDriverProvider driverProvider) {
		super(driverProvider);
	}

	private final static String SPORT_LINK = "//*[@id='main-menu']/a[4]";
	private final static String EXTENSION_LINK_TEXT = "Add Extensions";

	
	public void open() {
		get("http://www.seleniumframework.com/Practiceform/");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void clickExtensionLink(){
		//findElement(By.xpath(SPORT_LINK)).click();
		findElement(By.linkText(EXTENSION_LINK_TEXT)).click();
	}
	
	public void textAreaCheck() {
		findElement(By.id("vfb-9")).sendKeys("");
	}
	
	public void radioButtonCheck() {
		findElement(By.id("vfb-7-1")).click();
	}
	
}
