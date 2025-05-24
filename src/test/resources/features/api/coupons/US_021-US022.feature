Feature: Get Coupons Scenarios
  @API
  Scenario: Verify that a GET request to /api/coupons/{id} with valid authorization and correct id returns status
  code 200, response_message "Coupons Details", and that all expected coupons fields are present in the response body.

    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/myCoupons" path parameters.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Service Coupon History Listed Successfully".

  @API
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
  @API
  Scenario: When a GET request is sent to /api/myCoupons endpoint with invalid (invalid API key) authorization information, it should be verified that the status code returned is 401 and the response_message in the response body is "Invalid token or token missing".
    Given The api user constructs the base url with the "invalid" token.
    And The api user sets "api/myCoupons" path parameters.
    When The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
  @API
  Scenario: When a GET request with valid authorization information and correct data (id) is sent to the /api/coupon-details/{id} endpoint, it should be verified that the status code returned is 200 and the response_message in the response body is "Coupon Details".
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/coupon-details/106" path parameters.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Coupon Details".
  @API
  Scenario: The contents of the user data (id, service_id, coupon_name, coupon_type, coupon_type_text, coupon_percentage, coupon_amount, start_date, end_date, valid_days, user_limit, user_limit_count, description, service_title, status) in the response body must be verified.
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/coupon-details/106" path parameters.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Coupon Details".
    And The api user validates the "106", "168", "PROCPNNEAWS", "1", "Percentage", "20", "0", "2024-07-17", "2024-08-01", "15", "10", "0", "Coupon Desc.", "Efe", and "Expired" in the response body.
  @API
  Scenario: When a GET request is sent to the /api/coupon-details/{id} endpoint that does not contain valid authorization information and (id), it should be verified that the status code returned is 203 and the response_message in the response body is "Id missing".
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/coupon-details/" path parameters.
    When The api user sends a GET request and saves the returned response try catch.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Id missing".
  @API
  Scenario: When a GET request is sent to the /api/coupon-details/{id} endpoint with valid authorization information and an unregistered (id), it should be verified that the status code returned is 203 and the response_message in the response body is "No Details found".
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/coupon-details/10623" path parameters.
    When The api user sends a GET request and saves the returned response try catch.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "No Details found".
  @API
  Scenario: When a GET request is sent to the /api/coupon-details/{id} endpoint with invalid (invalid API key) authorization information, it should be verified that the status code returned is 401 and the response_message in the response body is "Invalid token or token missing".
    Given The api user constructs the base url with the "invalid" token.
    And The api user sets "api/coupon-details/106" path parameters.
    When The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.





