Feature: Blog Category API

  Background:
    Given The API base URL is set
    And I set the "Authorization" header to "SZk44qHV59wMIlUGa256"

  Scenario: Successfully add a new blog category
    Given I have a new blog category with:
      | name      | description               |
      | Education | Articles about learning   |
    When I send a POST request to "/api/addBlogCategory" with the blog category data
    Then the response status code should be 200
    And the response message should be "Blog Category added successfully"
    And the response should contain "added_blog_category_id"

  Scenario: Retrieve blog category details with valid token and existing ID
    Given the endpoint is set to blogCategory with id 1
    When a GET request is sent with valid token
    Then the response status code should be 200
    And the response message should be "Blog Category Details"
    And the blog category details should be present

  Scenario: Request without ID and without authorization
    Given no ID is provided in the blogCategory endpoint
    When a GET request is sent without authorization
    Then the response status code should be 203
    And the response message should be "Id missing"

  Scenario: Request with valid token and non-existent ID
    Given the endpoint is set to blogCategory with id 99999
    When a GET request is sent with valid token
    Then the response status code should be 203
    And the response message should be "No Details for this id."

  Scenario: Request with invalid token
    Given the endpoint is set to blogCategory with id 1
    When a GET request is sent with invalid token
    Then the response status code should be 401
    And the response message should be "Invalid token or token missing"