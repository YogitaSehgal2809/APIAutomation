package reqresapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DelayedResponseTest {
    @Test
    public void verifyDelayedResponse(){
        RequestSpecification request = RestAssured.given();
        Response response=request.get("https://reqres.in/api/users?delay=2");
        long responseTime=response.getTimeIn(TimeUnit.MILLISECONDS);
        System.out.println("Response time is : "+responseTime);
        System.out.println("Response time is : "+responseTime);

    }
}
