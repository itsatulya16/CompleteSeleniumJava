package tests;

import Pages.fb_page;
import Utils.AllureListner;
import Utils.Utility;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.io.FileNotFoundException;

//@Listeners(AllureListner.class)
public class validationTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(validationTest.class);
    @Step("entering username")
    @Test(description = "validate username entered in textBox")
    public void validateUserNameEntered() throws FileNotFoundException {
        fb_page fb = new fb_page();
//        Allure.addAttachment("entering username", Utility.takeScreenShot());
//        fb.validateTitle("Facebook ? log in or sign up");
        logger.info("entering username as balaaa");
        fb.enterUserName("bala");

    }


    @Step("entering password")
    @Test(description = "validate username entered in textBox")
    public void validatePasswordEntered() throws FileNotFoundException {
        fb_page fb = new fb_page();
//        Allure.addAttachment("entering passWord", Utility.takeScreenShot());
        logger.info("entering password as 1233");
        fb.enterPassword("Pass@1233");

    }
}
