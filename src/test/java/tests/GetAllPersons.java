package tests;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import helpers.PersonServiceHelper;
import io.restassured.response.Response;
import model.Person;

public class GetAllPersons {
	public PersonServiceHelper  presonservicehelper;
	
	@BeforeClass
	public void init() throws IOException {

		presonservicehelper=new PersonServiceHelper();
	}
	@Test
	public void testgetall() {
		Response response=presonservicehelper.getAllPerson();

		
		java.lang.reflect.Type type=new TypeReference<List<Person>>() {}.getType();
		List<model.Person> personlist=response.as(type);
		assertNotNull(personlist);

		Assert.assertTrue(response.statusCode()==200);
	}
}
