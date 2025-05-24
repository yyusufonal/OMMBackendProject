Feature: As a provider, I want to be able to create a new blog record via API connection.
  @API
  Scenario: Verify that a POST request to /api/addBlog with valid authorization and correct data returns status code 200
  and response_message “Blog added successfully”.

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/addBlog" path parameters.
    When The api user prepares a post request body to send to the api addBlog endpoint
    When The api user sends a POST request and saves the returned response to Blog.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Blog added successfully".

  @API
  Scenario: Verify that a POST request to /api/addBlog with valid authorization but missing data returns status code
  203 and response_message “Title, summary, content and category_id is required.”

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/addBlog" path parameters.
    And The api user prepares a post request body containing missing data to send to the api addBlog endpoint.
    When The api user sends a POST request and saves the returned response to Blog.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Title, summary, content and category_id is required.".

  @API
  Scenario: Verify that a POST request to /api/addBlog without valid authorization and data returns status code 203
  and response_message “Title, summary, content and category_id is required.”

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/addBlog" path parameters.
    And The api user prepares a post request without any data to send to the api addBlog endpoint.
    When The api user sends a POST request and saves the returned response to Blog.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Title, summary, content and category_id is required.".


  @API
  Scenario: Verify that a POST request to /api/addBlog with an invalid API key and correct data returns status code
  401 and response_message “Invalid token or token missing”.

    Given The api user constructs the base url with the "invalid" token.
    And The api user sets "api/addBlog" path parameters.
    And The api user prepares a post request body to send to the api addBlog endpoint
    When The api user sends a POST request and saves the returned response to Blog.
    Then The api user verifies that the status code is 401.
    And The api user verifies that the "response.response_message" information in the response body is "Invalid token or token missing".


  @API
  Scenario Outline: Verify that the newly created blog via /api/addBlog is successfully created by sending a GET request
  to /api/blog/{id} using the added_blog_id returned in the POST response.

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/blog/<id>" path parameters.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 200.


    Examples:
      | id |
      | 90 |
