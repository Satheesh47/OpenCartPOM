package com.opencart.utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
    public static String generateTimeStamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return formatter.format(new Date());
    }

    public static String generateEmailWithTimeStamp() {
        return "Test_"+generateTimeStamp()+"@gmail.com";
    }

    public static Object[][] getTestDataFromExcel(String sheetName) throws IOException {

        File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Test_Data.xlsx");
        XSSFWorkbook workbook = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];

        for (int i = 0; i < rows; i++) {
            XSSFRow row = sheet.getRow(i+1);
            for (int j = 0; j < cols; j++) {
                XSSFCell cell = row.getCell(j);
                CellType cellType = cell.getCellType();
                switch (cellType) {
                    case STRING:
                        data[i][j] = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        data[i][j] = Integer.toString((int) cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        data[i][j] = cell.getBooleanCellValue();
                        break;
                }
            }
        }
        return data;
    }

    public static String captureScreenShot(WebDriver driver,String screenShotName) {

        File srcScrShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String destScrShotPath = System.getProperty("user.dir")+"\\test-output\\Screenshots_"+screenShotName+"_"+generateTimeStamp()+".png";
        try {
            FileHandler.copy(srcScrShot,new File(destScrShotPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return destScrShotPath;
    }
}
