Feature: PATCH Coupons Scenarios

  Scenario Outline: When a PATCH body containing valid authorization information and correct (id) and correct data (service_id, coupon_name, coupon_percentage, start_date, valid_days, user_limit, description)  is sent to the /api/editCoupon/{id} endpoint, it must be verified that the returned status code is 200 and the response_message information in the response body is "Coupon Details Updated successfully".
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/editCoupon/<coupon_id>" path parameters.
    When The api users creates a list that contains fields to patch <service_id>, "<coupon_name>", <coupon_percentage>, "<start_date>", <valid_days>, <user_limit>, "<description>"
    Then The api user sends a PATCH request with the body and saves the returned response.
    And The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Coupon Details Updated successfully".
    Examples:
      | coupon_id | service_id | coupon_name | coupon_percentage | start_date | valid_days | user_limit | description  |
      | 129       | 15         | new2         | 20               | 2025-05-22 | 15         | 10         | Coupon Desc. |

  Scenario Outline: When a PATCH body with valid authorization information and correct (id) and correct data (valid_days, user_limit, description) is sent to /api/editCoupon/{id} endpoint, it should be verified that the status code returned is 200 and the response_message in the response body is "Coupon Details Updated successfully".
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/editCoupon/<coupon_id>" path parameters.
    When The api user creates a list that contains fields to patch <valid_days>, <user_limit>, "<description>"
    Then The api user sends a PATCH request with the body and saves the returned response.
    And The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Coupon Details Updated successfully".
    Examples:
      | coupon_id | valid_days | user_limit | description  |
      | 129       | 15         | 10         | Coupon Desc. |

  Scenario Outline: When a PATCH request is sent to the /api/editCoupon/{id} endpoint with valid authorization information, the status code returned is 203 and the response_message in the response body is "No data for updated.".
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/editCoupon/<coupon_id>" path parameters.
    Then The api user sends a PATCH request with the body and saves the returned response.
    And The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "No data for updated.".
    Examples: 
      | coupon_id |
      | 129       |

  Scenario Outline: When sending a PATCH body (service_id, coupon_name, coupon_percentage, start_date, valid_days, user_limit, description) containing a correct (id) and invalid service_id with valid authorization information to the /api/editCoupon/{id} endpoint, it should be verified that the status code returned is 203 and the response_message in the response body is "Service ID is invalid.".
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/editCoupon/<coupon_id>" path parameters.
    When The api users creates a list that contains fields to patch <service_id>, "<coupon_name>", <coupon_percentage>, "<start_date>", <valid_days>, <user_limit>, "<description>"
    Then The api user sends a PATCH request with the body and saves the returned response.
    And The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Service ID is invalid.".
    Examples: 
      | coupon_id | service_id | coupon_name | coupon_percentage | start_date | valid_days | user_limit | description  |
      | 129       | 1532334    | new2        | 20               | 2025-05-22 | 15         | 10         | Coupon Desc. |

  Scenario Outline: When sending a PATCH body with valid authorization information (id) and correct data (service_id, coupon_name, coupon_percentage, start_date, valid_days, user_limit, description) to /api/editCoupon/{id} endpoint, it should be verified that the status code returned is 203 and the response_message in the response body is "Id missing".
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/editCoupon/" path parameters.
    When The api users creates a list that contains fields to patch <service_id>, "<coupon_name>", <coupon_percentage>, "<start_date>", <valid_days>, <user_limit>, "<description>"
    Then The api user sends a PATCH request with the body and saves the returned response.
    And The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "Id missing".
    Examples:
      | coupon_id | service_id | coupon_name | coupon_percentage | start_date | valid_days | user_limit | description  |
      | null       | 15         | new2         | 20               | 2025-05-22 | 15         | 10         | Coupon Desc. |

  Scenario Outline: When sending a PATCH body containing an unregistered (id) and correct data (service_id, coupon_name, coupon_percentage, start_date, valid_days, user_limit, description) with valid authorization information to the /api/editCoupon/{id} endpoint, it should be verified that the status code returned is 203 and the response_message in the response body is "No Results found for the given ID".
  Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/editCoupon/<coupon_id>" path parameters.
    When The api users creates a list that contains fields to patch <service_id>, "<coupon_name>", <coupon_percentage>, "<start_date>", <valid_days>, <user_limit>, "<description>"
    Then The api user sends a PATCH request with the body and saves the returned response.
    And The api user verifies that the status code is 203.
    And The api user verifies that the "response.response_message" information in the response body is "No Results found for the given ID".
    Examples:
      | coupon_id | service_id | coupon_name | coupon_percentage | start_date | valid_days | user_limit | description  |
      | 129453    | 15         | new2         | 20               | 2025-05-22 | 15         | 10         | Coupon Desc. |

  Scenario Outline: Verify that the updated_coupon_id in the response body returned from the /api/editCoupon/{id} endpoint is the same as the id path parameter in the /api/editCoupon/{id} endpoint
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/editCoupon/<coupon_id>" path parameters.
    When The api users creates a list that contains fields to patch <service_id>, "<coupon_name>", <coupon_percentage>, "<start_date>", <valid_days>, <user_limit>, "<description>"
    Then The api user sends a PATCH request with the body and saves the returned response.
    And The api user verifies that the status code is 200.
    And The api user verifies that the "response.updated_coupon_id" information in the response body is the same as the "coupon_id" path parameter.
    Examples:
      | coupon_id | service_id | coupon_name | coupon_percentage | start_date | valid_days | user_limit | description  |
      | 129       | 15         | new2         | 20               | 2025-05-22 | 15         | 10         | Coupon Desc. |

  Scenario: The API should verify that the coupon record requested to be updated via the API has been updated.(With the updated_coupon_id returned in the response body, a GET request can be sent to the /api/coupon-details/{id} endpoint to verify that the record has been updated.)
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editCoupon/129" path parameters.
    When The api users creates a list that contains fields to patch 15, "new2", 20, "2025-05-22", 15, 10, "Coupon Desc."
    And The api user sends a PATCH request with the body and saves the returned response.
    Then The api user verifies that the status code is 200..
    And The api user verifies that the "response.response_message" information in the response body is "Coupon Details Updated successfully"..
    And The api user verifies that the "data.updated_coupon_id" information in the response body is the same as the "coupon_id" path parameter.
    And The api user stores the updated coupon id from response
    And The api user sends a GET request to verify the updated coupon
    Then The api user verifies that the status code is 200..
    And The api user validates the "15", "PROnew2", "20", "2025-05-22", "15", "10", "Coupon Desc." in the response body.


