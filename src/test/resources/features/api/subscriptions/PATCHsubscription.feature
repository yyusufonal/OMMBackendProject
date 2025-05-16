Feature: As a provider, I want to be able to update the information of the subscription with the specified id number via API connection

  Scenario: Verify that a PATCH request to /api/editSubscription/{id} with valid authorization and correct data returns status code 200 and success message

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "/api/addSubscription/41" path parameters.
    When The api user prepares a patch request body to send to the api editBlog endpoint
    And The api user sends a PATCH request and saves the returned response.
    Then The api user verifies that the status code is 200.
    When The api user verifies that the "response.response_message" information in the response body is "Subscription Updated successfully".
    Then The api user verifies that the "data.updated_subscription_id" information in the response body is the same as the id path parameter in the endpoint.



