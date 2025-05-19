Feature: As a provider, I want to be able to delete blog information with the specified id number via API connection.

  Scenario: Verify that a DELETE request to /api/deleteBlog/{id} with valid authorization and correct id returns status
  code 200, response_message “Blog deleted successfully”, and that deleted_blog_id in the response matches the path
  parameter id.

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/deleteBlog/93" path parameters.
    When The api user sends a DELETE request and saves the returned response.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Blog deleted successfully".
    And The api user verifies that the "data.deleted_blog_id" information in the response body is the same as the id path parameter in the endpoint.

  Scenario: Verify that a DELETE request to /api/deleteBlog/{id} with valid authorization but missing id returns status
  code 203 and response_message “Id missing”.

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/deleteBlog" path parameters.
    When The api user sends a DELETE request and saves the returned response.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Id missing".


  Scenario: Verify that a DELETE request to /api/deleteBlog/{id} with valid authorization and a non-existent id returns
  status code 203 and response_message “Blog not found. Invalid ID.”

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/deleteBlog/6541" path parameters.
    When The api user sends a DELETE request and saves the returned response.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Blog not found. Invalid ID.".



  Scenario: Verify that a DELETE request to /api/deleteBlog/{id} with an invalid API key returns status code 401 and
  response_message “Invalid token or token missing”.

    Given The api user constructs the base url with the "invalid" token.
    And The api user sets "api/deleteBlog/85" path parameters.
    When The api user sends a DELETE request and saves the returned response.
    Then The api user verifies that the status code is 401.
    And The api user verifies that the "response.response_message" information in the response body is "Invalid token or token missing".



  Scenario: Verify that the deleted blog is successfully removed via API by sending a GET request to /api/blog/{id}
  using the deleted_blog_id returned in the DELETE response.

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/blog/93" path parameters.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 203.

