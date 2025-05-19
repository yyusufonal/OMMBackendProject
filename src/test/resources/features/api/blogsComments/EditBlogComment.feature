Feature: As a provider, I want to be able to update blog comment information with the specified id number via API connection

  Scenario Outline: Verify that a PATCH request to /api/editBlogComment/{id} with valid authorization and correct data
  (name, email, comment) returns status code 200, response_message “Blog Comment Updated successfully”, and that updated_comment_id in
  the response matches the path parameter id.

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editBlogComment/<id>" path parameters.
    When The api user prepares a patch request body to send to the api editBlogComment endpoint
    Then The api user sends a PATCH request and records the response.
    When The api user verifies that the status code is 200.
    Then The api user verifies that the "response.response_message" information in the response body is "Blog Comment Updated successfully".
    Then The api user verifies that the "data.updated_blog_comment_id" information in the response body is the same as the id path parameter in the endpoint.

    Examples:
    | id  |
    | 52  |


  Scenario Outline: Verify that a PATCH request to /api/editBlogComment/{id} with valid authorization, correct id, and correct data (only name) returns status code 200 and response_message “Blog Comment Updated successfully”

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editBlogComment/<id>" path parameters.
    When The api user prepares a patch request body to send to the api editBlogComment endpoint
    Then The api user sends a PATCH request and records the response.
    Then The api user verifies that the status code is 200.
    Then The api user verifies that the "response.response_message" information in the response body is "Blog Comment Updated successfully".

    Examples:
      | id  |
      | 52  |

  Scenario Outline: Verify that a PATCH request to /api/editBlogComment/{id} with valid authorization, correct id, and no data returns status code 203 and response_message “No data for updated.”

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editBlogComment/<id>" path parameters.
    When The api user prepares a patch request body with no data to send to the api editBlogComment endpoint.
    Then The api user sends a PATCH request with no datas and save the returned response.
    Then The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "No data for updated.".

    Examples:
      | id  |
      | 52  |

  Scenario: Verify that a PATCH request to /api/editBlogComment without id and with valid authorization and correct data returns status code 203 and response_message “Id missing”

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editBlogComment" path parameters.
    When The api user prepares a patch request body to send to the api editBlogComment endpoint
    Then The api user sends a PATCH request and records the response.
    Then The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "Id missing".

  Scenario Outline: Verify that a PATCH request to /api/editBlogComment/{id} with valid authorization, non-existent id, and correct data returns status code 203 and response_message “No Results found for the given ID”

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editBlogComment/<id>" path parameters.
    When The api user prepares a patch request body to send to the api editBlogComment endpoint
    Then The api user sends a PATCH request and records the response.
    Then The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "No Results found for the given ID".

    Examples:
      | id   |
      | 9999 |

  Scenario Outline: Verify that a PATCH request to /api/editBlogComment/{id} with invalid token and valid data returns status code 401 and correct error message

    Given The api user constructs the base url with the "invalid" token.
    Then The api user sets "api/editBlogComment/<id>" path parameters.
    When The api user prepares a patch request body to send to the api editBlogComment endpoint
    And The api user sends a PATCH request, saves the returned response, and verifies that the status code is 401 with the reason phrase Unauthorized.

    Examples:
      | id  |
      | 52  |

  Scenario Outline: Verify that the updated_blog_comment_id in the response body matches the id path parameter used in the PATCH request to /api/editBlogComment/{id}

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editBlogComment/<id>" path parameters.
    When The api user prepares a patch request body to send to the api editBlogComment endpoint
    Then The api user sends a PATCH request and records the response.
    Then The api user verifies that the "data.updated_blog_comment_id" information in the response body is the same as the id path parameter in the endpoint.

    Examples:
      | id  |
      | 52  |

  Scenario Outline: Verify that the updated blog comment is successfully modified via API by sending a
  GET request to /api/blogComment/{id} using the updated_blog_comment_id returned in the PATCH response.

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/blogComment/<id>" path parameters.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the comment information is "Please write more about this topic. Updated comment."

    Examples:
      | id |
      | 30 |



