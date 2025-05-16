Feature:As a provider I want to be able to access blogs via API connection.
        # Bir sağlayıcı olarak API bağlantısı üzerinden bloglara erişebilmek istiyorum.

Scenario Outline: Verify that a GET request to /api/blogs with valid authorization returns status code 200,
response_message “Blogs Listed Successfully”, and blog id(x) includes all expected blog fields.
  # Geçerli yetkilendirme ile /api/blogs endpoint'ine yapılan GET isteğinin durum kodu 200,
  # response_message "Blogs Listed Successfully" dönmesini ve id(x) olan blogun tüm beklenen alanları içermesini doğrula.

    Given The api user constructs the base url with the "provider" token.
    # Api kullanicisi "provider" token ile base urli olusturur
    Then The api user sets "api/blogs" path parameters.
    # Api kullanicisi "api/blogs" path parametrelerini olusturur
    And The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    When The api user verifies that the status code is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    Then The api user verifies that the "response.response_message" information in the response body is "Blogs Listed Successfully".
    # Api kullanicisi response bodydeki response_message bilgisinin "Blogs Listed Successfully" oldugunu dogrular
     And The api user verifies the information in the response body for the entry with the specified <dataIndex> index, including "<lang_id>", "<title>", "<slug>", "<tags>" and "<summary>".
    # Api kullanıcısı response body icindeki <dataIndex> indexe sahip olanin "<lang_id>", "<title>", "<slug>", "<tags>" ve "<summary>" bilgilerini doğrular.

    Examples:
      | dataIndex | lang_id | title                                                                             | slug        | tags | summary                     |
      | 0         | 1       | Discovering the Benefits of Hiring Local Professionals through OnlineMasterMarket | discovering |      | In today's fast-paced world |


  Scenario: Verify that a GET request to /api/blogs with an invalid API key returns status code 401 and response_message
  “Invalid token or token missing”.
    #Geçersiz bir API anahtarı (token) ile /api/blogs endpoint'ine yapılan GET isteğinde,
    #  durum kodunun 401 ve response_message bilgisinin "Invalid token or token missing" olduğu doğrulanmalıdır.


    Given The api user constructs the base url with the "invalid" token.
    # Api kullanicisi "invalid" token ile base urli olusturur
    And The api user sets "api/blogs" path parameters.
    # Api kullanicisi "api/blogs" path parametrelerini olusturur
    Then The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
    # Api kullanicisi GET request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrular
