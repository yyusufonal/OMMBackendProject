package stepdefinitions;

import config_Requirements.ConfigLoader;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;


import static org.hamcrest.Matchers.*;
import static stepdefinitions.API_Stepdefinitions.jsonPath;
import static stepdefinitions.API_Stepdefinitions.response;

public class SubscriptionStepdefinitions {


	String  exceptionMesaj;
	ConfigLoader configLoader = new ConfigLoader();

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
}


