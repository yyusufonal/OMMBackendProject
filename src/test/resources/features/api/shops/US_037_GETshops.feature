Feature:US_036 As a provider, I want to be able to access the shops via the API connection
  @API
  Scenario:TC001 Ensure that a GET request to the /api/myShops endpoint with valid authorization returns
            a 200 OK status and includes the message "Shops Listed Successfully"

    Given The api user constructs the base url with the "provider" token.
    # Api kullanicisi "provider" token ile base urli olusturur
    Then The api user sets "api/myShops" path parameters.
    # Api kullanicisi "/api/myShops" path parametrelerini olusturur
    When The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    When The api user verifies that the status code is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    And The api user verifies that the "response.response_message" information in the response body is "Shops Listed Successfully".
    # Api kullanicisi response bodydeki response_message bilgisinin "Shops Listed Successfully" oldugunu dogrular

  @API
  Scenario Outline:TC002 Verify that a GET request to /api/blogs with valid authorization returns status code 200,
  response_message “Blogs Listed Successfully”, and blog id(x) includes all expected blog fields.

    Given The api user constructs the base url with the "provider" token.
    # Api kullanicisi "provider" token ile base urli olusturur
    Then The api user sets "api/myShops" path parameters.
    # Api kullanicisi "api/blogs" path parametrelerini olusturur
    When The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    And The api user verifies the information in the response body for the entry with the specified <dataIndex> index, including "<shop_code>", "<shop_name>", "<country_code>", "<tax_allow>" and "<tax_number>","<contact_no>","<email>","<address>","<country_name>","<state_name>","<city_name>","<postal_code>".
    # Api kullanıcısı response body icindeki <dataIndex> indexe sahip olanin "<lang_id>", "<title>", "<slug>", "<tags>" ve "<summary>" bilgilerini doğrular.

    Examples:
      | dataIndex | shop_code | shop_name    | country_code| tax_allow | tax_number |contact_no|email                 |address             |country_name|state_name   |city_name|postal_code|
      | 0         | SHOP5GUZuT| Elegant Touch|             |  No       |            |2547896321|info@eleganttouch.com |9W6R+2C Boston      |USA (+1)    |Massachusetts|Boston   |96698      |

  @API
    Scenario: TC003 Verify that a GET request to the /api/myShops endpoint with an invalid API key returns a 401 Unauthorized
              status code and the response body includes the message "Invalid token or token missing."

      Given The api user constructs the base url with the "invalid" token.
      # Api kullanicisi "invalid" token ile base urli olusturur
      When The api user sets "api/myShops" path parameters.
      # Api kullanicisi "api/blogs" path parametrelerini olusturur
      And The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
      # Api kullanicisi GET request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrular


