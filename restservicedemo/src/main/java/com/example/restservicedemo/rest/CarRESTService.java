package com.example.restservicedemo.rest;


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
import com.example.restservicedemo.service.CarManager;

@Path("Car")
public class CarRESTService {
	
	private CarManager cm = new CarManager();
	
	@GET
	@Path("/{carId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Car getCar(@PathParam("carId") Long id) {
		Car c = cm.getCar(id);
		return c;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCar(Car car) {
		cm.addCar(car);
		return Response.status(201).entity("Car").build();
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
