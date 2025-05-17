Feature: API_US059 - Product update and verification through API

  Scenario Outline: TC001 - Successful product update and post-update verification
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editProduct/<id>" path parameters.
    When The api user prepares a patch request body to send to the api editProduct endpoint
    Then The api user sends a PATCHH request and save the returned response.
    When The api user verifies that the statusS code is 200.
    Then The api user verifies that the "response.response_message" information in the responses body is "Product Updated successfully".
    Then The api user verifies that the "data.updated_product_id" information in the response body is the same as the id path parameter in the endpoint.
    Then The api user sets "api/product-details/<id>" path parameters.
    When The api user sends a GET request and saves the returned response.

    Examples:
      |id |
      |55|

    Scenario Outline: TC002 - Update request with missing data
      Given The api user constructs the base url with the "provider" token.
      Then The api user sets "api/editProduct/<id>" path parameters.
      When The api user prepares a patch request body to send to empty data the api editProduct endpoint
      Then The api user sends a PATCHH request and save the returned response.
      When The api user verifies that the status code is 203.
      Then The api user verifies that the "response.response_message" information in the response body is "No data for updated.".

      Examples:
        |id |
        |55|