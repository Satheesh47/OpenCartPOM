package com.opencart.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import static com.opencart.utils.Constants.*;
import static com.opencart.utils.Utilities.*;
import java.io.File;

public class ExtentReporter {

    private String extentReportFileName;

    public String getExtentReportFileName() {
        return extentReportFileName;
    }

    public void setExtentReportFileName(String extentReportFileName) {
        this.extentReportFileName = extentReportFileName;
    }

    public ExtentReports generateExtentReport() {
        ExtentReports extentReport = new ExtentReports();
        String fileName = EXTENT_REPORT_PATH+generateTimeStamp()+".html";
        setExtentReportFileName(fileName);
        File extentReportFile = new File(fileName);
        ExtentSparkReporter spark = new ExtentSparkReporter(extentReportFile);
        spark.config().setTheme(Theme.DARK);
        spark.config().setReportName("Open Cart POM Project Automation Results");
        spark.config().setDocumentTitle("Open Cart Automation Report");
        spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
        extentReport.attachReporter(spark);
        extentReport.setSystemInfo("Application URL",BASE_URL);
        extentReport.setSystemInfo("Browser",BROWSER);
        extentReport.setSystemInfo("Email",VALID_USERNAME);
        extentReport.setSystemInfo("Password",VALID_PASSWORD);
        extentReport.setSystemInfo("Operating System",System.getProperty("os.name"));
        extentReport.setSystemInfo("User Name",System.getProperty("user.name"));
        extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
        return extentReport;
    }
}
