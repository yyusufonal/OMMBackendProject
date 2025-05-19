Feature: API_US058 - As a provider, I want to be able to create a new product record via API connection.

  Scenario Outline: TC001 - Successful product creation and creation verification
    #AC1
    Given The api user constructs the base url with the "provider" tokenN.
    Then The api user sets "api/addProduct" path parametersS.
    When The api user prepares a post request body to send to the api addProduct endpoint
    Then The api user sends a POSTT request and saves the returned responses
    Then The api user verifies that the statusS code is 200.
    Then The api user verifies that the "response.response_message" informationN in the response body is "Product Added successfully".
    #AC5
    Given The api user constructs the base url with the "provider" tokenN.
    Then The api user sets "api/product-details/<id>" path parametersS.
    When The api user sends a GET request and saves the returned response.
    Then The api user verifies that the status code is 200.
    And The api user verifies that the "response.response_message" information in the response body is "Product Details".

    Examples:
    |id|
    |76|

  #AC2
  Scenario: TC002 - Product creation with missing or empty data
    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/addProduct" path parameters.
    When The api user prepares a post request body with missing required fields to send to the api addProduct endpoint
    Then The api user sends a POST request and saves the returned responses
    Then The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "Add product failed, required fields empty.".

    #AC3
    Given The api user constructs the base url with the "provider" token.
    Given The api user prepares an empty POST request body
    Then The api user sets "api/addProduct" path parameters.
    Then The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "Add product failed, required fields empty.".

  #AC4
  Scenario: TC003 - Invalid authorization token with valid product data
    Given The api user constructs the base url with the "invalid" token.
    Then The api user sets "api/addProduct" path parameters.
    When The api user prepares a post request body to send to the api addShop endpoint
    Then The api user sends a POST request and saves the returned response
    When The api user verifies that the status code is 401.
    And The api user verifies that the "response.response_message" information in the response body is "Invalid token or token missing".