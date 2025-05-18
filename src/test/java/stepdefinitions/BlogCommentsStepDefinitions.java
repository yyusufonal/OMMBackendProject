package stepdefinitions;

import config_Requirements.ConfigLoader;
import hooks.HooksAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.json.JSONObject;
import utilities.API_Utilities.API_Methods;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.assertEquals;
import static stepdefinitions.API_Stepdefinitions.response;

public class BlogCommentsStepDefinitions {

    int extractedId;
    JSONObject jsonObjectRequest = new JSONObject();
    JsonPath jsonPath;
    String exceptionMesaj;
    ConfigLoader configLoader = new ConfigLoader();

    @Then("The api user verifies the information in the response body for the entry with the specified {int} index, including {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}.")
    public void the_api_user_verifies_the_blog_comment_fields(int dataIndex, String post_id, String user_id, String email, String name, String comment, String ip_address, String status, String created_at) {
        jsonPath = response.jsonPath();

        assertEquals(post_id, jsonPath.getString("data.blogs[" + dataIndex + "].post_id"));
        assertEquals(user_id, jsonPath.getString("data.blogs[" + dataIndex + "].user_id"));
        assertEquals(email, jsonPath.getString("data.blogs[" + dataIndex + "].email"));
        assertEquals(name, jsonPath.getString("data.blogs[" + dataIndex + "].name"));
        assertEquals(comment, jsonPath.getString("data.blogs[" + dataIndex + "].comment").trim());
        assertEquals(ip_address, jsonPath.getString("data.blogs[" + dataIndex + "].ip_address"));
        assertEquals(status, jsonPath.getString("data.blogs[" + dataIndex + "].status"));
        assertEquals(created_at, jsonPath.getString("data.blogs[" + dataIndex + "].created_at"));
    }

    @Given("The api user sets path parameter with blogComment id {int}")
    public void the_api_user_sets_path_parameter_with_blog_comment_id(Integer id) {
        API_Methods.pathParam("blogComment/" + id);
    }

    @Given("The api user sends a GET request to blogComment by id and saves the response")
    public void the_api_user_sends_a_get_request_to_blog_comment_by_id_and_saves_the_response() {
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);
        response.prettyPrint();
    }

    @Given("The api user verifies that the status code is {int} and response_message is {string}")
    public void the_api_user_verifies_that_the_status_code_is_and_response_message_is(Integer statusCode, String responseMessage) {
        response.then()
                .assertThat()
                .statusCode(statusCode)
                .body("response_message", equalTo(responseMessage));
    }

    @Then("The api user validates the {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string} fields in the response body.")
    public void validate_response_body_fields(String data_id, String post_id, String user_id, String email,
                                              String name, String comment, String ip_address, String status,
                                              String created_at) {

        JsonPath json = response.jsonPath();

        assertEquals(data_id, json.getString("data[0].id"));
        assertEquals(post_id, json.getString("data[0].post_id"));
        assertEquals(user_id, json.getString("data[0].user_id"));
        assertEquals(email, json.getString("data[0].email"));
        assertEquals(name, json.getString("data[0].name"));
        assertEquals(comment, json.getString("data[0].comment"));
        assertEquals(ip_address, json.getString("data[0].ip_address"));
        assertEquals(status, json.getString("data[0].status"));
        assertEquals(created_at, json.getString("data[0].created_at"));
    }


    @Given("The api user prepares a post request body to send to the api addBlogComment endpoint")
    public void the_api_user_prepares_a_post_request_body_to_send_to_the_api_add_blog_comment_endpoint() {
        jsonObjectRequest = new JSONObject();
        jsonObjectRequest.put("post_id", 2);
        jsonObjectRequest.put("name", "Anthony");
        jsonObjectRequest.put("email", "anthony@gmail.com");
        jsonObjectRequest.put("comment", "This blog helped me a lot, thanks!");
        System.out.println("Valid POST body for addBlogComment: " + jsonObjectRequest);



    }
    @Given("The api user sends a POST request to addBlogComment and saves the returned response.")
    public void the_api_user_sends_a_post_request_to_add_blog_comment_and_saves_the_returned_response() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .body(jsonObjectRequest.toString())
                .when()
                .post(API_Methods.fullPath);

        response.prettyPrint();
    }
    @Given("The api user prepares a post request body containing missing data to send to the api addBlogComment endpoint.")
    public void the_api_user_prepares_a_post_request_body_containing_missing_data_to_send_to_the_api_add_blog_comment_endpoint() {
        jsonObjectRequest = new JSONObject();
        jsonObjectRequest.put("post_id", 2);
        System.out.println("POST Body with missing data: " + jsonObjectRequest);
    }
    @Given("The api user prepares an empty POST request body.")
    public void the_api_user_prepares_an_empty_post_request_body() {
        jsonObjectRequest = new JSONObject();
        System.out.println("Empty POST body: " + jsonObjectRequest);
    }
    @Given("The api user prepares a POST request body with {string}, {string}, {string}, {string}.")
    public void the_api_user_prepares_a_post_request_body_with(String post_id, String name, String email, String comment) {
        jsonObjectRequest = new JSONObject();
        jsonObjectRequest.put("post_id", post_id);
        jsonObjectRequest.put("name", name);
        jsonObjectRequest.put("email", email);
        jsonObjectRequest.put("comment", comment);

        System.out.println("POST body: " + jsonObjectRequest);
    }
    @When("The api user prepares a patch request body to send to the api editBlogComment endpoint")
    public void the_api_user_prepares_a_patch_request_body_to_send_to_api_editblogcomment_endpoint() {

        jsonObjectRequest.put("name", "Anthony");
        jsonObjectRequest.put("email", "anthony@gmail.com");
        jsonObjectRequest.put("comment", "Please write more about this topic. Updated comment.");

        System.out.println("Request Body: " + jsonObjectRequest.toString());
    }
    @Then("The api user sends a PATCH request and records the response.")
    public void the_api_user_sends_a_patch_request_and_records_the_response() {

        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .body(jsonObjectRequest.toString())
                .when()
                .patch(API_Methods.fullPath);

        response.prettyPrint();
    }
    @When("The api user prepares a patch request body with no data to send to the api editBlogComment endpoint.")
    public void the_api_user_prepares_patch_body_with_no_data_to_send_to_edit_BlogCommentEndpoint() {

        jsonObjectRequest = new JSONObject();

        System.out.println("PATCH Body (empty): " + jsonObjectRequest);
    }

    @Then("The api user sends a PATCH request with no datas and save the returned response.")
    public void the_api_user_sends_a_patch_request_with_no_datas_and_save_the_returned_response() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(jsonObjectRequest.toString())
                .patch(API_Methods.fullPath);

        response.prettyPrint();
    }
    @Then("The api user verifies that the comment information is {string}")
    public void the_api_user_verifies_that_the_comment_information_is(String value) {
        response.then()
                .assertThat()
                .body("data[0].comment",equalTo(value));
    }



}
