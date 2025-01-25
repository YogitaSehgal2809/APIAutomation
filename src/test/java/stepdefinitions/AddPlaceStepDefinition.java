package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlacePayload;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class AddPlaceStepDefinition {

    RequestSpecification request;
    ResponseSpecification res;
    Response response;


    @Given("Add Place Payload is available")
    public void add_place_payload_is_available(){

        //validate if add place API is working as expected

        //given-input details - query param, headers, body - request
        //when-submit details - resource end point, http methods - send to api
        //then-validate response - get back
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
        AddPlacePayload p=new AddPlacePayload();
        p.setAccuracy(56);
        p.setAddress("#42, East Bhatia nagar, yamuna nagar");
        p.setPhone_number("9068054668");
        p.setLanguage("English");
        p.setName("Yogita Sehgal");
        p.setWebsite("http://google.com");
        List<String> l=new ArrayList<String>();
        l.add("type 3");
        l.add("type 4");
        p.setTypes(l);
        Location location=new Location();
        location.setLat(29.9);
        location.setLng(32.6);
        p.setLocation(location);
        request =given().log().all().spec(req).body(p);
        //Resource will get concatenated with baseURI
        res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }
    @When("User calls {string} API with Post http request")
    public void user_calls_api_with_post_http_request(String string) {

        response =request.when().log().all().post("/maps/api/place/add/json").then().spec(res).extract().response();
    }
    @Then("API call is success with status code {int}")
    public void api_call_is_success_with_status_code(Integer int1) {
       assertEquals(200,response.getStatusCode());


    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String ExpectedValue) {
String stringResponse=    response.asString();
        JsonPath js=new JsonPath(stringResponse);
        String actualStatuscode=js.get(keyValue).toString();
        assertEquals(ExpectedValue,actualStatuscode);

    }

}
