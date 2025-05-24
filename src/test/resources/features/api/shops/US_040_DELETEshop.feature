Feature: US_040 As a provider, I want to be able to delete shop information with the specified id number via API connection.
  @API
  Scenario: TC001 Verify that a DELETE request to /api/deleteShop/{id} with valid authorization and correct id returns status
  code 200, response_message “Shop deleted successfully”, and that deleted_shop_id in the response matches the path
  parameter id.
    
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/deleteShop" path parameters for edit shop.
    Then The api user sends a DELETE request and saves the returned response.
    When The api user verifies that the status code is 200.
    Then The api user verifies that the "response.response_message" information in the response body is "Shop deleted successfully".
    And  The api user verifies that the "data.deleted_shop_id" information in the response body is the same as the id path parameter in the endpoint.

  @API
  Scenario: TC002 Verify that a DELETE request to /api/deleteShop/{id} with valid authorization but missing id returns status
            code 203 and response_message “Id missing”.

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/deleteShop" path parameters.
    Then The api user sends a DELETE request and saves the returned response.
    When The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Id missing".

  @API
  Scenario: TC003 Verify that a DELETE request to /api/deleteShop/{id} with valid authorization and a non-existent id returns
            status code 203 and response_message “Shop delete failed.No shop this id.”

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/deleteShop/250" path parameters.
    Then The api user sends a DELETE request and saves the returned response.
    When The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Shop delete failed.No shop this id.".

  @API
  Scenario: TC004  Verify that a DELETE request to /api/deleteShop/{id} with an invalid API key returns status code 401 and
             response_message “Invalid token or token missing”.

    Given The api user constructs the base url with the "invalid" token.
    Then The api user sets "api/deleteShop/108" path parameters.
    And The api user sends a DELETE request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.

  @API
  Scenario: TC005 Verify that the deleted blog is successfully removed via API by sending a GET request to /api/shop-details/{id}
            using the deleted_shop_id returned in the DELETE response.

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/shop-details" path parameters for edit shop.
    Then The api user sends a GET request and saves the returned response.
    And The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "No Details found".


