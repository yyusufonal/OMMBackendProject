package stepdefinitions;

import config_Requirements.ConfigLoader;
import hooks.HooksAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.junit.Assert;
import pojos.AddBlogPojo;
import utilities.API_Utilities.API_Methods;
import utilities.API_Utilities.TestData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import static stepdefinitions.API_Stepdefinitions.response;

public class BlogsStepdefinitions {

    JsonPath jsonPath;
    String  exceptionMesaj;
    ConfigLoader configLoader = new ConfigLoader();
    JSONObject jsonObjectRequest = new JSONObject();
    HashMap<String, Object> hashMapRequest = new HashMap<>();
    TestData testData = new TestData();
    AddBlogPojo addBlogPojoRequest;

    @Then("The api user verifies the information in the response body for the entry with the specified {int} index, including {string}, {string}, {string}, {string} and {string}.")
    public void the_api_user_verifies_the_information_in_the_response_body_for_the_entry_with_the_specified_index_including_and(int dataIndex, String lang_id, String title, String slug, String tags, String summary) {
        jsonPath = response.jsonPath();
        Assert.assertEquals(lang_id, jsonPath.getString("data.blogs[" + dataIndex + "].lang_id"));
        Assert.assertEquals(title, jsonPath.getString("data.blogs[" + dataIndex + "].title"));
        Assert.assertTrue(jsonPath.getString("data.blogs[" + dataIndex + "].slug").contains(slug));
        Assert.assertEquals(tags, jsonPath.getString("data.blogs[" + dataIndex + "].tags"));
        Assert.assertTrue(jsonPath.getString("data.blogs[" + dataIndex + "].summary").contains(summary));

    }

    @And("The api user validates the {string}, {string}, {string}, {string}, {string} and {string} contents of the data in the response body.")
    public void the_api_user_validates_the_and_contents_of_the_data_in_the_response_body(String id, String lang_id, String title, String slug, String tags, String summary) {
        response.then()
                .assertThat()
                .body("data[0].id", equalTo(id),
                        "data[0].lang_id", equalTo(lang_id),
                        "data[0].title", containsString(title),
                        "data[0].slug", containsString(slug),
                        "data[0].tags", equalTo(tags),
                        "data[0].summary", containsString(summary));
    }


    @When("The api user prepares a post request body to send to the api addBlog endpoint")
    public void the_api_user_prepares_a_post_request_body_to_send_to_the_api_add_blog_endpoint() {
        jsonObjectRequest.put("title", "New Blog");
        jsonObjectRequest.put("category_id", 1);
        jsonObjectRequest.put("summary", "Blog Summary.");
        jsonObjectRequest.put("content", "Blog Content");
        System.out.println("Post Body : " + jsonObjectRequest);
    }

    @When("The api user sends a POST request and saves the returned response to Blog.")
    public void the_api_user_sends_a_post_request_and_saves_the_returned_response() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(addBlogPojoRequest)
                .post(API_Methods.fullPath);

        response.prettyPrint();
    }


    @When("The api user prepares a post request body containing missing data to send to the api addBlog endpoint.")
    public void the_api_user_prepares_a_post_request_body_containing_missing_data_to_send_to_the_api_add_blog_endpoint() {


        hashMapRequest.put("title", "New Blog");
        hashMapRequest.put("category_id", 1);
        hashMapRequest.put("summary", "Blog Summary.");
        System.out.println("Post Body : " + hashMapRequest);
    }

    @When("The api user prepares a post request without any data to send to the api addBlog endpoint.")
    public void the_api_user_prepares_a_post_request_without_any_data_to_send_to_the_api_add_blog_endpoint() {
    }

    @Then("The api user prepares a patch request body to send to the api editBlog endpoint")
    public void the_api_user_prepares_a_patch_request_body_to_send_to_the_api_edit_blog_endpoint() {
        hashMapRequest = testData.blogEditRequestBody();
        System.out.println("Patch Body : " + hashMapRequest);
    }
    @When("The api user sends a PATCH request and saves the returned response to Blog.")
    public void the_api_user_sends_a_patch_request_and_saves_the_returned_response() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(hashMapRequest)
                .patch(API_Methods.fullPath);

        response.prettyPrint();
    }

    @And("Thee api user verifies that the {string} information in the response body is the same as the id path parameter in the endpoint.")
    public void the_api_user_verifies_that_the_information_in_the_response_body_is_the_same_as_the_id_path_parameter_in_the_endpoint(String key) {
        jsonPath = response.jsonPath();
        int dataKey = Integer.parseInt(jsonPath.getString(key));
        Assert.assertEquals(dataKey, API_Methods.id);
    }

    @When("Thee api user sends a PATCH request, saves the returned response, and verifies that the status code is {string} with the reason phrase Unauthorized.")
    public void the_api_user_sends_a_patch_request_saves_the_returned_response_and_verifies_that_the_status_code_is_with_the_reason_phrase_unauthorized(String string) {
        try {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .when()
                    .body(hashMapRequest)
                    .patch(API_Methods.fullPath);
        } catch (Exception e) {
            exceptionMesaj = e.getMessage();
        }
        System.out.println("exceptionMesaj : " + exceptionMesaj);
        Assert.assertEquals(configLoader.getApiConfig("unauthorizedExceptionMessage"), exceptionMesaj);
    }


    @Then("The api user verifies that the content information is {string}")
    public void the_api_user_verifies_that_the_content_information_is(String content) {
        response.then()
                .assertThat()
                .body("data[0].content",equalTo(content));
    }

    @When("Thee api user sends a DELETE request and saves the returned response.")
    public void the_api_user_sends_a_delete_request_and_saves_the_returned_response() {
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .delete(API_Methods.fullPath);
        response.prettyPrint();
    }



}
