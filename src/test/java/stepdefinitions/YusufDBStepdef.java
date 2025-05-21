package stepdefinitions;

import helperDB.CommonData;
import helperDB.JDBC_Structure_Methods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manage.Manage;
import static helperDB.JDBC_Structure_Methods.*;
import java.sql.SQLException;

public class YusufDBStepdef extends Manage {

    CommonData data = new CommonData();

    public YusufDBStepdef() throws SQLException {
    }

    @Given("Database connection established.")
    public void database_connection_established() {
        createConnection();

    }

    @When("Database closed.")
    public void database_closed() {
        closeConnection();
    }

    @Then("Prepare query to insert {int} data entry into the shop_services_list table.")
    public void prepare_query_to_insert_data_entry_into_the_shop_services_list_table(Integer number) {
        query = getUS03_insert_shop_services_list();
        preparedStatement = getPraperedStatement(query);



    }
    @When("Validate that {int} new records have been successfully inserted into the {string} table.")
    public void validate_that_new_records_have_been_successfully_inserted_into_the_table(Integer int1, String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }



}
