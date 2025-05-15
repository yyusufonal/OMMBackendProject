Feature: As a provider, I should be able to access the detailed information of the shop with the specified id number via the API connection.

  @API
Scenario:Verify that a GET request to /api/shop-details/{id} with valid authorization and correct ID
          returns status code 200,response_message “Shop Details”

  Given The api user constructs the base url with the "provider" token.
  Then The api user sets "api/shop-details/5" path parameters.
  When The api user sends a GET request and saves the returned response.
  Then The api user verifies that the status code is 200.
  And The api user verifies that the " response.response_message" information in the response body is "Shop Details".


  Scenario: