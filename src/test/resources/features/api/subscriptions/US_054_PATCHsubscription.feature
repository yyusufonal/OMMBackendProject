Feature: As a provider, I want to be able to update the information of the subscription with the specified id number via API connection

  @API
  Scenario: Scenario: Verify that a PATCH request to /api/editSubscription/{id} with valid authorization and correct data returns status code 200 and success message

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editSubscription/40" path parameters.
    When The api user prepares a patch request body to send to the api editSubscription endpoint
    And The api user sends a PATCH request and saves the returned response to subscriptions.
    Then The api user verifies that the status code is 200.
    When The api user verifies that the "response.response_message" information in the response body is "Subscription Updated successfully".
    Then The api user verifies that the "data.updated_subscription_id" information in the response body is the same as the id path parameter in the endpoint.


  @API
  Scenario: Scenario: Verify that a PATCH request to /api/editSubscription/{id} with valid authorization and no data returns status code 203 and correct error message

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editSubscription/40" path parameters.
    And The api user prepares a patch request that does not contain any data to send to the api editsubscription endpoint.
    Then The api user sends a PATCH request and saves the returned response to subscriptions.
    And The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "No data for updated.".


  @API
  Scenario: Verify that a PATCH request to /api/editSubscription endpoint without ID but with valid data returns status code 203 and correct error message

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editSubscription" path parameters.
    And The api user prepares a patch request body to send to the api editSubscription endpoint
    Then The api user sends a PATCH request and saves the returned response to subscriptions.
    And The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "Id missing".


  @API
  Scenario: Verify that a PATCH request to /api/editSubscription/{id} endpoint with non-existent ID returns status code 203 and correct error message

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editSubscription/1111" path parameters.
    And The api user prepares a patch request body to send to the api editSubscription endpoint
    Then The api user sends a PATCH request and saves the returned response to subscriptions.
    And The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "No Results found for the given ID".


  @API
  Scenario: Scenario: Verify that a PATCH request to /api/editSubscription/{id} with invalid API key returns status code 401 and correct error message

    Given The api user constructs the base url with the "invalid" token.
    And The api user sets "api/editSubscription/40" path parameters.
    Then The api user prepares a patch request body to send to the api editSubscription endpoint
    And The api user sends a PATCH request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.


  @API
  Scenario Outline: Verify that the updated subscription record can be retrieved and confirmed via GET request

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/subscription_details/<id>" path parameters.
    And The api user sends a GET request and saves the returned response.
    Then The api user verifies that the fee_description information is "iki tene bonty 1 tene bisket forwla".

    Examples:
      | id |
      | 40 |