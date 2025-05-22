package utilities.API_Utilities;

import org.json.JSONObject;

import java.util.HashMap;

import static stepdefinitions.API_Stepdefinitions.jsonObjectRequest;
import static stepdefinitions.API_Stepdefinitions.*;

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

    public JSONObject editShopRequestBody(){

        jsonObjectRequest.put("shop_title", "New Shop Updated");
        jsonObjectRequest.put("description", "New Shop Desc" );
        System.out.println("JSON BODY ==> " +jsonObjectRequest);

        return jsonObjectRequest;
    }



}
