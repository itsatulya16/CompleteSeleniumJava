package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
    public static WebDriver driver;

    @BeforeMethod
    public static void setUp() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        driver = new ChromeDriver();
        driver.get("https://www.ebay.com/");
        driver.manage().window().maximize();
    }

    @AfterMethod
    void cleanUp() {
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
