package addlibraryapi;

import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class AddDeleteBook {

	//parametrizing the test, using data provider so that it can run with multiple data sets
	//Add dataProvider attribute and parameters in method name
	@Test(dataProvider = "Data")
	public void addBook(String isbn,String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		//for post request header type is mandatory
		//we can send parameters dynamically from test to create payload
		//and can run multiple times with different set of data, make it unique and runs everytime and does not fails on creating duplicate records		
		String addBookResponse=given().header("Content-Type","application/json").
				body(Payload.addBook(isbn,aisle)).when().post("/Library/Addbook.php")
				.then().statusCode(200).
				extract().response().asString();
		JsonPath js=ReusableMethods.rawToJson(addBookResponse);
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

	//set of data to be sent for the test, need to be sent as an array, number of arrays sent will determine the number of runs
	@DataProvider(name="Data")
	public Object[][] getData() {
		return new Object[][]{{"test","10204"},{"test","10205"},{"test","10206"}};
	}

}
