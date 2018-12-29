package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qa.base.TestBase;

public class TestUtil extends TestBase{
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	
	
	// Need to be change Reg_TestData location path accordingly .....
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")+ "/src/main/java/com/qa/testdata/FlightBooking.xlsx";
	
	static Workbook book;
	static Sheet sheet;
	
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		//System.out.println(sheet.getLastRowNum() + "--------" + sheet.getRow(0).getLastCellNum());
		//System.err.println(sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				//System.out.println(data[i][k]);
			}
		}
		return data;
	}

	public static void setCellData(String sheetName, String columnName, String res, int count) throws Exception	{

        FileInputStream fis = new FileInputStream(TESTDATA_SHEET_PATH);
        FileOutputStream fos = null;
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        XSSFRow row = null;
        XSSFCell cell = null;
        
        
        int col_Num = -1;
        row = sheet.getRow(0);
        for(int i = 0; i < row.getLastCellNum(); i++)
        {
            if(row.getCell(i).getStringCellValue().trim().equals(columnName))
            {
                col_Num = i;
            }
        }
 
        row = sheet.getRow(count);
        if(row == null)
            row = sheet.createRow(count);
 
        cell = row.getCell(col_Num);
        if(cell == null)
            cell = row.createCell(col_Num);
 
        cell.setCellValue(res);
 
        fos = new FileOutputStream(TESTDATA_SHEET_PATH);
        workbook.write(fos);
        fos.close();
	}

}
