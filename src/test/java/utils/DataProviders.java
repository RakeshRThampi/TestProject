package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataProviders {
	public String filePath = "F:\\IDEs\\Eclipse\\eclipse-workspace\\ExcelPlayDP\\src\\test\\resources\\testdata\\users.xlsx";
	public String sheetName = "users";
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;

	public  void setExcelFileAndSheet(String filePath, String sheetName) {
		this.filePath = filePath;
		this.sheetName = sheetName;
	}
	
    @DataProvider (name = "sauceusers")
     public Object[][] dpMethod() throws IOException{
    	return getExcelData(filePath, sheetName);
     }
    @DataProvider (name = "rrt")
    public Object[] dpMethod2() throws IOException{
    	Object myArray[][]={{"rakesh"}, {"R"}, {"Thampi"}};
    	return myArray;
    }

    public String[][] getExcelData(String filePath, String sheetName) throws IOException {
    	String[][] data = null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            // Get any row (expecting all rows have same no. of cells and no gaps in between)
            XSSFRow row = sheet.getRow(0);
            // Actual numbers, not zero based
            int noOfActualRows = sheet.getPhysicalNumberOfRows();
            int noOfActualCols = row.getPhysicalNumberOfCells();
            System.out.println("noOfRows" + noOfActualRows + ", noOfCols" + noOfActualCols);
            Cell cell;
            data = new String[noOfActualRows-1][noOfActualCols]; // No need of row title, so '-1'

            for (int i = 1; i < noOfActualRows; i++) {
                for (int j = 0; j < noOfActualCols; j++) {
                    row = sheet.getRow(i); // Get first row avoiding title, first iteration will be 1 in loop
                    cell = row.getCell(j); // For first row and so on, get the cell value by i, j combo
                    
                    if(cell.getCellType()==CellType.STRING) 
                        data[i - 1][j] = cell.getStringCellValue(); 
                    else if(cell.getCellType()==CellType.NUMERIC) 
                        data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
                    else
                    	data[i - 1][j] = cell.getStringCellValue();
                }
            }
            workbook.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("The exception is: " + e.getMessage());
        }
        return data;
    }
}
