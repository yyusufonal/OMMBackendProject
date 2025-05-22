package helperDB;

import com.github.javafaker.Faker;
import lombok.Getter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static helperDB.JDBC_Structure_Methods.*;

@Getter
public class CommonData {
    public static Faker faker = new Faker();
    public static int rowCount;
    public static SQLException exception;
    public static Map<String, Double> actualRatings;
    public static Map<String, Double> expectedRatings; // Bu kısımda beklenen değerleri tanımlayacaksınız


    //(email, password, username, full_name, profile_img, role, token)
    private String email;
    private String password;
    private String username;
    private String fullName;
    private String profile_img;
    private int role;
    private String token;
    public static int userId;
    private String newPassword;
    public static List<String> service_coupons;
    public static List<String> expected_service_coupons=new ArrayList<>(Arrays.asList("PROdeneme","PROCPNNEW73",
            "PROer345","PROTEST","PROCPANEWTY","PROWise","PROCPNNEAW","PROCPNNEAWS"));
    public static Map<String, Integer> bankAccount;
    public static Map<Integer,String> blog_posts;
    public static Map<Integer,Integer> book_service;
    public static Map<Integer,Integer> business_hours;
    //US27 (mobileno, country_code, currency_code, status, usertype)
    private String mobileNo;
    private String countryCode;
    private String currencyCode;
    private int status;
    private int usertype;
    private String address;

    //add Batch bize int array döndürür.
    public static int [] result;

    public CommonData() throws SQLException {
        this.email = faker.internet().emailAddress();
        this.password = faker.internet().password();
        this.username = faker.name().username();
        this.fullName = faker.name().fullName();
        this.profile_img = faker.internet().image();
        this.role = 0;
        this.token = faker.internet().uuid();
        this.mobileNo=faker.numerify("#########");
        this.countryCode=faker.country().currencyCode();
        this.currencyCode=faker.currency().code();
        this.status=faker.random().nextInt(0,1);
        this.usertype=faker.random().nextInt(0,5);
        this.address=faker.address().fullAddress();


    }

    public static int getLastInsertedUserId(String query, String email) throws SQLException {
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("user_id");
        } else {
            throw new SQLException("Kullanıcı ID bulunamadı");
        }
    }

    public String generateNewPassword(Faker faker, String currentPassword) {
        String newPassword;
        do {
            newPassword = faker.internet().password();
        } while (newPassword.equals(currentPassword));
        return newPassword;
    }

}
