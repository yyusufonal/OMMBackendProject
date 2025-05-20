Feature:US_038 As a provider, I want to be able to create a new shop record via the API connection.

  Scenario: TC001 Verify that a POST request to /api/addShop with valid authorization and correct data
            (shop_title, description, contact_no, email, tax_allow, address, category, sub_category) 
             returns status code 200 and response_message 'Shop added successfully'.
    
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/addShop" path parameters.
    When The api user prepares a post request body to send to the api addShop endpoint
    Then The api user sends a POST request and saves the returned response and save shop id.
    Then The api user verifies that the status code is 200.
    Then The api user verifies that the "response.response_message" information in the response body is "Shop added successfully".

  Scenario:TC002 Verify that the newly created Shop exists by using its ID

    Given The api user constructs the base url with the "provider" token.
    Then  The api user sets "api/shop-details" path parameters for edit shop.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 200.




  Scenario: TC003 Verify that a POST request to /api/addShop with valid authorization and missing data (address)
            (shop_title, description, contact_no, email, tax_allow, category, sub_category)
            returns status code 203 and response_message 'address is required'.

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/addShop" path parameters.
    When The api user prepares a post request body containing missing data to send to the api addShop endpoint.
    Then The api user send a POST request and saves the returned response to Shop.
    When The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "address is required".

  Scenario: TC004 Verify that a POST request to /api/addShop with valid authorization and empty body returns
            status code 203 and response message "No data for updated. Required fields empty\""

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/addShop" path parameters.
    When The api user prepares a post request without any data to send to the api addShop endpoint.
    Then The api user send a POST request and saves the returned response to Shop.
    And The api user verifies that the status code is 203.
    Then The api user verifies that the "response_message" information in the response body is "No data for updated. Required fields empty\"".

  Scenario: TC005 Verify that a POST request to /api/addShop with invalid authorization and correct data returns
            status code 401 and response message "Invalid token or token missing"

    Given The api user constructs the base url with the "invalid" token.
    Then The api user sets "api/addShop" path parameters.
    When The api user prepares a post request body to send to the api addShop endpoint
    Then The api user send a POST request and saves the returned response to Shop.
    When The api user verifies that the status code is 401.
    And The api user verifies that the "response.response_message" information in the response body is "Invalid token or token missing".



