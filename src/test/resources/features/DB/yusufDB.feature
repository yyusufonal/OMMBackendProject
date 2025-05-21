Feature:US_003,US_008 and US_015 Database testing


  Scenario: Enter the necessary data in the shop_services_list table and add 3 data at the same time.

    Given Database connection established.
    Then Prepare query to insert 3 data entry into the shop_services_list table.
    When Validate that 3 new records have been successfully inserted into the 'shop_services_list' table.
    And Database closed.

