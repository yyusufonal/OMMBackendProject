package helperDB;

import com.github.javafaker.Faker;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Getter

public class ShopService {
    static Faker faker = new Faker();
    //(provider_id, shop_id, service_offer_id,
    // service_offer_name, staff_id, labour_charge, duration, duration_in, remarks, delete_status
    // Sabit değişkenler
    private static final List<String> remarksList = new ArrayList<>(Arrays.asList(
            "No remarks", "Include extra", "Discount applied", "New customer", "Provider"
    ));
    public static int randomIndex;

    // Sınıf değişkenleri
    private int provider_id;
    private int shop_id;
    private int service_offer_id;
    private String service_offer_name;
    private int staff_id;
    private double labour_charge;
    private String duration;
    private String duration_in;
    private String remarks;
    private int delete_status;

    // Constructor
    public ShopService(int provider_id, int shop_id, int service_offer_id, String service_offer_name,
                       int staff_id, double labour_charge, int duration,
                       String duration_in, String remarks, int delete_status) {
        this.provider_id = provider_id;
        this.shop_id = shop_id;
        this.service_offer_id = service_offer_id;
        this.service_offer_name = service_offer_name;
        this.staff_id = staff_id;
        this.labour_charge = labour_charge;
        this.duration = String.valueOf(duration);
        this.duration_in = duration_in;
        this.remarks = remarks != null ? remarks : getRandomRemark();
        this.delete_status = delete_status;
    }

    // Rastgele bir açıklama seçen metod
    private static String getRandomRemark() {
        randomIndex = faker.random().nextInt(remarksList.size());
        return remarksList.get(randomIndex);
    }

    // Sınıfın işleyişine uygun diğer metodlar burada eklenebilir.


    public List<ShopService> getServices() {
        //(int provider_id, int shop_id, int service_offer_id, String service_offer_name,
        //                           int staff_id, double labour_charge, int duration,
        //                           String duration_in, String remarks, int delete_status)
        return List.of(
                new ShopService(faker.number().numberBetween(100, 1000), faker.number().numberBetween(2000, 4000), (int) faker.number().randomNumber(), faker.company().name(), (int) faker.number().randomNumber(), faker.number().randomDouble(3, 1, 9), faker.number().numberBetween(1, 10), "Minuten", ShopService.getRandomRemark(), faker.number().numberBetween(0, 1)),
                new ShopService(faker.number().numberBetween(100, 1000), faker.number().numberBetween(2000, 4000), (int) faker.number().randomNumber(), faker.company().name(), (int) faker.number().randomNumber(), faker.number().randomDouble(3, 1, 9), faker.number().numberBetween(1, 10), "Minuten", ShopService.getRandomRemark(), faker.number().numberBetween(0, 1)),
                new ShopService(faker.number().numberBetween(100, 1000), faker.number().numberBetween(2000, 4000), (int) faker.number().randomNumber(), faker.company().name(), (int) faker.number().randomNumber(), faker.number().randomDouble(3, 1, 9), faker.number().numberBetween(1, 10), "Minuten", ShopService.getRandomRemark(), faker.number().numberBetween(0, 1))
        );
    }


    public static List<ShopService> generateServices(int count) {
        List<ShopService> services = new ArrayList<>();
        Random random = new Random();
       int randomIndex = random.nextInt(remarksList.size());

        for (int i = 0; i < count; i++) {
            ShopService service = new ShopService(
                    faker.number().numberBetween(100, 1000),
                    faker.number().numberBetween(2000, 4000),
                    (int) faker.number().randomNumber(),
                    faker.company().name(),
                    (int) faker.number().randomNumber(),
                    faker.number().randomDouble(3, 1, 9),
                    faker.number().numberBetween(1, 10),
                    "Minuten",
                    ShopService.getRandomRemark(),
                    faker.number().numberBetween(0, 1)

            );
            services.add(service);
        }
        return services;
    }
    }


