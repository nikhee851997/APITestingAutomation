package tests;

import static io.restassured.RestAssured.get;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.restassured.response.Response;

public class A_TestExample 
{
	public SoftAssert soft;
	
	@Test
	public void test_1() 
	{
		
		Response response = get("https://reqres.in/api/users?page=2");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		System.out.println();
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("Content-Type"));
		
		int statusCode = response.getStatusCode();
		
		soft= new SoftAssert();
		soft.assertEquals(statusCode, 200);
		soft.assertAll();		
	}
	
	
}







