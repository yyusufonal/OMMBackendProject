package stepdefinitions;

import helperDB.CommonData;
import helperDB.JDBC_Structure_Methods;
import helperDB.depositDetails;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manage.Manage;
import org.junit.Assert;

import static helperDB.JDBC_Structure_Methods.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import static helperDB.CommonData.*;
import static helperDB.depositDetails.insertFakeData;
import static org.junit.Assert.assertEquals;


public class EmreDBStepdef extends Manage {

    CommonData data = new CommonData();

    public EmreDBStepdef() throws SQLException {

    }

    @Given("Prepare query to insert {int} data entry into the deposit_details table")
    public void prepare_query_to_insert_data_entry_into_the_deposit_details_table(Integer numberofrecords) {
        query = getUS21_insert_bulk_data_deposit_details();
        preparedStatement = getPraperedStatement(query);
        depositDetails inserter = new depositDetails();
        try {
            result = insertFakeData(numberofrecords);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Given("Verify bulk {int} data added to the table")
    public void verify_bulk_data_added_to_the_table(int rowCount) {

        System.out.println("Inserted " + result.length + " records successfully.");
        System.out.println(Arrays.toString(result));
        assertEquals(rowCount, result.length);
    }

    @Given("The last {int} password reset requests are retrieved")
    public void the_last_password_reset_requests_are_retrieved(Integer int1) throws SQLException {
        query = getUS23_forget_password_3();
        resultSet = getStatement().executeQuery(query);
    }

    @When("The requests should be displayed")
    public void displayPasswordResetRequests() {
        try {
            if (resultSet == null) {
                Assert.fail("ResultSet is null. Did you forget to retrieve data?");
            }

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                String email = resultSet.getString("email");
                String endtimeFormatted = resultSet.getString("endtime_formatted");

                System.out.printf("%d | %d | %s | %s%n", id, userId, email, endtimeFormatted);
            }
        } catch (SQLException e) {
                              e.printStackTrace();


        }
    }
}