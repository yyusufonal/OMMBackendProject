package stepdefinitions;

import com.google.gson.JsonObject;
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

    public static Response response;
    public static JsonPath jsonPath;
   // static JsonPath jsonPath;
    String  exceptionMesaj;
    ConfigLoader configLoader = new ConfigLoader();
    JSONObject jsonObjectRequest = new JSONObject();

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

       // response.prettyPrint();


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
        System.out.println("HATA MESAJI :" + exceptionMesaj);
        Assert.assertEquals(configLoader.getApiConfig("unauthorizedExceptionMessage"),exceptionMesaj);

    }




}