package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;



public class RestfulAPI {

	@Then("{string} should match with the {string} in {string}")
	public void shouldMatchWithTheBackend(String element, String field, String service) {



		// Get the RequestSpecification of the request that you want to sent
		RequestSpecification httpRequest = RestAssured.given();

		//Specify API request URL and send
		Response response = null;
		switch (service) {
			case "businesslayerAPI-1":
				response = httpRequest.get("key");
				break;
			case "bbusinesslayerAPI-2":
				response = httpRequest.get("key2");
				break;
			case "tbusinesslayerAPI-3":
				response = httpRequest.get("key3");
				break;
		}
		//Print whole JSON response
		System.out.println("Response Body is =>  " + response.asString());
		JsonPath jsonPathEvaluator = response.jsonPath();

		//Define which part of the JSON file you want to get and print
		String accountDb = jsonPathEvaluator.get(field);
		System.out.println("Value in busines layer :" + accountDb);

		//Get text from frontend element
		String accountFront = elementLocator(element).getText();
		System.out.println("Value in UI layer :" + accountFront);

		//Assert
		Assert.assertEquals(accountDb, accountFront);


	}




}