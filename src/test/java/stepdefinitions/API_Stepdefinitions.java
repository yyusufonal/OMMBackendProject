package stepdefinitions;

import config_Requirements.ConfigLoader;
import hooks.HooksAPI;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import pojos.AddBlogPojo;
import utilities.API_Utilities.API_Methods;
import utilities.API_Utilities.TestData;

import java.sql.SQLOutput;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class API_Stepdefinitions {
    JsonPath jsonPath;
    Response response;
    String  exceptionMesaj;
    ConfigLoader configLoader = new ConfigLoader();

    @Given("The api user constructs the base url with the {string} token.")
    public void the_api_user_constructs_the_base_url_with_the_token(String user) {
        HooksAPI.setUpApi(user);

    }
    @Then("The api user sets {string} path parameters.")
    public void the_api_user_sets_path_parameters(String pathParam) {

       API_Methods.pathParam(pathParam);
    }
    @When("The api user sends a GET request and saves the returned response.")
    public void the_api_user_sends_a_get_request_and_saves_the_returned_response() {
        response =given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);

        response.prettyPrint();


    }
    @When("The api user verifies that the status code is {int}.")
    public void the_api_user_verifies_that_the_status_code_is(Integer code) {

        response.then()
                .assertThat()
                .statusCode(code);
    }
    @When("The api user verifies that the {string} information in the response body is {string}.")
    public void the_api_user_verifies_that_the_information_in_the_response_body_is(String key, String value) {
        response.then()
                .assertThat()
                .body(key,equalTo(value));

    }

    @When("The api user verifies the information in the response body for the entry with the specified {int} index, including {string}, {string}, {string}, {string} and {string},{string},{string},{string},{string},{string},{string},{string}.")
    public void the_api_user_verifies_the_information_in_the_response_body_for_the_entry_with_the_specified_index_including_and(Integer dataIndex, String shop_code, String shop_name , String country_code, String tax_allow, String tax_number, String contact_no, String email, String address, String country_name, String state_name, String city_name, String postal_code) {
    jsonPath = response.jsonPath();

    Assert.assertEquals(shop_code,jsonPath.getString("data.shop_list[" +dataIndex+ "].shop_code"));
    Assert.assertEquals(shop_name,jsonPath.getString("data.shop_list[" +dataIndex+ "].shop_name"));
    Assert.assertEquals(country_code,jsonPath.getString("data.shop_list[" +dataIndex+ "].country_code"));
    Assert.assertEquals(tax_allow,jsonPath.getString("data.shop_list[" +dataIndex+ "].tax_allow"));
    Assert.assertNull(jsonPath.getString("data.shop_list["+ dataIndex +"].tax_number"));
    Assert.assertEquals(contact_no,jsonPath.getString("data.shop_list[" +dataIndex+ "].contact_no"));
    Assert.assertEquals(email,jsonPath.getString("data.shop_list[" +dataIndex+ "].email"));
    Assert.assertTrue(jsonPath.getString("data.shop_list["+ dataIndex+"].address").contains(address));
    Assert.assertEquals(country_name,jsonPath.getString("data.shop_list[" +dataIndex+ "].country_name"));
    Assert.assertEquals(city_name,jsonPath.getString("data.shop_list[" +dataIndex+ "].city_name"));
    Assert.assertEquals(postal_code,jsonPath.getString("data.shop_list[" +dataIndex+ "].postal_code"));
    Assert.assertEquals(state_name,jsonPath.getString("data.shop_list[" +dataIndex+ "].state_name"));



    }

    @When("The api user sends a GET request, saves the returned response, and verifies that the status code is {string} with the reason phrase Unauthorized.")
    public void the_api_user_sends_a_get_request_saves_the_returned_response_and_verifies_that_the_status_code_is_with_the_reason_phrase_unauthorized(String string) {
        try {
            response =given()
                    .spec(HooksAPI.spec)
                    .when()
                    .get(API_Methods.fullPath);
        } catch (Exception e) {

            exceptionMesaj = e.getMessage();
        }
        Assert.assertEquals(configLoader.getApiConfig("unauthorizedExceptionMessage"),exceptionMesaj);

    }




}