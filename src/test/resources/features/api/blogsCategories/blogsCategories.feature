Feature: Add a New Blog Category

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