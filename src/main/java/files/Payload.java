package files;

public class Payload {

	public static String AddPlace()
	{
		String payload="{\n"
				+ "  \"location\": {\n"
				+ "    \"lat\": -38.383494,\n"
				+ "    \"lng\": 33.427362\n"
				+ "  },\n"
				+ "  \"accuracy\": 50,\n"
				+ "  \"name\": \"Sweet Home\",\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\n"
				+ "  \"types\": [\n"
				+ "    \"shoe park\",\n"
				+ "    \"shop\"\n"
				+ "  ],\n"
				+ "  \"website\": \"http://google.com\",\n"
				+ "  \"language\": \"French-IN\"\n"
				+ "}";
		return payload;
	}

	public static String PutPlace(String placeId,String address)
	{
		String payload="{\n"
				+ "\"place_id\":\""+placeId+"\",\n"
				+ "\"address\":\""+address+"\",\n"
				+ "\"key\":\"qaclick123\"\n"
				+ "}";
		return payload;
	}

	public static String complexJson()
	{
		return "{\n"
				+ "\n"
				+ "\"dashboard\": {\n"
				+ "\n"
				+ "\"purchaseAmount\": 962,\n"
				+ "\n"
				+ "\"website\": \"rahulshettyacademy.com\"\n"
				+ "\n"
				+ "},\n"
				+ "\n"
				+ "\"courses\": [\n"
				+ "\n"
				+ "{\n"
				+ "\n"
				+ "\"title\": \"Selenium Python\",\n"
				+ "\n"
				+ "\"price\": 50,\n"
				+ "\n"
				+ "\"copies\": 6\n"
				+ "\n"
				+ "},\n"
				+ "\n"
				+ "{\n"
				+ "\n"
				+ "\"title\": \"Cypress\",\n"
				+ "\n"
				+ "\"price\": 40,\n"
				+ "\n"
				+ "\"copies\": 4\n"
				+ "\n"
				+ "},\n"
				+ "\n"
				+ "{\n"
				+ "\n"
				+ "\"title\": \"RPA\",\n"
				+ "\n"
				+ "\"price\": 45,\n"
				+ "\n"
				+ "\"copies\": 10\n"
				+ "\n"
				+ "}\n"
				+ ",\n"
				+ "\n"
				+ "{\n"
				+ "\n"
				+ "\"title\": \"Appium\",\n"
				+ "\n"
				+ "\"price\": 52,\n"
				+ "\n"
				+ "\"copies\": 1\n"
				+ "\n"
				+ "}\n"
				+ "\n"
				+ "]\n"
				+ "\n"
				+ "}\n"
				+ "\n"
				+ "";	}

	public static String addBook(String isbn, String aisle) {
		return "{\n"
				+ "\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\n"
				+ "\"isbn\":\""+isbn+"\",\n"
				+ "\"aisle\":\""+aisle+"\",\n"
				+ "\"author\":\"John foe\"\n"
				+ "}";
	}

	public static String deleteBook(String id) {
		return "{\n"
				+ "    \"ID\":\""+id+"\"\n"
				+ "}";
	}
}
