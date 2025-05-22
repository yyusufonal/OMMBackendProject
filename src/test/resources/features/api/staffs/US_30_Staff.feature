Feature: Delete staff by ID as a provider

  Scenario Outline: Delete staff by ID with valid token and verify response
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/deleteStaff/<id>" path parameters.
    Then The api user sends a DELETE request and saves the returned response.
    Then The api user verifies that the status code is 200.
    Then The api user verifies that the "response.response_message" information in the response body is "Staff deleted successfully".
    And The api user verifies that the "data.deleted_staff_id" information in the response body is the same as the id path parameter in the endpoint.

    Examples:
      | id  |
      | 163 |