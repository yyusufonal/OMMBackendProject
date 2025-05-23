package stepdefinitions;

import helperDB.CommonData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import manage.Manage;
import org.junit.runners.model.Statement;
import utilities.DB_Utilities.JDBCMethods;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import static helperDB.JDBC_Structure_Methods.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UmmuhanDBStepdef extends Manage {

    CommonData data;
    Statement statement;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    String query;


    public UmmuhanDBStepdef() throws SQLException {
        data = new CommonData();
    }

    @Given("Prepare query to insert data entry into the administrators table.")
    public void prepare_query_to_insert_data_entry_into_the_administrators_table() throws SQLException {
        query = "INSERT INTO u201212290_onlinemasterqa.administrators " +
                "(email, password, username, full_name, profile_img, role, token) VALUES (?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = getPraperedStatement(query);

        preparedStatement.setString(1, data.getEmail());
        preparedStatement.setString(2, data.getPassword());
        preparedStatement.setString(3, data.getUsername());
        preparedStatement.setString(4, data.getFullName());
        preparedStatement.setString(5, data.getProfile_img());
        preparedStatement.setInt(6, data.getRole());
        preparedStatement.setString(7, data.getToken());
    }

    @Then("Verify that {int} added to the table.")
    public void verify_that_added_to_the_table(int expectedRowCount) throws SQLException {
        int actualRowCount = preparedStatement.executeUpdate();
        assertEquals("Veri eklenemedi!", expectedRowCount, actualRowCount);
    }

    @Then("Update password.")
    public void update_password() throws SQLException {
        String selectQuery = "SELECT user_id FROM u201212290_onlinemasterqa.administrators " +
                "WHERE email = ? ORDER BY user_id DESC LIMIT 1";
        CommonData.userId = CommonData.getLastInsertedUserId(selectQuery, data.getEmail());

        String newPassword = data.generateNewPassword(CommonData.faker, data.getPassword());

        query = "UPDATE u201212290_onlinemasterqa.administrators SET password = SHA2(?,256) WHERE user_id = ?";
        preparedStatement = getPraperedStatement(query);
        preparedStatement.setString(1, newPassword);
        preparedStatement.setInt(2, CommonData.userId);

        int updatedRows = preparedStatement.executeUpdate();
        assertEquals("Şifre güncellenemedi!", 1, updatedRows);
    }

    @Then("Query blog_posts table for titles containing 'professional' \\(case-insensitive).")
    public void query_blog_posts_by_title_professional_case_insensitive() {
        query = getUS09slug_blog_posts();
        JDBCMethods.executeQuery(query);
    }

    @Then("Print the titles of matching blog posts.")
    public void print_titles_of_matching_blog_posts() {
        List<Map<String, Object>> results = JDBCMethods.getQueryResultMap(query);
        System.out.println(" Blog Posts with 'professional' in slug/title");
        for (Map<String, Object> row : results) {
            System.out.println("ID: " + row.get("id") + " | Title: " + row.get("title"));
        }
    }

    @Given("Query blog_posts table for posts created within the last 30 days.")
    public void query_blog_posts_last_30_days() {
        query = getUS10blog_interval_30days();
        JDBCMethods.executeQuery(query);
    }

    @Then("Verify the returned post IDs are listed.")
    public void verify_returned_post_ids_are_listed() {
        List<Object> ids = JDBCMethods.getColumnData(query, "id");
        assertFalse("Son 30 gün içinde blog bulunamadı!", ids.isEmpty());
        System.out.println("Recent Blog Post IDs: " + ids);
    }

}