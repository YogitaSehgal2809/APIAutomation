package addlibraryapi;

import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class JSONfromExternalSource {

	@Test
	public void addBook() throws IOException
	{

		//Since body() accepts string, convert externl JSON file content to string
		//this can be done by converting file content to byte then byte to string
		//there are 2 ways to fetch the file from system, by placing it on system, or by placing it in eclipse

		String Payload1=new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"//src//resources//Payload.json")));
		System.out.println(Payload1);
		String Payload2=new String(Files.readAllBytes(Paths.get("/home/yogitasehgal/Downloads/Payload.json")));
		System.out.println(Payload2);
		RestAssured.baseURI="http://216.10.245.166";
		
		String addBookResponse=given().header("Content-Type","application/json").
				body(Payload1).when().post("/Library/Addbook.php")
				.then().statusCode(200).
				extract().response().asString();
		JsonPath js=ReusableMethods.rawToJson(addBookResponse);
		String id=js.get("ID");
		System.out.println("book got added with id: "+id);
		
		
		
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
