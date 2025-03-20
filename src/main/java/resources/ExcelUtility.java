package resources;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelUtility {

    public ArrayList<String> getData(String testcasename,String Sheetname) throws IOException {
        //		Identify test cases column, purchase test case row, scan row, get data
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir") + "/src/main/java/resources/InputData/TestData.xlsx");
//		Pass filepath to workbook, so that work book know which file to access
        XSSFWorkbook wb=new XSSFWorkbook(fis);
        int sheetCount=wb.getNumberOfSheets();
        ArrayList<String> a=new ArrayList<>();
        for(int i=0;i<sheetCount;i++)
        {
            if(wb.getSheetName(i).equalsIgnoreCase(Sheetname)){
                XSSFSheet sheet=wb.getSheetAt(i);
//				sheet is collection of rows
                Iterator<Row> row=sheet.iterator();
//				get first row, row is collection of cells
                Row firstrow=row.next();
//				Scan row, check which cell has test case value
//				row is collection of cells, first row generally contains field names
                Iterator<Cell> cell=firstrow.cellIterator();
                int k=0,column=0;
                while (cell.hasNext()){
                    if(cell.next().getStringCellValue().equalsIgnoreCase("Testcases")){
                        column=k;
                    }
                    k++;
                }
                while (row.hasNext()){
//					checks in subsequent rows
                    Row r=row.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcasename)){
                        Iterator<Cell> cell2=r.cellIterator();
                        while (cell2.hasNext()){
                            Cell c=cell2.next();
                            if(c.getCellType()==CellType.NUMERIC){
                                a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }
                            else if(c.getCellType()==CellType.STRING){
                                a.add(c.getStringCellValue());
                            }


                        }
                    }
                }
            }
        }
        return a;



    }

    public static void main(String[] args) throws IOException {
        ExcelUtility e=new ExcelUtility();
        ArrayList<String> a=e.getData("Add Profile","data");
        System.out.println(a.get(0));
        System.out.println(a.get(1));
        System.out.println(a.get(2));
    }
}
