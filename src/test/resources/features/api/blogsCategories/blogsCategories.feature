Feature: Blog Category List

  Scenario: Get blog category list with valid token

    * The API user sets base URL with valid provider token
    * The API user sets path "/api/blogCategories"
    * The API user sends a GET request to blogCategories endpoint
    * The API user verifies that the status code is 200
    * The API user verifies that the response_message is "Blog Categories Listed Successfully"
    * The API user verifies that the response contains expected category fields