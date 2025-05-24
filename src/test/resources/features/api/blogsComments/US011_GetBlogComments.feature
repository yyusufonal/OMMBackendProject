Feature: As a provider, I want to be able to access blog comments via the API connection

  @API
  Scenario: TC001 Verify that a GET request to the /api/blogComments endpoint with valid authorization returns 200 OK
  and includes the message "Comments Listed Successfully"


    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/blogComments" path parameters.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Comments Listed Successfully".

  @API
  Scenario Outline: TC002 Verify that the blog comment fields in the response body of /api/blogComments include expected information


    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/blogComments" path parameters.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies the information in the response body for the entry with the specified <dataIndex> index, including "<post_id>", "<user_id>", "<email>", "<name>", "<comment>", "<ip_address>", "<status>", "<created_at>".


    Examples:
      | dataIndex | post_id | user_id | email             | name     | comment                                              | ip_address | status | created_at           |
      | 0         | 2       | 4       | anthony@gmail.com | Anthony  | Please write more about this topic. | 127.1.1.1  | 1      | 2025-05-16 20:45:04  |
  @API
  Scenario: TC003 Verify that a GET request to the /api/blogComments endpoint with invalid API key returns 401 Unauthorized
  and the message "Invalid token or token missing."


    Given The api user constructs the base url with the "invalid" token.
    When The api user sets "api/blogComments" path parameters.
    And The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.