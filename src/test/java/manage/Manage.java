package manage;

import lombok.Getter;

@Getter
public class Manage {

    /**
     * US_01
     **/
    private String US01_Update_the_address_provider_address_table = "UPDATE u201212290_onlinemasterqa.provider_address pa\n" +
            "JOIN  u201212290_onlinemasterqa.providers p ON pa.provider_id = p.id\n" +
            "SET pa.address =? \n" +
            "WHERE p.email = \"oske.work@gmail.com\";";
    /**
     * US_02
     **/
    private String US02_coupons_table = "SELECT s.id AS service_id, \n" +
            "       sc.coupon_name, \n" +
            "       sc.end_date\n" +
            "FROM services s\n" +
            "JOIN service_coupons sc ON s.id = sc.service_id\n" +
            "GROUP BY s.id, sc.coupon_name, sc.end_date\n" +
            "ORDER BY s.id;";
    /**
     * US_03
     **/
    private String US03_insert_shop_services_list = "INSERT INTO u201212290_onlinemasterqa.shop_services_list (provider_id, shop_id, service_offer_id, service_offer_name, staff_id, labour_charge, duration, duration_in, remarks, delete_status)\n" +
            "VALUES (?,?,?,?,?,?,?,?,?,?)";
    /**
     * US_04
     **/
    private String US04_insert_data_administrator = "INSERT INTO u201212290_onlinemasterqa.administrators (email, password, username, full_name, profile_img, role, token) Values (?,?,?,?,?,?,?);";

