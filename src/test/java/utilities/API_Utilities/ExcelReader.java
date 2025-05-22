package utilities.API_Utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelReader {

    static Sheet sheet1;

    public static int isimAltindakiDegeriGetir(String isim) {
        String excelDosyaYolu = "src/test/resources/TestData.xlsx";

        Workbook workbook = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(excelDosyaYolu);
            workbook = WorkbookFactory.create(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Excel dosyası okunamadı.");
        }

        sheet1 = workbook.getSheet("Sheet1");

        // 1. satır: başlıklar
        Row headerRow = sheet1.getRow(0);
        int sutunIndex = -1;

        // İstenen ismin olduğu sütun indexini bul
        for (Cell cell : headerRow) {
            if (cell.getStringCellValue().equalsIgnoreCase(isim)) {
                sutunIndex = cell.getColumnIndex();
                break;
            }
        }

        if (sutunIndex == -1) {
            throw new RuntimeException("İsim bulunamadı: " + isim);
        }

        // 2. satırdaki (index 1) ilgili hücreyi al
        Row veriRow = sheet1.getRow(1);
        Cell hedefCell = veriRow.getCell(sutunIndex);

        // Hücre değeri sayı değilse veya boşsa kontrol et
        if (hedefCell == null || hedefCell.getCellType() != CellType.NUMERIC) {
            throw new RuntimeException("Geçerli bir sayı bulunamadı.");
        }

        return (int) hedefCell.getNumericCellValue();
    }






    public static void isimAltindakiDegeriGuncelle(String isim, int yeniDeger) {
        String excelDosyaYolu = "src/test/resources/TestData.xlsx";

        Workbook workbook = null;
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileInputStream = new FileInputStream(excelDosyaYolu);
            workbook = WorkbookFactory.create(fileInputStream);
            sheet1 = workbook.getSheet("Sheet1");

            // Başlık (isimlerin bulunduğu) satırı al
            Row headerRow = sheet1.getRow(0);
            int sutunIndex = -1;

            for (Cell cell : headerRow) {
                if (cell.getStringCellValue().equalsIgnoreCase(isim)) {
                    sutunIndex = cell.getColumnIndex();
                    break;
                }
            }

            if (sutunIndex == -1) {
                throw new RuntimeException("İsim bulunamadı: " + isim);
            }

            // 2. satır (index 1) veya varsa daha fazla satır
            Row veriRow = sheet1.getRow(1);
            if (veriRow == null) {
                veriRow = sheet1.createRow(1); // yoksa oluştur
            }

            Cell hedefCell = veriRow.getCell(sutunIndex);
            if (hedefCell == null) {
                hedefCell = veriRow.createCell(sutunIndex);
            }

            hedefCell.setCellValue(yeniDeger); // yeni değeri ata

            // Excel dosyasını kaydet
            fileOutputStream = new FileOutputStream(excelDosyaYolu);
            workbook.write(fileOutputStream);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Excel dosyası yazılamadı.");
        } finally {
            try {
                if (fileInputStream != null) fileInputStream.close();
                if (fileOutputStream != null) fileOutputStream.close();
                if (workbook != null) workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
