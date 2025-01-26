package authorization;

import files.ReusableMethods;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import pojoclasses.Api;
import pojoclasses.GetCourses;
import pojoclasses.WebAutomation;

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
		for(int i=0;i<api.size();i++)
		{
			if(api.get(i).getCourseTitle().equals("SoapUI Webservices testing"))
				System.out.println(api.get(i).getPrice());
		}
		System.out.println("Courses under web automation are");
		List<WebAutomation> web=response2.getCourses().getWebAutomation();
		ArrayList<String> courseNamesActual=new ArrayList<String>();
		for(int i=0;i<web.size();i++)
		{
			System.out.println(web.get(i).getCourseTitle());
			courseNamesActual.add(web.get(i).getCourseTitle());
		}
		
		Assert.assertTrue(courseNamesActual.equals(courseNamesExpected));
	}

}
