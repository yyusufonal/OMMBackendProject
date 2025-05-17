Feature: As a provider, I should be able to access the detailed information of the blog comment with the specified id number through the API connection.

  Scenario Outline: Verify that a GET request to /api/blogComment/{id} with valid authorization and correct id returns status code 200, response_message “Blog  Comment Details”, and that all expected fields are present in the response body.

    * The api user constructs the base url with the "provider" token.
    * The api user sets "api/blogComment/<id>" path parameters.
    * The api user sends a GET request and saves the returned response.
    * The api user verifies that the status code is 200.
    * The api user verifies that the "response.response_message" information in the response body is "Blog  Comment Details".
    * The api user validates the "<data_id>", "<post_id>", "<user_id>", "<email>", "<name>", "<comment>", "<ip_address>", "<status>", "<created_at>" fields in the response body.

    Examples:
      | id | data_id | post_id | user_id | email              | name     | comment                                                  | ip_address | status | created_at          |
      | 24 | 24      | 2       | 4       | anthony@gmail.com  | Anthony  | Please write more about this topic. Updated comment.     | 127.1.1.1  | 1      | 2025-05-16 19:47:39 |

  Scenario: Verify that a GET request to /api/blogComment/{id} without id returns status code 203 and response_message “Id missing”

    * The api user constructs the base url with the "provider" token.
    * The api user sets "api/blogComment" path parameters.
    * The api user sends a GET request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "response.response_message" information in the response body is "Id missing".

  Scenario: Verify that a GET request to /api/blogComment/{id} with valid authorization and unregistered id returns status code 203 and response_message “No Details for this id.”

    * The api user constructs the base url with the "provider" token.
    * The api user sets "api/blogComment/9999" path parameters.
    * The api user sends a GET request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "response.response_message" information in the response body is "No Details for this id.".

  Scenario: Verify that a GET request to /api/blogComment/{id} with an invalid API key returns status code 401 and response_message “Invalid token or token missing”

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/blogComment/1" path parameters.
    * The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
