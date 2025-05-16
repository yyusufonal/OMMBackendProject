Feature:US_037 As a provider, I should be able to access the detailed information of the shop with the specified id number via the API connection.

    Scenario:TC001 Verify that a GET request to /api/shop-details/{id} with valid authorization and correct ID
    returns status code 200,response_message “Shop Details”

      Given The api user constructs the base url with the "provider" token.
      Then The api user sets "api/shop-details/5" path parameters.
      When The api user sends a GET request and saves the returned response.
      Then The api user verifies that the status code is 200.
      And The api user verifies that the " response.response_message" information in the response body is "Shop Details".


    Scenario Outline: TC002 This test verifies that the user data in the response body includes and correctly returns all required fields:
           id, shop_code, shop_name, country_code, tax_allow, tax_number, contact_no, email, address, country_name,
           state_name, city_name, and postal_code.
    
      Given The api user constructs the base url with the "provider" token.
      Then The api user sets "api/shop-details/5" path parameters.
      When The api user sends a GET request and saves the returned response.
      And The api user validates the <dataIndex> index, including "<id>", "<shop_code>", "<shop_name>", "<country_code>","<tax_allow>","<tax_number>","<contact_no>","<email>","<address>","<country_name>","<state_name>","<city_name>" and "<postal_code>" contents of the data in the response body.

      Examples:
      | dataIndex |id| shop_code | shop_name    | country_code| tax_allow | tax_number |contact_no|email                 |address             |country_name|state_name   |city_name|postal_code|
      | 0         | 5|SHOP5GUZuT| Elegant Touch |             |  No       |            |2547896321|info@eleganttouch.com |9W6R+2C Boston      |USA (+1)    |Massachusetts|Boston   |96698      |


    Scenario: TC003 This test checks that a GET request to the /api/shop-details/{id} endpoint with valid authorization but without providing an ID returns
              a 203 status code and the response body includes the message "Id missing."

      Given The api user constructs the base url with the "provider" token.
      Then  The api user sets "api/shop-details" path parameters.
      When The api user sends a GET request and saves the returned response.
      Then The api user verifies that the status code is 203.
      And The api user verifies that the "response.response_message" information in the response body is "Id missing".

    Scenario: TC004 This test confirms that a GET request to the /api/shop-details/{id} endpoint with valid authorization but
              a non-existent ID returns a 203 status code and the response body contains the message "No Details found."

      Given The api user constructs the base url with the "provider" token.
      Then  The api user sets "api/shop-details/1255" path parameters.
      When The api user sends a GET request and saves the returned response.
      Then The api user verifies that the status code is 203.
      And The api user verifies that the "response.response_message" information in the response body is "No Details found".


    Scenario: TC005 This test verifies that a GET request to the /api/shop-details/{id} endpoint with an invalid API key returns
              a 401 Unauthorized status code and the response body includes the message "Invalid token or token missing."

      Given The api user constructs the base url with the "invalid" token.
      Then  The api user sets "api/shop-details/5" path parameters.
      And The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.