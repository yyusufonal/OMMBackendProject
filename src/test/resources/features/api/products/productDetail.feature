Feature: API_US057 - Product detail retrieval through API

  Scenario Outline: TC001 - Valid authorization and valid product id
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/product-details/11" path parameters.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Product Details".
    And The api user validates the <dataIndex> index, including "<id>", "<user_id>", "<manufactured_by>", "<shop_id>","<category>","<subcategory>","<product_name>","<unit>","<unit_name>","<unit_value>","<currency>","<currency_code>" and "<prices>","<sales_price>","<product_discount>","<short_description>","<description>","<category_name>","<subcategory_name>","<shop_name>","<price>","<sale_price>","<discount>" contents of the data in the response body.


    Examples:
      | dataIndex |id| user_id | manufactured_by    | shop_id| category | subcategory |product_name|unit |unit_name|unit_value|currency|currency_code|prices|sales_price|product_discount|short_description|description           |category_name      |subcategory_name|shop_name    |price|sale_price|discount|
      | 0         |11| 5       | Vital Balance      |    6   |  6       |     6       |Tangle-Free  | 1  |pc       | 60       | $      | USD          |   9 | 9         | 0              | Jump Rope       |This jump rope is made|Health & Wellness|Fitness         |Vital Balance|9    |9         |0       |


    #(id, user_id, manufactured_by, shop_id, category, subcategory, product_name, unit, unit_name, unit_value, currency, currency_code, prices,
    # sales_price, product_discount, short_description, description, category_name, subcategory_name, shop_name, price, sale_price, discount