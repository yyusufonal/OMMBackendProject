Feature: As a provider, I want to be able to delete blog category information with the specified id number via API connection.

  @Delete
  Scenario: CATEGORY Verify that when a DELETE request is sent to the /api/deleteBlogCategory/{id} endpoint with valid authorization and the
  correct ID, the response returns a status code of 200, the message is 'Blog Category deleted successfully' and the
  deleted_blog_category_id matches the ID in the request path, confirming the deletion of the blog category via the API.

    * The api user sets "api/deleteBlogCategory" path parameters.
    * The api user sends a "DELETE" request and saves the returned response.
    * The api user verifies that the status code is 200.
    * The api user verifies that the "message" information in the response body is "Blog Category deleted successfully".
    * The api user verifies that the "data.deleted_blog_category_id" information in the returned response body is the same as the id path parameter written in the endpoint.
    * The api user verifies that the "response.response_message" is "No Details for this id." by sending a GET request to the "api" "blogCategory" endpoint with the "data.deleted_blog_category_id" returned in the response body.


  Scenario: Verify that when a DELETE request is sent to the /api/deleteBlogCategory/{id} endpoint with valid authorization
  and no ID is provided, the response returns a status code of 203 and the message is 'Id missing'

    * The api user sets "api/deleteBlogCategory" path parameters.
    * The api user sends a "DELETE" request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "message" information in the response body is "Id missing".


  Scenario: Verify that when a DELETE request is sent to the /api/deleteBlogCategory/{id} endpoint with valid authorization and
  a non-existent ID, the response returns a status code of 203 and the message is 'Blog Category not found. Invalid ID.'

    * The api user sets "api/deleteBlogCategory/1452" path parameters.
    * The api user sends a "DELETE" request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "message" information in the response body is "Blog Category not found. Invalid ID.".

  @Delete
  Scenario: CATEGORY Invalid Token Verify that when a DELETE request is sent to the /api/deleteBlogCategory/{id} endpoint with an invalid API key,
  the response returns a status code of 401 and the response_message is 'Invalid token or token missing'

    * The api user sets "api/deleteBlogCategory" path parameters.
    * The api user sends a "DELETE" request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
