package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Car;
import com.example.shdemo.domain.Person;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
public class SellingManagerDBUnitTest {

	@Autowired
	SellingManager sellingManager;

	@Test
	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/addPersonData.xml", 
	assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void getClientCheck() {
        assertEquals(2, sellingManager.getAllClients().size());
        
        Person p = new Person();
        p.setFirstName("Kaziu");
        p.setPin("8754");
        p.setRegistrationDate(new Date());
        
        sellingManager.addClient(p);
	}
	
	@Test
	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/addPersonData.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void addClientCheck() {
		
		Person p = new Person();
        p.setFirstName("Kaziu");
        p.setPin("8754");
        p.setRegistrationDate(new Date());
        
        sellingManager.addClient(p); 
	}
	
	@Test
	@DatabaseSetup("/fullData.xml")
	public void findClientByPinCheck() {
		Person p0;
		p0 = sellingManager.findClientByPin("1234");
		assertEquals("1234", p0.getPin());
	}
	
	@Test
	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/addCarData.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void addCarCheck() {
		
		Car car = new Car();
		car.setId(5L);
		car.setMake("Skoda");
		car.setModel("Fabia");
        car.setSold(false);
        
        sellingManager.addNewCar(car); 
        
	}
	
	@Test
	@DatabaseSetup("/fullData.xml")
	public void getCarsCheck() {
		
        assertEquals(1, sellingManager.getAvailableCars().size());
	}
	
	@Test
	@DatabaseSetup("/fullData.xml")
	public void findCarByIdCheck() {
		Car c0;
		c0 = sellingManager.findCarById(3L);
		assertEquals("Fiat", c0.getMake());
	}
	
	@Test
	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/sellCarData.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void sellCarCheck () {
		Person prsn = sellingManager.findClientByPin("1234");
		Car cr = sellingManager.findCarById(3L);
		
		sellingManager.sellCar(prsn.getId(), cr.getId());
		assertEquals(0, sellingManager.getAvailableCars().size());
	}
	
}
