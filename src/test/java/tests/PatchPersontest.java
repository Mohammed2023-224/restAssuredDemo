package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import constants.FakerData;
import constants.Loggers;
import helpers.PersonServiceHelper;
import io.restassured.response.Response;
import model.Person;

public class PatchPersontest {

	public PersonServiceHelper presonservicehelper;

	@BeforeClass
	public void init() throws IOException {

		presonservicehelper = new PersonServiceHelper();
	}

	@Test(priority = 1)
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
		Loggers.logger.info("create global variable user id {} from the response ", id);
		con.setAttribute("userId", id);
	}

	@Test(priority = 2)
	public void updatePersonTest(ITestContext con) {
		Loggers.logger.info("create fake data for the patch request");
		Person person = new Person();
		Faker faker = new Faker();
		person.setFname(faker.name().firstName());
		person.setAge(faker.number().randomDigit());
		person.setLname(faker.name().lastName());
		person.setAddress(faker.address().fullAddress());
		int id = (Integer) con.getAttribute("userId");
		Loggers.logger.info("getting id variable{}", id);
		Response response = presonservicehelper.updatePerson(id, person);
		Loggers.logger.info("checking that status code =200");
		Assert.assertTrue(response.statusCode() == 200);

	}

	@Test(priority = 3)
	public void deletePersonTest(ITestContext con) {
		Loggers.logger.info("getting the id to delete {}", con.getAttribute("userId"));
		Response response = presonservicehelper.deletePerson((Integer) con.getAttribute("userId"));
		Assert.assertTrue(response.statusCode() == 200);

	}
}
