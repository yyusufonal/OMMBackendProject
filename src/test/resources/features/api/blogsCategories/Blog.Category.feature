Feature: As a provider, I should be able to access the detailed information of the blog category with the specified id number via the API connection.

  Scenario Outline: Verify that when a GET request is sent to the /api/blogCategory/{id} endpoint with valid authorization and the
  correct data (id), the response returns a status code of 200, the response_message is 'Blog Category Details' and the response
  body contains the correct category details, including id, lang_id, name, slug, description, keywords, category_order, status,
  createdAt, createdBy, updatedAt, updatedBy

    Given The api user constructs the base url with the "provider" token.
    * The api user sets "api/blogCategory/<id>" path parameters.
    * The api user sends a "GET" request and saves the returned response.
    * The api user verifies that the status code is 200.
    * The api user verifies that the "response.response_message" information in the response body is "Blog Category Details".
    * The api user verifies that the data in the response body includes "<id>", "<lang_id>", "<name>", "<slug>", "<description>", "<keywords>", "<category_order>", "<status>", "<createdAt>", "<createdBy>", "<updatedAt>" and "<updatedBy>".

    Examples:
      | id | id | lang_id | name               | slug               | description | keywords | category_order | status | createdAt           | createdBy | updatedAt           | updatedBy |
      | 1  | 1  | 1       | Household Services | household-services |             |          | 1              | 1      | 2022-10-03 19:57:19 | 1         | 2024-06-28 23:15:56 | 1         |


  Scenario: Verify that when a GET request is sent to the /api/blogCategory/{id} endpoint with valid authorization but without an ID,
  the response returns a status code of 203 and the response_message is 'Id missing'

    Given The api user constructs the base url with the "provider" token.
    * The api user sets "api/blogCategory" path parameters.
    * The api user sends a "GET" request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "response.response_message" information in the response body is "Id missing".


  Scenario Outline: Verify that when a GET request is sent to the /api/blogCategory/{id} endpoint with valid authorization and a
  non-existent ID, the response returns a status code of 203 and the response_message is 'No Details for this id.'

    Given The api user constructs the base url with the "provider" token.
    * The api user sets "api/blogCategory/<id>" path parameters.
    * The api user sends a "GET" request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "response.response_message" information in the response body is "No Details for this id.".

    Examples:
      | id   |
      | 8521 |


  Scenario Outline: Invalid Token Verify that when a GET request is sent to the /api/blogCategory/{id} endpoint with an invalid API key,
  the response returns a status code of 401 and the response_message is 'Invalid token or token missing'

    Given The api user constructs the base url with the "provider" token.
    * The api user sets "api/blogCategory/<id>" path parameters.
    * The api user sends a "GET" request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.

    Examples:
      | id |
      | 1  |


