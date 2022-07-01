package com.evs.qcodes.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.itextpdf.text.log.SysoCounter;

public class ExcelUtil {

	/**
	 * This method is to create the object of the Excel sheet from which we want to
	 * read the TestData.getObject().
	 * 
	 * @param dataSheetPath This is the location where we have kept the Excel sheet
	 *                      file.
	 * @return It will return a Workbook object which has reference to the excel
	 *         sheet.
	 * @throws IOException If file is not found then we will get this exception.
	 */

	public Workbook getDataBook(String dataSheetPath) {

		Workbook workbook = null;
		File xlFile = new File(dataSheetPath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(xlFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] arrPath = dataSheetPath.split("\\.");
		String fileExt = arrPath[1];
		if ("xlsx".equalsIgnoreCase(fileExt)) {
			try {
				workbook = new XSSFWorkbook(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				workbook = new HSSFWorkbook(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return workbook;
	}

	/**
	 * This method is to get the individual work sheet from the workbook.
	 * 
	 * @param workBook  Need to provide the workbook object which we have generated
	 *                  from the getDataBook function.
	 * @param sheetName Name of the sheet which you want to read from the workbook.
	 * @return it will return the sheet object with all rows and columns.
	 */
	public Sheet getDataSheet(Workbook workBook, String sheetName) {
		Sheet sheet = null;
		sheet = workBook.getSheet(sheetName);
		return sheet;
	}

	/**
	 * This method is used to get the column number by providing the Column Name,
	 * because POI utility always looks for number, so we need the column number to
	 * get the TestData.getObject().
	 * 
	 * @param sheet      Sheet object which has the TestData.getObject().
	 * @param columnName Column name for which we need the column number.
	 * @return It will return integer value.
	 */
	public int getColumnNumberByColumnName(Sheet sheet, String columnName) {
		Row firstRowColumns = sheet.getRow(0);
		int columnNumber = -1;
		int columnCount = firstRowColumns.getLastCellNum();
		for (int i = 0; i <= columnCount - 1; i++) {
			if (firstRowColumns.getCell(i).getStringCellValue().toLowerCase().contains(columnName.toLowerCase())) {
				columnNumber = i;
				break;
			}
		}
		return columnNumber;
	}

	/**
	 * This method is to get the Row number for the provided Row ID.
	 * 
	 * @param sheet      Sheet object from which we want to have the row number.
	 * @param columnName Name of the column to determine the Cell value.
	 * @param rowID      Actual Row value for which we need the Row number.
	 * @return It will return the integer value.
	 */
	public int getRowNumberByRowID(Sheet sheet, String rowID, String columnName) {
		int rowCount = sheet.getLastRowNum();
		int columnNumber = getColumnNumberByColumnName(sheet, columnName);
		int rowNumber = -1;
		for (int i = 1; i <= rowCount; i++) {
			Cell cell = sheet.getRow(i).getCell(columnNumber);
			if (cell != null && cell.getStringCellValue().equalsIgnoreCase(rowID)) {
				rowNumber = i;
				break;
			}
		}
		return rowNumber;
	}

	/*
	 * below method is actually returning multiple row numbers of testcase with same
	 * testcase id in a List this method is being called in getTestDataRowsList
	 * method of TestData.java class
	 */
	public List<Integer> getRowNumbersListByRowID(Sheet sheet, String columnName, String rowID) {
		List<Integer> listData = new ArrayList<>();
		int rowCount = sheet.getLastRowNum();
		int columnNumber = getColumnNumberByColumnName(sheet, columnName);
		for (int i = 1; i <= rowCount; i++) {
			Cell cell = sheet.getRow(i).getCell(columnNumber);
			if (cell != null && cell.getStringCellValue().equalsIgnoreCase(rowID)) {
				listData.add(i);

			}
		}
		return listData;
	}

	public List<String> getColumnList(Sheet sheet) {
		Row row = sheet.getRow(0);
		ArrayList<String> columnList = new ArrayList<>();
		int columnCount = row.getLastCellNum();
		for (int i = 0; i <= columnCount - 1; i++) {
			String columnName = getCellData(row, i);
			columnList.add(columnName);
		}

		return columnList;

	}

	public String getCellData(Row row, int cellNum) {
		String cellValue = null;
		Cell cell = row.getCell(cellNum, MissingCellPolicy.CREATE_NULL_AS_BLANK);
		if (cell.getCellType().equals(CellType.STRING)) {
			cellValue = cell.getStringCellValue();
		} else if (cell.getCellType().equals(CellType.NUMERIC)) {
			Double dblVal = cell.getNumericCellValue();
			cellValue = dblVal.toString();
		}
		return cellValue;
	}

	public void getKeywordScript(WebUtil wb) {

		Workbook wbook = getDataBook("src\\main\\resources\\KeyWordDriven.xlsx");
		Sheet sheetObj = wbook.getSheet("Sheet1");
		List<String> columnList = getColumnList(sheetObj);

		int rowNumber = getRowNumberByRowID(sheetObj, "TC0001FbLogin", "TestCaseID");
		while (!getCellData(sheetObj.getRow(rowNumber), getColumnNumberByColumnName(sheetObj, "Keyword")).trim()
				.equalsIgnoreCase("")) {
			String keywordName = getCellData(sheetObj.getRow(rowNumber),
					getColumnNumberByColumnName(sheetObj, "Keyword"));
			String objectName = getCellData(sheetObj.getRow(rowNumber),
					getColumnNumberByColumnName(sheetObj, "ObjectName"));
			String locatorType = getCellData(sheetObj.getRow(rowNumber),
					getColumnNumberByColumnName(sheetObj, "LocatorType"));
			String locatorValue = getCellData(sheetObj.getRow(rowNumber),
					getColumnNumberByColumnName(sheetObj, "LocatorValue"));
			String dataValue = getCellData(sheetObj.getRow(rowNumber),
					getColumnNumberByColumnName(sheetObj, "TestData"));
			String executeValue = getCellData(sheetObj.getRow(rowNumber),
					getColumnNumberByColumnName(sheetObj, "Execute(Y/N)"));

			if (keywordName.equalsIgnoreCase("click")) {
				wb.click(wb.getElement(locatorType, locatorValue), objectName);
			} else if (keywordName.equalsIgnoreCase("input")) {

				wb.sendKeys(wb.getElement(locatorType, locatorValue), dataValue, objectName);

			} else if (keywordName.equalsIgnoreCase("launchBrowser")) {
				wb.launchBrowser(dataValue);
			} else if (keywordName.equalsIgnoreCase("OpenURL")) {
				wb.navigateUrl(dataValue);
			}

			rowNumber++;
		}

	}

	@Test
	public void test() {

		WebUtil wb = new WebUtil();
		ExcelUtil util = new ExcelUtil();
		util.getKeywordScript(wb);

	}

}