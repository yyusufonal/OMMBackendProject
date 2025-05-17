Feature: As a provider, I want to list my products and validate product details via API connection.

  Scenario: When a valid authorization token and correct shop_id are sent in a GET request to the /api/myProducts endpoint,
  the status code should be 200, and the response_message should be "Products Listed Successfully".

  #AC1: When a valid authorization token and correct shop_id are sent in a GET request to the /api/myProducts endpoint,
       #the status code should be 200, and the response_message should be "Products Listed Successfully".

  #AC2: For the product with id(x), the following fields in the response body must be verified:
       #product_name, currency, currency_code, product_currency, product_price, sale_price, product_discount,
       #short_description, category_name, subcategory_name, product_image.

    * The api user constructs the base url with the "provider" token.
    * The api user sets "api/myProducts" path parameters.
    * The user add body parameters shopId
    * The api user sends a GET request and user add body.
    * The api user verifies that the status code is 200.
    * The api user verifies that the "response.response_message" information in the response body is "Products Listed Successfully".
    * The api user verifies that the "data.product_list[0]" information in the response body is "<id>", "<product_name>", "<currency>", "<currency_code>", "<product_currency>" and "<product_price>","<sale_price>","<product_discount>","<short_description>","<category_name>","<subcategory_name>","<product_image>".


    #Examples:
    #  | id | product_name | currency    | currency_code| product_currency | product_price |sale_price|product_discount                 |short_description             |category_name|subcategory_name   |product_image|
    #And  For the product with id(x), the following fields in the response body must be verified "<id>", "<product_name>", "<currency>", "<currency_code>", "<product_currency>" and "<product_price>","<sale_price>","<product_discount>","<short_description>","<category_name>","<subcategory_name>","<product_image>".
    # Api kullanıcısı response body icindeki <dataIndex> indexe sahip olanin "<lang_id>", "<title>", "<slug>", "<tags>" ve "<summary>" bilgilerini doğrular.

  Scenario: AC3 - Valid token but missing shop_id
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/myProducts" path parameters.
    When the user sends a GET request to "/api/myProducts" without a shop_id
    Then The api user sends a GET request and user add body.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "shop_id is required.".

  Scenario: AC4 - Valid token but unregistered shop_id
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/myProducts" path parameters.
    When the user sends a GET request to "/api/myProducts" with an unregistered shop_id
    Then The api user sends a GET request and user add body.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "No shop this id or No product this shop.".

  Scenario: AC5 - Invalid authorization token with valid shop_id
    Given The api user constructs the base url with the "invalid" token.
    Then The api user sets "api/myProducts" path parameters.
    Then The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
