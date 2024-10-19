package tests;

import DB_Testing.TestLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseClass {
    public static WebDriver driver;

    public static void setUp(String url) {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }
    @BeforeTest
    public void clearDBLogs(){
        TestLogger.clearTestResults();
    }

    @AfterMethod
    public void logResult(ITestResult result) {
        String testName = result.getName();
        String status = result.isSuccess() ? "PASS" : "FAIL";
        double executionTime = (result.getEndMillis() - result.getStartMillis()) / 1000.0;

        TestLogger.logTestResult(testName, status, executionTime);
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
