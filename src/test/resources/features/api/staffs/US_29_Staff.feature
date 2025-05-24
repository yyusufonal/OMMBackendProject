 Feature: US_029 As a provider, I want to be able to update the information of the staff with the specified id number via API connection.

   @API
  Scenario Outline: TC001 Verify that a PATCH request to /api/editStaff/{id} with valid authorization and correct data
  (firstname, mobileno, email, gender, shop_id, about_emp) returns status code 200, response_message “Staff updated successfully”,
  and updated_staff_id in the response body matches the id in the path parameter.

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editStaff/<id>" path parameters.
    When The api user prepares a patch request body to send to the api editStaff endpoint
    Then The api user sends a PATCH request and saves the returned responsee.
    When The api user verifies that the status code is 200.
    Then The api user verifies that the "response.response_message" information in the response body is "Staff updated successfully".
    Then The api user verifies that the "data.update_staff_id" information in the response body is the same as the id path parameter in the endpoint.

    Examples:
      | id  |
      | 184 |

   @API
  Scenario Outline: TC002 Verify that a PATCH request to /api/editStaff/{id} with valid authorization and partial data (firstname, email)
  returns status code 200 and response_message “Staff updated successfully”.

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editStaff/<id>" path parameters.
      When The api user prepares a patch request body to send to the api editStaff endpointt
    Then The api user sends a PATCH request and saves the returned responsee.
    When The api user verifies that the status code is 200.
    Then The api user verifies that the "response.response_message" information in the response body is "Staff updated successfully".

    Examples:
      | id  |
      | 184 |
   @API
  Scenario Outline: TC003 Verify that a PATCH request to /api/editStaff/{id} with valid authorization and no data
  returns status code 203 and the response_message "No data to update".

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editStaff/<id>" path parameters.
     When The api user prepares a patch request body with no data to sends to the api editShop endpoint
    Then The api user sends a PATCH request and saves the returned responsee.
    When The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "No data for updated.".

    Examples:
      | id  |
      | 184 |
   @API
  Scenario: TC004 Verify that a PATCH request to /api/editStaff without an id and with valid data
  returns status code 203 and the response_message “Id missing”.

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editStaff" path parameters.
    When The api user prepares a patch request body to send to the api editStaff endpoint
    Then The api user sends a PATCH request and saves the returned responsee.
    When The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "Id missing".
   @API
  Scenario Outline: TC005 Verify that a PATCH request to /api/editStaff/{id} with valid authorization and non-existent id
  returns status code 203 and the response_message “Failed to update staff. Invalid id.”

    Given The api user constructs the base url with the "provider" token.
    Then The api user sets "api/editStaff/<id>" path parameters.
    When The api user prepares a patch request body to send to the api editStaff endpoint
    Then The api user sends a PATCH request and saves the returned responsee.
    When The api user verifies that the status code is 203.
    Then The api user verifies that the "response.response_message" information in the response body is "Failed to update staff.No staff this id.".


    Examples:
      | id  |
      | 999 |
   @API
  Scenario Outline: TC006 Verify that a PATCH request to /api/editStaff/{id} with invalid token and valid data
  returns status code 401 and the reason phrase Unauthorized.

    Given The api user constructs the base url with the "invalid" token.
    Then The api user sets "api/editStaff/<id>" path parameters.
    When The api user prepares a patch request body to send to the api editStaff endpoint
    Then The api user sends a PATCH request, saves the returned response, and verifies that the status code is 401 with the reason phrase Unauthorized.

    Examples:
      | id  |
      | 163 |

