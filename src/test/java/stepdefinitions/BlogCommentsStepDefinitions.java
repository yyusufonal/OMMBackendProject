package stepdefinitions;

import config_Requirements.ConfigLoader;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import static stepdefinitions.API_Stepdefinitions.response;

public class BlogCommentsStepDefinitions {

    JsonPath jsonPath;
    String  exceptionMesaj;
    ConfigLoader configLoader = new ConfigLoader();

    @Then("The api user verifies the information in the response body for the entry with the specified {int} index, including {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}.")
    public void the_api_user_verifies_the_blog_comment_fields(int dataIndex, String post_id, String user_id, String email, String name, String comment, String ip_address, String status, String created_at) {

        jsonPath = response.jsonPath();

        Assert.assertEquals(post_id,jsonPath.getString("data.blogs[" + dataIndex + "].post_id"));
        Assert.assertEquals(user_id,jsonPath.getString("data.blogs[" + dataIndex + "].user_id"));
        Assert.assertEquals(email,jsonPath.getString("data.blogs[" + dataIndex + "].email"));
        Assert.assertEquals(name,jsonPath.getString("data.blogs[" + dataIndex + "].name"));
        Assert.assertEquals(comment,jsonPath.getString("data.blogs[" + dataIndex + "].comment"));
        Assert.assertEquals(ip_address,jsonPath.getString("data.blogs[" + dataIndex + "].ip_address"));
        Assert.assertEquals(status,jsonPath.getString("data.blogs[" + dataIndex + "].status"));
        Assert.assertEquals(created_at,jsonPath.getString("data.blogs[" + dataIndex + "].created_at"));
    }
}
