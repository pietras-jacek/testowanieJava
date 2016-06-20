package com.example.shdemo.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Car;
import com.example.shdemo.domain.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SellingManagerTest {

	@Autowired
	SellingManager sellingManager;

	private final String NAME_1 = "Bolek";
	private final String PIN_1 = "1234";
	private final Date DATE = new Date();

	private final String NAME_2 = "Lolek";
	private final String PIN_2 = "4321";
	
	private final String NAME_3 = "Koziołek";
	private final String PIN_3 = "1111";

	private final String MODEL_1 = "126p";
	private final String MAKE_1 = "Fiat";
	private final Boolean SOLD_1 = false;

	private final String MODEL_2 = "Mondeo";
	private final String MAKE_2 = "Ford";
	
	private final String MODEL_3 = "Racer";
	private final String MAKE_3 = "Maluch";

	@Test
	public void addClientCheck() {

		List<Person> retrievedClients = sellingManager.getAllClients();

		// If there is a client with PIN_1 delete it
		for (Person client : retrievedClients) {
			if (client.getPin().equals(PIN_1)) {
				sellingManager.deleteClient(client);
			}
		}

		Person person = new Person();
		person.setFirstName(NAME_1);
		person.setPin(PIN_1);
		// ... other properties here

		// Pin is Unique
		sellingManager.addClient(person);

		Person retrievedClient = sellingManager.findClientByPin(PIN_1);

		assertEquals(NAME_1, retrievedClient.getFirstName());
		assertEquals(PIN_1, retrievedClient.getPin());
		// ... check other properties here
	}

	@Test
	public void addCarCheck() {

		Car car = new Car();
		car.setMake(MAKE_1);
		car.setModel(MODEL_1);
		// ... other properties here

		Long carId = sellingManager.addNewCar(car);

		Car retrievedCar = sellingManager.findCarById(carId);
		assertEquals(MAKE_1, retrievedCar.getMake());
		assertEquals(MODEL_1, retrievedCar.getModel());
		// ... check other properties here
		
		retrievedCar.setMake("SYRENA");

	}

	@Test
	public void sellCarCheck() {

		Person person = new Person();
		person.setFirstName(NAME_2);
		person.setPin(PIN_2);

		sellingManager.addClient(person);

		Person retrievedPerson = sellingManager.findClientByPin(PIN_2);

		Car car = new Car();
		car.setMake(MAKE_2);
		car.setModel(MODEL_2);

		Long carId = sellingManager.addNewCar(car);

		sellingManager.sellCar(retrievedPerson.getId(), carId);

		List<Car> ownedCars = sellingManager.getOwnedCars(retrievedPerson);

		assertEquals(1, ownedCars.size());
		assertEquals(MAKE_2, ownedCars.get(0).getMake());
		assertEquals(MODEL_2, ownedCars.get(0).getModel());
	}

	@Test
	public void disposeCarCheck() {
		// Do it yourself
		
		Person person = new Person();
		person.setFirstName(NAME_3);
		person.setPin(PIN_3);
		
		sellingManager.addClient(person);
		
		Car car = new Car();
		car.setMake(MAKE_3);
		car.setModel(MODEL_3);
		car.setSold(true);
	
		
		Long carId = sellingManager.addNewCar(car);
		
		
		Person person2 = sellingManager.findClientByPin(PIN_3);
		Car car2 = sellingManager.findCarById(carId);
		
		sellingManager.disposeCar(person2, car2);
		
		assertEquals(0, person2.getCars().size());
	}


}
