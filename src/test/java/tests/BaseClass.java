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


    // it's used for parallel testing > each test have separate thread and test is isolated
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver(){
        return driver.get();
    }

    private static final Logger log = LogManager.getLogger(BaseClass.class);


   @BeforeMethod
   // this method runs test in local as well as on remote server using selenium grid
    public void setUp() {
        String browser = Utility.getValue("browser");
        switch (browser) {
            case "chrome":
                //to run tests in local for debug
                try {
                    WebDriverManager.chromedriver().clearDriverCache().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-notifications");
                    options.addArguments("--disable-popup-blocking");
                    if (Boolean.parseBoolean(Utility.getValue("headless"))) {
                        options.addArguments("--headless");
                    }

                     driver.set(new ChromeDriver(options));

                    log.info("Chrome driver setup successfully");
                } catch (Exception e) {
                    log.error("chrome is not launched {}", e.getMessage());
                }

                break;

            case "docker":
                try {
                    //to run in docker container in remote machine <my gaming pc> -
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setBrowserName("chrome");
                    driver.set(new RemoteWebDriver(new URL("http://192.168.0.108:4444/wd/hub"), capabilities));
                    //this is my remote gaming pc--

                } catch (Exception e) {

                    log.warn("docker is not ready{}", e.getMessage());

                }
                break;
            default:
                log.error("no browser set up found");
                break;
        }
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        getDriver().get("https://www.facebook.com");
    }

    // cross browser + parallel test

    @Parameters("browser")
//    @BeforeMethod
    public void setUpCrossBrowser(String browser){
       if (browser.equals("chrome")){
           WebDriverManager.chromedriver().clearDriverCache().setup();
           driver.set(new ChromeDriver());
       } else if (browser.equals("firefox")) {
           WebDriverManager.firefoxdriver().clearDriverCache().setup();
           driver.set(new FirefoxDriver());
       }
       getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
       getDriver().get("https://www.facebook.com");
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
            Allure.addAttachment(result.getName(), new Utility().takeScreenShot());
        } else if (ITestResult.SUCCESS == result.getStatus()) {
            status = "SUCCESS";
            System.out.println("Test passed: " + result.getName());
        } else {
            status = "SKIPPED";
            System.out.println("Test skipped: " + result.getName());

        }
        // follwing code will log the test result logs in DB---------------------------------

       // TestLogger.logTestResult(result.getName(), status, (result.getEndMillis() - result.getStartMillis()) / 1000.0);

            getDriver().quit();
            driver.remove();

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
