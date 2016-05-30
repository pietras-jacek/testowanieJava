package com.example.restservicedemo;


import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.CarManager;
import com.example.restservicedemo.service.PersonManager;
import com.jayway.restassured.RestAssured;

public class CarServiceTest {

	public static final String CAR_MAKE = "Opel";
	
	private static Person person;

	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";
		
		person = new Person(2, "Aleksander", 1985);
		
		given().
		contentType("application/json").
		body(person).
		when().post("/person/").
		then().assertThat().statusCode(201);
	}


	@Test
	public void addCars() {
		delete("/car/").then().assertThat().statusCode(200);

		Car car = new Car(1, CAR_MAKE, "Corsa", 2000, person);

		given().contentType("application/json").
		body(car).when().post("/car/").then().assertThat()
				.statusCode(201);

		Car rCar = get("/car/1").as(Car.class);

		assertThat(CAR_MAKE, equalToIgnoringCase(rCar.getMake()));
		assertThat("Corsa", equalToIgnoringCase(rCar.getModel()));

	}
	
	@Test
	public void getPerson() {

		delete("/car/").then().assertThat().statusCode(200);

		Car car = new Car(1, CAR_MAKE, "Astra", 1976, person);

		given().contentType(MediaType.APPLICATION_JSON).body(car).when().post("/car/").then().assertThat()
				.statusCode(201);

		get("/car/1").then().assertThat().body("make", equalTo("Opel"));

		Car rCar = get("/car/1").as(Car.class);

		assertThat(CAR_MAKE, equalToIgnoringCase(rCar.getMake()));
		assertThat(rCar.getMake(), equalToIgnoringCase("Opel"));

	}
	
	
	
	
	@Test
	public void getAllCars() {
		String cars = get("/car/all/").asString();

		assertNotNull(cars);
	}

}
