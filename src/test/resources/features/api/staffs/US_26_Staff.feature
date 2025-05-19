Feature: US_0026 Staff API Test


  Scenario: TC001 - Get staff list with invalid token
    Given The api user constructs the base url with the "provider" token.
    And The api user sets "api/myStaffs" path parameters.
    And The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Staffs Listed Successfully".


  Scenario: TC002 - Get staff list with invalid token
    Given The api user constructs the base url with the "invalidApiKey" token.
    And The api user sets "api/myStaffs" path parameters.
    And The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 401.
    And The api user verifies that the "response.response_message" information in the response body is "Invalid token or token missing".


    Scenario: TC003 -  Verify detailed staff information by ID
      Given The api user constructs the base url with the "provider" token.
      And The api user sets "api/myStaffs" path parameters.
      And The api user sends a GET request and saves the returned response.
      When The api user verifies the information in the response body for the entry with the specified <dataIndex> index, including "<shop_code>", "<shop_name>", "<country_code>", "<tax_allow>" and "<tax_number>","<contact_no>","<email>","<address>","<country_name>","<state_name>","<city_name>","<postal_code>".


