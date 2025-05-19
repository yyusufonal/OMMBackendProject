Feature: Get Coupons Scenarios

  Scenario: Verify that a GET request to /api/coupons/{id} with valid authorization and correct id returns status
  code 200, response_message “Coupons Details”, and that all expected coupons fields are present in the response body.

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/myCoupons" path parameters.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Service Coupon History Listed Successfully".


  Scenario Outline: The information returned in the response body for id(x) (service_id, coupon_name, coupon_type, coupon_type_text, coupon_percentage, coupon_amount, start_date, end_date, valid_days, user_limit, user_limit_count, description, service_title, status) must be verified.
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/myCoupons" path parameters.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Service Coupon History Listed Successfully".
    And The api user validates the "<id>", "<service_id>", "<coupon_name>", "<coupon_type>", "<coupon_type_text>", "<coupon_percentage>", "<coupon_amount>", "<start_date>", "<end_date>", "<valid_days>", "<user_limit>", "<user_limit_count>", "<description>", "<service_title>", and "<status>" in the response body.
    Examples:
      | id  | service_id | coupon_name | coupon_type | coupon_type_text | coupon_percentage | coupon_amount | start_date | end_date   | valid_days | user_limit | user_limit_count | description  | service_title | status |
      | 106 | 168        | PROCPNNEAWS | 1           | Percentage       | 20                | 0             | 2024-07-17 | 2024-08-01 | 15         | 10         | 0                | Coupon Desc. | Efe           | Expired |

