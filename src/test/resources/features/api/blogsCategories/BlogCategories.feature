Feature: As a provider, I want to be able to access blog categories via API connection.

  Scenario Outline: Verify that when a GET request is sent to the /api/blogCategories endpoint with valid authorization, the
  response returns a status code of 200, the response_message is 'Blog Categories Listed Successfully' and the category with ID (x)
  has the correct details in the response body, including lang_id, name, slug, description, keywords, category_order, status, createdAt,
  createdBy, updatedAt, updatedBy

    * The api user sets "api/blogCategories" path parameters.
    * The api user sends a "GET" request and saves the returned response.
    * The api user verifies that the status code is 200.
    * The api user verifies that the "response.response_message" information in the response body is "Blog Categories Listed Successfully".
    * The api user verifies the information in the response body for the entry with the specified <dataindex> index, including "<lang_id>", "<name>", "<slug>", "<description>", "<keywords>", "<category_order>", "<status>", "<createdAt>", "<createdBy>", "<updatedAt>" and "<updatedBy>".

    Examples:
      | dataindex | lang_id | name               | slug               | description | keywords | category_order | status | createdAt           | createdBy | updatedAt           | updatedBy |
      | 0         | 1       | Household Services | household-services |             |          | 1              | 1      | 2022-10-03 19:57:19 | 1         | 2024-06-28 23:15:56 | 1         |


  Scenario: Invalid Token Verify that when a GET request is sent to the /api/blogCategories endpoint with an invalid API key,
  the response returns a status code of 401 and the response_message is 'Invalid token or token missing'

    * The api user sets "api/blogCategories" path parameters.
    * The api user sends a "GET" request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
