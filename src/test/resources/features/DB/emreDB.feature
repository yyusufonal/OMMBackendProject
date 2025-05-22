Feature: US_021,US_023 VE US_039 database test


  Scenario: insert 5 data entry into the deposit_details table
    Given Database connection established.
    When Prepare query to insert 5 data entry into the deposit_details table
    Then Verify bulk 5 data added to the table
    And Database closed.


  Scenario: List Recently Created Password Reset Requests
    Given Database connection established.
    Then The last 3 password reset requests are retrieved
    Then The requests should be displayed
    And Database closed.


  Scenario: Calculate and verify the number of employees who joined in August

    Given Database connection established.
    When I calculate the total number of employees in the employee_basic_details table whose "created_at" date is in August
    Then The number of employees who joined in August should be equal to the expected value
    And Database closed.