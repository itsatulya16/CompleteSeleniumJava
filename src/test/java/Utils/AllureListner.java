package Utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseClass;

import java.io.FileNotFoundException;

public class AllureListner implements ITestListener {

//    @Override
//    public void onTestFailure(ITestResult result) {
//        try {
//            Allure.addAttachment(result.getName(), Utility.takeScreenShot());
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }


//    @Attachment(value = "Page Screenshot", type = "image/png")
//    public byte[] saveScreenshotPNG(WebDriver driver) {
//        System.out.println("taking screen shot");
//        try {
//            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//        } catch (Exception e) {
//            System.err.println("Error capturing screenshot: " + e.getMessage());
//            return new byte[0];
//        }
//    }
}
