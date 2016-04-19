package com.example.seleniumdemo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class SeleniumFrameworkSite {

	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void driverSetup() {
		// ChromeDrirver, FireforxDriver, ...
//                System.setProperty("webdriver.chrome.driver", 
//                        "\\C:\\Users\\Dom\\Desktop\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "/home/PJWSTK/s10146/Pulpit/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Before
	public void open_Url(){
		driver.get("http://www.seleniumframework.com/Practiceform/");
	}
	
	@Test
	public void findByLinkText(){
		element = driver.findElement(By.linkText("This is a link"));
		assertNotNull(element);
	}

	@Test
	public void testLinkAndPrSc(){
		//driver.findElement(By.xpath("//li[@class=' menu-item menu-item-type-post_type menu-item-object-page menu-item-2983 first']")).click();
		driver.findElement(By.xpath("//*[@id='main-nav']/li[1]/a/span")).click();
		File prsc = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(prsc);
		try{
			FileUtils.copyFile(prsc, new File("testlink.png"));
		} catch (IOException e){
			e.printStackTrace();
			assertTrue(false);
		}
		// Go back to Main Page
		driver.navigate().to("http://www.seleniumframework.com/Practiceform/");
		element = driver.findElement(By.xpath("//*[@id='button1']"));
		assertNotNull(element);
	}

	@Test
	public void testVerification(){
		driver.findElement(By.id("vfb-3")).sendKeys("ToolsQA");
		driver.findElement(By.xpath("//*[@id='item-vfb-2']/div/h3")).click();
		element = driver.findElement(By.xpath("//label[contains(text(),'Please enter only digits.')]"));
		assertNotNull(element);
		
		driver.findElement(By.id("vfb-3")).clear();
		driver.findElement(By.xpath("//*[@id='item-vfb-2']/div/h3")).click();
		element = driver.findElement(By.xpath("//*[contains(text(),'This field is required.')]"));
		assertNotNull(element);
		
		driver.findElement(By.id("vfb-3")).sendKeys("1");
		driver.findElement(By.xpath("//*[@id='item-vfb-2']/div/h3")).click();
		element = driver.findElement(By.xpath("//*[contains(text(),'Please enter at least 2 characters.')]"));
		assertNotNull(element);
		
		driver.findElement(By.id("vfb-3")).sendKeys("13");
		driver.findElement(By.xpath("//*[@id='item-vfb-2']/div/h3")).click();
		element = driver.findElement(By.xpath("//*[contains(text(),'Please enter no more than 2 characters.')]"));
		assertNotNull(element);
		
		driver.findElement(By.id("vfb-3")).clear();
		driver.findElement(By.id("vfb-3")).sendKeys("13");
		driver.findElement(By.xpath("//*[@id='item-vfb-2']/div/h3")).click();
		element = driver.findElement(By.xpath("//*[contains(text(),'')]"));
		assertNotNull(element);
		
		boolean staus = driver.findElement(By.xpath("//*[@id='item-vfb-2']/ul/li[1]/span/label[1]")).isDisplayed();
		assertFalse(staus);//wlasciwie to nam mowi ze element jest dalej wyswietlany w DOM ale jest pusty
		
		driver.findElement(By.name("vfb-submit")).click();
		assertNotNull(driver.findElement(By.id("form_success")));
		
	}

	@Test
	public void testCheckbox() throws InterruptedException{
		
		driver.findElement(By.xpath("//*[@id='vfb-6-0']")).click();
		Thread.sleep(2000);
		boolean bValue = false;
		bValue = driver.findElement(By.xpath("//*[@id='vfb-6-0']")).isSelected();
		assertTrue(bValue);
	}
	
	
	@Test
	public void testRadio(){
		
		List rB = driver.findElements(By.name("vfb-7"));
		assertEquals(3,rB.size());
	
		boolean bValue = false;
		driver.findElement(By.id("vfb-7-1")).click();
		bValue = driver.findElement(By.id("vfb-7-1")).isSelected();
		assertTrue(bValue);


	}
	
	
	@Test
	public void testSelect() throws InterruptedException{
		
		Select select = new Select(driver.findElement(By.id("vfb-12")));
		select.selectByIndex(2);
		Thread.sleep(2000);
		select.selectByValue("Option 2");
		Thread.sleep(2000);
	
	}
	
	@Test
	public void testAlert(){
		driver.findElement(By.id("alert")).click();
		Alert simpleAlert = driver.switchTo().alert();
		String alertText = simpleAlert.getText();
		System.out.println("Alert text is " + alertText);
		simpleAlert.accept();
		
	}
	
	@AfterClass
	public static void cleanp() {
		driver.quit();
	}
}
