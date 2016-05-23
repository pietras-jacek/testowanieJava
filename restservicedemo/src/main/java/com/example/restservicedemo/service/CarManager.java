package com.example.restservicedemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.restservicedemo.domain.Car;

public class CarManager {
	
	private Connection connection;
	
	private static final String URL = "jdbc:hsqldb:hsql://localhost/workdb";
	private static final String CREATE_TABLE_CAR = "CREATE TABLE Car(id bigint GENERATED BY DEFAULT AS IDENTITY, make varchar(20), model varchar(20), yop integer)";

	private PreparedStatement addCarsStmt;
	private PreparedStatement deleteAllCarsStmt;
	private PreparedStatement getAllCarsStmt;
	private PreparedStatement getCarByIdStmt;
	
	private Statement statement;
	
	public CarManager() {
		try {
			connection = DriverManager.getConnection(URL);
			statement = connection.createStatement();
			
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			
			while (rs.next()) {
				if ("Car".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}
			
			if (!tableExists)
				statement.executeUpdate(CREATE_TABLE_CAR);

			addCarsStmt = connection
					.prepareStatement("INSERT INTO Car (id, make, model, yop) VALUES (?, ?, ?, ?)");
			deleteAllCarsStmt = connection
					.prepareStatement("DELETE FROM Car");
			getAllCarsStmt = connection
					.prepareStatement("SELECT id, make, model, yop FROM Car");
			getCarByIdStmt = connection
					.prepareStatement("SELECT id, make, model, yop FROM Car where id = ?");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	Connection getConnnection() {
		return connection;
	}
	
	public void clearCars() {
		try {
			deleteAllCarsStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int addCar(Car car) {
		int count = 0;
		
		try {
			addCarsStmt.setLong(1, car.getId());
			addCarsStmt.setString(2, car.getMake());
			addCarsStmt.setString(3, car.getModel());
			addCarsStmt.setInt(4, car.getYop());
			
			count = addCarsStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public List<Car> getAllCars() {
		List<Car> cars = new ArrayList<Car>();
		
		try {
			ResultSet rs = getAllCarsStmt.executeQuery();
			
			while (rs.next()) {
				Car c = new Car();
				c.setId(rs.getInt("id"));
				c.setMake(rs.getString("make"));
				c.setModel(rs.getString("model"));
				c.setYop(rs.getInt("yop"));
				cars.add(c);
			}
 		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cars;
	}
	
	public Car getCar(Long id) {
		Car c = new Car();
		
		try {
			getCarByIdStmt.setLong(1, id);
			ResultSet rs = getCarByIdStmt.executeQuery();
			
			while (rs.next()) {
				c.setId(rs.getInt("id"));
				c.setMake(rs.getString("make"));
				c.setModel(rs.getString("model"));
				c.setYop(rs.getInt("yop"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
}
