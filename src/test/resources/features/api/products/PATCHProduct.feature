Feature: API_US059 - Product update and verification through API

  Scenario Outline: TC001 - Successful product update and post-update verification
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editProduct/<id>" path parameters.
    When The api user prepares a patch request body to send to the api editProduct endpoint
    Then The api user sends a PATCHH request and save the returned response.
    When The api user verifies that the statusS code is 200.
    Then The api user verifies that the "response.response_message" information in the responses body is "Product Updated successfully".

    Examples:
      |id |
      |11|