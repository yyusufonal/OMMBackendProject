package stepdefinitions;

import io.cucumber.java.en.When;
import org.junit.Assert;

import static stepdefinitions.API_Stepdefinitions.jsonPath;
import static stepdefinitions.API_Stepdefinitions.response;

public class ShopsStepDef {

    @When("The api user verifies the information in the response body for the entry with the specified {int} index, including {string}, {string}, {string}, {string} and {string},{string},{string},{string},{string},{string},{string},{string}.")
    public void the_api_user_verifies_the_information_in_the_response_body_for_the_entry_with_the_specified_index_including_and(Integer dataIndex, String shop_code, String shop_name , String country_code, String tax_allow, String tax_number, String contact_no, String email, String address, String country_name, String state_name, String city_name, String postal_code) {
        jsonPath = response.jsonPath();
        Assert.assertEquals(shop_code,jsonPath.getString("data.shop_list[" +dataIndex+ "].shop_code"));
        Assert.assertEquals(shop_name,jsonPath.getString("data.shop_list[" +dataIndex+ "].shop_name"));
        Assert.assertEquals(country_code,jsonPath.getString("data.shop_list[" +dataIndex+ "].country_code"));
        Assert.assertEquals(tax_allow,jsonPath.getString("data.shop_list[" +dataIndex+ "].tax_allow"));
        Assert.assertNull(jsonPath.getString("data.shop_list["+ dataIndex +"].tax_number"));
        Assert.assertEquals(contact_no,jsonPath.getString("data.shop_list[" +dataIndex+ "].contact_no"));
        Assert.assertEquals(email,jsonPath.getString("data.shop_list[" +dataIndex+ "].email"));
        Assert.assertTrue(jsonPath.getString("data.shop_list["+ dataIndex+"].address").contains(address));
        Assert.assertEquals(country_name,jsonPath.getString("data.shop_list[" +dataIndex+ "].country_name"));
        Assert.assertEquals(city_name,jsonPath.getString("data.shop_list[" +dataIndex+ "].city_name"));
        Assert.assertEquals(postal_code,jsonPath.getString("data.shop_list[" +dataIndex+ "].postal_code"));
        Assert.assertEquals(state_name,jsonPath.getString("data.shop_list[" +dataIndex+ "].state_name"));



    }
}
