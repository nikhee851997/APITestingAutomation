package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.annotations.Test;


public class C_JSONSchemaValidator
{
	
	
@Test
public void Get()
{
			
	baseURI = "https://reqres.in/api";
			
			given()
				.get("/users?page=2")
			    .then()
	/*  So here we had copy reqres.in/ get/ responsebody and converted json to jsonschema by using converter
	 *  json schema is a Json doc that describes structure of Json document making it easier to validate and process.
	 *  json schema doc is placed in class path as src/main/resource: project/buildpath/configurebuildpath in eclipse
	 */
				.assertThat().body(matchesJsonSchemaInClasspath("Schema.json"))
				.statusCode(200);	
			
}

}
