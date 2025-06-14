package stepdefinitions;

import hooks.HooksAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.API_Utilities.API_Methods;
import utilities.API_Utilities.ExcelReader;
import utilities.API_Utilities.TestData;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static stepdefinitions.API_Stepdefinitions.jsonObjectRequest;
import static stepdefinitions.API_Stepdefinitions.*;

public class ShopsStepDef {
    JSONObject jsonObjectRequest = new JSONObject();
    int shopId;
    TestData testDataEditShop = new TestData();

    @When("The api user verifies the information in the response body for the entry with the specified {int} index, including {string}, {string}, {string}, {string} and {string},{string},{string},{string},{string},{string},{string},{string}.")
    public void the_api_user_verifies_the_information_in_the_response_body_for_the_entry_with_the_specified_index_including_and(Integer dataIndex, String shop_code, String shop_name, String country_code, String tax_allow, String tax_number, String contact_no, String email, String address, String country_name, String state_name, String city_name, String postal_code) {
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

    @When("The api user validates the {int} index, including {string}, {string}, {string}, {string},{string},{string},{string},{string},{string},{string},{string},{string} and {string} contents of the data in the response body.")
    public void the_api_user_validates_the_index_including_and_contents_of_the_data_in_the_response_body(Integer dataIndex, String id, String shop_code, String shop_name, String country_code, String tax_allow, String tax_number, String contact_no, String email, String address, String country_name, String state_name, String city_name, String postal_code) {
        jsonPath = response.jsonPath();

        Assert.assertEquals(id,jsonPath.getString("data.id"));
        Assert.assertEquals(shop_code,jsonPath.getString("data.shop_code"));
        Assert.assertEquals(shop_name,jsonPath.getString("data.shop_name"));
        Assert.assertEquals(country_code,jsonPath.getString("data.country_code"));
        Assert.assertEquals(tax_allow,jsonPath.getString("data.tax_allow"));
        Assert.assertEquals(contact_no,jsonPath.getString("data.contact_no"));
        Assert.assertEquals(email,jsonPath.getString("data.email"));
        Assert.assertEquals(country_name,jsonPath.getString("data.country_name"));
        Assert.assertEquals(state_name,jsonPath.getString("data.state_name"));
        Assert.assertEquals(city_name,jsonPath.getString("data.city_name"));
        Assert.assertEquals(postal_code,jsonPath.getString("data.postal_code"));
        Assert.assertNull(jsonPath.getString("data.tax_number"));
        Assert.assertTrue(jsonPath.getString("data.address").contains(address));
    }

    @When("The api user prepares a post request body to send to the api addShop endpoint")
    public void the_api_user_prepares_a_post_request_body_to_send_to_the_api_add_shop_endpoint() {

        jsonObjectRequest.put("shop_title", "New Shop");
        jsonObjectRequest.put("description", "New Shop Desc");
        jsonObjectRequest.put("contact_no" , "12365478985");
        jsonObjectRequest.put("email" , "newshop@gmail.com");
        jsonObjectRequest.put("tax_allow", 1);
        jsonObjectRequest.put("address" , "New York City,USA");
        jsonObjectRequest.put("category" , 1);
        jsonObjectRequest.put("sub_category" , 3);

        System.out.println("POST BODY :" + jsonObjectRequest);


    }

    @Then("The api user sends a POST request and saves the returned response and save shop id.")
    public void the_api_user_sends_a_post_request_and_saves_the_returned_response_and_save_shop_id() {

        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(jsonObjectRequest.toString())
                .post(API_Methods.fullPath);

        response.prettyPrint();

        int shopId = response.jsonPath().getInt("data.added_shop_id");
        System.out.println("Shop ID: " + shopId);

        ExcelReader.isimAltindakiDegeriGuncelle("YUSUF", shopId);


    }

    @When("The api user prepares a post request body containing missing data to send to the api addShop endpoint.")
    public void theApiUserPreparesAPostRequestBodyContainingMissingDataToSendToTheApiAddShopEndpoint() {


        jsonObjectRequest.put("shop_title", "New Shop Updated");
        jsonObjectRequest.put("description", "New Shop Desc");
        jsonObjectRequest.put("contact_no" , "12365478985");
        jsonObjectRequest.put("email" , "newshop@gmail.com");
        jsonObjectRequest.put("tax_allow", 1);
        jsonObjectRequest.put("category" , 1);
        jsonObjectRequest.put("sub_category" , 3);

        System.out.println("POST BODY :" + jsonObjectRequest);

    }

    @When("The api user prepares a post request without any data to send to the api addShop endpoint.")
    public void theApiUserPreparesAPostRequestWithoutAnyDataToSendToTheApiAddShopEndpoint() {

        jsonObjectRequest = new JSONObject();

    }

    @When("The api user prepares a patch request body to send to the api editShop endpoint")
    public void theApiUserPreparesAPatchRequestBodyToSendToTheApiEditShopEndpoint() {

        jsonObjectRequest.put("shop_title", "New Shop Updated");
        jsonObjectRequest.put("description", "New Shop Desc");
        jsonObjectRequest.put("contact_no", "12365478985");
        jsonObjectRequest.put("email", "newshop@gmail.com");
        jsonObjectRequest.put("tax_allow", 1);
        jsonObjectRequest.put("address", "New York City,USA");
        jsonObjectRequest.put("category", 1);
        jsonObjectRequest.put("sub_category", 5);

        System.out.println("JSON BODY : ==>" + jsonObjectRequest);
    }

    @Then("The api user sends a PATCH request and save the returned response.")
    public void theApiUserSendsAPATCHRequestAndSaveTheReturnedResponse() {

        response =given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(jsonObjectRequest.toString())
                .patch(API_Methods.fullPath);

        response.prettyPrint();
    }

    @When("The api user prepares a patch request body to sends to the api editShop endpoint")
    public void theApiUserPreparesAPatchRequestBodyToSendsToTheApiEditShopEndpoint() {

        jsonObjectRequest= testDataEditShop.editShopRequestBody();
    }



    @When("The api user prepares a patch request body with no data to sends to the api editShop endpoint")
    public void theApiUserPreparesAPatchRequestBodyWithNoDataToSendsToTheApiEditShopEndpoint() {
        jsonObjectRequest = new JSONObject();
    }

    @And("The api user sends a PATCH request, saves the returned response, and verifies that the status code is {int} with the reason phrase Unauthorized.")
    public void theApiUserSendsAPATCHRequestSavesTheReturnedResponseAndVerifiesThatTheStatusCodeIsWithTheReasonPhraseUnauthorized(int code) {
        try {
            response = given()
                    .spec(HooksAPI.spec)
                    .when()
                    .body(jsonObjectRequest.toString())
                    .patch(API_Methods.fullPath);
        } catch (Exception e) {

            exceptionMesaj = e.getMessage();
        }
        System.out.println("HATA MESAJI :" + exceptionMesaj);
        Assert.assertEquals(configLoader.getApiConfig("unauthorizedExceptionMessage"), exceptionMesaj);
    }

    @When("The api user validates including {string}, {string}, {string}, {string}, {int}, {string}, {int}, {int} contents of the data in the response body.")
    public void the_api_user_validates_including_contents_of_the_data_in_the_response_body(String shop_title, String description, String contact_no, String email, Integer tax_allow, String address, Integer category, Integer sub_category) {

        jsonPath = response.jsonPath();


        Assert.assertEquals(description, jsonPath.getString("data.description"));
        Assert.assertEquals(contact_no, jsonPath.getString("data.contact_no"));
        Assert.assertEquals(email, jsonPath.getString("data.email"));
        Assert.assertEquals(tax_allow, Integer.valueOf(jsonPath.getInt("data.tax_allow")));
        Assert.assertEquals(category, Integer.valueOf(jsonPath.getInt("data.category")));
        Assert.assertEquals(sub_category, Integer.valueOf(jsonPath.getInt("data.sub_category")));
        Assert.assertEquals(address, jsonPath.getString("data.address"));
        Assert.assertEquals(shop_title, jsonPath.getString("data.shop_title"));


    }


    @And("The api user verifies that the email information is {string}")
    public void theApiUserVerifiesThatTheEmailInformationIs(String value) {

        response.then()
                .assertThat()
                .body("data.email", equalTo(value));
    }

    @Then("The api user send a POST request and saves the returned response to Shop.")
    public void the_api_user_send_a_post_request_and_saves_the_returned_response_to_shop() {

        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(jsonObjectRequest.toString())
                .post(API_Methods.fullPath);

        response.prettyPrint();

    }

    @Then("The api user sets {string} path parameters for edit shop.")
    public void the_api_user_sets_path_parameters_for_edit_shop(String params) {
        int shopId = ExcelReader.isimAltindakiDegeriGetir("YUSUF");
        String pathParams = params + "/" + shopId;
        API_Methods.pathParam(pathParams);
    }
}
