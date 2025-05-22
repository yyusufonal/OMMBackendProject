Feature: As a provider, I want to be able to create a new blog category record via API connection.

  Scenario Outline: Verify that when a POST request is sent to the /api/addBlogCategory endpoint with valid authorization
  and correct data (name, description), the response returns a status code of 200, the response_message is
  'Blog Category added successfully' and the creation of the new blog category is confirmed via the API.

    * The api user sets "api/addBlogCategory" path parameters.
    * The api user prepares a POST request containing "<name>" and "<description>" information to send to the api addBlogCategory endpoint.
    * The api user sends a "POST" request and saves the returned response.
    * The api user verifies that the status code is 200.
    * The api user verifies that the "response.response_message" information in the response body is "Blog Category added successfully".
    * The api user verifies that the "response.response_message" is "Blog Category Details" by sending a GET request to the "api" "blogCategory" endpoint with the "data.added_blog_category_id" returned in the response body.

    Examples:
      | name          | description                |
      | Blog Category | blog category description. |


  Scenario Outline: Verify that when a POST request is sent to the /api/addBlogCategory endpoint with valid authorization
  but missing data, the response returns a status code of 203 and the response_message is 'Name and description is required.'

    * The api user sets "api/addBlogCategory" path parameters.
    * The api user prepares a POST request containing "<name>" information to send to the api addBlogCategory endpoint.
    * The api user sends a "POST" request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "response.response_message" information in the response body is "Name and description is required.".

    Examples:
      | name          |
      | Blog Category |


  Scenario: Verify that when an empty POST request is sent to the /api/addBlogCategory endpoint with valid authorization,
  the response returns a status code of 203 and the response_message is 'Name and description is required.'

    * The api user sets "api/addBlogCategory" path parameters.
    * The api user prepares a POST request that contains no data.
    * The api user sends a "POST" request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "response.response_message" information in the response body is "Name and description is required.".


  Scenario Outline: Invalid Token Verify that when a POST request is sent to the /api/addBlogCategory endpoint with an invalid API key and
  correct data (name, description), the response returns a status code of 401 and the response_message is
  ''Invalid token or token missing'

    * The api user sets "api/addBlogCategory" path parameters.
    * The api user prepares a POST request containing "<name>" and "<description>" information to send to the api addBlogCategory endpoint.
    * The api user sends a "POST" request and saves the returned response.
    * The api user verifies that the status code is 401.
    * The api user verifies that the "response.response_message" information in the response body is "Invalid token or token missing".

    Examples:
      | name          | description                |
