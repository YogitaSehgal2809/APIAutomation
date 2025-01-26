package bookstoreapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class CreateAccount {
    @Test
    public void testCreateAccount()
    {
        RequestSpecification request= RestAssured.given();
        request.contentType(ContentType.JSON);
        request.body("{\n" +
                "  \"userName\": \"Yogita28091\",\n" +
                "  \"password\": \"QuickWin@123\"\n" +
                "}");
//        Using incorrect method
        Response response=request.post("https://demoqa.com/Account/v1/User");
//        Fetch status code
        int statusCode=response.statusCode();
        System.out.println("Status code "+statusCode);


    }
}
