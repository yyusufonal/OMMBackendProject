Feature: Delete Blog Comment
  @API
  Scenario Outline: TC001 Verify that a DELETE request to /api/deleteBlogComment/{id} with valid authorization and correct ID returns status code 200 and response message "Blog Comment deleted successfully"

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/deleteBlogComment/<id>" path parameters.
    Then The api user sends a DELETE request and saves the returned response.
    Then The api user verifies that the status code is 200.
    Then The api user verifies that the "response.response_message" information in the response body is "Blog Comment deleted successfully".
    And The api user verifies that the "data.deleted_blog_comment_id" information in the response body is the same as the id path parameter in the endpoint.

    Examples:
      | id |
      | 165 |

  @API
  Scenario: TC002 Verify that a DELETE request to /api/deleteBlogComment/{id} without ID returns status code 203 and response message "Id missing"

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/deleteBlogComment" path parameters.
    Then The api user sends a DELETE request and saves the returned response.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Id missing".
  @API
  Scenario Outline: TC003 Verify that a DELETE request to /api/deleteBlogComment/{id} with valid authorization and non-existent ID returns status code 203 and response message "Blog comment not found. Invalid ID."

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/deleteBlogComment/<id>" path parameters.
    Then The api user sends a DELETE request and saves the returned response.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Blog comment not found. Invalid ID.".

    Examples:
      | id  |
      | 999 |
  @API
  Scenario Outline: TC004 Verify that a DELETE request to /api/deleteBlogComment/{id} with invalid API key returns status code 401 and response message "Invalid token or token missing"

    Given The api user constructs the base url with the "invalid" token.
    Then The api user sets "api/deleteBlogComment/<id>" path parameters.
    And The api user sends a DELETE request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.

    Examples:
      | id  |
      | 165  |


  Scenario Outline: TC005 Verify that the deleted_blog_comment_id in the response body matches the ID in the path parameter

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/deleteBlogComment/<id>" path parameters.
    Then The api user sends a DELETE request and saves the returned response.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "data.deleted_blog_comment_id" information in the response body is the same as the id path parameter in the endpoint.

    Examples:
      | id |
      | 165 |

  Scenario Outline: TC006 Verify that the deletion of the blog comment is confirmed via
  GET request using the deleted_blog_comment_id returned by the API

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/blogComment/<id>" path parameters.
    Then The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 200.


    Examples:
      | id  |
      | 165  |
