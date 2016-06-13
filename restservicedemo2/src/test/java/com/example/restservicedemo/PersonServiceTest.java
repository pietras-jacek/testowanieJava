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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasItems;
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
	
	private static final String PERSON_FIRST_NAME = "Aleksander";
	
	@BeforeClass
    public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";   	
    }
	
	@Test
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
	public void getPerson() {
		Person person = new Person(1L, PERSON_FIRST_NAME, 1976);
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(person).
	    when().	     
	    post("/person/").then().assertThat().statusCode(201);
		get("/person/1").then().assertThat().body("firstName", equalTo(PERSON_FIRST_NAME));

		Person example = get("/person/1").as(Person.class);
		assertThat(example.getFirstName(), equalToIgnoringCase(PERSON_FIRST_NAME));
	}
	
	
	@Test
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
	
	@Test
	public void getPersonss(){
		delete("/car/").then().assertThat().statusCode(200);
		delete("/person/").then().assertThat().statusCode(200);
		Person p1 = new Person(6L,"Jacek", 1991);
		Person p2 = new Person(7L,"Maciek", 1993);
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(p1)
		.when()
			.post("/person/").then().assertThat().statusCode(201);
		
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(p2)
		.when()
			.post("/person/").then().assertThat().statusCode(201);

		given()
		.when()
			.get("/person/all")
		.then()
			.body("person[0].id", equalTo("6"))
			.body("person[0].firstName", equalTo("Jacek"))
			.body("person[0].yob", equalTo("1991"))
			.body("person[1].id", equalTo("7"))
			.body("person[1].firstName",equalTo("Maciek"))
			.body("person[1].yob", equalTo("1993"))
			.body("person.id", hasItems("6","7"));
	}
	
	@Test
	public void getAllPersons() {	
		String persons = get("/person/all").asString();
		assertNotNull(persons);
	}
	
	@Test
	public void clearPersons() {
		delete("/person/").then().assertThat().statusCode(200);
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(Person.class).
	    when().get("/person/0").
	    then().assertThat().body("firstName", equalTo(null));
	}
	

}
