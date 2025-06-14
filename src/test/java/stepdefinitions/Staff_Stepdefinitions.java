package stepdefinitions;

import hooks.HooksAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.API_Utilities.API_Methods;

import static io.restassured.RestAssured.given;
import static stepdefinitions.API_Stepdefinitions.*;
import static stepdefinitions.API_Stepdefinitions.jsonObjectRequest;


public class Staff_Stepdefinitions {

    JSONObject jsonObjectRequest = new JSONObject();

    @When("The api user verifies the information in the response body for the entry with the specified <dataIndex> index, including {string}, {string}, {string}, {string} and {string},{string},{string},{string},{string},{string},{string},{string}.")
    public void theApiUserVerifiesTheInformationInTheResponseBodyForTheEntryWithTheSpecifiedDataIndexIndexIncludingAnd(Integer dataIndex, String provider_id, String first_name, String last_name, String country_code, String contact_no, String email, String dob, String gender, String profile_img, String designation, String exp_year, String exp_month, String status) {

        jsonPath = response.jsonPath();
        Assert.assertEquals(provider_id, jsonPath.getString("data.staff_list[" + dataIndex + "].provider_id"));
        Assert.assertEquals(first_name, jsonPath.getString("data.staff_list[" + dataIndex + "].first_name"));
        Assert.assertEquals(last_name, jsonPath.getString("data.staff_list[" + dataIndex + "].last_name"));
        Assert.assertEquals(country_code, jsonPath.getString("data.staff_list[" + dataIndex + "].country_code"));
        Assert.assertEquals(contact_no, jsonPath.getString("data.staff_list[" + dataIndex + "].contact_no"));
        Assert.assertEquals(email, jsonPath.getString("data.staff_list[" + dataIndex + "].email"));
        Assert.assertEquals(dob, jsonPath.getString("data.staff_list[" + dataIndex + "].dob"));
        Assert.assertEquals(gender, jsonPath.getString("data.staff_list[" + dataIndex + "].gender"));
        Assert.assertEquals(profile_img, jsonPath.getString("data.staff_list[" + dataIndex + "].profile_img"));
        Assert.assertEquals(designation, jsonPath.getString("data.staff_list[" + dataIndex + "].designation"));
        Assert.assertEquals(exp_year, jsonPath.getString("data.staff_list[" + dataIndex + "].exp_year"));
        Assert.assertEquals(exp_month, jsonPath.getString("data.staff_list[" + dataIndex + "].exp_month"));
        Assert.assertEquals(status, jsonPath.getString("data.staff_list[" + dataIndex + "].status"));
    }

    @And("The api user prepares a post request body to send to the api addStaff endpoint")
    public void the_api_user_prepares_a_post_request_body_to_send_to_the_api_addStaff_endpoint() {



        jsonObjectRequest.put("firstname", "Mar Edmont");
        jsonObjectRequest.put("mobileno", "Marc Edmont");
        jsonObjectRequest.put("email", "Marc Emont");
        jsonObjectRequest.put("gender", "Marc Emont");
        jsonObjectRequest.put("dob", "Marc Edmnt");
        jsonObjectRequest.put("shop_id", 15);
        jsonObjectRequest.put("about_emp", "Marc Edmont");


    }

    @Then("The api user sends a POST request and saves the returned response.")
    public void the_api_user_sends_a_post_request_and_saves_the_returned_response() {



        response =given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(jsonObjectRequest.toString())
                .post(API_Methods.fullPath);

        response.prettyPrint();
    }


    @And("The api user prepares an invalid post request body to send to the api addStaff endpoint")
    public void the_api_user_prepares_an_invalid_post_request_body_to_send_to_the_api_addStaff_endpoint() {



        jsonObjectRequest.put("firstnam", "Marc Edmont");
        jsonObjectRequest.put("mobileno", "Marc Edmont");
        jsonObjectRequest.put("email", "Marc Edmont");
        jsonObjectRequest.put("gender", "Marc Edmont");
        jsonObjectRequest.put("dob", "Marc Edmont");
        jsonObjectRequest.put("shop_id", 15);
        jsonObjectRequest.put("about_emp", "Marc Edmont");


    }

    @And("The api user prepares a post request body to send to the api addBlog endpointt")
    public void the_api_user_prepares_a_post_request_body_to_send_to_the_api_addStaff_endpointt() {



        jsonObjectRequest.put("id", "Mar Edmont");
        jsonObjectRequest.put("provider_id", "Marc Edmont");
        jsonObjectRequest.put("first_name", "Marc Emont");
        jsonObjectRequest.put("last_name", "Marc Emont");
        jsonObjectRequest.put("country_code", "Marc Edmnt");
        jsonObjectRequest.put("contact_no", 15);
        jsonObjectRequest.put("email", "Marc Edmont");
        jsonObjectRequest.put("password", "Marc Edmont");
        jsonObjectRequest.put("dob", "Marc Edmont");
        jsonObjectRequest.put("gender", "Marc Edmont");


    }

    @When("The api user prepares a patch request body to send to the api editStaff endpoint")
    public void theApiUserPreparesAPatchRequestBodyToSendToTheApiEditStaffEndpoint() {
        jsonObjectRequest.put("firstname", "Updated Name");
        jsonObjectRequest.put("mobileno", "5551234967");
        jsonObjectRequest.put("email", "updatedstafff@gmail.com");
        jsonObjectRequest.put("gender", "female");
        jsonObjectRequest.put("shop_id", 10);
        jsonObjectRequest.put("about_emp", "Updated employee info");

        System.out.println("JSON BODY : ==> " + jsonObjectRequest);
    }


    @Then("The api user sends a PATCH request and saves the returned responsee.")
    public void theApiUserSendsAPATCHRequestAndSavesTheReturnedResponsee() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(jsonObjectRequest.toString())
                .patch(API_Methods.fullPath);

        response.prettyPrint();
    }

    @When("The api user prepares a patch request body to send to the api editStaff endpointt")
    public void theApiUserPreparesAPatchRequestBodyToSendToTheApiEditStaffEndpointt() {
        jsonObjectRequest.put("firstname", "Updated Name");
        jsonObjectRequest.put("mobileno", "5551234967");


        System.out.println("JSON BODY : ==> " + jsonObjectRequest);
    }


}
/*
id, provider_id, first_name, last_name, country_code, contact_no, email, password, dob, gender
 */