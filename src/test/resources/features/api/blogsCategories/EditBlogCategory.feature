Feature: As a provider, I want to be able to update the blog category information with the specified id number via API connection.

  @Patch
  Scenario Outline: CATEGORY Verify that when a PATCH request is sent to the /api/editBlogCategory/{id} endpoint with valid authorization,
  the correct ID, and correct data (name, description), the response returns a status code of 200, the response_message is
  'Blog Category Updated successfully' the updated_blog_category_id matches the ID in the path, and the blog category update
  is confirmed via the API.

    * The api user sets "api/editBlogCategory" path parameters.
    * The api user prepares a PATCH request containing "<name>" and "<description>" information to send to the api editBlogCategory endpoint.
    * The api user sends a "PATCH" request and saves the returned response.
    * The api user verifies that the status code is 200.
    * The api user verifies that the "response.response_message" information in the response body is "Blog Category Updated successfully".
    * The api user verifies that the "data.updated_blog_category_id" information in the returned response body is the same as the id path parameter written in the endpoint.
    * The api user verifies that the "data[0].name" is "Updated Blog Category" by sending a GET request to the "api" "blogCategory" endpoint with the "data.updated_blog_category_id" returned in the response body.

    Examples:
      | name                  | description                        |
      | Updated Blog Category | blog category description updated. |


  @Patch
  Scenario Outline: CATEGORY Verify that when a PATCH request is sent to the /api/editBlogCategory/{id} endpoint with valid authorization,
  the correct ID, and valid description data, the response returns a status code of 200 and the response_message is
  'Blog Category Updated successfully'

    * The api user sets "api/editBlogCategory" path parameters.
    * The api user prepares a PATCH request containing "<description>" information to send to the api editBlogCategory endpoint.
    * The api user sends a "PATCH" request and saves the returned response.
    * The api user verifies that the status code is 200.
    * The api user verifies that the "response.response_message" information in the response body is "Blog Category Updated successfully".

    Examples:
      | description                            |
      | new blog category description updated. |


  @Patch
  Scenario: CATEGORY Verify that when a PATCH request is sent to the /api/editBlogCategory/{id} endpoint with valid authorization,
  the correct ID, and no data, the response returns a status code of 203 and the response_message is 'No data for updated.'

    * The api user sets "api/editBlogCategory" path parameters.
    * The api user prepares a PATCH request without containing any data.
    * The api user sends a "PATCH" request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "response.response_message" information in the response body is "No data for updated.".


  Scenario Outline: Verify that when a PATCH request is sent to the /api/editBlogCategory/{id} endpoint with valid authorization
  but without an ID and with valid data (name, description), the response returns a status code of 203 and the response_message
  is 'Id missing'

    * The api user sets "api/editBlogCategory" path parameters.
    * The api user prepares a PATCH request containing "<name>" and "<description>" information to send to the api editBlogCategory endpoint.
    * The api user sends a "PATCH" request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "response.response_message" information in the response body is "Id missing".

    Examples:
      | name                  | description                        |
      | Updated Blog Category | blog category description updated. |


  Scenario Outline: Verify that when a PATCH request is sent to the /api/editBlogCategory/{id} endpoint with valid authorization,
  a non-existent ID, and valid data (name, description), the response returns a status code of 203 and the response_message is
  'No Results found for the given ID'

    * The api user sets "api/editBlogCategory/4125" path parameters.
    * The api user prepares a PATCH request containing "<name>" and "<description>" information to send to the api editBlogCategory endpoint.
    * The api user sends a "PATCH" request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "response.response_message" information in the response body is "No Results found for the given ID".

    Examples:
      | name                  | description                        |
      | Updated Blog Category | blog category description updated. |


  @Patch
  Scenario Outline: CATEGORY Invalid Token Verify that when a PATCH request is sent to the /api/editBlogCategory/{id} endpoint with an invalid API key,
  the correct ID, and valid data (name, description), the response returns a status code of 401 and the response_message is
  'Invalid token or token missing'

    * The api user sets "api/editBlogCategory" path parameters.
    * The api user prepares a PATCH request containing "<name>" and "<description>" information to send to the api editBlogCategory endpoint.
    * The api user sends a "PATCH" request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.

    Examples:
      | name                  | description                        |
      | Updated Blog Category | blog category description updated. |
