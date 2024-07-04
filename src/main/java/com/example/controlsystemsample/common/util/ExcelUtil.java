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

		createHeader(sheet, headers);
		createData(sheet, headers, data);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		workbook.close();

		return outputStream.toByteArray();
	}

	private static void createHeader(XSSFSheet sheet, List<Map<String, Object>> headers) {
		Row headerRow = sheet.createRow(0); // Create header row at the first row (index 0)
		int colNum = 0;
		for (Map<String, Object> header : headers) {
			Cell cell = headerRow.createCell(colNum++);
			cell.setCellValue(header.get("label").toString());
		}
	}

	private static void createData(XSSFSheet sheet, List<Map<String, Object>> headers, List<Map<String, Object>> data) {
		int rowNum = 1; // Start data rows from the second row (index 1)
		for (Map<String, Object> rowData : data) {
			Row row = sheet.createRow(rowNum++);
			int colNum = 0;
			for (Map<String, Object> header : headers) {
				String fieldName = header.get("field").toString();
				Cell cell = row.createCell(colNum++);
				Object value = rowData.get(fieldName);
				cell.setCellValue(value != null ? value.toString() : "");
			}
		}
	}

}
