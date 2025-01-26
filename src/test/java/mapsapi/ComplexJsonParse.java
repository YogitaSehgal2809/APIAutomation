package mapsapi;

import files.Payload;
import files.ReusableMethods;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {

		JsonPath js=ReusableMethods.rawToJson(Payload.complexJson());

		//No of courses returned by API - size method returns the count of elements in an array
		int count =js.getInt("courses.size()");
		System.out.println("No of courses returned by API: "+count);

		//print purchase amount
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase amount: "+purchaseAmount);

		//first course title
		String  course1=js.getString("courses[0].title");
		System.out.println("first course title: "+course1);


		//print all course titles and their prices-dynamic array handling
		for(int i=0;i<count;i++)
		{
			String courseTitle=js.get("courses["+i+"].title");
			int coursePrice=js.get("courses["+i+"].price");
			System.out.println("title "+(i+1)+":"+courseTitle);
			System.out.println("price "+(i+1)+":"+coursePrice);
		}

		//print copies when title is RPA, array indexes might change

		for(int i=0;i<count;i++)
		{
			//			get titles for all courses
			String title=js.get("courses["+i+"].title");
			//			compare the title with RPA
			if(title.equalsIgnoreCase("RPA"))
			{
				System.out.println("Copies Sold for RPA: "+js.get("courses["+i+"].copies"));
				break;
			}
		}






	}
}