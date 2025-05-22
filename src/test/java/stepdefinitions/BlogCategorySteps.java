package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class BlogCategorySteps {

    private Response response;
    private String baseUrl = "https://qa.onlinemastermarket.com/api";
    private String validToken = "SZk44qHV59wMIlUGa256";
    private String invalidToken = "SZk44qHV59wMIlUGa257";
    private String endpoint;
    private Map<String, String> requestBody;

    // ------------------ US06: Add Blog Category ------------------ //

    @Given("The API base URL is set")
    public void the_api_base_url_is_set() {
        RestAssured.baseURI = baseUrl;
    }

    @And("I set the \"Authorization\" header to \"API Key {string}\"")
    public void i_set_the_authorization_header(String apiKey) {
        this.validToken = apiKey;  // Update token for dynamic use
    }

    @Given("I have a new blog category with:")
    public void i_have_a_new_blog_category_with(io.cucumber.datatable.DataTable dataTable) {
        requestBody = dataTable.asMaps().get(0);
    }

    @When("I send a POST request to {string} with the blog category data")
    public void i_send_a_post_request_to_with_the_blog_category_data(String path) {
        response = given()
                .header("Authorization", validToken)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(baseUrl + path);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatusCode) {
        assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
    }

    @And("the response message should be {string}")
    public void the_response_message_should_be(String expectedMessage) {
        String actualMessage = response.jsonPath().getString("response.response_message");
        assertEquals(expectedMessage, actualMessage);
    }

    @And("the response should contain {string}")
    public void the_response_should_contain(String key) {
        assertNotNull(response.jsonPath().get(key));
    }

    // ------------------ US07: Get Blog Category Details ------------------ //

    @Given("the endpoint is set to blogCategory with id {int}")
    public void setEndpointWithId(int id) {
        endpoint = baseUrl + "/blogCategory/" + id;
    }

    @Given("no ID is provided in the blogCategory endpoint")
    public void setEndpointWithoutId() {
        endpoint = baseUrl + "/blogCategory/";
    }

    @When("a GET request is sent with valid token")
    public void sendGetWithValidToken() {
        response = given()
                .header("Authorization", validToken)
                .when()
                .get(endpoint);
    }

    @When("a GET request is sent with invalid token")
    public void sendGetWithInvalidToken() {
        response = given()
                .header("Authorization", invalidToken)
                .when()
                .get(endpoint);
    }

    @When("a GET request is sent without authorization")
    public void sendGetWithoutAuth() {
        response = when().get(endpoint);
    }

    @Then("the response message should be {string}")
    public void verifyResponseMessage(String message) {
        response.then().body("response.response_message", equalTo(message));
    }

    @Then("the blog category details should be present")
    public void verifyCategoryDetails() {
        response.then()
                .body("data[0].id", notNullValue())
                .body("data[0].lang_id", notNullValue())
                .body("data[0].name", notNullValue())
                .body("data[0].slug", notNullValue())
                .body("data[0].description", notNullValue())
                .body("data[0].keywords", notNullValue())
                .body("data[0].category_order", notNullValue())
                .body("data[0].status", notNullValue())
                .body("data[0].createdAt", notNullValue())
                .body("data[0].createdBy", notNullValue())
                .body("data[0].updatedAt", notNullValue())
                .body("data[0].updatedBy", notNullValue());
    }
}