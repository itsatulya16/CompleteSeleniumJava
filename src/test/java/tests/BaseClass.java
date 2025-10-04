package tests;

import DB_Testing.TestLogger;
import Utils.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileNotFoundException;

import java.net.URL;
import java.time.Duration;

public class BaseClass {

    public static WebDriver driver;

    private static final Logger log = LogManager.getLogger(BaseClass.class);

    @BeforeMethod
    public void setUp() {
        String browser = Utility.getValue("browser");
        switch (browser) {
            case "chrome":
                try {
                    WebDriverManager.chromedriver().clearDriverCache().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-notifications");
                    options.addArguments("--disable-popup-blocking");
                    if (Boolean.parseBoolean(Utility.getValue("headless"))) {
                        options.addArguments("--headless");
                    }

                    driver = new ChromeDriver(options);

                    log.info("Chrome driver setup successfully");
                } catch (Exception e) {
                    log.error("chrome is not launched {}", e.getMessage());
                }

                break;

            case "docker":
                try {
                    //to run in docker container --------------------------------------
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setBrowserName("chrome");
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

                } catch (Exception e) {

                    log.warn("docker is not ready{}", e.getMessage());

                }
                break;
            case "fireFox":
                try {
                    System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");

                    FirefoxOptions options = new FirefoxOptions();
                    if (Boolean.parseBoolean(Utility.getValue("headless"))) {
                        options.addArguments("--headless");
                    }
                    options.addArguments("--disable-notifications");
                    options.addArguments("--disable-popup-blocking");

                    driver = new FirefoxDriver(options);
                } catch (Exception f) {

                    log.warn("Error in setting up Firefox driver: {}", f.getMessage());
                }
            default:
                log.error("no browser set up found");
                break;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.facebook.com");
    }



//    @BeforeTest
//    public void clearDBLogs() {
//        TestLogger.clearTestResults();
//    }

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
        // follwing code will log the test result logs in DB---------------------------------

       // TestLogger.logTestResult(result.getName(), status, (result.getEndMillis() - result.getStartMillis()) / 1000.0);

        if (driver != null) {
            driver.quit();
        }
    }



    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] saveScreenShot() {
        if (driver == null) {
            System.err.println("Driver is not initialized. Screenshot not taken.");
            return new byte[0];
        }
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            System.err.println("Error capturing screenshot: " + e.getMessage());
            return new byte[0];
        }
    }
}
