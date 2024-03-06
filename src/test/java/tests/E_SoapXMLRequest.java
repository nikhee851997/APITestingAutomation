package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.FileInputStream;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

public class E_SoapXMLRequest 
{
	
	@Test
	public void validateSoapXML() throws Exception 
	{
		/* So we copy data from dneonline.com/WSDL:webservice description language of SOAP 
		 * Also with help of wizdlar extension in webstore we copy the Add data 
		 * Paste it in .xml file inside project by placing int values in its position of data 2,3
		 *  If the data in file is in bytes then we use FileInputStream
		 */
		
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\SoapRequest\\Add.xml");
// we can also get dependencies from reqres.in /doc/usage guide and select/what we need we can see all latest dependencies there.
		String request = IOUtils.toString(file, "UTF-8");
		
		baseURI = "http://www.dneonline.com";
		
		given()
			.contentType("text/xml")
			.accept(ContentType.XML)
			.body(request)
		    .when().post("/calculator.asmx")
		    .then().statusCode(200)
		    .log().all()
		    .and()
		    
        /* Data of response body will be copy from dneonline.com and paste in free formatter.com
		 * We try to get the x path for it by using xpath tester 
		 * With changing int value 5 we are expecting
		 * And result of int values given is found as end result of test
		*/
		    
		    .body("//*:AddResult.text()", equalTo("5"));
			
		
	}

}

