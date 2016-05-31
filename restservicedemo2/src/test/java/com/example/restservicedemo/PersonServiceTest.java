package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.*;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.domain.PersonsResponse;
import com.jayway.restassured.RestAssured;


public class PersonServiceTest {
	
	private static final String PERSON_FIRST_NAME = "Jasiu";
	
	@BeforeClass
    public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";   	
    }
	
	@Test
	@Ignore
	public void addPersons(){		
		
		delete("/person/").then().assertThat().statusCode(200);
		
		Person person = new Person(1L, PERSON_FIRST_NAME, 1976);
		
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(person).
	    when().	     
	    post("/person/").then().assertThat().statusCode(201);
				
		Person rPerson = get("/person/1").as(Person.class);
		
		assertThat(rPerson.getFirstName(), equalToIgnoringCase(PERSON_FIRST_NAME));
		
	}
	
	@Test
	@Ignore
	public void getPersons(){		
		

		given().
	       contentType(MediaType.APPLICATION_JSON).
	    when().	     
	    get("/person/").then().assertThat().statusCode(200);
				
		PersonsResponse rPerson = get("/person/").as(PersonsResponse.class);
		
		List<Person> persons = rPerson.getPerson();
		
		assertEquals(2, persons.size());
		
		System.out.println(persons.get(1).getFirstName());
		
		
	}

}
