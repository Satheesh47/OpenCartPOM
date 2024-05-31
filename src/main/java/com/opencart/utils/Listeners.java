package com.opencart.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Listeners implements ITestListener {
    ExtentReports extentReport;
    ExtentTest extentTest;
    ExtentReporter extentReporter = new ExtentReporter();

    @Override
    public void onStart(ITestContext context) {
        extentReport = extentReporter.generateExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReport.createTest(result.getName());
        extentTest.log(Status.INFO,result.getName()+" execution started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS,result.getName()+" successfully passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        String destScrShotPath = Utilities.captureScreenShot(driver,result.getName());
        extentTest.addScreenCaptureFromPath(destScrShotPath);
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.FAIL,result.getName()+" got failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.SKIP,result.getName()+" got skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
        File extentReport = new File(extentReporter.getExtentReportFileName());
        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}