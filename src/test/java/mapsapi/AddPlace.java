package mapsapi;

import files.Payload;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class AddPlace {

	public static void main(String[] args) {
		//validate if add place API is working as expected

		//given-input details - query param, headers, body
		//when-submit details - resource end point, http methods 
		//then-validate response

		//Resource will get concatenated with baseURI
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.AddPlace())
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)");

	}

}
