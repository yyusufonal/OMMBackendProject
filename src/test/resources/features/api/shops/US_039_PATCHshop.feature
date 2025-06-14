Feature:US_039 As a provider, I want to be able to update the information of the shop with the specified id number via the API connection.
  @API
  Scenario: TC001 Verify that a PATCH request to /api/editShop/{id} with valid authorization and correct data
  (shop_title, description, contact_no, email, tax_allow, address, category, sub_category)
  returns status code 200, response_message “Shop  Updated successfully”, and that updated_shop_id in the response matches the path parameter id.

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editShop" path parameters for edit shop.
    When The api user prepares a patch request body to send to the api editShop endpoint
    Then The api user sends a PATCH request and save the returned response.
    When The api user verifies that the status code is 200.
    Then The api user verifies that the "response.response_message" information in the response body is "Shop updated successfully".
    Then The api user verifies that the "data.updated_shop_id" information in the response body is the same as the id path parameter in the endpoint.


  @API
  Scenario: TC002 Verify that a PATCH request to /api/editShop/{id} with valid authorization and correct data (shop_title, description)
  returns status code 200, response_message “Shop  Updated successfully”.

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editShop" path parameters for edit shop.
    When The api user prepares a patch request body to sends to the api editShop endpoint
    Then The api user sends a PATCH request and save the returned response.
    When The api user verifies that the status code is 200.
    Then The api user verifies that the "response.response_message" information in the response body is "Shop updated successfully".


  @API
  Scenario: TC003 Verify that a PATCH request to /api/editShop/{id} with valid authorization and no data is sent to the /api/editShop/{id} endpoint,
            returns status code is 203 and the response_message in the response body is "No data to update".

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editShop" path parameters for edit shop.
    When The api user prepares a patch request body with no data to sends to the api editShop endpoint
    Then The api user sends a PATCH request and save the returned response.
    When The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "No data to update ".


  @API
  Scenario: TC004 Verify that a PATCH request to /api/editShop/{id} with valid authorization and no id with  correct data
  (shop_title, description, contact_no, email, tax_allow, address, category, sub_category) returns status code 203, response_message “Id missing”,

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editShop" path parameters.
    When The api user prepares a patch request body to send to the api editShop endpoint
    Then The api user sends a PATCH request and save the returned response.
    When The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "Id missing".
  @API
  Scenario Outline: TC005 Verify that a PATCH request to /api/editShop/{id} with valid authorization and non-existent id with correct data
  (shop_title, description, contact_no, email, tax_allow, address, category, sub_category)
  returns status code 203, response_message “Failed to update shop.İnvalid id.”.

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editShop/<id>" path parameters.
    When The api user prepares a patch request body to send to the api editShop endpoint
    Then The api user sends a PATCH request and save the returned response.
    When The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "Failed to update shop.İnvalid id.".

    Examples:
      |id |
      |4545|

  @API
  Scenario Outline:  TC006 Verify that a PATCH request to /api/editShop with invalid token and valid data returns status code 401 and correct error message

    Given The api user constructs the base url with the "invalid" token.
    Then The api user sets "api/editShop/<id>" path parameters.
    And The api user prepares a patch request body to send to the api editShop endpoint
    And The api user sends a PATCH request, saves the returned response, and verifies that the status code is 401 with the reason phrase Unauthorized.

    Examples:
      |id |
      |101|
  @API
  Scenario:TC007 – This test verifies that the user data in the response body is updated correctly and includes all required fields after the update.
  (shop_title, description, contact_no, email, tax_allow, address, category, sub_category).

    Given The api user constructs the base url with the "provider" token.
    Then  The api user sets "api/shop-details" path parameters for edit shop.
    When The api user sends a GET request and saves the returned response.
    And The api user verifies that the email information is "newshop@gmail.com"






