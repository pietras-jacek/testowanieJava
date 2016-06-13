package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.ws.rs.core.MediaType;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.PersonManager;
import com.jayway.restassured.RestAssured;

public class CarServiceRESTDBTest {
	
	private static IDatabaseConnection connection ;
    private static IDatabaseTester databaseTester;

    private static PersonManager pm = new PersonManager();

    @BeforeClass
    public static void setUp() throws Exception{
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restservicedemo/api";

        Connection jdbcConnection;
        jdbcConnection = DriverManager.getConnection(
                "jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
        connection = new DatabaseConnection(jdbcConnection);
        //DatabaseConfig config = connection.getConfig();
        //config.setProperty(DatabaseConfig.FEATURE_DATATYPE_WARNING, false);
		//config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
        

    }

    @Before
	public void set() throws Exception {
    	pm.dropCarTable();
		pm.dropPersonTable();
		pm.createPersonTable();
		pm.createCarTable();
	 	databaseTester = new JdbcDatabaseTester(
                "org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(
                new FileInputStream(new File("src/test/resources/fullDataWithCars.xml")));
        databaseTester.setDataSet(dataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.onSetup();
       
		
	}
    
    @Test
    public void addCar() throws Exception {
    	
        Person person = new Person(1, "Wojciech", 1978);
        Car car = new Car(3, "Ford Fiesta", 2008, person);
        
        
        given().contentType(MediaType.APPLICATION_JSON).body(car).
        when().post("/car/addOwner/").
        then().assertThat().statusCode(201);

        IDataSet dbDataSet = connection.createDataSet();
        ITable actualTable = dbDataSet.getTable("CAR");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
                new File("src/test/resources/carData.xml"));
        ITable expectedTable = expectedDataSet.getTable("CAR");

        Assertion.assertEquals(expectedTable, actualTable);
    }
    

    @AfterClass
    public static void tearDown() throws Exception {
    	pm.dropCarTable();
		pm.dropPersonTable();
		pm.createPersonTable();
		pm.createCarTable();
        databaseTester.onTearDown();
    }
	

}
