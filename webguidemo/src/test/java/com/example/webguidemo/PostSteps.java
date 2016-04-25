package com.example.webguidemo;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;

import static org.junit.Assert.*;
import org.openqa.selenium.WebElement;

public class PostSteps {

    private final Pages pages;
    WebElement element;

    public PostSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("user is on Home page")
    public void userIsOnHomePage() {
        pages.home().open();
    }

    @When("user opens a post link")
    public void userClicksPostLink() {
        pages.post().open();
    }

    @Then("post page is shown")
    public void postPageIsShown() {
        assertEquals("Poczta - Najlepsza Poczta, największe załączniki - WP", pages.post().getTitle());
    }

    @When("user clicks log in with blank inputs")
    public void userClicksEmptyLogIn() {
        pages.post().clickSubmit();
    }

    @When("user type $wrong_login and $wrong_password in the input")
    public void invalidLoginAndPassword(String wrong_login, String wrong_password) {
        element = pages.post().findLoginField();
        element.sendKeys(wrong_login);
        element = pages.post().findPasswordField();
        element.sendKeys(wrong_password);
        pages.post().clickSubmit();
    }

    @Then("error is shown $error")
    public void postPageErrorIsShown(String error) {
        assertEquals(error, pages.post().errorMessage());
    }

    @When("user write $correct_login and $correct_password in the input")
    public void validLoginAndPassword(String correct_login, String correct_password) {
        element = pages.post().findLoginField();
        element.clear();
        element.sendKeys(correct_login);
        element = pages.post().findPasswordField();
        element.clear();
        element.sendKeys(correct_password);
        pages.post().clickSubmit();
    }

    @Then("Post box page is shown")
    public void postBoxPageIsShown() {
        assertEquals("PocztaWP - 1 nowych", pages.post().getTitle());
    }

    @Then("error in log in page shown $error_message")
    public void postLoginPageErrorIsShown(String error_message) {
        assertEquals(error_message, pages.post().errorLoginMessage());
    }
}
