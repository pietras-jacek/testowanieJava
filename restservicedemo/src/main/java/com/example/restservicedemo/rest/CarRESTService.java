package com.example.restservicedemo.rest;


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
import com.example.restservicedemo.service.CarManager;
import com.example.restservicedemo.service.PersonManager;

@Path("car")
public class CarRESTService {
	
	private CarManager cm = new CarManager();
	private PersonManager pm = new PersonManager();
	
	@GET
	@Path("/{carId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Car getCar(@PathParam("carId") Long id) {
		Car c = new Car();
		c.setId(id);
		Car car = cm.getCarWithOwner(c);
		return car;
	}
	
	@GET
	@Path("/carOwner/{ownerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Car> getCarsOwner(@PathParam("ownerId") Long id) {
		Person owner = pm.getPerson(id);
		
		List<Car> car = cm.getOwnerCars(owner);
		return car;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCar(Car car) {
		cm.addCar(car);
		return Response.status(201).entity("Car").build();
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Car> getAllCars() {
		List<Car> cars = cm.getAllCars();
		return cars;
}
	
	@GET
	@Path("/test2")
	@Produces(MediaType.TEXT_HTML)
	public String test2() {
		return "REST API /car is running";
	}
	
	@DELETE
	public Response clearCars() {
		cm.clearCars();
		return Response.status(200).build();
	}

}
