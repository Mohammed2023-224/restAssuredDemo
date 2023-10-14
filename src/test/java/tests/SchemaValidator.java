package tests;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import constants.EndPoint;
import constants.Loggers;
import helpers.PersonServiceHelper;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaValidator {
	public PersonServiceHelper presonservicehelper;

	@BeforeClass
	public void init() throws IOException {

		presonservicehelper = new PersonServiceHelper();
	}

	@Test
	public void testgetall() {
		Loggers.logger.info("test the schema of the get response to the file ");
		RestAssured.given()
		.get(EndPoint.GET_ALL_PERSONS)
		.then()
		.assertThat()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
	}
}
