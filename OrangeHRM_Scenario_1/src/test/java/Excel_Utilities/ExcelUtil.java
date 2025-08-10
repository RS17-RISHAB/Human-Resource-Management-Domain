package Excel_Utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;          // ✅ Correct import
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

    public static Object[][] getData(String excelPath, String sheetName)
            throws EncryptedDocumentException, IOException {

        try (FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "\\src\\test\\resources\\Login_Data.xlsx");
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);  // ✅ No wrong casting
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getLastCellNum();

            Object[][] data = new Object[rowCount - 1][colCount];

            for (int i = 1; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
                }
            }
            return data;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
