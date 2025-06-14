package stepdefinitions;

import helperDB.JDBC_Structure_Methods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.*;
import manage.Manage;
import org.junit.Assert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import static helperDB.CommonData.*;
import static helperDB.ShopService.generateServices;
import static org.junit.Assert.assertEquals;
import static helperDB.JDBC_Structure_Methods.*;


public class CemDB_StepDefinition extends Manage {



    String mostActiveName;
    int messageCount;

    @Given("database connection is established")
    public void database_connection_is_established() {

        JDBC_Structure_Methods.createConnection();
    }
    @When("the person who sent the most messages from the contact_form_details table is queried")
    public void the_person_who_sent_the_most_messages_from_the_contact_form_details_table_is_queried() {
        JDBC_Structure_Methods.query = "SELECT name, COUNT(*) AS message_count " +
                "FROM contact_form_details " +
                "GROUP BY name " +
                "ORDER BY message_count DESC " +
                "LIMIT 1";
        try {
            JDBC_Structure_Methods.resultSet = JDBC_Structure_Methods.getStatement().executeQuery(JDBC_Structure_Methods.query);
            if (JDBC_Structure_Methods.resultSet.next()) {
                mostActiveName = JDBC_Structure_Methods.resultSet.getString("name");
                messageCount = JDBC_Structure_Methods.resultSet.getInt("message_count");
                System.out.println("En çok mesaj gönderen: " + mostActiveName + " (" + messageCount + ")");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Then("the name of the person who sent the most messages should not be empty")
    public void the_name_of_the_person_who_sent_the_most_messages_should_not_be_empty() {
        Assert.assertNotNull("En çok mesaj gönderen kişi null", mostActiveName);
        Assert.assertFalse("Ad boş", mostActiveName.trim().isEmpty());
    }
    @Then("the number of messages should be greater than zero")
    public void the_number_of_messages_should_be_greater_than_zero() {
        Assert.assertTrue("Mesaj sayısı 0 ya da daha az", messageCount > 0);
    }

    @Given("the database connection is established")
    public void theDatabaseConnectionIsEstablished() {
        JDBC_Structure_Methods.createConnection();
    }

    @When("the products with discounted price at least {int} percent higher than normal price are queried")
    public void theProductsWithDiscountedPriceAtLeastPercentHigherThanNormalPriceAreQueried(int arg0) {
        JDBC_Structure_Methods.query = "SELECT * FROM products WHERE sale_price >= price * 1.20";
        try {
            resultSet = JDBC_Structure_Methods.getStatement().executeQuery(JDBC_Structure_Methods.query);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Query failed: " + e.getMessage());
        }
    }

    @Then("each listed product's discounted price should be equal to or greater than {int} percent of its normal price")
    public void eachListedProductSDiscountedPriceShouldBeEqualToOrGreaterThanPercentOfItsNormalPrice(int arg0) {
        try {
            while (resultSet.next()) {
                double price = resultSet.getDouble("price");
                double salePrice = resultSet.getDouble("sale_price");
                Assert.assertTrue(
                        "Sale price is not at least 20% higher: " + salePrice + " < " + (price * 1.20),
                        salePrice >= price * 1.20
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error verifying results: " + e.getMessage());
        } finally {
            JDBC_Structure_Methods.closeConnection();
        }
    }

}
