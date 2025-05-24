Feature: As a provider, I want to be able to update the information of the blog with the specified id number
  via API connection.
  @API
  Scenario: Verify that a PATCH request to /api/editBlog/{id} with valid authorization and correct data returns status code
  200, response_message “Blog  Updated successfully”, and that updated_blog_id in the response matches the path parameter id.

    Given  The api user constructs the base url with the "provider" token.
    And The api user sets "api/editBlog/91" path parameters.
    And The api user prepares a patch request body to send to the api editBlog endpoint
    When The api user sends a PATCH request and saves the returned response to Blog.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Blog  Updated successfully".
    And The api user checks that the response field "data.updated_blog_id" equals the id sent in the endpoint.


  @API
  Scenario: Verify that a PATCH request to /api/editBlog/{id} with valid authorization but no data returns status code
  203 and response_message “No data for updated.”

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/editBlog/47" path parameters.
    And  The api user prepares a post request without any data to send to the api addBlog endpoint.
    When The api user sends a PATCH request and saves the returned response to Blog.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "No data for updated.".

  @API
  Scenario: Verify that a PATCH request to /api/editBlog/{id} with valid authorization and correct data but missing
  id returns status code 203 and response_message “Id missing”.

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/editBlog" path parameters.
    And The api user prepares a patch request body to send to the api editBlog endpoint
    When The api user sends a PATCH request and saves the returned response to Blog.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Id missing".

  @API
  Scenario: Verify that a PATCH request to /api/editBlog/{id} with valid authorization and correct data but
  a non-existent id returns status code 203 and response_message “No Results found for the given ID”.

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/editBlog/6789" path parameters.
    And The api user prepares a patch request body to send to the api editBlog endpoint
    When The api user sends a PATCH request and saves the returned response to Blog.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "No Results found for the given ID".

  @API
  Scenario: Verify that a PATCH request to /api/editBlog/{id} with an invalid API key, correct id, and valid data
  returns status code 401 and response_message “Invalid token or token missing”.

    Given The api user constructs the base url with the "invalid" token.
    And The api user sets "api/editBlog/91" path parameters.
    And The api user prepares a patch request body to send to the api editBlog endpoint
    When The api user executes a PATCH request and validates that the response status is 401 Unauthorized.


  @API
  Scenario Outline: Verify that the updated blog is successfully modified via API by sending a GET request to /api/blog/{id}
  using the updated_blog_id returned in the PATCH response.

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/blog/<id>" path parameters.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the content information is "Content"


    Examples:
      | id |
      | 91 |