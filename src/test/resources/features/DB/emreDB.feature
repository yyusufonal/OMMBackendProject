Feature: US_021,US_023 VE US_039 database test

  @DB
  Scenario: US021 insert 5 data entry into the deposit_details table
    Given Database connection established.
    When Prepare query to insert 5 data entry into the deposit_details table
    Then Verify bulk 5 data added to the table
    And Database closed.

  @DB
  Scenario: US023 List Recently Created Password Reset Requests
    Given Database connection established.
    Then The last 3 password reset requests are retrieved
    Then The requests should be displayed
    And Database closed.

  @DB
  Scenario: US039 Calculate and verify the number of employees who joined in August

    Given Database connection established.
    When I calculate the total number of employees who joined in August in employee_basic_details table
    Then The number of employees joined in August should be verified
    And Database closed.