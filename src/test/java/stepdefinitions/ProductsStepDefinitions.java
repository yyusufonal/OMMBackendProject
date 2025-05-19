package stepdefinitions;

import config_Requirements.ConfigLoader;
import hooks.HooksAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.API_Utilities.API_Methods;


import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import static io.restassured.RestAssured.given;
import static stepdefinitions.API_Stepdefinitions.*;

public class ProductsStepDefinitions {

    JSONObject jsonObjectId = new JSONObject();
    JSONObject jsonObjectRequest = new JSONObject();

    @Given("The api user constructs the base url with the {string} tokenN.")
    public void theApiUserConstructsTheBaseUrlWithTheTokenN(String user) {
        HooksAPI.setUpApi(user);
    }

    @Then("The api user sets {string} path parametersS.")
    public void theApiUserSetsPathParametersS(String pathParam) {
        API_Methods.pathParam(pathParam);
    }

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
        System.out.println(jsonObjectId);
    }


    @When("the user sends a GET request to {string} without a shop_id")
    public void theUserSendsAGETRequestToWithoutAShop_id(String arg0) {
        jsonObjectId.put("shop_id","");
        response =given()
                .spec(HooksAPI.spec)
                .when()
                .body(jsonObjectId.toString())
                .get(API_Methods.fullPath);
        response.prettyPrint();
    }

    @When("the user sends a GET request to {string} with an unregistered shop_id")
    public void theUserSendsAGETRequestToWithAnUnregisteredShop_id(String arg01) {
        jsonObjectId.put("shop_id",89);
        response =given()
                .spec(HooksAPI.spec)
                .when()
                .body(jsonObjectId.toString())
                .get(API_Methods.fullPath);
        response.prettyPrint();
    }


    @And("The api user validates the {int} index, including {string}, {string}, {string}, {string},{string},{string},{string},{string},{string},{string},{string},{string} and {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} contents of the data in the response body.")
    public void the_api_user_validates_the_index_including_and_contents_of_the_data_in_the_response_body(Integer dataIndex,String id, String user_id, String manufactured_by, String shop_id, String category, String subcategory, String product_name, String unit, String unit_name, String unit_value, String currency, String currency_code, String prices, String sales_price, String product_discount, String short_description, String description, String category_name, String subcategory_name, String shop_name, String price, String sale_price, String discount) {
        jsonPath = response.jsonPath();


        assertEquals(id, jsonPath.getString( "data.id"));
        assertEquals(user_id, jsonPath.getString("data.user_id"));
        assertEquals(manufactured_by, jsonPath.getString("data.manufactured_by"));
        assertEquals(shop_id, jsonPath.getString("data.shop_id"));
        assertEquals(category, jsonPath.getString( "data.category"));
        assertEquals(subcategory, jsonPath.getString("data.subcategory"));
        assertTrue(product_name, jsonPath.getString("data.product_name").contains("New Test Product"));
        assertEquals(unit, jsonPath.getString("data.unit"));
        assertEquals(unit_name, jsonPath.getString("data.unit_name"));
        assertEquals(unit_value, jsonPath.getString("data.unit_value"));
        assertEquals(currency, jsonPath.getString("data.currency"));
        assertEquals(currency_code, jsonPath.getString("data.currency_code"));
        assertEquals(prices, jsonPath.getString("data.prices"));
        assertEquals(sales_price, jsonPath.getString("data.sales_price"));
        assertEquals(product_discount, jsonPath.getString("data.product_discount"));
        assertTrue(short_description, jsonPath.getString("data.short_description").contains("Test Short Desc."));
        assertTrue(description, jsonPath.getString("data.description").contains("Test Desc"));
        assertEquals(category_name, jsonPath.getString("data.category_name").trim());
        assertEquals(subcategory_name, jsonPath.getString("data.subcategory_name"));
        assertEquals(shop_name, jsonPath.getString("data.shop_name"));
        assertEquals(price, jsonPath.getString( "data.price"));
        assertEquals(sale_price, jsonPath.getString(  "data.sale_price"));
        assertEquals(discount, jsonPath.getString(  "data.discount"));
    }

    @When("the user sends a GET request to {string} with an unregistered productid")
    public void theUserSendsAGETRequestToWithAnUnregisteredProductid(String arg0) {
        jsonObjectId.put("shop_id",999);
        System.out.println(jsonObjectId);
    }

    @Then("The api user prepares a post request body to send to the api addProduct endpoint")
    public void theApiUserPreparesAPostRequestBodyToSendToTheApiAddProductEndpoint() {

        jsonObjectRequest.put("shop_id", 5);
        jsonObjectRequest.put("category", 1);
        jsonObjectRequest.put("subcategory", 2);
        jsonObjectRequest.put("product_name", "New Test Product");
        jsonObjectRequest.put("unit_value", 50);
        jsonObjectRequest.put("unit", 2);
        jsonObjectRequest.put("price", 200);
        jsonObjectRequest.put("discount", 0);
        jsonObjectRequest.put("sale_price", 200);
        jsonObjectRequest.put("short_description", "Test Short Desc.");
        jsonObjectRequest.put("description", "Test Desc");
        jsonObjectRequest.put("manufactured_by", "QuickHand Solitions");

        System.out.println("Request Body: " + jsonObjectRequest.toString());
    }

    @Then("The api user sends a POST request and saves the returned responses")
    public void theApiUserSendsAPOSTRequestAndSavesTheReturnedResponses() {
        response= given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(jsonObjectRequest.toString())
                .post(API_Methods.fullPath);
        response.prettyPrint();
    }

    @When("The api user prepares a post request body with missing required fields to send to the api addProduct endpoint")
    public void theApiUserPreparesAPostRequestBodyWithMissingRequiredFieldsToSendToTheApiAddProductEndpoint() {
        jsonObjectRequest = new JSONObject();
    }

    @Given("The api user prepares an empty POST request body")
    public void theApiUserPreparesAnEmptyPOSTRequestBody() {
        jsonObjectRequest.put("shop_id", "");
        jsonObjectRequest.put("category", "");
        jsonObjectRequest.put("subcategory", "");
        jsonObjectRequest.put("product_name", "");
        jsonObjectRequest.put("unit_value", "");
        jsonObjectRequest.put("unit", "");
        jsonObjectRequest.put("price", "");
        jsonObjectRequest.put("discount", "");
        jsonObjectRequest.put("sale_price", "");
        jsonObjectRequest.put("short_description", "");
        jsonObjectRequest.put("description", "");
        jsonObjectRequest.put("manufactured_by", "");

        System.out.println("Request Body: " + jsonObjectRequest.toString());
    }

    @Then("The api user sends a POSTT request and saves the returned responses")
    public void theApiUserSendsAPOSTTRequestAndSavesTheReturnedResponses() {
        response= given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(jsonObjectRequest.toString())
                .post(API_Methods.fullPath);
        response.prettyPrint();
    }

    @When("The api user prepares a patch request body to send to the api editProduct endpoint")
    public void theApiUserPreparesAPatchRequestBodyToSendToTheApiEditProductEndpoint() {

        jsonObjectRequest.put("product_name", "New Test Product");
        jsonObjectRequest.put("price", 200);
        jsonObjectRequest.put("short_description", "Test Short Desc.");
        jsonObjectRequest.put("description", "Test Desc");

        System.out.println("Request Body: " + jsonObjectRequest.toString());
    }

    @Then("The api user sends a PATCHH request and save the returned response.")
    public void theApiUserSendsAPATCHHRequestAndSaveTheReturnedResponse() {
        response =given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(jsonObjectRequest.toString())
                .patch(API_Methods.fullPath);

        response.prettyPrint();
    }

    @When("The api user verifies that the statusS code is {int}.")
    public void theApiUserVerifiesThatTheStatusSCodeIs(int code) {
        response.then()
                .assertThat()
                .statusCode(code);
    }


    @Then("The api user verifies that the {string} information in the responses body is {string}.")
    public void theApiUserVerifiesThatTheInformationInTheResponsesBodyIs(String key, String value) {
        response.then()
                .assertThat()
                .body(key,equalTo(value));
    }

    @When("The api user prepares a patch request body to send to empty data the api editProduct endpoint")
    public void theApiUserPreparesAPatchRequestBodyToSendToEmptyDataTheApiEditProductEndpoint() {
        jsonObjectRequest = new JSONObject();
    }

    @Then("The api user verifies that the {string} information in the responsee body is {string}.")
    public void theApiUserVerifiesThatTheInformationInTheResponseeBodyIs(String key, String value) {
        try {
            response.then()
                    .assertThat()
                    .body(key,equalTo(value));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("HATA MESAJI :" + exceptionMesaj);
        Assert.assertEquals(configLoader.getApiConfig("unauthorizedExceptionMessage"),exceptionMesaj);
    }

    @Then("The api user verifies that the {string} informationN in the response body is {string}.")
    public void theApiUserVerifiesThatTheInformationNInTheResponseBodyIs(String key, String value) {
        response.then()
                .assertThat()
                .body(key,equalTo(value));
    }
}
