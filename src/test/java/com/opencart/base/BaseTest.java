package com.opencart.base;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.opencart.utils.ExtentManager;
import com.opencart.utils.Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.hc.client5.http.utils.Base64;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import static com.opencart.utils.Constants.*;

public class BaseTest {
    protected WebDriver driver;
    @BeforeMethod
    public void initialSetup(ITestResult result){
//        ExtentTest test = ExtentManager.getExtentReportInstance().createTest(result.getMethod().getMethodName());
//        ExtentManager.setExtentTest(test);
//        ExtentManager.getExtentTest().log(Status.INFO,result.getName()+" execution started");
    }
    public WebDriver initializeBrowserAndOpen(String browserName) {

            if (browserName.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver= new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }

        driver.manage().window().maximize();
        driver.get(BASE_URL);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIME));

        return driver;
    }
    @AfterMethod
    public void quitDriver(ITestResult result) throws IOException {
        if (!result.isSuccess()) {
            String destScrShotPath = Utilities.captureScreenShot(driver,result.getName());
//            File f = new File(destScrShotPath);
//            FileInputStream fis = new FileInputStream(f);
//            byte[] bytes = new byte[(int)f.length()];
//            fis.read(bytes);
//            String base64Img = new String(Base64.encodeBase64(bytes), StandardCharsets.UTF_8);
            byte[] file = FileUtils.readFileToByteArray(new File(destScrShotPath));
            String base64Img = Base64.encodeBase64String(file);
//            ExtentManager.getExtentTest().addScreenCaptureFromPath(destScrShotPath)
//                    .fail(MediaEntityBuilder.createScreenCaptureFromPath(destScrShotPath).build());
            ExtentManager.getExtentTest().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Img).build());
            ExtentManager.getExtentTest().log(Status.INFO,result.getThrowable());
            ExtentManager.getExtentTest().log(Status.FAIL,result.getName()+" got failed");
        }
        driver.quit();
    }

    @AfterSuite
    public void flushExtentReport() {
        ExtentManager.getExtentReportInstance().flush();
    }
}
