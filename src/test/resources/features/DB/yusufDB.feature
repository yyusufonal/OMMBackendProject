Feature:US_003,US_008 and US_015 Database testing


  Scenario: Enter the necessary data in the shop_services_list table and add 3 data at the same time.

    Given Database connection established.
    Then Prepare query to insert 3 data entry into the shop_services_list table.
    When Validate that 3 new records have been successfully inserted into the shop_services_list table.
    And Database closed.


  Scenario: Delete the ones with inactive status in bank_account table. Verify that they are deleted.

    Given Database connection established.
    Then Prepare query for deleted into the bank_account table.
    Then Verify that any data deleted.
    And Database closed.


  Scenario:Verify the dates of the newest and oldest categories among category creation dates.

    Given Database connection established.
    Then  Create a statement to list categories from oldest to most recent
    When Confirm the query yields category data
    And Database closed.
