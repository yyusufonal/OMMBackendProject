package stepdefinitions;

import config_Requirements.ConfigLoader;
import hooks.HooksAPI;
import io.cucumber.core.gherkin.messages.internal.gherkin.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.API_Utilities.API_Methods;
import utilities.API_Utilities.TestData;


import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static stepdefinitions.API_Stepdefinitions.jsonPath;
import static stepdefinitions.API_Stepdefinitions.response;

public class SubscriptionStepdefinitions {


	JSONObject jsonObjectRequest = new JSONObject();

	String  exceptionMesaj;

	ConfigLoader configLoader = new ConfigLoader();

	HashMap<String, Object> hashMapRequest = new HashMap<>();

	TestData testData = new TestData();

	@Then("The api user verifies the information in the response body for the entry with the specified {int} ,{string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string} and {string}.")
	public void the_api_user_verifies_the_information_in_the_response_body_for_the_entry_with_the_specified_and(int dataIndex, String id, String subscription_name, String fee, String currency_code, String duration, String fee_description, String subscription_content, String subscription_type, String status, String type) {
		jsonPath = response.jsonPath();
		Assert.assertEquals(subscription_name,jsonPath.getString("data[" +dataIndex+ "].subscription_name"));
		Assert.assertEquals(fee,jsonPath.getString("data[" +dataIndex+ "].fee"));
		Assert.assertEquals(currency_code,jsonPath.getString("data[" +dataIndex+ "].currency_code"));
		Assert.assertEquals(duration,jsonPath.getString("data[" +dataIndex+ "].duration"));
		Assert.assertEquals(fee_description,jsonPath.getString("data[" +dataIndex+ "].fee_description"));
		Assert.assertEquals(subscription_content,jsonPath.getString("data[" +dataIndex+ "].subscription_content"));
		Assert.assertEquals(subscription_type,jsonPath.getString("data[" +dataIndex+ "].subscription_type"));
		Assert.assertEquals(status,jsonPath.getString("data[" +dataIndex+ "].status"));
		Assert.assertEquals(type,jsonPath.getString("data[" +dataIndex+ "].type"));
	}

	@Then("The api user verifies the information in the response body for the entry with the specified <dataIndex> index , {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string} and {string}.")
	public void the_api_user_verifies_the_information_in_the_response_body_for_the_entry_with_the_specified_data_index_index_and(String id, String subscription_name,String fee,  String currency_code, String duration, String fee_description , String subscription_content, String subscription_type, String status, String type) {
		response.then()
				.assertThat()
				.body("data[0].id", equalTo(id),
						"data[0].subscription_name", equalTo(subscription_name),
						"data[0].fee", containsString(fee),
						"data[0].currency_code", containsString(currency_code),
						"data[0].duration", equalTo(duration),
						"data[0].fee_description", containsString(fee_description),
						"data[0].subscription_content", containsString(subscription_content),
						"data[0].subscription_type", containsString(subscription_type),
						"data[0].status", containsString(status),
						"data[0].type", containsString(type));

	}


	@Then("The api user verifies the information in the response body for the entry with the specified {int} index , {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string} and {string}.")
	public void the_api_user_verifies_the_information_in_the_response_body_for_the_entry_with_the_specified_index_and(Integer dataIndex,String subscription_name,String fee,  String currency_code, String duration, String fee_description , String subscription_content, String subscription_type, String status, String type) {
	jsonPath = response.jsonPath();

		Assert.assertEquals(subscription_name,jsonPath.getString("data.subscription[" +dataIndex+ "].subscription_name"));
		Assert.assertEquals(fee,jsonPath.getString("data.subscription[" +dataIndex+ "].fee"));
		Assert.assertEquals(currency_code,jsonPath.getString("data.subscription[" +dataIndex+ "].currency_code"));
		Assert.assertEquals(duration,jsonPath.getString("data.subscription[" +dataIndex+ "].duration"));
		Assert.assertEquals(fee_description,jsonPath.getString("data.subscription[" +dataIndex+ "].fee_description"));
		Assert.assertEquals(subscription_content,jsonPath.getString("data.subscription[" +dataIndex+ "].subscription_content"));
		Assert.assertEquals(subscription_type,jsonPath.getString("data.subscription[" +dataIndex+ "].subscription_type"));
		Assert.assertEquals(status,jsonPath.getString("data.subscription[" +dataIndex+ "].status"));
		Assert.assertEquals(type,jsonPath.getString("data.subscription[" +dataIndex+ "].type"));

	}


