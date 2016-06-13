package com.example.restservicedemo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.PersonManager;

public class BLTest {

	static PersonManager pm = new PersonManager();
	
	@Before
	public void clear() {
		pm.clearCars();
		pm.clearPersons();	
	}
	

	@Test
	public void checkCarAdding() {

		Car c = new Car();
		c.setModel("Ford Fiesta");
		c.setYop(1998);

		assertEquals(1, pm.addCar(c));
	}
	
	@Test
	public void checkPersonAdding() {
		Person p = new Person(2, "Marek",  1991);
		assertEquals(1, pm.addPerson(p));
	}
	
	@Test
	public void checkGetCarWithOwner() {
		Person person = new Person(3, "Adam", 1990);
		assertEquals(1, pm.addPerson(person));
		
		List<Person> persons = pm.getAllPersons();
		assertTrue(persons.size() > 0);
		

		Car c = new Car(1, "Opel Astra", 2005, person);
		c.setModel("Opel Astra");
		c.setYop(2005);
		c.setOwner(person);
		assertEquals(1, pm.addCarWithId(c));
		
		Person owner = persons.get(0);
		assertEquals(3, owner.getId());
		
		List<Car> cars = pm.getAllCars();
		assertTrue(cars.size() > 0);
		Car carOwner = cars.get(0);
		
		pm.sellCar(carOwner, owner);
		Car rCar = pm.getCarWithOwner(carOwner);
		
		assertEquals(owner.getFirstName(), rCar.getOwner().getFirstName());

	}

	@Test
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
		p1.setId(3);
		p1.setFirstName("Zieliński");
		p1.setYob(1978);

		Person p2 = new Person();
		p2.setId(4);
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
	public void checkGetAllPersons() {
		Person p1 = new Person(1, "Adam", 1990);
		Person p2 = new Person(2, "Wojciech", 1993);
		Person p3 = new Person(3, "Bogdan", 1995);
		pm.addPerson(p1);
		pm.addPerson(p2);
		pm.addPerson(p3);
		
		List<Person> persons = pm.getAllPersons();
		assertTrue(persons.size() > 0);
		assertEquals(persons, pm.getAllPersons());
	}
	
	@AfterClass
	public static void tearDown() {
		pm.clearCars();
		pm.clearPersons();	
	}
	

}
