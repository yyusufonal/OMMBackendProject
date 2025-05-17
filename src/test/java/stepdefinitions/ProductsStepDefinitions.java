package stepdefinitions;

import config_Requirements.ConfigLoader;
import hooks.HooksAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.API_Utilities.API_Methods;


import java.util.Map;

import static stepdefinitions.API_Stepdefinitions.jsonPath;
import static stepdefinitions.API_Stepdefinitions.response;


import static io.restassured.RestAssured.given;

public class ProductsStepDefinitions {

    JSONObject jsonObjectId = new JSONObject();

    @Given("The user add body parameters shopId")
    public void the_user_add_body_parameters_shop_id() {
        jsonObjectId.put("shop_id", 5);
    }

    @Given("The api user sends a GET request and user add body.")
    public void the_api_user_sends_a_get_request_and_user_add_body() {
        response =given()
                .spec(HooksAPI.spec)
                .when()
                .body(jsonObjectId.toString())
                .get(API_Methods.fullPath);
        response.prettyPrint();
    }

    @Given("The api user verifies that the {string} information in the response body is {string}, {string}, {string}, {string}, {string} and {string},{string},{string},{string},{string},{string},{string}.")
    public void the_api_user_verifies_that_the_information_in_the_response_body_is_and(String id, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11, String string12, String string13) {
        jsonPath = response.jsonPath();
        jsonPath.prettyPrint();
    }


    @When("the user sends a GET request to {string} without a shop_id")
    public void theUserSendsAGETRequestToWithoutAShop_id(String arg0) {
        jsonObjectId.put("shop_id","");
    }

    @When("the user sends a GET request to {string} with an unregistered shop_id")
    public void theUserSendsAGETRequestToWithAnUnregisteredShop_id(String response) {
        jsonObjectId.put("shop_id",89);
    }
}
