package com.magnitia.Batch29_Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.magnitia.Batch29_Reports.Reports;

import jdk.internal.org.jline.utils.Log;

public class ExcelUtils {
	public static FileInputStream fis;
	public static XSSFWorkbook rwb;
	public static XSSFCell cell;
	public static XSSFSheet rsh;
	public static FileOutputStream fout;
	
	/**
	 * 
	 * @param FilePath
	 * @return boolean
	 * @author GcReddy
	 * @description:  it will demand for Filepath.xlsx and invoke the xlsx file and return boolean true
	 * if it is invoked flag returns true else flag become false
	 */
	public static boolean invokeExcel(String FilePath) {
		boolean falg = false;
		try {
			fis = new FileInputStream(FilePath);
			falg = true;
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			 rwb = new XSSFWorkbook(fis);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return falg;
	}
	//--------------------------------------------------------------------------------------------
	
	/**
	 * @author "GcReddy"
	 * @param  row number, column Number
	 *@description  demands rowNum, colNum, and read the string data or non string String data from
	 * provided excel sheet and return the String value;
	 */
	public static String readSpecificCellData(int rowNum, int colNum) {
		String data ="";
		try {
		  cell = rsh.getRow(rowNum).getCell(colNum);
		  data = cell.getStringCellValue();
		}catch(Exception e) {
			e.printStackTrace();
			cell = rsh.getRow(rowNum).getCell(colNum);
			DataFormatter format = new DataFormatter();
			data = format.formatCellValue(cell);			
		}
		return data;		
	}
	
	//---------------------------------------------------------------------------------------
	public static Object[][] readData(String sheetName){
		Object[][] excelData = null;
		 rsh = rwb.getSheet(sheetName);
		 int nur = rsh.getPhysicalNumberOfRows();
		 int nuc = rsh.getRow(0).getPhysicalNumberOfCells();
		 excelData = new Object [nur-1][nuc]; 
		 int i=1;
		 int ci=0;
		 
		 for( i=1; i<nur; i++, ci++) {
			 int j=0;
			 int cj =0;
			 for (j=0; j<nuc; j++, cj++) {
				 excelData[ci][cj] = readSpecificCellData(i, j);
		System.out.println("Data store at index-- " + "Data[" + ci + "]" + "[" + cj + "]==>>" + "[" + i
							+ "]" + "[" + j + "]" + "--->" + excelData[ci][cj]);
			 }
		 }	
		
		return excelData;		
	}
	//   ----------------------------------------------------------------------------------------
	public static boolean setCellValue(String result, int rowNum, int colNum) throws IOException {
		boolean flag = false;
		try {
			cell = rsh.getRow(rowNum).createCell(colNum);
			cell.setCellValue(result);
		}catch(Exception e) {
			Reports.fail("Setting cell value", "Setting cell value", "anypath.png");
			Log.info("setting cell value failed");
		}
		try {
			fout = new FileOutputStream("path of the otuput file.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	rwb.write(fout);  // saves the workbook
		
		return false;	
		
	}
	
	
	
	
	

}
