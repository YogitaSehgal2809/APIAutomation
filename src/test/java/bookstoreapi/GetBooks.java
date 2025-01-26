package bookstoreapi;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetBooks {
	
	@Test
	public void getBookDetails() {
		
		// Specify the base URL to the RESTful web service 
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books"; 
		// Get the RequestSpecification of the request to be sent to the server. 
		RequestSpecification httpRequest = RestAssured.given(); 
		// specify the method type (GET) and the parameters if any. 
		//In this case the request does not take any parameters 
//		Response response = httpRequest.request(Method.GET, "");
//		or use
		Response response = httpRequest.get();

		// Print the status and message body of the response received from the server
		System.out.println("Status line received => " + response.getStatusLine());
//		System.out.println("Response=>" + response.prettyPrint());
//		System.out.println("Response Body is =>  " + response.asString());

//		method to get and validate the status code
		int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCode,200,"Fetched book details successfully");

		Headers headers=response.headers();
		for(Header header:headers){
			System.out.println(header.getName()+" : "+header.getValue());
		}
		System.out.println("Content Type : "+response.header("Content-Type"));
		Assert.assertEquals(response.header("Content-Type"),"application/json; charset=utf-8");


		
	}

}
