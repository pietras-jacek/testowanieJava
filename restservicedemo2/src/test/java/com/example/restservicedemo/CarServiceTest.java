package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import javax.ws.rs.core.MediaType;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.jayway.restassured.RestAssured;


public class CarServiceTest {
	
	@BeforeClass
	public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";
	}
	
	@Test
	@Ignore
	public void getCar(){
		get("/cars/0").then().assertThat().body("model", equalTo("Opel"));
		
		
		Car aCar = get("/cars/0").as(Car.class);
		
		assertThat(aCar.getModel(), equalToIgnoringCase("Opel"));
	}
	
	@Test
	@Ignore
	public void addCar(){
		
		Car aCar = new Car(2, "Ford", 2011);
		given().
		       contentType(MediaType.APPLICATION_JSON).
		       body(aCar).
		when().	     
		post("/cars/").then().assertThat().
		statusCode(201).
		body(containsString("Car saved:"));
	}
	

}
