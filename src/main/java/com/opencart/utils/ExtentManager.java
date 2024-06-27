package com.opencart.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import static com.opencart.utils.Constants.*;
import static com.opencart.utils.Utilities.*;
import java.io.File;

public class ExtentManager {
    public static String extentReportFileName;
    private static ExtentReports extentReport;
    //private static ExtentTest extentTest;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static ExtentReports getExtentReportInstance() {
        if (extentReport == null) {
            extentReport = new ExtentReports();
            extentReportFileName = EXTENT_REPORT_PATH + generateTimeStamp() + ".html";
            File extentReportFile = new File(extentReportFileName);
            ExtentSparkReporter spark = new ExtentSparkReporter(extentReportFile);
            spark.config().setTheme(Theme.DARK);
            spark.config().setReportName("Open Cart POM Project Automation Results");
            spark.config().setDocumentTitle("Open Cart Automation Report");
            spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
            extentReport.attachReporter(spark);
            extentReport.setSystemInfo("Application URL", BASE_URL);
            extentReport.setSystemInfo("Browser", BROWSER);
            extentReport.setSystemInfo("Email", VALID_USERNAME);
            extentReport.setSystemInfo("Password", VALID_PASSWORD);
            extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
            extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
            extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
        return extentReport;
    }
    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }
    public static void setExtentTest(ExtentTest extentTest) {
        ExtentManager.extentTest.set(extentTest);
    }
}
