package com.petstore;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class PetStore {
	

	@Test(priority=3)
	public void findPet() {
		
			 RestAssured.baseURI = "https://petstore.swagger.io/v2";
			  String response = given().log().all().pathParam("id","1033").header("Content-Type","application/json")
			.when().get("/pet/1033")
			.then().log().all().assertThat().statusCode(200).extract().response().asString();
			
			JsonPath j = new JsonPath(response);
			int a = j.get("id");
			System.out.println(a);
			System.out.println(response);
		
	}
	
	@Test(priority=1)
	public void AddPet() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		String response1 = given().log().all().header("Content-Type","application/json")
		.body("{\r\n" + 
				"  \"id\": 1033,\r\n" + 
				"  \"category\": {\r\n" + 
				"    \"id\": 33,\r\n" + 
				"    \"name\": \"Pet\"\r\n" + 
				"  },\r\n" + 
				"  \"name\": \"doggie\",\r\n" + 
				"  \"photoUrls\": [\r\n" + 
				"    \"string\"\r\n" + 
				"  ],\r\n" + 
				"  \"tags\": [\r\n" + 
				"    {\r\n" + 
				"      \"id\": 10,\r\n" + 
				"      \"name\": \"Hutch\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"status\": \"available\"\r\n" + 
				"}")
		.when().post("/pet")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath j = new JsonPath(response1);
		System.out.println(j);
			
	}
	
	@DataProvider(name = "status")
	public Object[][] getStatus() {
	 return new Object[][] {{"sold"},{"available"}};
	}
	
	
	
	@Test(priority=2,dataProvider="status")
	public void findByStatus(String s) {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		String response2 = given().log().all().queryParam("status",s).header("Content-Type","application/json")
		.when().get("/pet/findByStatus")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(response2);
		System.out.println(js);
		System.out.println(response2);
		
	}
	
	@Test(priority = 4)
	public void Update() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		String response3 = given().log().all().header("Content-Type","application/json")
				.body("{\r\n" + 
						"    \"id\": 1033,\r\n" + 
						"    \"category\": {\r\n" + 
						"        \"id\": 33,\r\n" + 
						"        \"name\": \"Pet\"\r\n" + 
						"    },\r\n" + 
						"    \"name\": \"doggie\",\r\n" + 
						"    \"photoUrls\": [\r\n" + 
						"        \"string\"\r\n" + 
						"    ],\r\n" + 
						"    \"tags\": [\r\n" + 
						"        {\r\n" + 
						"            \"id\": 10,\r\n" + 
						"            \"name\": \"Hutch\"\r\n" + 
						"        }\r\n" + 
						"    ],\r\n" + 
						"    \"status\": \"Not available\"\r\n" + 
						"}")
				.when().put("/pet")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath change = new JsonPath(response3);
		System.out.println(change);
		System.out.println(response3);
	}

}
