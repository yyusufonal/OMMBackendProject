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

public class HilalDBStepdef extends Manage{

    CommonData data;

    public HilalDBStepdef() throws SQLException {
        data = new CommonData();
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
}
