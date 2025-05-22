package stepdefinitions;

import helperDB.CommonData;
import io.cucumber.java.en.Given;
import manage.Manage;
import java.sql.SQLException;
import static helperDB.JDBC_Structure_Methods.*;
import static org.junit.Assert.assertEquals;

public class UmmuhanDBStepdef extends Manage {

    CommonData data;

    public UmmuhanDBStepdef() throws SQLException {
        data = new CommonData();
    }

    @Given("Prepare query to insert data entry into the administrators table")
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

    @Given("Verify that {int} added to the table")
    public void verify_that_added_to_the_table(int expectedRowCount) throws SQLException {
        int actualRowCount = preparedStatement.executeUpdate();
        assertEquals("Veri eklenemedi!", expectedRowCount, actualRowCount);
    }

    @Given("Update password")
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

}