package com.example.webguidemo;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;

import static org.junit.Assert.*;

public class TelemanSteps {
	
	private final Pages pages;

	public TelemanSteps(Pages pages) {
		this.pages = pages;
	}
	
	@Given("user is on Home page")
    public void userIsOnHomePage(){        
        pages.home().open();        
    }
 
    @When("user opens a link")
    public void userClicksExtensionLink(){        
        pages.home().clickExtensionLink();
    }
 
    @Then("Extension page is shown")
    public void sportPageIsShown(){
//       assertEquals("Sport w Programie TV - Program telewizyjny Teleman.pl", pages.sport().getTitle());
       assertEquals("Selenium Framework | Add Extensions", pages.extension().getTitle());
    }	

}
