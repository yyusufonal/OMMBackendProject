package helperDB;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static helperDB.CommonData.faker;
@Getter
public class BankAccount {
    /**
     * ( user_id, user_name, acc_no, bank_name, bank_addr, ifsc_code, pancard_no, paypal_account, paypal_email_id, status)
     */
    private int userId;
    private String userName;
    private String acc_no;
    private String bank_name;
    private String bank_addr;
    private String ifsc_code;
    private String pancard_no;
    private String paypal_account;
    private String paypal_email_id;
    private static List<String> statusList = new ArrayList<String>(Arrays.asList("pending", "active", "inactive"));
    private String status;
    public static int randomIndex;


    public BankAccount(int userId, String userName, String acc_no, String bankName, String bank_addr, String ifscCode, String pancardNo, String paypalAccount, String paypal_email_id,String status) {
    this.userId=userId;
    this.userName=userName;
    this.acc_no=acc_no;
    this.bank_name=bankName;
    this.bank_addr=bank_addr;
    this.ifsc_code=ifscCode;
    this.pancard_no=pancardNo;
    this.paypal_account=paypalAccount;
    this.paypal_email_id=paypal_email_id;
    this.status=status != null ? status : getRandomStatus();

    }


    private static String getRandomStatus() {
        randomIndex = faker.random().nextInt(statusList.size());
        return statusList.get(randomIndex);
    }

    public List<BankAccount> getAccount() {

        return List.of(
                new BankAccount(faker.number().numberBetween(100, 1000), faker.name().fullName(),  faker.lorem().word(), faker.lorem().word(), faker.address().fullAddress(), faker.lorem().word(), faker.lorem().word(),  faker.lorem().word(), faker.internet().ipV4Address(), statusList.get(randomIndex)),
                new BankAccount(faker.number().numberBetween(100, 1000), faker.name().fullName(), faker.lorem().word(),  faker.lorem().word(), faker.address().fullAddress(), faker.lorem().word(),  faker.lorem().word(), faker.lorem().word(), faker.internet().ipV4Address(), statusList.get(randomIndex)),
                new BankAccount(faker.number().numberBetween(100, 1000), faker.name().fullName(),  faker.lorem().word(),  faker.lorem().word(), faker.address().fullAddress(), faker.lorem().word(), faker.lorem().word(), faker.lorem().word(), faker.internet().ipV4Address(), statusList.get(randomIndex)),
                new BankAccount(faker.number().numberBetween(100, 1000), faker.name().fullName(),  faker.lorem().word(),  faker.lorem().word(), faker.address().fullAddress(),  faker.lorem().word(), faker.lorem().word(),  faker.lorem().word(), faker.internet().ipV4Address(), statusList.get(randomIndex))

        );
    }


    public static List<BankAccount> generateAccounts(int count) {
        List<BankAccount> accounts = new ArrayList<>();
        Random random = new Random();
        randomIndex = random.nextInt(statusList.size());

        for (int i = 0; i < count; i++) {
            BankAccount account = new BankAccount(
                    //user_id, user_name, acc_no, bank_name, bank_addr, ifsc_code, pancard_no, paypal_account, paypal_email_id, status
                    faker.number().numberBetween(100, 1000),
                    faker.name().fullName(),
                    faker.lorem().word(),
                    faker.lorem().word(),
                    faker.address().fullAddress(),
                    faker.lorem().word(),
                    faker.lorem().word(),
                    faker.lorem().word(),
                    faker.internet().ipV4Address(),
                    statusList.get(randomIndex)

            );
            accounts.add(account);
        }
        return accounts;
    }
}
