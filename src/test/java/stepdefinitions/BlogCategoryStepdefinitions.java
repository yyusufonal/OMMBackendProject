package stepdefinitions;

import config_Requirements.ConfigLoader;
import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import utilities.API_Utilities.API_Methods;
import utilities.API_Utilities.TestData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static stepdefinitions.API_Stepdefinitions.*;
import static stepdefinitions.API_Stepdefinitions.response;

public class BlogCategoryStepdefinitions {



    @Given("The api user verifies the information in the response body for the entry with the specified {int} index, including {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string} and {string}.")
    public void verify_response_data_by_index(int dataindex, String lang_id, String name, String slug, String description, String keywords, String category_order, String status, String createdAt, String createdBy, String updatedAt, String updatedBy) {
        API_Methods.response.then().assertThat().body(
                "data.blogs[" + dataindex + "].lang_id", equalTo(lang_id),
                "data.blogs[" + dataindex + "].name", equalTo(name),
                "data.blogs[" + dataindex + "].slug", equalTo(slug),
                "data.blogs[" + dataindex + "].description", equalTo(description),
                "data.blogs[" + dataindex + "].keywords", equalTo(keywords),
                "data.blogs[" + dataindex + "].category_order", equalTo(category_order),
                "data.blogs[" + dataindex + "].status", equalTo(status),
                "data.blogs[" + dataindex + "].createdAt", equalTo(createdAt),
                "data.blogs[" + dataindex + "].createdBy", equalTo(createdBy),
                "data.blogs[" + dataindex + "].updatedAt", equalTo(updatedAt),
                "data.blogs[" + dataindex + "].updatedBy", equalTo(updatedBy));
    }

    @Given("The api user verifies that the data in the response body includes {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string} and {string}.")
    public void verify_blog_category_data(String id, String lang_id, String name, String slug, String description, String keywords, String category_order, String status, String createdAt, String createdBy, String updatedAt, String updatedBy) {
        API_Methods.response.then().assertThat().body(
                "data[0].id", equalTo(id),
                "data[0].lang_id", equalTo(lang_id),
                "data[0].name", equalTo(name),
                "data[0].slug", equalTo(slug),
                "data[0].description", equalTo(description),
                "data[0].keywords", equalTo(keywords),
                "data[0].category_order", equalTo(category_order),
                "data[0].status", equalTo(status),
                "data[0].createdAt", equalTo(createdAt),
                "data[0].createdBy", equalTo(createdBy),
                "data[0].updatedAt", equalTo(updatedAt),
                "data[0].updatedBy", equalTo(updatedBy));
    }

    @Given("The api user prepares a POST request containing {string} and {string} information to send to the api addBlogCategory endpoint.")
    public void the_api_user_prepares_a_post_request_containing_and_information_to_send_to_the_api_add_blog_category_endpoint(String name, String description) {
        jsonObjectRequest.put( "name" , name) ;
        jsonObjectRequest.put( "description" , description) ;
        System.out.println("postbody" + jsonObjectRequest);
    }

    @Given("The api user sends a POST request and saves the returned response to blog category.")
    public void the_api_user_sends_a_post_request_and_saves_the_returned_response_to_blog_category() {

        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(jsonObjectRequest.toString())
                .post(API_Methods.fullPath);

        response.prettyPrint();

    }

    @Given("The api user verifies that the status code is {int} to blog category.")
    public void the_api_user_verifies_that_the_status_code_is_to_blog_category(Integer code) {
        response.then()
                .assertThat()
                .statusCode(code);
    }

    @Given("The api user verifies that in blog category {string} information in the response body is {string}.")
    public void the_api_user_verifies_that_in_blog_category_information_in_the_response_body_is(String key, String value) {
        response.then()
                .assertThat()
                .body(key,equalTo(value));
    }






}