package stepdefinitions;
import helperDB.CommonData;
import helperDB.JDBC_Structure_Methods;
import helperDB.ShopService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manage.Manage;
import utilities.DB_Utilities.JDBCMethods;
import static helperDB.JDBC_Structure_Methods.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import static helperDB.CommonData.*;
import static helperDB.ShopService.generateServices;
import static org.junit.Assert.*;

public class HilalDBStepdef extends Manage {

    CommonData data;

    public HilalDBStepdef() throws SQLException {
        data = new CommonData();
    }

    @Given("Database connection established and coupon limit updated.")
    public void databaseConnectionEstablishedWithCouponUpdate() throws SQLException {
        // First establish database connection
        JDBC_Structure_Methods.createConnection();
        
        // Check coupon state before update
        List<Map<String, Object>> beforeUpdate = JDBCMethods.getQueryResultMap(US25_check_coupon_before_update);
        assertFalse("Coupon with ID 84 not found", beforeUpdate.isEmpty());
        
        // Then update the coupon's user limit count
        JDBCMethods.update(US25_update_coupon_user_limit);
        
        // Verify the update
        List<Map<String, Object>> afterUpdate = JDBCMethods.getQueryResultMap(US25_check_updated_coupon);
        assertFalse("Coupon update failed", afterUpdate.isEmpty());
        
        // Verify that user_limit_count equals user_limit
        Map<String, Object> updatedCoupon = afterUpdate.get(0);
        Object userLimitCountObj = updatedCoupon.get("user_limit_count");
        Object userLimitObj = updatedCoupon.get("user_limit");
        
        assertNotNull("User limit count is null", userLimitCountObj);
        assertNotNull("User limit is null", userLimitObj);
        
        int userLimitCount = ((Number) userLimitCountObj).intValue();
        int userLimit = ((Number) userLimitObj).intValue();
        
        assertEquals("User limit count does not match user limit", userLimit, userLimitCount);
    }

    @When("The user executes the query to get services with their coupons")
    public void theUserExecutesTheQueryToGetServicesWithTheirCoupons() {
        String query = getUS02_coupons_table();
        JDBCMethods.executeQuery(query);
    }

    @Then("The user verifies that the services are grouped by their IDs")
    public void theUserVerifiesThatTheServicesAreGroupedByTheirIDs() throws Exception {
        List<Map<String, Object>> results = JDBCMethods.getQueryResultMap(getUS02_coupons_table());
        assertFalse("No results found in the query", results.isEmpty());
        
        int previousId = -1;
        for (Map<String, Object> row : results) {
            Object serviceIdObj = row.get("id");
            assertNotNull("Service ID is null. Available columns: " + row.keySet(), serviceIdObj);
            
            int currentId;
            if (serviceIdObj instanceof Integer) {
                currentId = (Integer) serviceIdObj;
            } else if (serviceIdObj instanceof Long) {
                currentId = ((Long) serviceIdObj).intValue();
            } else {
                currentId = Integer.parseInt(serviceIdObj.toString());
            }
            
            assertTrue("Services are not properly grouped by ID", currentId >= previousId);
            previousId = currentId;
        }
    }

    @Then("The user verifies that each service has valid coupon information")
    public void theUserVerifiesThatEachServiceHasValidCouponInformation() {
        List<Map<String, Object>> results = JDBCMethods.getQueryResultMap(getUS02_coupons_table());
        for (Map<String, Object> row : results) {
            assertNotNull("Coupon name is null", row.get("coupon_name"));
            assertNotNull("End date is null", row.get("end_date"));
        }
    }

    @Then("The user verifies that the coupon end dates are in correct format")
    public void theUserVerifiesThatTheCouponEndDatesAreInCorrectFormat() {
        List<Map<String, Object>> results = JDBCMethods.getQueryResultMap(getUS02_coupons_table());
        
        for (Map<String, Object> row : results) {
            Object endDateObj = row.get("end_date");
            assertNotNull("End date is null", endDateObj);
            
            try {
                LocalDate endDate;
                if (endDateObj instanceof java.sql.Date) {
                    endDate = ((java.sql.Date) endDateObj).toLocalDate();
                } else if (endDateObj instanceof java.util.Date) {
                    endDate = ((java.util.Date) endDateObj).toInstant()
                            .atZone(java.time.ZoneId.systemDefault())
                            .toLocalDate();
                } else {
                    endDate = LocalDate.parse(endDateObj.toString());
                }
                // Tarih formatı geçerli, başka bir kontrol yapmıyoruz
            } catch (Exception e) {
                fail("Invalid date format: " + endDateObj + ". Error: " + e.getMessage());
            }
        }
    }

    @When("The user executes the query to get coupons that reached maximum user limit")
    public void theUserExecutesTheQueryToGetCouponsThatReachedMaximumUserLimit() {
        String query = US25_user_limit_count_service_coupons;
        JDBCMethods.executeQuery(query);
    }

    @Then("The user verifies that the coupons have reached their user limit")
    public void theUserVerifiesThatTheCouponsHaveReachedTheirUserLimit() {
        List<Map<String, Object>> results = JDBCMethods.getQueryResultMap(US25_user_limit_count_service_coupons);
        assertFalse("No coupons found that reached maximum user limit", results.isEmpty());
    }

    @Then("The user verifies that the user limit count is greater than or equal to user limit")
    public void theUserVerifiesThatTheUserLimitCountIsGreaterThanOrEqualToUserLimit() {
        List<Map<String, Object>> results = JDBCMethods.getQueryResultMap(US25_user_limit_count_service_coupons);
        
        for (Map<String, Object> row : results) {
            Object userLimitCountObj = row.get("user_limit_count");
            Object userLimitObj = row.get("user_limit");
            
            assertNotNull("User limit count is null", userLimitCountObj);
            assertNotNull("User limit is null", userLimitObj);
            
            int userLimitCount = ((Number) userLimitCountObj).intValue();
            int userLimit = ((Number) userLimitObj).intValue();
            
            assertTrue("User limit count (" + userLimitCount + ") is less than user limit (" + userLimit + ")",
                      userLimitCount >= userLimit);
        }
    }
}
