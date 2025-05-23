Feature: Find the person who sent the most messages

  Scenario: Find the person who sent the most messages in the contact_form_details table
    Given database connection is established
    When the person who sent the most messages from the contact_form_details table is queried
    Then the name of the person who sent the most messages should not be empty
    And the number of messages should be greater than zero