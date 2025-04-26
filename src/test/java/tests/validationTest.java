package tests;

import Pages.fb_page;
import Utils.AllureListner;
import Utils.Utility;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.*;

import java.io.FileNotFoundException;

//@Listeners(AllureListner.class)
public class validationTest extends BaseClass {

    @Step("entering username")
    @Test(description = "validate username entered in textBox")
    public void validateUserNameEntered() throws FileNotFoundException {
        fb_page fb = new fb_page();
//        Allure.addAttachment("entering username", Utility.takeScreenShot());
        fb.validateTitle("Facebook – log in or sign up");
        fb.enterUserName("bala");

    }

    @Step("entering password")
   // @Test(description = "validate username entered in textBox")
    public void validatePasswordEntered() throws FileNotFoundException {
        fb_page fb = new fb_page();
//        Allure.addAttachment("entering passWord", Utility.takeScreenShot());
        fb.enterPassword("Pass@1233");

    }
}
