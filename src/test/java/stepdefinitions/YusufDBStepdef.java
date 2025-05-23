package stepdefinitions;

import helperDB.CommonData;
import helperDB.JDBC_Structure_Methods;
import helperDB.ShopService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manage.Manage;
import org.junit.Assert;

import static helperDB.JDBC_Structure_Methods.*;

import java.sql.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static helperDB.CommonData.*;
import static helperDB.ShopService.generateServices;
import static org.junit.Assert.assertEquals;

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
    public void prepare_query_to_insert_data_entry_into_the_shop_services_list_table(Integer count) throws SQLException {
        query = getUS03_insert_shop_services_list();
        preparedStatement = getPraperedStatement(query);
        List<ShopService> services = generateServices(count);

        int flag = 0;

        for(ShopService service:services){
            preparedStatement.setInt(1,services.get(flag).getProvider_id());
            preparedStatement.setInt(2,services.get(flag).getShop_id());
            preparedStatement.setInt(3,services.get(flag).getService_offer_id());
            preparedStatement.setString(4,services.get(flag).getService_offer_name());
            preparedStatement.setInt(5,services.get(flag).getStaff_id());
            preparedStatement.setDouble(6,services.get(flag).getLabour_charge());
            preparedStatement.setString(7,services.get(flag).getDuration());
            preparedStatement.setString(8,services.get(flag).getDuration_in());
            preparedStatement.setString(9,services.get(flag).getRemarks());
            preparedStatement.setInt(10,services.get(flag).getDelete_status());

            preparedStatement.addBatch();
            flag++;
            if (flag ==services.size()){
                result =preparedStatement.executeBatch();
            }

        }

    }
    @When("Validate that {int} new records have been successfully inserted into the shop_services_list table.")
    public void validate_that_new_records_have_been_successfully_inserted_into_the_shop_services_list_table(int rowCount) throws SQLException {

        String selectQuery = "SELECT * FROM shop_services_list ORDER BY id DESC LIMIT " + rowCount;
        PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
        ResultSet rs = selectStmt.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        while (rs.next()) {
            StringBuilder row = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                row.append(rsmd.getColumnName(i)).append(": ").append(rs.getString(i));
                if (i < columnCount) row.append(", ");
            }
            System.out.println(row.toString());
        }

        System.out.println("Inserted " + result.length + " records successfully.");
        System.out.println(Arrays.toString(result));
        assertEquals(rowCount,result.length);



    }

    @Then("Prepare query for deleted into the bank_account table.")
    public void prepare_query_for_deleted_into_the_bank_account_table() throws SQLException {
        query =getUS08deleted_data_inactive();
        rowCount = getStatement().executeUpdate(query);

    }
    @Then("Verify that any data deleted.")
    public void verify_that_any_data_deleted() {
        Assert.assertTrue(rowCount > 0);
        System.out.println(rowCount+"  ADET İNAKTİF KAYIT SİLİNDİ");

    }

    @Then("Create a statement to list categories from oldest to most recent")
    public void create_a_statement_to_list_categories_from_oldest_to_most_recent() throws SQLException {
        query =getUS15oldest_newest_categorie();
        resultSet=getStatement().executeQuery(query);
    }
    @When("Confirm the query yields category data")
    public void confirm_the_query_yields_category_data() throws SQLException {
        while (resultSet.next()) {
            java.util.Date newest_category = resultSet.getDate("newest_category");
            Date oldest_category = resultSet.getDate("oldest_category");
            System.out.printf("newest_category: %s, oldest_category: %s", newest_category, oldest_category);


        }
    }
}
