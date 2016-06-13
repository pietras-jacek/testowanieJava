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
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.domain.PersonsResponse;
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
		given().
		       contentType(MediaType.APPLICATION_JSON).
		       body(Car).
		when().	     
		post("/car/").then().assertThat().
		statusCode(201);
		
//		Car rCar = get("/car/1").as(Car.class);
//		
//		assertThat(CAR_MODEL, equalToIgnoringCase(rCar.getModel()));
		
	}
	
	@Test
	@Ignore
	public void getAllCars(){
		String cars = get("/car/all/").asString();

		assertNotNull(cars);
	}
	
//	@Test
//	public void clearCars() {
//		delete("/car").then().assertThat().statusCode(200);
//		given()
//	       	.contentType(MediaType.APPLICATION_JSON)
//	       	.body(Car.class)
//	    .when()
//	    .then()
//	    	.body("", Matchers.hasSize(0));
//		
//	}
	
	
		
}
