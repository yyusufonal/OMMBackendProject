package stepdefinitions;

import io.cucumber.java.en.When;
import org.junit.Assert;

import static stepdefinitions.API_Stepdefinitions.jsonPath;
import static stepdefinitions.API_Stepdefinitions.response;

public class Staff_Stepdefinitions {


    @When("The api user verifies the information in the response body for the entry with the specified <dataIndex> index, including {string}, {string}, {string}, {string} and {string},{string},{string},{string},{string},{string},{string},{string}.")
    public void theApiUserVerifiesTheInformationInTheResponseBodyForTheEntryWithTheSpecifiedDataIndexIndexIncludingAnd(Integer dataIndex, String provider_id, String first_name, String last_name, String country_code, String contact_no, String email, String dob, String gender, String profile_img, String designation, String exp_year, String exp_month, String status) {

        jsonPath = response.jsonPath();
        Assert.assertEquals(provider_id,jsonPath.getString("data.staff_list[" +dataIndex+ "].provider_id"));
        Assert.assertEquals(first_name,jsonPath.getString("data.staff_list[" +dataIndex+ "].first_name"));
        Assert.assertEquals(last_name,jsonPath.getString("data.staff_list[" +dataIndex+ "].last_name"));
        Assert.assertEquals(country_code,jsonPath.getString("data.staff_list[" +dataIndex+ "].country_code"));
        Assert.assertEquals(contact_no,jsonPath.getString("data.staff_list[" +dataIndex+ "].contact_no"));
        Assert.assertEquals(email,jsonPath.getString("data.staff_list[" +dataIndex+ "].email"));
        Assert.assertEquals(dob,jsonPath.getString("data.staff_list[" +dataIndex+ "].dob"));
        Assert.assertEquals(gender,jsonPath.getString("data.staff_list[" +dataIndex+ "].gender"));
        Assert.assertEquals(profile_img,jsonPath.getString("data.staff_list[" +dataIndex+ "].profile_img"));
        Assert.assertEquals(designation,jsonPath.getString("data.staff_list[" +dataIndex+ "].designation"));
        Assert.assertEquals(exp_year,jsonPath.getString("data.staff_list[" +dataIndex+ "].exp_year"));
        Assert.assertEquals(exp_month,jsonPath.getString("data.staff_list[" +dataIndex+ "].exp_month"));
        Assert.assertEquals(status,jsonPath.getString("data.staff_list[" +dataIndex+ "].status"));
    }


    }
