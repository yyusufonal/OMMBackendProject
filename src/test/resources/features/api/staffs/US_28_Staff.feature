Feature: US_0027 Staff API Test


  @API
  Scenario: TC001 - Get staff list with valid token
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/addStaff" path parameters.
    And The api user prepares a post request body to send to the api addStaff endpoint
    Then The api user sends a POST request and saves the returned response.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Staff added successfully".




  @API
  Scenario: TC002 - Get staff list with invalid token
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/addStaff" path parameters.
    And The api user prepares an invalid post request body to send to the api addStaff endpoint
    Then The api user sends a POST request and saves the returned response.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Missing required fields.Firstname,mobileno,email,gender,dob and shop_id.".



  @API
  Scenario: TC003 - Get staff list with invalid token
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/addStaff" path parameters.
    Then The api user sends a POST request and saves the returned response.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Missing required fields.Firstname,mobileno,email,gender,dob and shop_id.".




  @API
  Scenario: TC004 - Get staff list with invalid token
    Given The api user constructs the base url with the "invalidApiKey" token.
    And The api user sets "api/addStaff" path parameters.
    And The api user prepares a post request body to send to the api addStaff endpoint
    Then The api user sends a POST request and saves the returned response.
    Then The api user verifies that the status code is 401.
    And The api user verifies that the "response.response_message" information in the response body is "Invalid token or token missing".

