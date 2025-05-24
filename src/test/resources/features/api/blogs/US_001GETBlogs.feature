Feature:As a provider I want to be able to access blogs via API connection.
  @API
Scenario Outline: Verify that a GET request to /api/blogs with valid authorization returns status code 200,
response_message “Blogs Listed Successfully”, and blog id(x) includes all expected blog fields.

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/blogs" path parameters.
    And The api user sends a GET request and saves the returned response.
    When The api user verifies that the status code is 200.
    Then The api user verifies that the "response.response_message" information in the response body is "Blogs Listed Successfully".
     And The api user verifies the information in the response body for the entry with the specified <dataIndex> index, including "<lang_id>", "<title>", "<slug>", "<tags>" and "<summary>".

    Examples:
      | dataIndex | lang_id | title                                                                             | slug        | tags | summary                     |
      | 0         | 1       | Discovering the Benefits of Hiring Local Professionals through OnlineMasterMarket | discovering |      | In today's fast-paced world |

  @API
  Scenario: Verify that a GET request to /api/blogs with an invalid API key returns status code 401 and response_message
  “Invalid token or token missing”.


    Given The api user constructs the base url with the "invalid" token.
    And The api user sets "api/blogs" path parameters.
    Then The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
