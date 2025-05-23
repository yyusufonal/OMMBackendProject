Feature: Find the person who sent the most messages

  Scenario: Find the person who sent the most messages in the contact_form_details table
    Given database connection is established
    When the person who sent the most messages from the contact_form_details table is queried
    Then the name of the person who sent the most messages should not be empty
    And the number of messages should be greater than zero

  Scenario: Select and list products with a discounted price 20% or more above the normal price
    Given the database connection is established
    When the products with discounted price at least 20 percent higher than normal price are queried
    Then each listed product's discounted price should be equal to or greater than 120 percent of its normal price