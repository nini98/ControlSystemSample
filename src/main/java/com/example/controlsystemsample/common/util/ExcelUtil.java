package com.example.controlsystemsample.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static byte[] createExcel(List<Map<String, Object>> headers, List<Map<String, Object>> data) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Sheet1");

		// Create header rows
		createHeader(sheet, headers, workbook);

		// Create data rows
		createData(sheet, data);

		// Write the workbook to a byte array
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		workbook.close();

		return outputStream.toByteArray();
	}

	private static void createHeader(XSSFSheet sheet, List<Map<String, Object>> headers, XSSFWorkbook workbook) {
		int rowNum = 0;
		for (Map<String, Object> headerRow : headers) {
			Row row = sheet.createRow(rowNum++);
			int colNum = 0;
			for (Map.Entry<String, Object> header : headerRow.entrySet()) {
				Cell cell = row.createCell(colNum++);
				cell.setCellValue(header.getValue().toString());
				// Apply style if needed
			}
		}
	}

	private static void createData(XSSFSheet sheet, List<Map<String, Object>> data) {
		int rowNum = sheet.getLastRowNum() + 1;
		for (Map<String, Object> rowData : data) {
			Row row = sheet.createRow(rowNum++);
			int colNum = 0;
			for (String key : rowData.keySet()) {
				Cell cell = row.createCell(colNum++);
				cell.setCellValue(rowData.get(key).toString());
			}
		}
	}
}
