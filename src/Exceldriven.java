import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class Exceldriven {

	
	@Test
	public void checkData() throws IOException {
		ArrayList<String> data = getDataFromExcel("Login Data","InvalidLogin");
		
		System.out.println(data);
		
	}
	
	public ArrayList<String> getDataFromExcel(String sheetName, String testCaseName) throws IOException {
		String path = System.getProperty("user.dir") + "\\dataTest.xlsx";
		System.out.println(path);
		ArrayList<String> aList = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(path);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int totalSheets = workbook.getNumberOfSheets();

		for (int i = 0; i < totalSheets; i++) {

			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				// we got sheet access
				XSSFSheet desiredSheet = workbook.getSheetAt(i);

				// now get row
				Iterator<Row> rows = desiredSheet.iterator();

				Row firstRow = rows.next();
				Iterator<Cell> columns = firstRow.cellIterator();
				int k = 0;
				int columnIndex = 0;
				// finding column with header testcase
				while (columns.hasNext()) {

					Cell cell = columns.next();
					if (cell.getStringCellValue().equalsIgnoreCase("TestCase")) {
						columnIndex = k;
					}
					k++;
				}

				// finding the testcasename in that column header

				while (rows.hasNext()) {

					Row r = rows.next();

					if (r.getCell(columnIndex).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						// we reach the row with testcase=testcasename
						Iterator<Cell> desiredRow = r.cellIterator();
						Cell cell;
						while (desiredRow.hasNext()) {
							cell = desiredRow.next();
							if (cell.getCellType() == CellType.STRING) {
								aList.add(cell.getStringCellValue());
							} else {
								aList.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
							}
						}
					}

				}
			}

		}
		return aList;
	}
	
	
	
}
