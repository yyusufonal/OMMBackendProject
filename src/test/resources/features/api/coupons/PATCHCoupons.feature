Feature: PATCH Coupons Scenarios

  Scenario Outline: When a PATCH body with valid authorization information and correct (id) and correct data (valid_days, user_limit, description) is sent to /api/editCoupon/{id} endpoint, it should be verified that the status code returned is 200 and the response_message in the response body is “Coupon Details Updated successfully”.
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/editCoupon/<coupon_id>" path parameters.
    When The api user creates a list that contains fields to patch <valid_days>, <user_limit>, "<description>"
    Then The api user sends a PATCH request with the body and saves the returned response.
    And The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Coupon Details Updated successfully".
    Examples:
      | coupon_id | valid_days | user_limit | description  |
      | 111       | 15         | 10         | Coupon Desc. |
