package utilities;


import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * @author mbn217
 * @Date 04/30/2018
 * @Purpose This is the ExcelUtil utility class that will contain most of the reusable
 *          method to read and write data from excel
 * @Usage Methods in this class are static , so you can just use class name with
 *        method name concatenated by "." example: ExcelUtil.setExcelFileSheet
 */
public class ExcelUtil {
	private static Logger log = Logger.getLogger(ExcelUtil.class);
    //Main Directory of the project
    public static final String currentDir = System.getProperty("user.dir");
    //Location of Test data excel file
    public static String testDataExcelPath = null;
    //Excel WorkBook
    private static XSSFWorkbook excelWBook;
    //Excel Sheet
    private static XSSFSheet excelWSheet;
    //Excel cell
    private static XSSFCell cell;
    //Excel row
    private static XSSFRow row;
    //Row Number
    public static int rowNumber;
    //Column Number
    public static int columnNumber;
    //Setter and Getters of row and columns
    public static void setRowNumber(int pRowNumber) {
        rowNumber = pRowNumber;
    }
    public static int getRowNumber() {
        return rowNumber;
    }
    public static void setColumnNumber(int pColumnNumber) {
        columnNumber = pColumnNumber;
    }
    public static int getColumnNumber() {
        return columnNumber;
    }
    
	/**
	 * @author mbn217
	 * @Date 05/02/2018
	 * @Purpose This method will creates FileInputStream and set excel file 
	 * and excel sheet to excelWBook and excelWSheet variables
	 * @param testDataExcelFileName
	 *            --> The Test data excel file name
	 * @param sheetName
	 *            --> Excel sheet name
	 * @return N/A
	 */
    public static void setExcelFileSheet( String testDataExcelFileName, String sheetName) {
    	log.info("Setting Excel file sheet");
            testDataExcelPath = currentDir + "\\src\\test\\resources\\TestData\\";
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(testDataExcelPath + testDataExcelFileName);
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
        } catch (Exception e) {
            try {
                throw (e);
            } catch (IOException e1) {
            	log.error("Error setting excel file sheet "+ e.getMessage());
            }
        }
    }

	/**
	 * @author mbn217
	 * @Date 05/02/2018
	 * @Purpose This method reads the test data from the Excel cell.
	 * We are passing row number and column number as parameters.
	 * @param RowNum
	 *            --> The row number from where we want to get the data
	 * @param ColNum
	 *            --> The column number from where we want to get the data
	 * @return N/A
	 */
    public static String getCellData(int RowNum, int ColNum) {
    	log.info("Getting Data from Excel");
        try {
            cell = excelWSheet.getRow(RowNum).getCell(ColNum);
            DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
        } catch (Exception e) {
            throw (e);
        }
    }

	/**
	 * @author mbn217
	 * @Date 05/02/2018
	 * @Purpose This method takes row number as a parameter and returns the data of given row number
	 * @param RowNum
	 *            --> The row number from where we want to get the data
	 * @return N/A
	 */
    public static XSSFRow getRowData(int RowNum) {
    	log.info("Getting Data from Excel using row number");
        try {
            row = excelWSheet.getRow(RowNum);
            return row;
        } catch (Exception e) {
            throw (e);
        }
    }

	/**
	 * @author mbn217
	 * @Date 05/02/2018
	 * @Purpose This method gets excel file, row and column number and set a value to the that cell.
	 * @param testDataExcelFileName
	 *            --> The Test data excel file name
	 * @param value
	 *            --> The value we want to set to the cell
	 * @param RowNum
	 *            --> The row number from where we want to set the value
	 * @param ColNum
	 * 			  --> The column number from where we want to set the data
	 * @return N/A
	 * @throws Exception if error occur while getting the excel file
	 * @throws IOException if file is not found
	 */
    public static void setCellData(String testDataExcelFileName, String value, int RowNum, int ColNum) {
    	log.info("Setting cell to a value : "+ value);
    	try {
            row = excelWSheet.getRow(RowNum);
            cell = row.getCell(ColNum);
            if (cell == null) {
                cell = row.createCell(ColNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            // Constant variables Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + testDataExcelFileName);
            excelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            try {
                throw (e);
            } catch (IOException e1) {
            	log.error("Error occured while setting the value : "+value+" to an cell : "+ e1.getMessage());
            }
        }
    }
}