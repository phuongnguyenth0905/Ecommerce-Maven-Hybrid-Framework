package utilitiesConfig;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import java.io.File;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelHelper {
    public static List<Map<String, String>> readExcel(String filePath, String sheetName) {
        List<Map<String, String>> excelData = new ArrayList<>();

        try {
            ClassLoader classLoader = ExcelHelper.class.getClassLoader();
            URL resource = classLoader.getResource(filePath);

            if (resource == null) {
                throw new FileNotFoundException("File not found in resources: " + filePath);
            }

            File file = new File(resource.toURI());
            FileInputStream fis = new FileInputStream(file);

            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            // Header
            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getLastCellNum();
            List<String> headers = new ArrayList<>();

            for (int i = 0; i < colCount; i++) {
                headers.add(headerRow.getCell(i).toString());
            }

            // Rows
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    rowData.put(headers.get(j), cell == null ? "" : cell.toString());
                }
                excelData.add(rowData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return excelData;
    }
}
