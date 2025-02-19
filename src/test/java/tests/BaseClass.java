package tests;

import DB_Testing.TestLogger;
import Utils.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.FileNotFoundException;

public class BaseClass {
    public static WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/"); // Default URL (replace with your URL)
    }

    @BeforeTest
    public void clearDBLogs() {
        TestLogger.clearTestResults();
    }

    // @IMP : following code is for attaching screenshot to allure report when test case failed
    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException, FileNotFoundException {
        if (ITestResult.FAILURE == result.getStatus()) {
            System.out.println("Test failed: " + result.getName());
            Allure.addAttachment(result.getName(), Utility.takeScreenShot());
        }
        if (driver != null) {
            driver.quit();
        }
    }

//    @Attachment(value = "Screenshot on Failure", type = "image/png")
//    public byte[] saveScreenShot() {
//        if (driver == null) {
//            System.err.println("Driver is not initialized. Screenshot not taken.");
//            return new byte[0];
//        }
//        try {
//            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//        } catch (Exception e) {
//            System.err.println("Error capturing screenshot: " + e.getMessage());
//            return new byte[0];
//        }
//    }
}
