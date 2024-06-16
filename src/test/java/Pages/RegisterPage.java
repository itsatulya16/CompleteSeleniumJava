package Pages;

import Utils.Utility;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

public class RegisterPage {
    private static String registerLink = "//a[contains(@href,'https://signup.eb')]";
    private static String creatAccount = "//h1[contains(text(),'Create an account')]";
    public static String personalRadioOpt = "//input[@id='personalaccount-radio']";
    public static String personalRadioOptLabel = "//label[contains(text(),'Personal')]";
    public static String businessRadioOptLabel = "//label[contains(text(),'Business')]";
    public static String firstName = "//input[contains(@id,'firstname')]";
    public static String lastName = "//input[contains(@id,'lastname')]";
    public static String email = "//input[contains(@id,'Email')]";
    public static String password = "//input[contains(@id,'password')]";
    public static String registerButton = "//button[contains(@id,'EMAIL_REG_FORM_SUBMIT')]";

    public void clickOnRegisterLink() {
        Utility.waitForElementVisibility(registerLink).click();
    }

    public void validateRegisterPageOpens() {
        Assert.assertTrue(Utility.waitForElementVisibility(creatAccount).isDisplayed(), "Registration page not open");
    }

    public static void validateRadioOptionsDisplay(String... radioOptions) {
        SoftAssert softAssert = new SoftAssert();
        Arrays.asList(radioOptions).forEach(option -> softAssert.assertTrue(Utility.waitForElementVisibility(option).isDisplayed(),
                String.format("the %s radio option is not displayed", option)));
        softAssert.assertAll();
    }

    public void selectRadioOption(String option) {
        boolean selected = Utility.waitForElementVisibility(option).isSelected();
        if (!selected) {
            Utility.waitForElementVisibility(option).click();
        }
    }

    public void validateFieldsDisplay(String... fields) {
        SoftAssert softAssert = new SoftAssert();
        Arrays.asList(fields).forEach(field -> softAssert.assertTrue(Utility.waitForElementVisibility(field).isDisplayed(),
                String.format("the %s is not displayed", field)));

    }

    public void enterValue(String fieldName, String value) {
        Utility.waitForElementVisibility(fieldName).clear();
        Utility.waitForElementVisibility(fieldName).sendKeys(value);
    }

    public void validateFormRegisterButtonEnable() {
        boolean enable = Utility.waitForElementToBeClickable(registerButton).isEnabled();
        Assert.assertTrue(enable, "Register button is not enable");
    }
}
