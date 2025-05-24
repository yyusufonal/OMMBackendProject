Feature:  US_004, US_007, US_014 database test
  @DB
  Scenario: Enter 1 data entry in the administrators table  After verifying that it is done, delete the added data.
    Given Database connection established.
    And create query to insert data entry into the administrators table
    Then Verify that 1 added to the table
    And Delete the added data
    Then Verify that 1 added to the table
    And Database closed

  @DB
  Scenario:Count and group the accounts according to banks in bank_account table.
    Given Database connection established.
    Then Prepare query group by bank_name and total accounts
     Then Verify results are obtained bank_account table
     And Database closed

  @DB
  Scenario:categories table groupby gendertype
    Given Database connection established.
    And Prepare query categories table groupby gendertype
     Then Verify results are obtained categories table
     And Database closed