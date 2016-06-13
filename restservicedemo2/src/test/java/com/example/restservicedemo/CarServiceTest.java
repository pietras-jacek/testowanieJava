package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.CarResponse;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.domain.PersonsResponse;
import com.example.restservicedemo.service.PersonManager;
import com.jayway.restassured.RestAssured;


public class CarServiceTest {

	private static final String CAR_MODEL = "Ford Mustang";
	
	@BeforeClass
	public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";
	}
	

	@Test
	public void addCar(){
		
		delete("/car/").then().assertThat().statusCode(200);
		
		Car Car = new Car(1, CAR_MODEL, 2011);
		given().contentType(MediaType.APPLICATION_JSON).body(Car).when().post("/car/add/").then().assertThat()
		.statusCode(201);
		
		Car rCar = get("/car/1").as(Car.class);
		
		assertThat(CAR_MODEL, equalToIgnoringCase(rCar.getModel()));
		
	}
	
	@Test
	public void getCars(){		
		
		given().
	       contentType(MediaType.APPLICATION_JSON).
	    when().	     
	    get("/car/").then().assertThat().statusCode(200);
				
		CarResponse rCar = get("/car/").as(CarResponse.class);
		
		List<Car> cars = rCar.getCar();
		
		assertEquals(2, cars.size());
		
		System.out.println(cars.get(1).getModel());
		
		
	}
	
	@Test
//	@Ignore
	public void getCarWithOwner(){
//		delete("/car/").then().assertThat().statusCode(200);
//		delete("/person/").then().assertThat().statusCode(200);
		
		Person person = new Person(2, "Wojciech", 1989);
		
		Car car = new Car(2,"Fiat 500", 2014, person);

		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(person)
		.when()
			.post("/person/").then().assertThat().statusCode(201);
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(car)
		.when()
			.post("/car/addOwner/").then().assertThat().statusCode(201);
		
		given()
		.when()
			.get("/car/carWithOwner/2")
		.then()
			.body("id", equalTo("2"))
			.body("model", equalTo("Fiat 500"))
			.body("owner.id", equalTo("2"))
			.body("owner.firstName", equalTo("Wojciech"))
			.body("owner.yob", equalTo("1989"))
			.body("yop", equalTo("2014"));
		
		delete("/car/").then().assertThat().statusCode(200);
		delete("/person/").then().assertThat().statusCode(200);
		
	}
	
	@Test
	public void AgetAllCars(){
		String cars = get("/car/all/").asString();

		assertNotNull(cars);
	}
	
	@Test
//	@Ignore
	public void clearCars() {
		delete("/car").then().assertThat().statusCode(200);
		given()
	       	.contentType(MediaType.APPLICATION_JSON)
	       	.body(Car.class)
	    .when()
	    .then()
	    	.body("", Matchers.hasSize(0));
		
	}
	
	
		
}
