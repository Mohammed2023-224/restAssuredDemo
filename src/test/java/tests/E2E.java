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

public class E2E {
	public PersonServiceHelper presonservicehelper;

	public String id;
	public Person personpatch;
	FakerData faker = new FakerData();

	@BeforeClass
	public void init() throws IOException {

		presonservicehelper = new PersonServiceHelper();
	}

	@Test(priority = 1)
	public void create_person(ITestContext con) {
		Loggers.logger.info("create fake data to the post request");
		Person person = new Person();

		person.setFname(faker.fname);
		person.setAge(faker.age);
		person.setLname(faker.lname);
		person.setAddress(faker.address);
		Loggers.logger.info("send the post request");

		Response reponse = presonservicehelper.createPerson(person);
		int id = reponse.jsonPath().get("id");
		Assert.assertTrue(reponse.statusCode() == 201);
		con.setAttribute("userId", id);

	}

	@Test(priority = 2)
	public void updatePersonTest(ITestContext con) {
		Loggers.logger.info("cretae fake data for the patch request");
		Person person = new Person();
		Faker faker2 = new Faker();
		person.setFname(faker2.name().firstName());
		person.setAge(faker2.number().randomDigit());
		person.setLname(faker.lname);
		person.setAddress(faker2.address().fullAddress());
		int id = (Integer) con.getAttribute("userId");
		Loggers.logger.info("getting id variable{}", id);
		Response response = presonservicehelper.updatePerson(id, person);

		Assert.assertTrue(response.statusCode() == 200);

	}

	@Test(priority = 3)
	public void deletePersonTest(ITestContext con) {

		Response response = presonservicehelper.deletePerson((Integer) con.getAttribute("userId"));
		Assert.assertTrue(response.statusCode() == 200);

	}

}
