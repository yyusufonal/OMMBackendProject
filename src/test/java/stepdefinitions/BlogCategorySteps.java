package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class BlogCategorySteps {

    private Response response;
    private String baseUrl = "https://qa.onlinemastermarket.com/";
    private String apiKey = "SZk44qHV59wMIlUGa256"; //
    private String endpoint;
    private Map<String, String> requestBody;

    @Given("The API base URL is set")
    public void the_api_base_url_is_set() {
        RestAssured.baseURI = baseUrl;
    }

    @And("I set the \"Authorization\" header to \"API Key {string}\"")
    public void i_set_the_authorization_header(String apiKey) {
        this.apiKey = apiKey;
    }

    @Given("I have a new blog category with:")
    public void i_have_a_new_blog_category_with(io.cucumber.datatable.DataTable dataTable) {
        requestBody = dataTable.asMaps().get(0);
    }

    @When("I send a POST request to {string} with the blog category data")
    public void i_send_a_post_request_to_with_the_blog_category_data(String path) {
        response = given()
                .header("Authorization", apiKey)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(path);
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
}