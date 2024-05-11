package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseClass {
    public static WebDriver driver;
    static ExtentReports report;
    public static ExtentTest test;
    static ExtentReports extent = new ExtentReports();
    @BeforeMethod
    public static void setUp(){

//        String path = System.getProperty("user.dir") + "\\driver\\chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver", path);

//TODO
       //   following code is using webdriver manager
        WebDriverManager.chromedriver().clearDriverCache().setup();

        driver = new ChromeDriver();
        driver.get("https://www.ebay.com/");
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
        extent.attachReporter(spark);
        driver.manage().window().maximize();
    }

    @AfterMethod
    void cleanUp(){
        extent.flush();
        driver.quit();
    }

    // TODO fix that method
//    public static String capture(WebDriver driver)throws IOException {
//        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        File dest = new File("src/../ExceImages/" + System.currentTimeMillis() + ".png");
//        String errlpath = dest.getAbsolutePath();
//        FileUtils.copyFile(scrFile, dest);
//                return errlpath;
//    }
}
