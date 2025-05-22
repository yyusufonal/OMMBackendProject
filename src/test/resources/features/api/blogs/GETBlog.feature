Feature: As a provider, I should be able to access the detailed information of the blog with the specified
  id number through the API connection.

  Scenario Outline: Verify that a GET request to /api/blog/{id} with valid authorization and correct id returns status
  code 200, response_message “Blog  Details”, and that all expected blog fields are present in the response body.

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/blog/<id>" path parameters.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Blog  Details".
    And The api user validates the "<data_id>", "<lang_id>", "<title>", "<slug>", "<tags>" and "<summary>" contents of the data in the response body.

    Examples:
      | id | data_id | lang_id | title              | slug      | tags | summary     |
      | 2  | 2       | 1       | New Blog Updated   | PC90      |      | Blog Summary|


  Scenario: Verify that a GET request to /api/blog/{id} without valid authorization and id returns status code 203 and
  response_message “Id missing”.

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/blog" path parameters.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Id missing".


  Scenario: Verify that a GET request to /api/blog/{id} with valid authorization and an unregistered id returns status
  code 203 and response_message “No Details for this id.”.

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/blog/6571" path parameters.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "No Details for this id.".



  Scenario: Verify that a GET request to /api/blog/{id} with an invalid API key returns status code 401 and
  response_message “Invalid token or token missing”.

    Given The api user constructs the base url with the "invalid" token.
    And The api user sets "api/blog/2" path parameters.
    When The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.

