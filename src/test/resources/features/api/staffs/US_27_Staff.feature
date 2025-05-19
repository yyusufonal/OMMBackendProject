Feature: As a provider, I should be able to access the detailed information of the staff with the specified id number via API connection.


  Scenario: TC001 - Validate Staff Detail Response with Valid ID
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/staff-detail/163" path parameters.
    And The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Staff Details".

  @API
  Scenario: TC002 - Validate user data fields and values in response body
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/staff-detail/163" path parameters.
    And The api user prepares a post request body to send to the api addBlog endpointt
    And The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Staff added successfully".

