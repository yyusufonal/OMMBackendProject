package stepdefinitions;

import helperDB.BankAccount;
import helperDB.CommonData;
import helperDB.ShopService;
import helperDB.depositDetails;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en_scouse.An;
import manage.Manage;
import org.junit.Assert;

import java.sql.SQLException;
import java.util.*;

import static helperDB.BankAccount.generateAccounts;
import static helperDB.CommonData.*;
import static helperDB.CommonData.result;
import static helperDB.JDBC_Structure_Methods.*;


import static org.junit.Assert.*;




import static helperDB.JDBC_Structure_Methods.getPraperedStatement;


public class SeyyidDBStepdef {
	Manage manage = new Manage();

	CommonData data = new CommonData();

	public SeyyidDBStepdef() throws SQLException {
	}


	@Given("create query to insert data entry into the administrators table")
	public void create_query_to_insert_data_entry_into_the_administrators_table() throws SQLException {
		query = manage.getUS04_insert_data_administrator();
		preparedStatement = getPraperedStatement(query);
		//(email, password, username, full_name, profile_img, role, token)
		preparedStatement.setString(1, data.getEmail());
		preparedStatement.setString(2, data.getPassword());
		preparedStatement.setString(3, data.getUsername());
		preparedStatement.setString(4, data.getFullName());
		preparedStatement.setString(5, data.getProfile_img());
		preparedStatement.setInt(6, data.getRole());
		preparedStatement.setString(7, data.getToken());
	}





	@And("Delete the added data")
	public void delete_the_added_data() throws SQLException {
		userId = getLastInsertedUserId(manage.getUS04getLastUserIdAdministrator(), data.getEmail());
		query = manage.getUS04deleted_data_administrator();
		preparedStatement = getPraperedStatement(query);
		preparedStatement.setInt(1, userId);

	}



	@Given("Prepare query group by bank_name and total accounts")
	public void prepare_query_group_by_bank_name_and_total_accounts() throws SQLException {
		query = manage.getUS07group_by_bankName_bankAccounts();
		resultSet = getStatement().executeQuery(query);
	}

	@Given("Verify results are obtained bank_account table")
	public void verify_results_are_obtained_bank_account_table() throws SQLException {
		bankAccount = new HashMap<>();
		assertTrue("ResultSet should have at least one row", resultSet.next());
		int flag = 0;
		do {
			String bankName = resultSet.getString("bank_name");
			int accountsCount = resultSet.getInt("total_accounts");

			bankAccount.put(bankName, accountsCount);

			// Sonuçları yazdırma (isteğe bağlı)
			System.out.printf("Status: %s, Count: %d%n", bankName, accountsCount);

		} while (resultSet.next());
		System.out.println(bankAccount.size());
	}

	@Given("Prepare query categories table groupby gendertype")
	public void prepare_query_categories_table_groupby_gendertype() throws SQLException {
		query = manage.getUS14categories_table();
		resultSet = getStatement().executeQuery(query);
	}

	@Given("Verify results are obtained categories table")
	public void verify_results_are_obtained_categories_table() throws SQLException {
		//gender_type, category_type, num_categories
		while (resultSet.next()) {
			Integer gender_type = resultSet.getInt("gender_type");
			Integer category_type = resultSet.getInt("category_type");
			Integer num_categories = resultSet.getInt("num_categories");


			System.out.printf("gender_type: %d, category_type: %s, num_categories: %s", gender_type, category_type, num_categories);
		}
	}

	@Given("Database closed")
	public void database_closed() {
		closeConnection();
	}

	@Then("Verify that {int} added to the table")
	public void verify_that_added_to_the_table(int expectedRowCount) throws SQLException {
		int actualRowCount = preparedStatement.executeUpdate();
		assertEquals("Veri eklenemedi!", expectedRowCount, actualRowCount);
	}

}
