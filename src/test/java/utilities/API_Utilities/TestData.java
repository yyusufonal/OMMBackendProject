package utilities.API_Utilities;

import io.cucumber.java.hu.Ha;

import java.util.HashMap;

public class TestData{
    HashMap<String, Object> requestBody;

    public HashMap blogEditRequestBody() {

        requestBody = new HashMap<>();

        requestBody.put("title", "New Blog Updated");
        requestBody.put("summary", "Summary");
        requestBody.put("content", "Content");

        return requestBody;
    }

    public HashMap blogEditRequestBodySeyyid (){


        requestBody = new HashMap<>();
        requestBody.put("subscription_name", "Test Fee Updated");
        requestBody.put("fee", 99);
        requestBody.put("duration", 69);
        requestBody.put("fee_description", "iki tene bonty 1 tene bisket forwla");

        return requestBody;
    }




}
