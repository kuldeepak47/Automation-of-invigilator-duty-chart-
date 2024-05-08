package Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

public class Reader {

	static XSSFWorkbook workBook1; // permanent faculties data
	static XSSFSheet sheet1;
	static XSSFWorkbook workBook2; // contract faculties data
	static XSSFSheet sheet2;
	static XSSFWorkbook workBook3; // contract faculties data
	static XSSFSheet sheet3;

	static XSSFWorkbook headerWorkBook;
	static XSSFSheet headerWorkSheet;

	public ArrayList<Data> professors = new ArrayList<Data>();
	public ArrayList<Data> contract = new ArrayList<Data>();
	public ArrayList<Header> header = new ArrayList<Header>();
	public ArrayList<Data> finalList = new ArrayList<Data>();
	public String branch_keyword[] = { "Civil", "Electrical", "Industrial", "Central", "Computer Engineering",
			"Telecommunication", "Mechanical", "Instrumentation", "Information", "Application", "Biomedical",
			"Pharmacy", "Management", "MBA", "Physics", "Chemistry", "Mathematics", "Humanities" };

	// Parameterized constructor
	public Reader(File FilePath1, String SheetName1, File FilePath2, String SheetName2) {
		try {

			workBook1 = new XSSFWorkbook(FilePath1);

			sheet1 = workBook1.getSheet(SheetName1);

			workBook2 = new XSSFWorkbook(FilePath2);

			sheet2 = workBook2.getSheet(SheetName2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	void readHeader(File filePathForHeader, String sheetName) {
		try {
			headerWorkBook = new XSSFWorkbook(filePathForHeader);
			headerWorkSheet = headerWorkBook.getSheet(sheetName);
			for (int i = 1; i < headerWorkSheet.getPhysicalNumberOfRows(); i++) {
				header.add(new Header(headerWorkSheet.getRow(i).getCell(0).getDateCellValue(),
						headerWorkSheet.getRow(i).getCell(1).getNumericCellValue()));
			}
		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
		}

	}

	void store() {
		try {
			for (int i = 4, j = 0; i < sheet1.getPhysicalNumberOfRows(); i++) {
				if (sheet1.getRow(i).getCell(0).getCellType() == CellType.STRING) {
					continue;
				} else {
					professors.add(new Data(sheet1.getRow(i).getCell(1).toString()));
					professors.get(j).setDesignation(sheet1.getRow(i).getCell(2).toString());
					professors.get(j).setDepartment(sheet1.getRow(i).getCell(3).toString());
					professors.get(j).getTotalDuty();
					j++;
				}
			}
			for (int i = 3, j = 0; i < sheet2.getPhysicalNumberOfRows() - 1; i++, j++) {
				contract.add(new Data(sheet2.getRow(i).getCell(1).toString()));
				contract.get(j).setDesignation("Contract faculty");
				contract.get(j).setDepartment(sheet2.getRow(i).getCell(2).toString());
			}
			for (int i = 0; i < branch_keyword.length; i++) {
				for (int j = 0; j < professors.size(); j++) {
					if (professors.get(j).getDepartment().contains(branch_keyword[i])) {
						finalList.add(professors.get(j));
					}
				}
				for (int k = 0; k < contract.size(); k++) {
					if (contract.get(k).getDepartment().contains(branch_keyword[i])) {
						finalList.add(contract.get(k));
					}
				}
			}
		} catch (Exception e) {
			e.getCause();
			System.out.println(e.getMessage());
		}
	}

	void availabilityStore(File filePathAvailability, String sheetName1) {
		try {
			workBook3 = new XSSFWorkbook(filePathAvailability);

			sheet3 = workBook3.getSheet(sheetName1);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
			for (int i = 1; i < sheet3.getPhysicalNumberOfRows(); i++) {
				for (int j = 0; j < finalList.size(); j++) {
					if (finalList.get(j).getName().compareToIgnoreCase(sheet3.getRow(i).getCell(2).toString()) == 0) {
						String s = sheet3.getRow(i).getCell(4).toString();
						StringTokenizer st = new StringTokenizer(s, ", ");
						while (st.hasMoreTokens()) {
							Date date = formatter.parse(st.nextToken());
							finalList.get(j).availability.add(date);
						}
					}
				}
			}
		} catch (Exception e) {
			e.getCause();
			System.out.println(e.getMessage());
		}
	}

	void generateFile(FileOutputStream outputStream) throws SQLException, IOException {

		XSSFWorkbook resultWorkbook = new XSSFWorkbook();
		XSSFSheet resultSheet = resultWorkbook.createSheet("Sheet 1");
		CreationHelper createHelper = resultWorkbook.getCreationHelper();
		try {
			Row row1Row = resultSheet.createRow(0);
			row1Row.setHeight((short) 1000);
			Cell cell2 = row1Row.createCell(0);
			cell2.setCellValue(
					"Acropolis Institute of Technology & Science Indore\nUG/PG Examination May 2024\n Invigilation Duty Chart\n\n(Exam Time is 11:00 AM TO 02:00 PM)\nReporting Time for Invigilators is 10:30 AM Sharp in Block 3\n");

			Font newFont = cell2.getSheet().getWorkbook().createFont();
			newFont.setBold(true);
			// newFont.setColor(10);
			newFont.setFontHeightInPoints((short) 12);
			// newFont.setItalic(true);

			CellStyle cellStyle1 = resultWorkbook.createCellStyle();
			cellStyle1.setAlignment(HorizontalAlignment.CENTER);
			cellStyle1.setVerticalAlignment(VerticalAlignment.TOP);
			cellStyle1.setWrapText(true);
			cellStyle1.setFont(newFont);
			cell2.setCellStyle(cellStyle1);

			resultSheet.addMergedRegion(new CellRangeAddress(0, 3, 0, header.size() + 3));
			for (int counter = 0; counter < finalList.size(); counter++) {
				Row row = resultSheet.createRow(counter + 9);
				Cell cell1 = row.createCell(0);
				cell1.setCellValue(counter + 1);
				Cell cell = row.createCell(1);
				Cell cell9 = row.createCell(2);
				Cell cell10 = row.createCell(header.size() + 4);
				cell.setCellValue(finalList.get(counter).getName());
				cell9.setCellValue(finalList.get(counter).getDepartment());

				if (finalList.get(counter).getDesignation().compareToIgnoreCase("professor") == 0)
					cell10.setCellValue("P");
				else if (finalList.get(counter).getDesignation().compareToIgnoreCase("associate professor") == 0)
					cell10.setCellValue("AP");
				else if (finalList.get(counter).getDesignation().compareToIgnoreCase("assistant professor") == 0
						|| finalList.get(counter).getDesignation().compareToIgnoreCase("asstt.w/s supdt.") == 0)
					cell10.setCellValue("AR");
				else if (finalList.get(counter).getDesignation().compareToIgnoreCase("director") == 0)
					cell10.setCellValue("DIRECTOR");
				else if (finalList.get(counter).getDesignation().compareToIgnoreCase("head") == 0) {
					cell10.setCellValue("HOD");
				} else if (finalList.get(counter).getDesignation().compareToIgnoreCase("dean") == 0) {
					cell10.setCellValue("DEAN");
				}

			}

			Row row = resultSheet.createRow(4);
			Cell cell1c = row.createCell(0);
			cell1c.setCellValue("Duty Chart No.");
			Cell cell2c = row.createCell(1);
			cell2c.setCellValue("Exam Dates");
			Font newFont1 = cell1c.getSheet().getWorkbook().createFont();

			newFont1.setBold(true);
			// newFont.setColor(10);
			newFont1.setFontHeightInPoints((short) 10);
			CellStyle cellStyle2 = resultWorkbook.createCellStyle();
			cellStyle2.setAlignment(HorizontalAlignment.CENTER);
			// cellStyle1.setVerticalAlignment(VerticalAlignment.TOP);
			cellStyle2.setWrapText(true);
			cellStyle2.setFont(newFont1);
			cell1c.setCellStyle(cellStyle2);
			cell2c.setCellStyle(cellStyle2);

			// Dates
			for (int counter = 0; counter < header.size(); counter++) {
				CellStyle cellStyle = resultWorkbook.createCellStyle();
				cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yy"));
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				cellStyle.setWrapText(true);
				cellStyle.setFont(newFont1);
				Cell cell = row.createCell(counter + 3);
				if (counter + 3 == header.size()) {
					row.createCell(counter + 6).setCellValue("Total");
					row.getCell(counter + 6).setCellStyle(cellStyle2);

				}
				cell.setCellValue(header.get(counter).getDate());
				cell.setCellStyle(cellStyle);

			}

			// No of students
			// cellStyle2.setFillForegroundColor(IndexedColors.AQUA.getIndex());
			// cellStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			Row studentsRow = resultSheet.createRow(5);
			studentsRow.createCell(1).setCellValue("No. of students");
			studentsRow.getCell(1).setCellStyle(cellStyle2);
			// totalNoOfstudents
			double totalNoOfstudents = 0;
			for (int i = 0; i < header.size(); i++) {
				totalNoOfstudents = totalNoOfstudents + header.get(i).getNoOfStudents();
			}

			for (int i = 0; i < header.size(); i++) {
				studentsRow.createCell(i + 3).setCellValue(header.get(i).getNoOfStudents());
				if (i + 3 == header.size()) {
					studentsRow.createCell(i + 6).setCellValue(totalNoOfstudents);
					studentsRow.getCell(i + 6).setCellStyle(cellStyle2);

				}
				studentsRow.getCell(i + 3).setCellStyle(cellStyle2);
				// .createCell(2).setCellValue(header.get(i).getNoOfStudents());
			}

			XSSFCellStyle style3 = resultWorkbook.createCellStyle();
			style3.setWrapText(true);
			style3.setFont(newFont1);
			style3.setAlignment(HorizontalAlignment.CENTER);
			style3.setFillForegroundColor(
					new XSSFColor(new java.awt.Color(245, 248, 163), new DefaultIndexedColorMap()));
			style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			XSSFCellStyle style4 = resultWorkbook.createCellStyle();
			style4.setWrapText(true);
			style4.setFont(newFont1);
			style4.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 59, 40), new DefaultIndexedColorMap()));
			style4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			// No of Invigilators
			Row invigilatorRow = resultSheet.createRow(6);
			invigilatorRow.createCell(1).setCellValue("No. of Invigilators");
			invigilatorRow.getCell(1).setCellStyle(cellStyle2);

			// totalNoOfInvigilator
			int totalNoOfInvigilator = 0;
			for (int i = 0; i < header.size(); i++) {
				totalNoOfInvigilator = totalNoOfInvigilator + header.get(i).getNoOfInvigilators();
			}

			for (int i = 0; i < header.size(); i++) {
				invigilatorRow.createCell(i + 3).setCellValue(header.get(i).getNoOfInvigilators());
				if (i + 3 == header.size()) {
					invigilatorRow.createCell(i + 6).setCellValue(totalNoOfInvigilator);
					invigilatorRow.getCell(i + 6).setCellStyle(cellStyle2);

				}
				invigilatorRow.getCell(i + 3).setCellStyle(style3);

			}
			Row Row7 = resultSheet.createRow(8);
			Row7.createCell(1).setCellValue("Name(Prof./Dr./Mr./Ms.)");
			Row7.getCell(1).setCellStyle(cellStyle2);

			Row7.createCell(2).setCellValue("Department");
			Row7.getCell(2).setCellStyle(cellStyle2);
			// for(int i=0;i<finalList.size();i++){
			// for (int j = 0; j < header.size(); j++) {
			// if()
			// }
			// }

			// Duty
			Duty duty = new Duty();
			duty.FillDuty(finalList, header);
			resultSheet.createRow(finalList.size() + 9);
			Cell celllc = resultSheet.createRow(finalList.size() + 9).createCell(1);
			celllc.setCellValue("Total Duties");
			resultSheet.getRow(finalList.size() + 9).getCell(1).setCellStyle(cellStyle2);
			int c = 0;
			for (int i = 0; i < finalList.size(); i++) {
				for (int j = 0; j < header.size(); j++) {
					if (finalList.get(i).duty.contains(header.get(j).getDate())) {
						resultSheet.getRow(i + 9).createCell(j + 3).setCellValue("D");
						header.get(j).increaseTotalD();
						resultSheet.getRow(i + 9).createCell(header.size() + 3)
								.setCellValue(finalList.get(i).duty.size());
						resultSheet.getRow(finalList.size() + 9).createCell(j + 3)
								.setCellValue(header.get(j).getTotalD());
						resultSheet.getRow(finalList.size() + 9).getCell(j + 3).setCellStyle(cellStyle2);
						c++;
					}
				}
			}

			for (int i = 0; i < finalList.size(); i++) {
				for (int j = 0; j < header.size(); j++) {
					if (finalList.get(i).getDesignation().compareToIgnoreCase("director") == 0
							|| finalList.get(i).getDesignation().compareToIgnoreCase("Dean") == 0
							|| finalList.get(i).getDesignation().compareToIgnoreCase("Head") == 0) {
						resultSheet.getRow(i + 9).createCell(j + 3).setCellStyle(style4);
						// resultSheet.getRow(i+9).setRowStyle(style4);
					}
				}
			}
			System.out.println(c);
			resultSheet.getRow(finalList.size() + 9).createCell(header.size() + 3).setCellValue(c);
			resultSheet.getRow(finalList.size() + 9).getCell(header.size() + 3).setCellStyle(cellStyle2);
			resultSheet.autoSizeColumn(1);
			resultSheet.autoSizeColumn(2);
			resultSheet.autoSizeColumn(header.size() + 4);
			resultSheet.createFreezePane(2, 7);
			resultWorkbook.write(outputStream);
			resultWorkbook.close();

			// Designation
			for (int i = 0; i < finalList.size(); i++) {
				resultSheet.getRow(i + 9).createCell(header.size() + 5).setCellValue(finalList.get(i).getDesignation());
			}

		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
		}
	}
}