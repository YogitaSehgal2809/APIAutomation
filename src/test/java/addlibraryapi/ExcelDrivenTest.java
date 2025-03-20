package addlibraryapi;

import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import resources.ExcelUtility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ExcelDrivenTest {

@Test
    public void addBook() throws IOException {
        HashMap<String, Object>  jsonAsMap = new HashMap<>();
        ExcelUtility e=new ExcelUtility();
        ArrayList<String> a=e.getData("RestAddBook","RestAssured");
        jsonAsMap.put("name",a.get(1));
        jsonAsMap.put("isbn",a.get(2));
        jsonAsMap.put("aisle",a.get(3));
        jsonAsMap.put("author",a.get(4));
//        for nested json create another map, put as jsonAsMap.put(key,map);
        RestAssured.baseURI="http://216.10.245.166";
        //for post request header type is mandatory
        //we can send parameters dynamically from test to create payload
        //and can run multiple times with different set of data, make it unique and runs everytime and does not fails on creating duplicate records
        String addBookResponse=given().header("Content-Type","application/json").
                body(jsonAsMap).when().post("/Library/Addbook.php")
                .then().statusCode(200).
                extract().response().asString();
        JsonPath js= ReusableMethods.rawToJson(addBookResponse);
        String id=js.get("ID");
        System.out.println("book got added with id: "+id);

        //delete added book so that the test can run everytime without failing
        String response=
                given()
                        .header("Content-Type","application/json").
                        body(Payload.deleteBook(id)).
                        when().
                        post("/Library/DeleteBook.php")
                        .then().statusCode(200).extract().response().asString();
        JsonPath js2=ReusableMethods.rawToJson(response);
        String msg=js2.get("msg");
        System.out.println(msg);
    }

}
