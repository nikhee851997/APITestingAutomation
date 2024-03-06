package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class D_TestsOnLocalAPI
{

	@Test
	public void get() 
	{
        /* To do this we have to reach https://github.com/typicode/json-server
         * Open command window
         *                   npm install json-server
         * create db.jsonfile in notepad++ and paste the data that we want in .json in our local host
         * save it in c/users/nikhil/ 
         * From that directory reach to command window 
         *                   json-server --watch db.json
         * Thats it will start in localhost:3000(port)
         */
		baseURI = "http://localhost:3000";

		given()
		.get("/users")
		.then().statusCode(200)
		.log().all();
		
	}

	@Test
	public void post() 
	{

		JSONObject request = new JSONObject();
		
		request.put("firstName", "Apple");
		request.put("lastName", "pro");
		request.put("version_number", 3);

		baseURI = "http://localhost:3000";

		 given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString())
		.when().post("/Electronics")
		.then().statusCode(201);

	}
	
	@Test
	public void put() 
	{

		JSONObject request = new JSONObject();
		
		request.put("firstName", "Mangus");
		request.put("lastName", "Book");
		request.put("version_number", 2);

		baseURI = "http://localhost:3000";

		 given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString())
// If i dont create id with my request server will create id in its response, so i can use that id for put, patch,delete.
        .when().put("/Electronics/8d9e")
		.then().statusCode(200)
		.log().all();

	}

	@Test
	public void patch() 
	{

		JSONObject request = new JSONObject();

		request.put("firstName", "Mango");
	
		baseURI = "http://localhost:3000";

		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString())
		.when().patch("/Electronics/8d9e")
		.then().statusCode(200)
		.log().all();

	}
	
	@Test
	public void delete() 
	{
		
		baseURI = "http://localhost:3000";
		
		when()
		.delete("/Electronics/8d9e")
		.then().statusCode(200);
		
	}







}
