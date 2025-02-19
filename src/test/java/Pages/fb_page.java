package Pages;

import Utils.Utility;
import org.testng.Assert;
import tests.BaseClass;

public class fb_page extends BaseClass {

    private String userName = "//*[@id='email']";
    private String passWord = "//*[@id='pass']";

    public void enterUserName(String username) {
        Utility.waitForElementVisibility(userName).sendKeys(username);
    }

    public void enterPassword(String passWord) {
        Utility.waitForElementVisibility(this.passWord).sendKeys(passWord);
    }

    public void validateTitle(String expected){
      String tiitle =   driver.getTitle();
        Assert.assertEquals(tiitle, expected, "title not matches");
    }

    public void clickLoginButton() {
                Utility.waitForElementVisibility("//button[@name='login']").click();
            }

    public void validateErrorMessage(String expectedMessage) {
        String actualMessage = Utility.waitForElementVisibility("//div[contains(@class, 'error_message')]").getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Error message does not match");
    }
}

