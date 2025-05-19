package stepdefinitions;

import hooks.HooksAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;
import utilities.API_Utilities.API_Methods;

import static io.restassured.RestAssured.given;
import static stepdefinitions.API_Stepdefinitions.*;


public class CouponsStepDefinitions {

    int createdCouponId;

    @Given("The api user validates the {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, and {string} in the response body.")
    public void theApiUserValidatesTheAndInTheResponseBody(String id, String service_id, String coupon_name, String coupon_type, String coupon_type_text, String coupon_percentage, String coupon_amount, String start_date, String end_date, String valid_days, String user_limit, String user_limit_count, String description, String service_title, String status) {
        response.then()
                .assertThat()
                .body("data.service_coupon_list[0].id", Matchers.equalTo(id))
                .body("data.service_coupon_list[0].service_id", Matchers.equalTo(service_id))
                .body("data.service_coupon_list[0].coupon_name", Matchers.equalTo(coupon_name))
                .body("data.service_coupon_list[0].coupon_type", Matchers.equalTo(coupon_type))
                .body("data.service_coupon_list[0].coupon_type_text", Matchers.equalTo(coupon_type_text))
                .body("data.service_coupon_list[0].coupon_percentage", Matchers.equalTo(coupon_percentage))
                .body("data.service_coupon_list[0].coupon_amount", Matchers.equalTo(coupon_amount))
                .body("data.service_coupon_list[0].start_date", Matchers.equalTo(start_date))
                .body("data.service_coupon_list[0].end_date", Matchers.equalTo(end_date))
                .body("data.service_coupon_list[0].valid_days", Matchers.equalTo(valid_days))
                .body("data.service_coupon_list[0].user_limit", Matchers.equalTo(user_limit))
                .body("data.service_coupon_list[0].user_limit_count", Matchers.equalTo(user_limit_count))
                .body("data.service_coupon_list[0].description", Matchers.equalTo(description))
                .body("data.service_coupon_list[0].service_title", Matchers.equalTo(service_title))
                .body("data.service_coupon_list[0].status", Matchers.equalTo(status));
    }

    @And("The api user stores the coupon id")
    public void theApiUserStoresTheCouponId() {
        jsonPath = response.jsonPath();
        createdCouponId = jsonPath.getInt("data.added_coupon_id");
        System.out.println("Coupon ID: " + createdCouponId);
    }


    @When("The api user creates a coupon with given data {int}, {int}, {string}, {int}, {int}, {string}")
    public void theApiUserCreatesCoupon(int service_id, int percentage, String start_date, int valid_days, int user_limit, String description) {
        String randomGeneratedCouponName = "NEW_COUPON_" + System.currentTimeMillis();
        jsonObjectRequest.put("service_id", service_id);
        jsonObjectRequest.put("coupon_name", randomGeneratedCouponName);
        jsonObjectRequest.put("percentage", percentage);
        jsonObjectRequest.put("start_date", start_date);
        jsonObjectRequest.put("valid_days", valid_days);
        jsonObjectRequest.put("user_limit", user_limit);
        jsonObjectRequest.put("description", description);
        System.out.println("POST BODY :" + jsonObjectRequest);
    }

    @When("The api user creates a list that contains fields to patch {int}, {int}, {string}")
    public void theApiUserCreatesExistingCouponWithSomeFields(int valid_days, int user_limit, String description) {
        jsonObjectRequest.put("valid_days", valid_days);
        jsonObjectRequest.put("user_limit", user_limit);
        jsonObjectRequest.put("description", description);
        System.out.println("POST BODY :" + jsonObjectRequest);
    }

    @When("Add coupon_id URL parameter to the URL.")
    public void addCouponIdURLParamToURL() {

    }

    @And("The api user sends a POST request with the body and saves the returned response.")
    public void theApiUserSendsAPOSTRequestWithTheBodyAndSavesTheReturnedResponse() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType("application/json")
                .body(jsonObjectRequest.toString())
                .when()
                .post(API_Methods.fullPath);
        response.prettyPrint();
    }

    @And("The api user sends a DELETE request with the body and saves the returned response.")
    public void theApiUserSendsADELETERequestWithTheBodyAndSavesTheReturnedResponse() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType("application/json")
                .when()
                .delete(API_Methods.fullPath);
        response.prettyPrint();
    }

    @And("The api user adds coupon_id URL parameter to {string}")
    public void theApiUserAddsCouponIdURLParamToURL(String url) {
        API_Methods.pathParam(url + createdCouponId);
        System.out.println("API URL: " + API_Methods.fullPath);
    }


    @And("The api user sends a PATCH request with the body and saves the returned response.")
    public void theApiUserSendsAPATCHRequestWithTheBodyAndSavesTheReturnedResponse() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType("application/json")
                .body(jsonObjectRequest.toString())
                .when()
                .patch(API_Methods.fullPath);
        response.prettyPrint();
    }




}



