package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import constants.FakerData;
import constants.Loggers;
import helpers.PersonServiceHelper;
import io.restassured.response.Response;
import model.Person;

public class DeletePersonTest {
	public PersonServiceHelper presonservicehelper;

	@BeforeClass
	public void init() throws IOException {

		presonservicehelper = new PersonServiceHelper();
		
	}
	@Test
	public void create_person(ITestContext con) {
		Loggers.logger.info("get faker data from faker class then adding it to pojo object");
		Person person = new Person();
		FakerData faker = new FakerData();
		person.setFname(faker.fname);
		person.setAge(faker.age);
		person.setLname(faker.lname);
		person.setAddress(faker.address);

		Response reponse = presonservicehelper.createPerson(person);
		int id = reponse.jsonPath().get("id");
		Loggers.logger.info("check status code==201");
		Assert.assertTrue(reponse.statusCode() == 201);
		Loggers.logger.info("create global variable user id {} from the response ",id);
		con.setAttribute("userId", id);
	}
	@Test
	public void deletePersonTest(ITestContext con) {

		Response response=presonservicehelper.deletePerson((Integer) con.getAttribute("userId"));
		Assert.assertTrue(response.statusCode()==200);
		
	}
	
	
}
