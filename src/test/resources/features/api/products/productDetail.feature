Feature: API_US057 - Product detail retrieval through API

  Scenario: TC004 - Valid authorization and valid product id
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/product-details/11" path parameters.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Product Details".