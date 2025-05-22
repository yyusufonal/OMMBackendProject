package stepdefinitions;

import io.cucumber.java.en.Given;
import org.junit.Assert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SaadatDBStepdef {

    String query;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    // Assume these methods are defined elsewhere in your codebase
    public PreparedStatement getPraperedStatement(String query) throws SQLException {
        // Replace with your actual implementation
        return null;
    }

    /**
     * US033
     **/
    @Given("Prepare query to update message status for users in the database")
    public void prepare_query_to_update_message_status_for_users_in_the_database() throws SQLException {
        query = "UPDATE user_messages SET active = 1 " +
                "WHERE subject = 'System Maintenance' " +
                "AND user_id IN (SELECT id FROM users WHERE user_status = 1)";
        preparedStatement = getPraperedStatement(query);
        int updatedRows = preparedStatement.executeUpdate();
        System.out.println("Rows updated: " + updatedRows);
        Assert.assertTrue("No rows were updated", updatedRows > 0);
    }

    /**
     * US036
     **/
    @Given("Prepare query to insert 5 chat records into the chat_table")
    public void prepare_query_to_insert_5_chat_records_into_the_chat_table() throws SQLException {
        query = "INSERT INTO chat_table (sender_id, receiver_id, message, timestamp) VALUES (?, ?, ?, ?)";
        preparedStatement = getPraperedStatement(query);

        int totalInserted = 0;
        for (int i = 0; i < 5; i++) {
            preparedStatement.setInt(1, 100 + i); // sender_id
            preparedStatement.setInt(2, 200 + i); // receiver_id
            preparedStatement.setString(3, "Test message " + (i + 1)); // message
            preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // timestamp
            preparedStatement.addBatch();
        }

        int[] result = preparedStatement.executeBatch();
        for (int count : result) {
            totalInserted += count;
        }

        System.out.println("Total rows inserted: " + totalInserted);
        Assert.assertEquals("Expected 5 rows to be inserted", 5, totalInserted);
    }

    /**
     * US038
     **/
    @Given("Prepare query to select id, email, and message between '2024-08-01' and '2024-08-10'")
    public void prepare_query_to_select_id_email_and_message_between_dates() throws SQLException {
        query = "SELECT id, email, message FROM contact_form_details " +
                "WHERE sent_date BETWEEN '2024-08-01' AND '2024-08-10'";
        preparedStatement = getPraperedStatement(query);
        resultSet = preparedStatement.executeQuery();

        boolean hasResults = false;
        while (resultSet.next()) {
            hasResults = true;
            int id = resultSet.getInt("id");
            String email = resultSet.getString("email");
            String message = resultSet.getString("message");
            System.out.println("ID: " + id + ", Email: " + email + ", Message: " + message);
        }

        Assert.assertTrue("No messages found between given dates", hasResults);
    }
}
