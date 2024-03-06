package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
//import java.util.HashMap;
//import java.util.Map;

public class B_Sampler
{
	
@Test
public void Get() 
{
		
 baseURI = "https://reqres.in/api";
		
	  given()
		 .get("/users?page=2")
	     .then().statusCode(200)
		 .body("data[4].first_name", equalTo("George"))
		 .body("data[1].id", equalTo(8))
		 .body("data.first_name", hasItems("George", "Rachel"))
		 .body("data.last_name", hasItems("Ferguson", "Howell"));
}
	
@Test
public void Post()
{
    /* key is always String, value (object) is String or int
    * Map<String, Object> map = new HashMap<String, Object>();
    */
				
 JSONObject request = new JSONObject();
		
  request.put("name", "Nikhil");
  request.put("job", "Automation Engineer");
		
		
	baseURI = "https://reqres.in/api";
		
		given()
			.header("Content-Type", "application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(request.toJSONString())
		    .when().post("/users")
		    .then().statusCode(201)
			.log().all();		
   }
	
@Test
public void Put()  // fully update
 {
		
	JSONObject request = new JSONObject();
	
	request.put("name", "Akhil");
	request.put("job", "Developer");
	
	
	baseURI = "https://reqres.in/api";
	
	given()
		.header("Content-Type", "application/json")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString())
	    .when().put("/users/2")
	    .then().statusCode(200)
	    .log().all();
		
  }

@Test
public void Patch() // partially update 
{
		
	JSONObject request = new JSONObject();
	
	request.put("name", "AkhilK");
	
	
	baseURI = "https://reqres.in";
	
	given()
		.header("Content-Type", "application/json")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString())
	    .when().patch("/api/users/2")
	    .then().statusCode(200)
		.log().all();
		
}

@Test
public void Delete() 
{
	
	baseURI = "https://reqres.in";
	
	when().
		delete("/api/users/2").
	    then().statusCode(204)
		.log().all();
		
}

}
















