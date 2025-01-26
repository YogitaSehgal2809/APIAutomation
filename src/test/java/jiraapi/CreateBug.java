package jiraapi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import files.ReusableMethods;

public class CreateBug {
	
	public static void main(String[] args)
	{
		RestAssured.baseURI="https://yogitasehgal6629.atlassian.net/";
		String response=given()
		.header("Content-Type","application/json")
		.header("Authorization","Basic eW9naXRhc2VoZ2FsNjYyOUBnbWFpbC5jb206QVRBVFQzeEZmR0YwVFpIRkdzTTZkRlM3d3NhRkdyRnV2V3R5eGo4WUZQWVBtVVVIbEh5dlRpTDZrbTRXLTEtcjRTNTE2QVlBdlRkZjZaR3JmWFNpMDZkWEZoajNiWVUwa2RtR0d6NnJKR2Jmc3NlSmQtTDBkdUh5VlN3Ri14VjdyRFozLWpwbmhEVkFZdnFRWHhtejBVczRLQ2FjZTFaVFBZa1VXVkxtMWUxQzJEczZBYUtncFFnPTVCQzVFRTIx")
		.body("{\n"
				+ "\"fields\": {\n"
				+ "   \"project\":\n"
				+ "   { \n"
				+ "      \"key\": \"SAT\"\n"
				+ "   },\n"
				+ "   \"summary\": \"Links are not working\",\n"
				+ "   \"issuetype\": {\n"
				+ "      \"name\": \"Bug\"\n"
				+ "   }\n"
				+ "  }\n"
				+ "}")
		.log().all()
		.when().post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		JsonPath js=ReusableMethods.rawToJson(response);
		String issueId=js.getString("id");
		
		given()
		.pathParam("key", issueId)
		.header("Authorization","Basic eW9naXRhc2VoZ2FsNjYyOUBnbWFpbC5jb206QVRBVFQzeEZmR0YwVFpIRkdzTTZkRlM3d3NhRkdyRnV2V3R5eGo4WUZQWVBtVVVIbEh5dlRpTDZrbTRXLTEtcjRTNTE2QVlBdlRkZjZaR3JmWFNpMDZkWEZoajNiWVUwa2RtR0d6NnJKR2Jmc3NlSmQtTDBkdUh5VlN3Ri14VjdyRFozLWpwbmhEVkFZdnFRWHhtejBVczRLQ2FjZTFaVFBZa1VXVkxtMWUxQzJEczZBYUtncFFnPTVCQzVFRTIx")
		.header("X-Atlassian-Token","no-check")
		.multiPart("file",new File("/home/yogitasehgal/Pictures/Screenshots/Screenshot from 2025-01-16 16-13-56.png"))
		.log().all()
		.post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
	}

}
