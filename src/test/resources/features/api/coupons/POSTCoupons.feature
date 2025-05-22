Feature: POST Coupons Scenarios

  Scenario Outline: Create a coupon to delete and store the coupon id
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/addCoupon" path parameters.
    When The api user creates a coupon with given data <service_id>, <percentage>, "<start_date>", <valid_days>, <user_limit>, "<description>"
    Then The api user sends a POST request with the body and saves the returned response.
    And The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Coupon added successfully".
    
    Examples:
      | service_id | percentage | start_date | valid_days | user_limit | description  |
      | 168        | 20         | 2024-07-17 | 15         | 10         | Coupon Desc. |

  Scenario Outline: When a POST body with valid authorization information and missing data is sent to /api/addCoupon endpoint, it should be verified that the status code returned is 203 and the response_message in the response body is "Missing required fields".
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/addCoupon" path parameters.
    When The api user creates a coupon with given data <service_id>, <percentage>, "<start_date>", <valid_days>, <user_limit>, "<description>"
    Then The api user sends a POST request with the body and saves the returned response.
    And The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Missing required fields".

    Examples:
      | service_id | percentage | start_date | valid_days | user_limit | description  |
      | null        | 20         | 2024-07-17 | 15         | 10         | Coupon Desc. |

  Scenario: When a POST request is sent to /api/addCoupon endpoint without valid authorization information and data, it should be verified that the status code returned is 203 and the response_message in the response body is "Missing required fields".
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/addCoupon" path parameters.
    Then The api user sends a POST request with the body and saves the returned response.
    And The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Missing required fields".

  
  Scenario Outline: When a POST body (service_id, coupon_name, percentage, start_date, valid_days, user_limit, description) containing valid authorization information and the same name (coupon_name) is sent to the /api/addCoupon endpoint, it should be verified that the returned status code is 203 and the response_message information in the response body is "The Coupon Name is already exist."
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/addCoupon" path parameters.
    When The api user creates a coupon with given data <service_id>, "<coupon_name>", <percentage>, "<start_date>", <valid_days>, <user_limit>, "<description>"
    Then The api user sends a POST request with the body and saves the returned response.
    And The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "The Coupon Name is already exist.".

    Examples:
      | service_id | coupon_name | percentage | start_date | valid_days | user_limit | description  |
      | 170        | new | 20         | 2024-07-17 | 15         | 10         | Coupon Desc. |

  Scenario Outline: When a POST body (service_id, coupon_name, percentage, start_date, valid_days, valid_days, user_limit, description) with valid authorization information and an invalid service_id is sent to /api/addCoupon endpoint, it should be verified that the status code returned is 203 and the response_message in the response body is “Invalid service id”.
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/addCoupon" path parameters.
    When The api user creates a coupon with given data <service_id>, <percentage>, "<start_date>", <valid_days>, <user_limit>, "<description>"
    Then The api user sends a POST request with the body and saves the returned response.
    And The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Invalid service id".

    Examples:
      | service_id | percentage | start_date | valid_days | user_limit | description  |
      | 16358      | 20         | 2024-07-17 | 15         | 10         | Coupon Desc. |

  Scenario Outline: When sending a POST body with invalid (invalid API key) authorization information and correct data (service_id, coupon_name, percentage, start_date, valid_days, user_limit, description) to /api/addCoupon endpoint, it should be verified that the status code returned is 401 and the response_message in the response body is “Invalid token or token missing”
    Given The api user constructs the base url with the "invalid" token.
    And The api user sets "api/addCoupon" path parameters.
    When The api user creates a coupon with given data <service_id>, <percentage>, "<start_date>", <valid_days>, <user_limit>, "<description>"
    Then The api user sends a POST request with the body and saves the returned response.
    And The api user verifies that the status code is 401.
    And The api user verifies that the "response.response_message" information in the response body is "Invalid token or token missing".

    Examples:
      | service_id | percentage | start_date | valid_days | user_limit | description  |
      | 168        | 20         | 2024-07-17 | 15         | 10         | Coupon Desc. |