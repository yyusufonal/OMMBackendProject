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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
        assertTrue(product_name, jsonPath.getString("data.product_name").contains("Tangle-Free"));
        assertEquals(unit, jsonPath.getString("data.unit"));
        assertEquals(unit_name, jsonPath.getString("data.unit_name"));
        assertEquals(unit_value, jsonPath.getString("data.unit_value"));
        assertEquals(currency, jsonPath.getString("data.currency"));
        assertEquals(currency_code, jsonPath.getString("data.currency_code"));
        assertEquals(prices, jsonPath.getString("data.prices"));
        assertEquals(sales_price, jsonPath.getString("data.sales_price"));
        assertEquals(product_discount, jsonPath.getString("data.product_discount"));
        assertTrue(short_description, jsonPath.getString("data.short_description").contains("Jump Rope"));
        assertTrue(description, jsonPath.getString("data.description").contains("This jump rope is made"));
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
    }
}
