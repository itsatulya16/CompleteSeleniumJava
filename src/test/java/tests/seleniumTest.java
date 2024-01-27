package tests;

import Pages.HomePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class seleniumTest {
    public static WebDriver driver;
    static ExtentReports report;
    public static ExtentTest test;
    static ExtentReports extent = new ExtentReports();
    @BeforeMethod
    public static void setUp(){
        String path = System.getProperty("user.dir") + "\\driver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
        extent.attachReporter(spark);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }


    // TODO fix that method
//    public static String capture(WebDriver driver)throws IOException {
//        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        File dest = new File("src/../ExceImages/" + System.currentTimeMillis() + ".png");
//        String errlpath = dest.getAbsolutePath();
//        FileUtils.copyFile(scrFile, dest);
//                return errlpath;
//    }

    @Test
    void test1(){
        test = extent.createTest("validate first test field", "so beautifully validated");
        HomePage.setUsername();
        HomePage.setPassWord();

    }

    @Test
    void test2(){
        test = extent.createTest("validate second test field", "so beautifully validated");
        HomePage.setUsername();
        HomePage.setPassWord();

    }

    @Test
    void test3(){
        test = extent.createTest("validate third test field", "so beautifully validated");
        HomePage.setUsername();
        HomePage.setPassWord();

    }

    @AfterMethod
    void cleanUp(){
        extent.flush();
        driver.quit();
    }
}
