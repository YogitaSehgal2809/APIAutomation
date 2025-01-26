package mapsapi;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoclasses.AddPlacePayload;
import pojoclasses.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class ReqSpecBuilderTest {

	public static void main(String[] args) {
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		//validate if add place API is working as expected

		//given-input details - query param, headers, body - request
		//when-submit details - resource end point, http methods - send to api
		//then-validate response - get back

		AddPlacePayload p=new AddPlacePayload();
		p.setAccuracy(50);
		p.setAddress("#42, East Bhatia nagar");
		p.setPhone_number("9068054660");
		p.setLanguage("Englishs");
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
		
		
		RequestSpecification request =given().log().all().spec(req).body(p);

		ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		Response response =request.when().post("/maps/api/place/add/json").
				then().spec(res).extract().response();
		String stringResponse=response.asString();
		System.out.println(stringResponse);

	}

}
