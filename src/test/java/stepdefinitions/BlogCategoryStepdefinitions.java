package stepdefinitions;

import config_Requirements.ConfigLoader;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import utilities.API_Utilities.API_Methods;
import utilities.API_Utilities.TestData;
import static stepdefinitions.API_Stepdefinitions.jsonObjectRequest;
import static stepdefinitions.API_Stepdefinitions.*;

import static org.hamcrest.Matchers.equalTo;

public class BlogCategoryStepdefinitions {

    public static Response response;
    public static JsonPath jsonPath;
    public static String exceptionMesaj;
    public static ConfigLoader configLoader = new ConfigLoader();
    public static JSONObject requestBody;
    public static TestData builder = new TestData(); // Assuming you have a TestData builder class

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
    public void the_api_user_prepares_a_post_request_containing_and_information_to_send_to_the_api_add_blog_category_endpoint(String string, String string2) {
        jsonObjectRequest.put( "name" , "New Blog Category") ;
        jsonObjectRequest.put( "description" , "blog category description.") ;
        System.out.println("postbody" + jsonObjectRequest);
    }

    @Given("The api user sends a {string} request and saves the returned response.")
    public void the_api_user_sends_a_request_and_saves_the_returned_response(String string) {
    }

    @Given("The api user verifies that the {string} is {string} by sending a GET request to the {string} {string} endpoint with the {string} returned in the response body.")
    public void the_api_user_verifies_that_the_is_by_sending_a_get_request_to_the_endpoint_with_the_returned_in_the_response_body(String string, String string2, String string3, String string4, String string5) {
    }

}