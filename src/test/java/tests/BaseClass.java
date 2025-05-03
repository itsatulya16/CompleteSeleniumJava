package tests;

import DB_Testing.TestLogger;
import Utils.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseClass {
    public static WebDriver driver;

    @BeforeMethod
    public void setUp() {
        try {
            System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");

            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");

            driver = new FirefoxDriver(options);
        } catch (Exception e) {
            System.out.println("Error in setting up Firefox driver: " + e.getMessage());
            System.out.println("Switching to Chrome driver");
        }
        try {
            WebDriverManager.chromedriver().clearDriverCache().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            driver = new ChromeDriver(options);
            System.out.println("Chrome driver setup successfully");
        } catch (Exception e) {
            System.out.println("Error in setting up Chrome driver: " + e.getMessage());
        }

        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");

        // to run in docker container --------------------------------------
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setBrowserName("chrome");
//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
//        driver.manage().window().maximize();
//        driver.get("https://www.facebook.com/");
    }

    @BeforeTest
    public void clearDBLogs() {
        TestLogger.clearTestResults();
    }

    // @IMP : following code is for attaching screenshot to allure report when test case failed
    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException, FileNotFoundException {
        String status;
        if (ITestResult.FAILURE == result.getStatus()) {
            status = "FAILURE";
            System.out.println("Test failed: " + result.getName());
            Allure.addAttachment(result.getName(), Utility.takeScreenShot());
        } else if (ITestResult.SUCCESS == result.getStatus()) {
            status = "SUCCESS";
            System.out.println("Test passed: " + result.getName());
        } else {
            status = "SKIPPED";
            System.out.println("Test skipped: " + result.getName());

        }
        // follwing code will log the test result logs in DB
        TestLogger.logTestResult(result.getName(), status, (result.getEndMillis() - result.getStartMillis()) / 1000.0);
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
