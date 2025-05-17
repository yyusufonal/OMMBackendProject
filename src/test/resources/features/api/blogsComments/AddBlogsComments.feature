Feature: As a provider, I should be able to add a blog comment via API connection with correct data and valid authorization.

  Scenario: Verify that a POST request to /api/addBlogComment with valid authorization and correct data returns status code 200 and response_message “Blog Comment added successfully”

    * The api user constructs the base url with the "provider" token.
    * The api user sets "api/addBlogComment" path parameters.
    * The api user prepares a post request body to send to the api addBlogComment endpoint
    * The api user sends a POST request to addBlogComment and saves the returned response.
    * The api user verifies that the status code is 200.
    * The api user verifies that the "response.response_message" information in the response body is "Blog Comment added successfully".


  Scenario: Verify that a POST request to /api/addBlogComment with valid authorization and missing data returns status code
  203 and response_message “Post ID, email, name, and comment is required.”

    * The api user constructs the base url with the "provider" token.
    * The api user sets "api/addBlogComment" path parameters.
    * The api user prepares a post request body containing missing data to send to the api addBlogComment endpoint.
    * The api user sends a POST request to addBlogComment and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "response.response_message" information in the response body is "Post ID, email, name, and comment is required.".

  Scenario: Verify that a POST request to /api/addBlogComment with valid authorization and no data returns status code 203 and response_message “Post ID, email, name, and comment is required.”

    * The api user constructs the base url with the "provider" token.
    * The api user sets "api/addBlogComment" path parameters.
    * The api user prepares an empty POST request body.
    * The api user sends a POST request to addBlogComment and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "response.response_message" information in the response body is "Post ID, email, name, and comment is required.".

  Scenario Outline: Verify that a POST request to /api/addBlogComment with invalid API key and correct data returns status code 401 and response_message “Invalid token or token missing”

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/addBlogComment" path parameters.
    * The api user prepares a POST request body with "<post_id>", "<name>", "<email>", "<comment>".
    * The api user sends a POST request to addBlogComment and saves the returned response.
    * The api user verifies that the status code is 401.
    * The api user verifies that the "response.response_message" information in the response body is "Invalid token or token missing".

    Examples:
      | post_id | name     | email             | comment                              |
      | 2       | Anthony  | anthony@gmail.com | This blog helped me a lot, thanks!   |

  Scenario Outline: Verify that the newly created blog comment via /api/addBlogComment is successfully created by sending a GET request to /api/blogComment/{id} using the added_blog_comment_id returned in the POST response.

    * The api user constructs the base url with the "provider" token.
    * The api user sets "api/blogComment/<id>" path parameters.
    * The api user sends a GET request and saves the returned response.
    * The api user verifies that the status code is 200.

    Examples:
      | id  |
      | 24  |