    private String US04getLastUserIdAdministrator = "Select user_id From u201212290_onlinemasterqa.administrators Where email=?;";
    private String US04deleted_data_administrator = "Delete From u201212290_onlinemasterqa.administrators Where user_id=?;";
    /**
     * US_05
     **/
    private String US05update_password_administrator = "UPDATE u201212290_onlinemasterqa.administrators SET password = SHA2(?,256) WHERE user_id =?;";
    /**
     * US_06
     **/
    private String US06insert_bank_accounts = "INSERT INTO u201212290_onlinemasterqa.bank_account \n" +
            "( user_id, user_name, acc_no, bank_name, bank_addr, ifsc_code, pancard_no, paypal_account, paypal_email_id, status)\n" +
            "VALUES (?,?,?,?,?,?,?,?,?,?)";
    /**
     * US_07
     **/
    private String US07group_by_bankName_bankAccounts = "SELECT bank_name, COUNT(*) as total_accounts \n" +
            "FROM  u201212290_onlinemasterqa.bank_account\n" +
            "GROUP BY bank_name;";
    /**
     * US_08
     */
    private String US08deleted_data_inactive = "DELETE FROM  u201212290_onlinemasterqa.bank_account\n" +
            "WHERE status = 'inactive';";
    /**
     * US_09
     */
    private String US09slug_blog_posts = "\n" +
            "SELECT * \n" +
            "FROM u201212290_onlinemasterqa.blog_posts \n" +
            "WHERE slug LIKE '%professionals%';";
    /**
     * US_10
     */
    private String US10blog_interval_30days = "SELECT  * \n" +
            "FROM  u201212290_onlinemasterqa.blog_posts \n" +
            "WHERE createdAt >= NOW() - INTERVAL 30 DAY;";
    /**
     * US_11
     */
    private String US11book_service = "SELECT provider_id, SUM(amount) AS total_amount\n" +
            "FROM  u201212290_onlinemasterqa.book_service\n" +
            "GROUP BY provider_id\n" +
            "ORDER BY total_amount DESC;";
    /**
     * US_12
     */
    private String US12book_service_max_shop_service="SELECT shop_id, service_count\n" +
            "FROM (\n" +
            "    SELECT shop_id, COUNT(*) AS service_count\n" +
            "    FROM  u201212290_onlinemasterqa.book_service\n" +
            "    GROUP BY shop_id\n" +
            "    ORDER BY service_count DESC\n" +
            "    LIMIT 1\n" +
            ") AS top_shop;";
    /**
     * US_13
     */
    private String US13_overlaptime_providers="SELECT A.provider_id AS provider1, B.provider_id AS provider2, A.day,\n" +
            "       GREATEST(A.from_time, B.from_time) AS overlap_start,\n" +
            "       LEAST(A.to_time, B.to_time) AS overlap_end\n" +
            "FROM (\n" +
            "    SELECT provider_id, \n" +
            "           JSON_UNQUOTE(JSON_EXTRACT(availability, '$[0].day')) AS day,\n" +
            "           JSON_UNQUOTE(JSON_EXTRACT(availability, '$[0].from_time')) AS from_time,\n" +
            "           JSON_UNQUOTE(JSON_EXTRACT(availability, '$[0].to_time')) AS to_time\n" +
            "    FROM u201212290_onlinemasterqa.business_hours\n" +
            ") AS A\n" +
            "JOIN (\n" +
            "    SELECT provider_id, \n" +
            "           JSON_UNQUOTE(JSON_EXTRACT(availability, '$[0].day')) AS day,\n" +
            "           JSON_UNQUOTE(JSON_EXTRACT(availability, '$[0].from_time')) AS from_time,\n" +
            "           JSON_UNQUOTE(JSON_EXTRACT(availability, '$[0].to_time')) AS to_time\n" +
            "    FROM u201212290_onlinemasterqa.business_hours\n" +
            ") AS B\n" +
            "ON A.day = B.day AND A.provider_id < B.provider_id\n" +
            "WHERE GREATEST(A.from_time, B.from_time) < LEAST(A.to_time, B.to_time);";
    /**
     * US_14
     */
    private String US14categories_table="SELECT gender_type, category_type, COUNT(*) AS num_categories\n" +
            "FROM u201212290_onlinemasterqa.categories\n" +
            "GROUP BY gender_type, category_type\n" +
            "ORDER BY gender_type, category_type;";
    /**
     * US_15
     */
    private String US15oldest_newest_categorie="SELECT MAX(created_at) AS newest_category, MIN(created_at) AS oldest_category\n" +
            "FROM u201212290_onlinemasterqa.categories;";
    /**US_16*/
    private String US16cotact_form_detail="SELECT id, name, email, message, created_at\n" +
            "FROM u201212290_onlinemasterqa.contact_form_details\n" +
            "WHERE created_at >= NOW() - INTERVAL 7 DAY;";
    /**US_17 */
    private String US17_name_message_count="SELECT name, COUNT(*) AS num_messages\n" +
            "FROM u201212290_onlinemasterqa.contact_form_details\n" +
            "GROUP BY name\n" +
            "ORDER BY num_messages DESC Limit 1;\n";
    private String US18_messages_like_servis="SELECT id, name, email, message, created_at\n" +
            "FROM u201212290_onlinemasterqa.contact_form_details\n" +
            "WHERE message LIKE '%servis%';";
    /**
     * US_19
     **/
    private String US19_insert_contact_reply = "INSERT INTO u201212290_onlinemasterqa.contact_reply (contact_id, name, reply, created_at)\n" +
            "SELECT id AS contact_id, name, \n" +
            "       CASE\n" +
            "           WHEN message LIKE '%servis%' THEN 'Servis oluşturma hakkında size nasıl yardımcı olabiliriz?'\n" +
            "           ELSE 'Mesajınızı aldık ve en kısa sürede geri döneceğiz.'\n" +
            "       END AS reply, \n" +
            "       NOW() AS created_at\n" +
            "FROM u201212290_onlinemasterqa.contact_form_details;";
    /**
     * US_20
     **/
    private String US20_year_message_count="SELECT YEAR(created_at) AS year, COUNT(*) AS message_count\n" +
            "FROM u201212290_onlinemasterqa.contact_form_details\n" +
            "GROUP BY YEAR(created_at)\n" +
            "ORDER BY year DESC;";
    /**
     * US_21
     **/
    private String US21_insert_bulk_data_deposit_details="INSERT INTO u201212290_onlinemasterqa.deposit_details " +
            "(provider_id, book_id, order_id, amount, deposit_date, deposit_status, created_at, deposit_completed_at) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * US_22
     **/
    private String US22_footer_menu="SELECT DATE_FORMAT(created_date, '%Y-%m') AS month, COUNT(*) AS num_menus\n" +
            "FROM u201212290_onlinemasterqa.footer_menu\n" +
            "GROUP BY month\n" +
            "ORDER BY month;";
    /**
     * US_23
     **/
    private String US23_forget_password_3="SELECT id, user_id, email, FROM_UNIXTIME(endtime) AS endtime_formatted\n" +
            "FROM u201212290_onlinemasterqa.forget_password_det\n" +
            "ORDER BY endtime DESC\n" +
            "LIMIT 3;";
    private String US24_rating_review="SELECT DATE(created) AS review_date, AVG(rating) AS average_rating\n" +
            "FROM  u201212290_onlinemasterqa.rating_review\n" +
            "WHERE created >= CURDATE() - INTERVAL 15 DAY\n" +
            "GROUP BY review_date\n" +
            "ORDER BY review_date DESC;";
    /**
     * US_25
     **/
    protected String US25_user_limit_count_service_coupons = "SELECT * FROM u201212290_onlinemasterqa.service_coupons WHERE user_limit_count >= user_limit;";

    protected String US25_check_coupon_before_update = "SELECT * FROM u201212290_onlinemasterqa.service_coupons WHERE id = 84;";

    protected String US25_update_coupon_user_limit = "UPDATE u201212290_onlinemasterqa.service_coupons " +
                                                    "SET user_limit_count = user_limit " +
                                                    "WHERE id = 84;";

    protected String US25_check_updated_coupon = "SELECT * FROM u201212290_onlinemasterqa.service_coupons WHERE id = 84;";

    /**
     * US_27
     **/
    private String US27_user_incomplate_data_insert = "INSERT INTO u201212290_onlinemasterqa.users (mobileno, country_code, currency_code, status, usertype)\n" +
            "VALUES (?, ?, ?, ?, ?)";


}