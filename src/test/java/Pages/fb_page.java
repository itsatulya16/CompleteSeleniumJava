package Pages;

import Utils.Utility;
import org.testng.Assert;
import tests.BaseClass;

public class fb_page extends BaseClass {

    private String userName = "//*[@id='email']";
    private String passWord = "//*[@id='pass']";
    Utility utility = new Utility();

    public void enterUserName(String username) {
        utility.waitForElementVisibility(userName).sendKeys(username);
    }

    public void enterPassword(String passWord) {
        utility.waitForElementVisibility(this.passWord).sendKeys(passWord);
    }

    public void validateTitle(String expected){
      String tiitle =   getDriver().getTitle();
        Assert.assertEquals(tiitle, expected, "title not matches");
    }

    public void clickLoginButton() {
        utility.waitForElementVisibility("//button[@name='login']").click();
            }

    public void validateErrorMessage(String expectedMessage) {
        String actualMessage = utility.waitForElementVisibility("//div[contains(@class, 'error_message')]").getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Error message does not match");
    }
}

