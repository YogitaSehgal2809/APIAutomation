package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import resources.APIResource;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

//to use all methods of Utils class without creating Utils object
public class StepDefinition extends Utils {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;
    TestDataBuild data=new TestDataBuild();
//    for second execution of delete scenario, place id will be set to null if its not static
    public static String placeId;


    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        requestSpecification =given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));

        }
    @When("User calls {string} API with {string} http request")
    public void user_calls_api_with_post_http_request(String resource, String method) {
        APIResource APIRes=APIResource.valueOf(resource);
        responseSpecification=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if(method.equalsIgnoreCase("POST"))
            response = requestSpecification.when().post(APIRes.getResource());
        else if(method.equalsIgnoreCase("GET"))
            response = requestSpecification.when().get(APIRes.getResource());
    }
    @Then("API call is success with status code {int}")
    public void api_call_is_success_with_status_code(Integer int1) {
        Assert.assertEquals(response.getStatusCode(),int1);


    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String ExpectedValue) {
        Assert.assertEquals(ExpectedValue,getJsonPath(response,keyValue));

    }

    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
         placeId=getJsonPath(response, "place_id");
        requestSpecification =given().spec(requestSpecification()).queryParam("place_id",placeId);
        user_calls_api_with_post_http_request(resource,"GET");
        String actualName=getJsonPath(response, "name");
        Assert.assertEquals(actualName,expectedName);

    }

    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {
        requestSpecification =given().spec(requestSpecification()).body(data.DeletePlacePayload(placeId));

    }


}
