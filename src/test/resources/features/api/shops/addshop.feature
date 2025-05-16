Feature:US_038 As a provider, I want to be able to create a new shop record via the API connection.

  Scenario: TC001 Verify that a POST request to /api/addShop with valid authorization and correct data
            (shop_title, description, contact_no, email, tax_allow, address, category, sub_category) 
             returns status code 200 and response_message 'Shop added successfully'.(100-101-102-103 olusturulan shop idler)
    
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/addShop" path parameters.
    When The api user prepares a post request body to send to the api addShop endpoint
    Then The api user sends a POST request and saves the returned response
    Then The api user verifies that the status code is 200.
    Then The api user verifies that the "response.response_message" information in the response body is "Shop added successfully".

  Scenario:TC002



  Scenario: TC003 Verify that a POST request to /api/addShop with valid authorization and missing data (address)
            (shop_title, description, contact_no, email, tax_allow, category, sub_category)
            returns status code 203 and response_message 'address is required'.

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/addShop" path parameters.
    When The api user prepares a post request body containing missing data to send to the api addShop endpoint.
    Then The api user sends a POST request and saves the returned response
    When The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "address is required".

