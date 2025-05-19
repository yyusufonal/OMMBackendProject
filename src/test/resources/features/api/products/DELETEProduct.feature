Feature: API_US060 - Product deletion via API

  #AC1-#AC5-#AC6
  Scenario Outline: TC001 - Successful product deletion and verification
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/deleteProduct/<id>" path parameters.
    Then The api user sends a DELETE request and saves the returned response.
    When The api user verifies that the status code is 200.
    Then The api user verifies that the "response.response_message" information in the response body is "Product deleted successfully".
    And  The api user verifies that the "data.deleted_product_id" information in the response body is the same as the id path parameter in the endpoint.

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/deleteProduct/<id>" path parameters.
    Then The api user sends a DELETE request and saves the returned response.
    When The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "Product not found. Invalid ID.".

    Examples:
      |id |
      |75|

  #AC2-#AC3
  Scenario: TC002 - Delete request with missing or invalid product ID
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/deleteProduct" path parameters.
    Then The api user sends a DELETE request and saves the returned response.
    When The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "Id missing".

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/deleteProduct/8898555" path parameters.
    Then The api user sends a DELETE request and saves the returned response.
    When The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "Product not found. Invalid ID.".

  #AC4
  Scenario: TC003 - Delete request with invalid authorization
    Given The api user constructs the base url with the "invalid" token.
    Then The api user sets "api/deleteProduct/22" path parameters.
    And The api user sends a DELETE request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.