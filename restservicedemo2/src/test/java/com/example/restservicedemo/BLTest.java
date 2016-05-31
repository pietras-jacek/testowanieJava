package com.example.restservicedemo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.PersonManager;

public class BLTest {

	PersonManager pm = new PersonManager();

	@Test
	@Ignore
	public void checkCarAdding() {

		Car c = new Car();
		c.setModel("Syrena");
		c.setYop(1973);

		assertEquals(1, pm.addCar(c));
	}

	@Test
	@Ignore
	public void checkSell() {

		Car c1 = new Car();
		c1.setModel("Syrena");
		c1.setYop(1973);

		Car c2 = new Car();
		c2.setModel("Fiat Punto");
		c2.setYop(1999);

		assertEquals(1, pm.addCar(c1));
		assertEquals(1, pm.addCar(c2));

		List<Car> cars = pm.getAllCars();

		assertTrue(cars.size() > 0);

		Car carToSell = cars.get(1);

		Person p1 = new Person();
		p1.setFirstName("Zieliński");
		p1.setYob(1978);

		Person p2 = new Person();
		p2.setFirstName("Kowalski");
		p2.setYob(1978);

		assertEquals(1, pm.addPerson(p1));
		assertEquals(1, pm.addPerson(p2));

		List<Person> persons = pm.getAllPersons();

		assertTrue(persons.size() > 1);

		Person owner = persons.get(1);
		
		
		pm.sellCar(carToSell, owner);
		
		Car rCar = pm.getCarWithOwner(carToSell);
		
		assertEquals(owner.getFirstName(), rCar.getOwner().getFirstName());

	}

	@Test
	@Ignore
	public void checkGetAll() {

	}

}
