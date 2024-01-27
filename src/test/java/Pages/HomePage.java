package Pages;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;

import static tests.seleniumTest.driver;
import static tests.seleniumTest.test;

public class HomePage {
    public static String username = "//*[@id='email']";
    public static String passWord = "//*[@id='pass']";

    public static void setUsername(){
        driver.findElement(By.xpath(username)).sendKeys("atul");
        test.log(Status.PASS, "this is passed");
        test.pass(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
    }

    public static void setPassWord(){
        driver.findElement(By.xpath(passWord)).sendKeys("Test");
        test.pass(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
    }
}
