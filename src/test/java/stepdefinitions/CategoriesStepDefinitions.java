package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class CategoriesStepDefinitions {

    Response response;
    JsonPath jsonPath;
    String baseUrl = "https://qa.onlinemastermarket.com/";

    @Given("The API user sets base URL with valid provider token")
    public void setBaseUrlWithToken() {
        // tokeni sabit olarak ekliyoruz
        baseURI = baseUrl;
        requestSpecification = given()
                .header("Authorization", "Bearer SZk44qHV59wMIlUGa256")
                .header("Content-Type", "application/json");
    }

    @And("The API user sets path {string}")
    public void setPath(String path) {

        response = requestSpecification.when().get(path);
        response.prettyPrint();
    }

    @And("The API user sends a GET request to blogCategories endpoint")
    public void sendGetRequest() {

    }

    @Then("The API user verifies that the status code is {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @And("The API user verifies that the response_message is {string}")
    public void verifyResponseMessage(String expectedMessage) {
        jsonPath = response.jsonPath();
        String actualMessage = jsonPath.getString("response_message");
        assertEquals(expectedMessage, actualMessage);
    }

    @And("The API user verifies that the response contains expected category fields")
    public void verifyCategoryFields() {
        jsonPath = response.jsonPath();

        assertNotNull(jsonPath.get("data[0].id"));
        assertNotNull(jsonPath.get("data[0].lang_id"));
        assertNotNull(jsonPath.get("data[0].name"));
        assertNotNull(jsonPath.get("data[0].slug"));
        assertNotNull(jsonPath.get("data[0].description"));
        assertNotNull(jsonPath.get("data[0].keywords"));
        assertNotNull(jsonPath.get("data[0].category_order"));
        assertNotNull(jsonPath.get("data[0].status"));
        assertNotNull(jsonPath.get("data[0].createdAt"));
        assertNotNull(jsonPath.get("data[0].createdBy"));
        assertNotNull(jsonPath.get("data[0].updatedAt"));
        assertNotNull(jsonPath.get("data[0].updatedBy"));
    }
}