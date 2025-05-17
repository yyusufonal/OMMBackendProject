package stepdefinitions;

import config_Requirements.ConfigLoader;
import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import utilities.API_Utilities.API_Methods;
import static org.hamcrest.Matchers.equalTo;
import pojos.AddBlogPojo;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static stepdefinitions.API_Stepdefinitions.response;

public class BlogCommentsStepDefinitions {

    JSONObject jsonObjectRequest = new JSONObject();
    JsonPath jsonPath;
    String exceptionMesaj;
    ConfigLoader configLoader = new ConfigLoader();

    @Then("The api user verifies the information in the response body for the entry with the specified {int} index, including {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}.")
    public void the_api_user_verifies_the_blog_comment_fields(int dataIndex, String post_id, String user_id, String email, String name, String comment, String ip_address, String status, String created_at) {
        jsonPath = response.jsonPath();

        Assert.assertEquals(post_id, jsonPath.getString("data.blogs[" + dataIndex + "].post_id"));
        Assert.assertEquals(user_id, jsonPath.getString("data.blogs[" + dataIndex + "].user_id"));
        Assert.assertEquals(email, jsonPath.getString("data.blogs[" + dataIndex + "].email"));
        Assert.assertEquals(name, jsonPath.getString("data.blogs[" + dataIndex + "].name"));
        Assert.assertEquals(comment, jsonPath.getString("data.blogs[" + dataIndex + "].comment").trim());
        Assert.assertEquals(ip_address, jsonPath.getString("data.blogs[" + dataIndex + "].ip_address"));
        Assert.assertEquals(status, jsonPath.getString("data.blogs[" + dataIndex + "].status"));
        Assert.assertEquals(created_at, jsonPath.getString("data.blogs[" + dataIndex + "].created_at"));
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

        Assert.assertEquals(data_id, json.getString("data[0].id"));
        Assert.assertEquals(post_id, json.getString("data[0].post_id"));
        Assert.assertEquals(user_id, json.getString("data[0].user_id"));
        Assert.assertEquals(email, json.getString("data[0].email"));
        Assert.assertEquals(name, json.getString("data[0].name"));
        Assert.assertEquals(comment, json.getString("data[0].comment"));
        Assert.assertEquals(ip_address, json.getString("data[0].ip_address"));
        Assert.assertEquals(status, json.getString("data[0].status"));
        Assert.assertEquals(created_at, json.getString("data[0].created_at"));
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
        jsonObjectRequest = new JSONObject(); // tamamen boş
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

}
