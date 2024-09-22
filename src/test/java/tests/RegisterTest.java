package tests;

import Pages.HomePage;
import Pages.RegisterPage;
import Pages.fb_page;
import Utils.MyListnerSetUp;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Pages.RegisterPage.*;

@Listeners(MyListnerSetUp.class)
public class RegisterTest extends BaseClass {

    /*
     * @author - Atul Ambade
     * Description - validate registration fields with valid credentials
     * */
    @Test
    public void validatePersonalRegistrationWithValidCredentials() {
        String firstNameValue = "Atul";
        String lastNameValue = "Automation Tester";
        String emailValue = "Atul@gmail.com";
        String passwordValue = "Test@1234";

        /*@Step 1: login to ebay, Expected: login successful*/
        setUp("https://www.ebay.com/");
        HomePage homePage = new HomePage();
        homePage.validateEbayOpen();

        /*@Step 2: click on register link, Expected: register page opens*/
        RegisterPage registerPage = new RegisterPage();
        registerPage.clickOnRegisterLink();
        registerPage.validateRegisterPageOpens();

        /*@Step 3: validate both radio options display 1. Personal and 2. Business*/
        RegisterPage.validateRadioOptionsDisplay(personalRadioOptLabel, businessRadioOptLabel);

        /*@Step 4: select personal account radio option*/
        registerPage.selectRadioOption(personalRadioOptLabel);

        /*@Step 5: validate below fields display
         * 1.first name
         * 2.Last name
         * 3.Email
         * 4.Password
         * Expected: filds display*/
        registerPage.validateFieldsDisplay(firstName, lastName, email, password);

        /*@Step 6: enter first name, Expected: value entered*/
        registerPage.enterValue(firstName, firstNameValue);

        /*@Step 7: enter Last name, Expected: value entered*/
        registerPage.enterValue(lastName, lastNameValue);

        /*@Step 8: enter Email, Expected: value entered*/
        registerPage.enterValue(email, emailValue);

        /*@Step 9: enter password, Expected: value entered*/
        registerPage.enterValue(password, passwordValue);

        /*@Step 10: validate Registed button is enabled*/
        registerPage.validateFormRegisterButtonEnable();
    }

    /*
     * @author - Atul Ambade
     * Description - validate ebay home page opens
     * */
    @Test
    public void validateEbaySiteOpens() {
        /*@Step 1: launch browser and hit ebay link, Expected: validate ebay home page opens*/
        setUp("https://www.ebay.com/");
        HomePage homePage = new HomePage();
        homePage.validateEbayOpen();
    }

    @Test(description = "validate fb open")
    public void validateFbOpens(){
        setUp("https://www.facebook.com/");
    }

    @Test(description = "validate username entered in textBox")
    public void validateUserNameEntered(){
        setUp("https://www.facebook.com/");
        fb_page fb = new fb_page();
        fb.enterUserName("bala");
    }

    @Test(description = "validate username entered in textBox")
    public void validatePasswordEntered(){
        setUp("https://www.facebook.com/");
        fb_page fb = new fb_page();
        fb.enterPassword("Pass@1233");
    }
}
