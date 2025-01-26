package authorization;

import files.ReusableMethods;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import pojo.Api;
import pojo.GetCourses;
import pojo.WebAutomation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuthClientCredentials {

	public static void main(String[] args) {
		String[] courseNames= {"Selenium Webdriver Java","Cypress","Protractor"};
		List<String> courseNamesExpected=Arrays.asList(courseNames);
		
		String response =given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParam("grant_type", "client_credentials")
				.formParam("scope", "trust")
				.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").then().log().all().extract().response().asString();
		//use then().extract().response().asString() or when().post(url).asString()
		JsonPath js=ReusableMethods.rawToJson(response);
		String access_token=js.getString("access_token");
		GetCourses response2=given()
				.queryParam("access_token", access_token)
				.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourses.class);

		//		System.out.println(response2.getlinkedIn());
		//		System.out.println(response2.getInstructor());
		System.out.println("Price for Soap UI Course is");

		List<Api> api=response2.getCourses().getApi();
		for(Api a:api)
		{
			if(a.getCourseTitle().equals("SoapUI Webservices testing"))
				System.out.println(a.getPrice());
		}
		System.out.println("Courses under web automation are");

		ArrayList<String> courseNamesActual=new ArrayList<String>();
		List<WebAutomation> web=response2.getCourses().getWebAutomation();
		for(WebAutomation w:web)
		{
			System.out.println(w.getCourseTitle());
			courseNamesActual.add(w.getCourseTitle());
		}
		
		Assert.assertEquals(courseNamesActual,courseNamesExpected);
	}

}
