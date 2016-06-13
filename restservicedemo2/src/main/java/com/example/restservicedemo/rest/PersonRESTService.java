package com.example.restservicedemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.PersonManager;

@Path("person")
public class PersonRESTService {	
	
	private PersonManager pm = new PersonManager();
	
	@GET
	@Path("/{personId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getPerson(@PathParam("personId") Long id){
		Person p = pm.getPerson(id);
		return p;
	}
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPerson(Person person){
		pm.addPerson(person);
		return Response.status(201).entity("Person").build(); 
	}
	
	

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getPersons(){
		Person p1 = new Person();
		p1.setFirstName("Bolek");
		
		Person p2 = new Person();
		p2.setFirstName("Lolek");
		
        List<Person> persons = new ArrayList<Person>();
        persons.add(p1);
        persons.add(p2);
		return persons;
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getAllPersons() {
		List<Person> persons = pm.getAllPersons();
		return persons;
}
		
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "REST API /person is running";
	}
	
	@DELETE
	public Response clearPersons(){
		pm.clearPersons();
		return Response.status(200).build();
	}
	
	@DELETE
	@Path("/clear/{personId}")
	public Response clearPerson(@PathParam("personId") Long id) {
		pm.clearPerson(id);
		return Response.status(200).build();
	}

}
