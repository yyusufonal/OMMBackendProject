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

  Scenario Outline: When a DELETE request is sent to the /api/deleteCoupon/{id} endpoint that does not contain valid authorization information and (id), it should be verified that the status code returned is 203 and the response_message in the response body is "Id missing".
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/addCoupon" path parameters.
    When The api user creates a coupon with given data <service_id>, <percentage>, "<start_date>", <valid_days>, <user_limit>, "<description>"
    Then The api user sends a POST request with the body and saves the returned response.
    And The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Coupon added successfully".
    And The api user stores the coupon id

    # Delete create coupon
    And The api user sets "api/deleteCoupon" path parameters.
    When The api user sends a DELETE request with the body and saves the returned response.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Id missing".

    Examples:
      | service_id | percentage | start_date | valid_days | user_limit | description  |
      | 168        | 20         | 2024-07-17 | 15         | 10         | Coupon Desc. |

  Scenario Outline: When a DELETE request is sent to the /api/deleteCoupon/{id} endpoint with valid authorization information and an unregistered (id), the status code returned is 203 and the response_message in the response body is "Coupon not found. Invalid ID." should be verified.
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/addCoupon" path parameters.
    When The api user creates a coupon with given data <service_id>, <percentage>, "<start_date>", <valid_days>, <user_limit>, "<description>"
    Then The api user sends a POST request with the body and saves the returned response.
    And The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Coupon added successfully".
    And The api user stores the coupon id

    # Delete create coupon
    And The api user sets "api/deleteCoupon/23434" path parameters.
    When The api user sends a DELETE request with the body and saves the returned response.
    Then The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Coupon not found. Invalid ID.".

    Examples:
      | service_id | percentage | start_date | valid_days | user_limit | description  |
      | 168        | 20         | 2024-07-17 | 15         | 10         | Coupon Desc. |

  Scenario: When a DELETE request is sent to the /api/deleteCoupon/{id} endpoint with invalid (invalid API key) authorization information, it should be verified that the status code returned is 401 and the response_message in the response body is "Invalid token or token missing".
    Given The api user constructs the base url with the "invalid" token.
    And The api user sets "api/deleteCoupon" path parameters.
    Then The api user sends a DELETE request, saves the returned response, and verifies that the status code is "401" with the reason phrase Unauthorized.

  Scenario Outline: Verify that the deleted_coupon_id in the response body returned from the /api/deleteCoupon/{id} endpoint is the same as the id path parameter in the /api/deleteCoupon/{id} endpoint.
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
    And The api user verifies that the "data.deleted_coupon_id" information in the response body is the same as the "coupon_id" path parameter.

    Examples:
      | service_id | percentage | start_date | valid_days | user_limit | description  |
      | 168        | 20         | 2024-07-17 | 15         | 10         | Coupon Desc. |

  Scenario: "The deletion of the coupon record requested to be deleted from the API must be verified from the API.(With the deleted_coupon_id returned in the response body, a GET request can be sent to the /api/coupon-details/{id} endpoint to verify that the record has been deleted.)"
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/myCoupons/168" path parameters.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 203.