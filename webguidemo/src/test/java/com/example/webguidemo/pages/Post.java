package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Post extends WebDriverPage {

    private final static String SUBMIT_BUTTON = "//*[@id=\"btnSubmit\"]";
    private final static String LOGOUT_BUTTON = "//*[@id=\"buttonLogout\"]";
    private final static String ERROR_MESSAGE = "#loginForm > section > fieldset.row.error > small";
    private final static String ERROR_LOGIN_MESSAGE = "#loginForm > div.szary.error > h1";
    private final static String LOGIN_USERNAME = "//*[@id=\"login\"]";
    private final static String LOGIN_PASSWORD = "//*[@id=\"password\"]";

    public Post(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void open() {
        get("http://www.poczta.wp.pl");
        manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void clickSubmit() {
        findElement(By.xpath(SUBMIT_BUTTON)).click();
    }

    public String errorMessage() {
        return findElement(By.cssSelector(ERROR_MESSAGE)).getText();
    }
    
    public String errorLoginMessage() {
        return findElement(By.cssSelector(ERROR_LOGIN_MESSAGE)).getText();
    }

    public WebElement findLoginField() {
        return findElement(By.xpath(LOGIN_USERNAME));
    }
    
    public WebElement findPasswordField() {
        return findElement(By.xpath(LOGIN_PASSWORD));
    }
    
    public void clickLogOut() {
        findElement(By.xpath(LOGOUT_BUTTON)).click();
    }
}
