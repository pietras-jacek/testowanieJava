package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.MediaType;

import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.jayway.restassured.RestAssured;

public class CarServiceTest {

	public static final String CAR_MAKE = "Opel";
	public static final String CAR_MODEL = "Astra";

	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";
	}

	@Test
	public void getCar() {
		get("/car/0").then().assertThat().body("model", equalTo("Corsa"));

		Car aCar = get("/car/0").as(Car.class);
		assertThat(aCar.getMake(), equalToIgnoringCase("Opel"));
	}

	@Test
	public void addCar() {

		Car aCar = new Car(2, "Ford", "Fiesta", 2011);
		given().contentType("application/json").body(aCar).when().post("/car/").then().assertThat().statusCode(201)
				.body(containsString("Car saved:"));
	}

	@Test
	public void addCars() {
		delete("/Car/").then().assertThat().statusCode(200);

		Car car = new Car(1L, CAR_MAKE, CAR_MODEL, 2000);

		given().contentType(MediaType.APPLICATION_JSON).body(car).when().post("/Car/").then().assertThat()
				.statusCode(201);

		Car rCar = get("/Car/1").as(Car.class);

		assertThat(CAR_MAKE, equalToIgnoringCase(rCar.getMake()));
		assertThat(CAR_MODEL, equalToIgnoringCase(rCar.getModel()));

	}

	@Test
	public void getAllCars() {
		String cars = get("/car/all/").asString();

		assertNotNull(cars);
	}

}
