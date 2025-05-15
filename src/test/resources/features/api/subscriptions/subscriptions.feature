Feature:  As a provider, I want to be able to access subscriptions via API connection.


  Scenario Outline: Verify that the GET /api/subscriptions endpoint returns status code 200, correct response message, and all required subscription fields.

    Given The api user constructs the base url with the "provider" token.
    # Api kullanicisi "provider" token ile base urli olusturur
    Then The api user sets "api/subscriptions" path parameters.
    # Api kullanicisi "api/subscriptions" path parametrelerini olusturur
    And The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    When The api user verifies that the status code is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    Then The api user verifies that the "response.response_message" information in the response body is "Subscription Fee Listed Successfully".
    # Api kullanicisi response bodydeki response_message bilgisinin "Blogs Listed Successfully" oldugunu dogrular
    And The api user verifies the information in the response body for the entry with the specified <dataIndex> index , "<subscription_name>", "<fee>", "<currency_code>", "<duration>", "<fee_description>", "<subscription_content>", "<subscription_type>", "<status>" and "<type>".
    # Api kullanıcısı response body icindeki <dataIndex> indexe sahip olanin <subscription_name>, <fee>, <currency_code>, <duration>, <fee_description>, <subscription_content>, <subscription_type>, <status>, <type>

  Examples:
    | dataIndex | subscription_name |fee   | currency_code |duration | fee_description    | subscription_content | subscription_type | status | type |
    |0          | Premium           |99.99 |USD            | 12      |Annual Subscription | Only one shop        |1                  |1       |1     |



  Scenario: Verify that GET /api/subscriptions returns 401 status and correct error message with invalid authorization.

    * The api user constructs the base url with the "invalid" token.
    # Api kullanicisi "invalid" token ile base urli olusturur
    * The api user sets "api/subscriptions" path parameters.
    # Api kullanicisi "api/blogs" path parametrelerini olusturur
    * The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
    # Api kullanicisi GET request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrular














