Feature: As a provider, I want to be able to create a new subscription record via API connection

  Scenario: Valid subscription creation using correct data and valid authorization.
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/addSubscription" path parameters.
    And The api user prepares a post request body to send to the api addSubscription endpoint
    Then The api user send a POST request and saves the returned response
    And The api user verifies that the status code is 200.
    Then The api user verifies that the "response.response_message" information in the response body is "Subscription added successfully".

  Scenario: Verify that the newly created subscription record exists by using its ID
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/subscription_details/48" path parameters.
    Then The api user sends a GET request and saves the returned response.
    And The api user verifies that the status code is 200.

  Scenario: Verify that a POST request to /api/addSubscription returns status code 203 and correct response message when data is missing
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/addSubscription" path parameters.
    When The api user sends a post request body containing missing data to send to the api addSubscribe endpoind
    And The api user send a POST request and saves the returned response
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Subscription name, fee, duration,fee description are required.".

  Scenario: Verify that a POST request to /api/addSubscription without valid authorization and data returns status code 203 and correct error message
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/addSubscription" path parameters.
    And The api user prepares a post request without any data to send to the api daaSubscription endpoint
    Then The api user send a POST request and saves the returned response
    And The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "Subscription name, fee, duration,fee description are required.".

  Scenario: Verify that a POST request to /api/addSubscription with invalid token and valid data returns status code 401 and correct error message

    Given The api user constructs the base url with the "invalid" token.
    Then The api user sets "api/addSubscription" path parameters.
    And The api user prepares a post request body to send to the api addSubscription endpoint
    Then The api user send a POST request and saves the returned response
    And The api user verifies that the status code is 401.
    Then The api user verifies that the "response.response_message" information in the response body is "Invalid token or token missing".

