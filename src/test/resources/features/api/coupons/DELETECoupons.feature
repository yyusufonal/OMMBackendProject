Feature: PATCH Coupons Scenarios


  Scenario Outline: Create a coupon to delete and store the coupon id
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/addCoupon" path parameters.
    When The api user creates a coupon with given data <service_id>, <percentage>, "<start_date>", <valid_days>, <user_limit>, "<description>"
    Then The api user sends a POST request with the body and saves the returned response.
    And The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Coupon added successfully".
    And The api user stores the coupon id

    # Delete create coupon
    Given The api user adds coupon_id URL parameter to "api/deleteCoupon/"
    When The api user sends a DELETE request with the body and saves the returned response.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Coupon deleted successfully".

    Examples:
      | service_id | percentage | start_date | valid_days | user_limit | description  |
      | 168        | 20         | 2024-07-17 | 15         | 10         | Coupon Desc. |


