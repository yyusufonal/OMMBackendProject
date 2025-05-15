Feature: As a provider, I should be able to access the detailed information of the subscription with the specified id number through the API connection.
  @API
  Scenario Outline: Verify that GET /api/subscription_details/{id} returns status 200, correct message, and complete subscription details for a valid request.

     Given The api user constructs the base url with the "provider" token.
     # Api kullanicisi "provider" token ile base urli olusturur
     Then The api user sets "api/subscription_details/<id>" path parameters.
     # Api kullanicisi "api/blogs" path parametrelerini olusturur
     And The api user sends a GET request and saves the returned response.
     # Api kullanicisi GET request gonderir ve donen responsei kaydeder
     Then The api user verifies that the status code is 200.
     # Api kullanicisi status codeun 200 oldugunu dogrular
     And The api user verifies that the "response.response_message" information in the response body is "Subscription Fee Details".
     # Api kullanicisi response bodydeki response_message bilgisinin "Blog  Details" oldugunu dogrular
    Then The api user verifies the information in the response body for the entry with the specified <dataIndex> ,"<id>", "<subscription_name>", "<fee>", "<currency_code>", "<duration>", "<fee_description>", "<subscription_content>", "<subscription_type>", "<status>" and "<type>".
    # Api kullanıcısı response body icindeki <dataIndex> indexe sahip olanin <subscription_name>, <fee>, <currency_code>, <duration>, <fee_description>, <subscription_content>, <subscription_type>, <status>, <type>

    Examples:
      |dataIndex|id         | subscription_name |fee   | currency_code |duration | fee_description    | subscription_content | subscription_type | status | type |
      |0        |1          | Premium           |99.99 |USD          | 12    |Annual Subscription     | Only one shop        |1                  |1       |1     |


  Scenario: Verify that a GET request to /api/blog/{id} without valid authorization and id returns status code 203 and
  response_message “Id missing”.

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/subscription_details" path parameters.
    Then The api user sends a GET request and saves the returned response.
    When The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Id missing".

  Scenario: GET request to /api/blog/{id} with an invalid API key returns status code 401 and response_message “Invalid token or token missing”

    Given The api user constructs the base url with the "invalid" token.
    And The api user sets "api/subscription_details/1" path parameters.
    Then The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.

  Scenario: GET /api/subscription_details/{id} with valid token and unregistered id returns 203 and expected message

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/subscription_details/1111" path parameters.
    And The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "No Details for this id.".