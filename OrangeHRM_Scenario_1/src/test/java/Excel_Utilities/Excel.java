package Excel_Utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		FileInputStream file  = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\Login_Data.xlsx") ;
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet  sheet = workbook.getSheet("Sheet1");
		int total_rows = sheet.getLastRowNum() ;
		int total_cell = sheet.getRow(1).getLastCellNum() ;
		for(int r =0 ; r<=total_rows ; r ++) {
			    XSSFRow currentrow = sheet.getRow(r);
			for(int c =0 ; c <total_cell ; c++ ) {
				
				XSSFCell cell = currentrow.getCell(c);
				System.out.println(cell.toString());
			}
		}
		
		workbook.close();
		file.close();
		
	}

}
