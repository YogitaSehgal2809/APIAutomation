package ecommerceapi;
import files.ReusableMethods;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojoclasses.LoginRequest;
import pojoclasses.LoginResponse;
import pojoclasses.OrderDetails;
import pojoclasses.Orders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class EcommerceApi {
	public static void main(String[] args)
	{
//		LOGIN
		//to pass common prerequisites
		RequestSpecification reqSpecLogin= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();

//		Importing POJO class to create payload to send
		LoginRequest login=new LoginRequest();
		login.setUserEmail("anshika@gmail.com");
		login.setUserPassword("Iamking@000");

//		Adding extra parameters to spec
		RequestSpecification reqSpecLogin2=given().spec(reqSpecLogin).body(login);

//		Parsing response using JsonPath
//		String response=reqSpecLogin2.when().post("api/ecom/auth/login").then().extract().response().asString();
//		JsonPath js= ReusableMethods.rawToJson(response);

//		Parsing response using POJO classes
		LoginResponse response = reqSpecLogin2.when().post("api/ecom/auth/login").then().extract().response().as(LoginResponse.class);
		String token=response.getToken();
//		System.out.println(response.getToken());
		String userId=response.getUserId();

		RequestSpecification reqSpecAddProduct= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization",token
		).build();

//		ADD PRODUCT

//		System.out.println(userId);
		RequestSpecification addProductRequest=given().spec(reqSpecAddProduct).param("productName","ADIDAS TSHIRT").
				param("productAddedBy",userId).
				param("productCategory","fashion").param("productSubCategory","shirts")
				.param("productPrice", 11500).param("productDescription","Addias Originals")
				.param("productFor","men").
				multiPart("productImage",new File("/home/yogitasehgal/Pictures/Screenshots/Screenshot from 2025-01-21 16-55-01.png"));

	String addProductResponse=addProductRequest.when().post("/api/ecom/product/add-product").then().extract().response().asString();
	JsonPath js=ReusableMethods.rawToJson(addProductResponse);
	String productId=js.get("productId");
	String message2=js.get("message");
	System.out.println(message2);

//	PLACE ORDER FOR PRODUCT
		RequestSpecification reqSpecPlaceOrder= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
				setContentType(ContentType.JSON).addHeader("authorization",token).build();

		OrderDetails orderDetails=new OrderDetails();
		orderDetails.setCountry("India");
		orderDetails.setProductOrderedId(productId);
		List<OrderDetails> orderDetailsList=new ArrayList<OrderDetails>();
		orderDetailsList.add(orderDetails);
//		System.out.println(productId);
		Orders orders=new Orders();
		orders.setOrders(orderDetailsList);
		RequestSpecification reqSpecPlaceOrder2=given().spec(reqSpecPlaceOrder).body(orders);

		String responsePlaceOrder=reqSpecPlaceOrder2.when().post("api/ecom/order/create-order").asString();
		JsonPath responsePlaceOrder2=ReusableMethods.rawToJson(responsePlaceOrder);
		String message3=responsePlaceOrder2.get("message");
		System.out.println(message3);
		String orderId=responsePlaceOrder2.get("orders[0]");
//		System.out.println(orderId);

//		DELETE PRODUCT
		RequestSpecification reqSpecDeleteProduct= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
				addHeader("authorization",token).build();

		RequestSpecification reqSpecDeleteProduct2= given().spec(reqSpecDeleteProduct).pathParams("productId",productId);
		String deleteProductResponse=reqSpecDeleteProduct2.when().delete("/api/ecom/product/delete-product/{productId}").asString();
		JsonPath deleteProductResponse2=ReusableMethods.rawToJson(deleteProductResponse);
		String message4=deleteProductResponse2.get("message");
		System.out.println(message4);

//		DELETE ORDER
		RequestSpecification reqSpecDeleteOrder= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
				addHeader("authorization",token).build();
//		System.out.println(orderId);
		RequestSpecification reqSpecDeleteOrder2= given().spec(reqSpecDeleteOrder).pathParams("orderId",orderId);
		String deleteOrderResponse=reqSpecDeleteOrder2.when().delete("/api/ecom/order/delete-order/{orderId}").asString();
		JsonPath deleteOrderResponse2=ReusableMethods.rawToJson(deleteOrderResponse);
		String message5=deleteOrderResponse2.get("message");
		System.out.println(message5);

//		To bypass SSL/HTTP certification add
//		given().relaxedHTTPSValidation();




	}

}
