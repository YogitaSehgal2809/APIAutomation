package mapsapi;

import files.Payload;
import files.ReusableMethods;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {
	//adding test annotation to run test case
	@Test
	public void sumOfCourses()
	{
		JsonPath js=ReusableMethods.rawToJson(Payload.complexJson());
		int count =js.getInt("courses.size()");
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");

		//check if data is consistent with the purchase amount
		int actualAmount=0;
		for(int i=0;i<count;i++)
		{
			int coursePrice=js.get("courses["+i+"].price");
			int courseCopies=js.get("courses["+i+"].copies");
			actualAmount+=courseCopies*coursePrice;
		}
		Assert.assertEquals(actualAmount, purchaseAmount);

	}

}
