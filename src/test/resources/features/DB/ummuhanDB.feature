
Feature: Database Testing

  Background: Database connectivity
    * Database connection established.

  @US_005
  Scenario: Update password in administrators table with SHA2 encryption.
    Given Prepare query to insert data entry into the administrators table.
    Then Verify that 1 added to the table.
    Then Update password.
    Then Database closed.

  @US_009
  Scenario: Retrieve blog posts where title contains 'professional' (case-insensitive).
    Given Query blog_posts table for titles containing 'professional' (case-insensitive).
    Then Print the titles of matching blog posts.
    Then Database closed.

  @US_010
  Scenario: Retrieve blog posts created in the last 30 days and verify by ID.
    Given Query blog_posts table for posts created within the last 30 days.
    Then Verify the returned post IDs are listed.
    Then Database closed.