	@Then("The api user prepares a post request body to send to the api addSubscription endpoint")
	public void the_api_user_prepares_a_post_request_body_to_send_to_the_api_add_subscription_endpoint() {
//{
//"subscription_name":"Test Fee",
//"fee" :750,
//"duration":365,
//"fee_description":"365 Days"
//}
		jsonObjectRequest.put("subscription_name","Test Fee");
		jsonObjectRequest.put("fee",750);
		jsonObjectRequest.put("duration",365);
		jsonObjectRequest.put("fee_description","365 Days");
		System.out.println("Post subscription" + jsonObjectRequest);

	}
	@Then("The api user send a POST request and saves the returned response")
	public void the_api_user_send_a_post_request_and_saves_the_returned_response() {
		response = given()
				.spec(HooksAPI.spec)
				.contentType(ContentType.JSON)
				.when()
				.body(jsonObjectRequest.toString())
				.post(API_Methods.fullPath);

		response.prettyPrint();
	}
	@When("The api user sends a post request body containing missing data to send to the api addSubscribe endpoind")
	public void the_api_user_sends_a_post_request_body_containing_missing_data_to_send_to_the_api_add_subscribe_endpoind() {
		jsonObjectRequest.put("subscription_name","Test Fee");
		jsonObjectRequest.put("fee",750);
		jsonObjectRequest.put("duration",365);
		System.out.println("Post subscription" + jsonObjectRequest);
	}

	@Then("The api user prepares a post request without any data to send to the api daaSubscription endpoint")
	public void the_api_user_prepares_a_post_request_without_any_data_to_send_to_the_api_daa_subscription_endpoint() {

	}

	@When("The api user prepares a patch request body to send to the api editSubscription endpoint")
	public void the_api_user_prepares_a_patch_request_body_to_send_to_the_api_edit_blog_endpoint() {

		jsonObjectRequest.put("subscription_name", "Test Fee Update");
		jsonObjectRequest.put("fee",99);
		jsonObjectRequest.put("duration",69);
		jsonObjectRequest.put("fee_description", "iki tene bonty 1 tene bisket forwla");
		System.out.println("Patch subscription body" + jsonObjectRequest);


	}
	@When("The api user sends a PATCH request and saves the returned response.")
	public void the_api_user_sends_a_patch_request_and_saves_the_returned_response() {
		response = given()
				.spec(HooksAPI.spec)
				.contentType(ContentType.JSON)
				.when()
				.body(jsonObjectRequest.toString())
				.patch(API_Methods.fullPath);

		response.prettyPrint();
	}
	@When("The api user verifies that the {string} information in the response body is the same as the id path parameter in the endpoint.")
	public void the_api_user_verifies_that_the_information_in_the_response_body_is_the_same_as_the_id_path_parameter_in_the_endpoint(String key) {
		jsonPath = response.jsonPath();
		int dataKey = Integer.parseInt(jsonPath.getString(key));
		Assert.assertEquals(dataKey,API_Methods.id);
	}

	@Then("The api user prepares a patch request that does not contain any data to send to the api editsubscription endpoint.")
	public void the_api_user_prepares_a_patch_request_that_does_not_contain_any_data_to_send_to_the_api_edit_blog_endpoint() {

	}
	@Then("The api user sends a PATCH request, saves the returned response, and verifies that the status code is {string} with the reason phrase Unauthorized.")
	public void the_api_user_sends_a_patch_request_saves_the_returned_response_and_verifies_that_the_status_code_is_with_the_reason_phrase_unauthorized(String string) {

		try {
			response = given()
					.spec(HooksAPI.spec)
					.contentType(ContentType.JSON)
					.when()
					.body(jsonObjectRequest.toString())
					.patch(API_Methods.fullPath);
		} catch (Exception e) {
			exceptionMesaj= e.getMessage();
		}
		System.out.println("exceptionMesaj" + exceptionMesaj);
		Assert.assertEquals(configLoader.getApiConfig("unauthorizedExceptionMessage"),exceptionMesaj);
	}

	@Then("The api user verifies that the fee_description information is {string}.")
	public void the_api_user_verifies_that_the_fee_description_information_is(String feeDescription) {
		response.then()
				.assertThat()
				.body("data[0].fee_description",equalTo(feeDescription));
	}



}


