
Feature: Database Testing

  Background: Database connectivity
    * Database connection established.

  @US05
  Scenario: Update password in administrators table with SHA2 encryption
    * Prepare query to insert data entry into the administrators table
    * Verify that 1 added to the table
    * Update password
    * Database closed.
