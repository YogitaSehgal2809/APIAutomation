package mapsapi;

import io.restassured.RestAssured;
import pojo.AddPlacePayload;
import pojo.Location;


import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class AddPlaceUsingPojoClasses {

	public static void main(String[] args) {
		//validate if add place API is working as expected

		//given-input details - query param, headers, body
		//when-submit details - resource end point, http methods 
		//then-validate response

		AddPlacePayload p=new AddPlacePayload();
		p.setAccuracy(50);
		p.setAddress("#42, East Bhatia nagar");
		p.setPhone_number("9068054660");
		p.setLanguage("English");
		p.setName("Yogita");
		List<String> l=new ArrayList<String>();
		l.add("type 1");
		l.add("type 2");
		p.setTypes(l);
		Location location=new Location();
		location.setLat(29.6);
		location.setLng(32.4);
		p.setLocation(location);
		
		//Resource will get concatenated with baseURI
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response =given().log().all().queryParam("key", "qaclick123")
		.body(p)
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);

	}

}
