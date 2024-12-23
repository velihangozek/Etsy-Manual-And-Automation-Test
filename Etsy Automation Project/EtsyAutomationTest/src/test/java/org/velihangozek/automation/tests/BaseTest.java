package org.velihangozek.automation.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.velihangozek.automation.utils.ConfigReader;
import org.velihangozek.automation.utils.DriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import java.io.File;

public abstract class BaseTest {

    protected static ExtentReports extent;  // ExtentReports instance
    protected ExtentTest test;              // ExtentTest instance

    @BeforeAll
    public static void setupSuite() {
        // Initialize Extent Reports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Initialize the driver and navigate to baseURL
        DriverManager.getDriver().get(ConfigReader.getProperty("baseURL"));
    }

    @BeforeEach
    public void setupTest() {
        // This can include login or setup specific to each test if required
    }

    @AfterEach
    public void teardownTest() {
        if (test != null && test.getStatus().toString().equalsIgnoreCase("fail")) {
            // Capture Screenshot
            File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            try {
                String screenshotPath = "screenshots/" + getClass().getSimpleName() + ".png";
                FileHandler.copy(screenshot, new File(screenshotPath));
                test.addScreenCaptureFromPath(screenshotPath, "Test Failure Screenshot");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Clear cookies after each test
        DriverManager.getDriver().manage().deleteAllCookies();
    }

    @AfterAll
    public static void teardownSuite() {
        // Close the driver
        DriverManager.closeDriver();
        extent.flush();
    }
}