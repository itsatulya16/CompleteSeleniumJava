package Pages;

import Utils.Utility;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.Assert;

import static tests.seleniumTest.test;

public class HomePage {
    public static String username = "//*[@id='email']";
    public static String passWord = "//*[@id='pass']";
    public static String ebaySearchBox = "//input[@id='gh-ac']";

    public static void setUsername() {
        Utility.waitForElementVisibility(username).sendKeys("atul");
        test.log(Status.PASS, "this is passed");
        test.pass(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
    }

    public static void setPassWord() {
        Utility.waitForElementVisibility(passWord).sendKeys("test");
        test.pass(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
    }

    public void validateEbayOpen(){
        boolean ebayOpen = Utility.waitForElementVisibility(ebaySearchBox).isDisplayed();
        Assert.assertTrue(ebayOpen, "Not landed on correct page");
    }
}
