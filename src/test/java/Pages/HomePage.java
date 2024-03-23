package Pages;

import Utils.Utility;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import static tests.seleniumTest.test;

public class HomePage {
    public static String username = "//*[@id='email']";
    public static String passWord = "//*[@id='pass']";

    public static void setUsername() {
        Utility.getWaitExplicit(username).sendKeys("atul");
        test.log(Status.PASS, "this is passed");
        test.pass(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
    }

    public static void setPassWord() {
        Utility.getWaitExplicit(passWord).sendKeys("test");
        test.pass(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
    }
}
