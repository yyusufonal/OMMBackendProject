package helperDB;

import java.sql.SQLException;
import java.util.List;

import static helperDB.CommonData.faker;
import static helperDB.CommonData.result;
import static helperDB.JDBC_Structure_Methods.preparedStatement;

public class depositDetails {
    private static List<depositDetails> deposits;
    public static int [] insertFakeData(int numberOfRecords) throws SQLException {
        for (int i = 0; i < numberOfRecords; i++) {
            // Faker ile rastgele veriler oluştur
            int providerId = faker.number().numberBetween(1, 100);
            int bookId = faker.number().numberBetween(1, 1000);
            int orderId = faker.number().numberBetween(1000, 2000);
            double amount = faker.number().randomDouble(2, 50, 500);
            java.sql.Date depositDate = new java.sql.Date(faker.date().past(30, java.util.concurrent.TimeUnit.DAYS).getTime());
            Integer depositStatus = faker.options().option(0, 1);
            java.sql.Timestamp createdAt = new java.sql.Timestamp(faker.date().past(30, java.util.concurrent.TimeUnit.DAYS).getTime());
            java.sql.Timestamp depositCompletedAt = depositStatus.equals(1) ?
                    new java.sql.Timestamp(faker.date().between(depositDate, new java.util.Date()).getTime()) : null;

            // PreparedStatement'e verileri ekle


            preparedStatement.setInt(1, providerId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.setInt(3, orderId);
            preparedStatement.setDouble(4, amount);
            preparedStatement.setDate(5, depositDate);
            preparedStatement.setInt(6, depositStatus);
            preparedStatement.setTimestamp(7, createdAt);
            preparedStatement.setTimestamp(8, depositCompletedAt);

            // Batch'e ekle
            preparedStatement.addBatch();

        }
        return   result = preparedStatement.executeBatch();

    }
}
