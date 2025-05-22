Feature: Database Testing

  Background: Database connectivity
    * Database connection established

  @US033
  Scenario: Activate the message with the subject 'System Maintenance' for users with user_status = 1
    * Prepare query to update message status for users in the database
    * Verify that the message status is updated for the correct number of users
    * Database closed



  @US036
  Scenario: Add 5 data to the chat_table table at the same time
    * Prepare query to insert 5 chat records into the chat_table
    * Verify that 5 records are inserted into the chat_table
    * Database closed

  @US038
  Scenario: List the id, email and message information of the messages sent between certain dates in the contact_form_details table
    * Prepare query to select id, email, and message between '2024-08-01' and '2024-08-10'
    * Verify that the correct data is retrieved from the contact_form_details table
    * Database closed