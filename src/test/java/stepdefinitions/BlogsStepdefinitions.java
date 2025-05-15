package stepdefinitions;

import config_Requirements.ConfigLoader;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import static stepdefinitions.API_Stepdefinitions.response;

public class BlogsStepdefinitions {

    JsonPath jsonPath;
    String  exceptionMesaj;
    ConfigLoader configLoader = new ConfigLoader();


    @Then("The api user verifies the information in the response body for the entry with the specified {int} index, including {string}, {string}, {string}, {string} and {string}.")
    public void the_api_user_verifies_the_information_in_the_response_body_for_the_entry_with_the_specified_index_including_and(int dataIndex, String lang_id, String title, String slug, String tags, String summary) {
        jsonPath = response.jsonPath();
        Assert.assertEquals(lang_id, jsonPath.getString("data.blogs[" + dataIndex + "].lang_id"));
        Assert.assertEquals(title, jsonPath.getString("data.blogs[" + dataIndex + "].title"));
        Assert.assertTrue(jsonPath.getString("data.blogs[" + dataIndex + "].slug").contains(slug));
        Assert.assertEquals(tags, jsonPath.getString("data.blogs[" + dataIndex + "].tags"));
        Assert.assertTrue(jsonPath.getString("data.blogs[" + dataIndex + "].summary").contains(summary));

    }
}
