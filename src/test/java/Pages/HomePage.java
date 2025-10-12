package Pages;

import Utils.Utility;
import org.openqa.selenium.Keys;
import org.testng.Assert;

public class HomePage {
    public static String username = "//*[@id='email']";
    public static String passWord = "//*[@id='pass']";
    public static String ebaySearchBox = "//input[@id='gh-ac']";
    Utility utility = new Utility();

    public void setUsername() {
        utility.waitForElementVisibility(username).sendKeys("atul");
    }

    public void setPassWord() {
        utility.waitForElementVisibility(passWord).sendKeys("test");
    }

    public void validateEbayOpen() {
        boolean ebayOpen = utility.waitForElementVisibility(ebaySearchBox).isDisplayed();
        Assert.assertTrue(!ebayOpen, "Not landed on correct page");
    }

    public void searchItem(String item) {
        utility.waitForElementVisibility(ebaySearchBox).sendKeys(item);
        utility.waitForElementVisibility(ebaySearchBox).sendKeys(Keys.ENTER);
    }
}
