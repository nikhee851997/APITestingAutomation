package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import java.io.FileInputStream;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;



public class F_XMLSchemaValidation
{
	
   @Test
	public void schemaValidation() throws Exception
   {
		
  /* If the data in file is in bytes then we use FileInputStream 
   * So we copy data from dneonline.com/WSDL:webservice description language of SOAP 
   * Also with help of wizdlar extension in webstore we copy the Add data 
   * Paste it in .xml file inside project by placing int values in its position of data 2,3
   */
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\SoapRequest\\Add.xml");
		
		String request = IOUtils.toString(file, "UTF-8");
		
		baseURI = "http://www.dneonline.com";
		
		given()
			.contentType("text/xml")
			.accept(ContentType.XML)
			.body(request).
		when()
			.post("/calculator.asmx").
		then().statusCode(200)
		.log().all()
		.and()
	/* Data of response body will be copied from dneonline.com and paste in free formatter.com
	 * We try to get the x path for it by using xpath tester 
	 * with changing int value 5 we are expecting
	 * And result of int values given is found as end result of test
	 */
		.body("//*:AddResult.text()", equalTo("5"))
		.and()
		
	/* After cpy the response of Add data and paste in freeformatter.com we try to convert xml file to xsd (shema definition) file
	 * We need to keep xsd (schema definition) file in class path which is src/main/resource: project/buildpath/configurebuildpath in eclipse
	 */
		
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("Calculator.xsd"));
    // If we get any errors while executing we can take help stackoverflow website	
		
	}

}
