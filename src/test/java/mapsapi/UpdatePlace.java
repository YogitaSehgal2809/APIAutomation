package mapsapi;

import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdatePlace {

	public static void main(String[] args) {

		//Add a place, update the address, view the updated place

		//Add place
		RestAssured.baseURI="https://rahulshettyacademy.com";
		//getting response and extracting response as string
		//Header is mentioned in case of update/post request
		String postResponse=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(Payload.AddPlace())
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

		//System.out.println(Response);
		//Parsing json - takes input as string and convert to JSON
		//JsonPath js=new JsonPath(postResponse);
		JsonPath js=ReusableMethods.rawToJson(postResponse);
		String placeId=js.getString("place_id");

		//System.out.println(placeId);
		//Update Place
		String newAddress="#42, abc city";

		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.PutPlace(placeId,newAddress)).
		when().put("/maps/api/place/update/json").
		then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

		//Get Place - asserting here itself

		//		given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId)
		//		.when().get("/maps/api/place/get/json").
		//		then().assertThat().statusCode(200).body("address", equalTo(newAddress));


		String getResponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId)
				.when().get("/maps/api/place/get/json").
				then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js1=ReusableMethods.rawToJson(getResponse);
		String actualAddress=js1.getString("address");
		//		System.out.println(actualAddress);

		//JUnit and TestNg frameworks are there for reporting
		Assert.assertEquals(actualAddress, newAddress);



	}

}
