package Pages;

import Utils.Utility;
import org.openqa.selenium.Keys;
import org.testng.Assert;

public class HomePage {
    public static String username = "//*[@id='email']";
    public static String passWord = "//*[@id='pass']";
    public static String ebaySearchBox = "//input[@id='gh-ac']";

    public static void setUsername() {
        Utility.waitForElementVisibility(username).sendKeys("atul");
    }

    public static void setPassWord() {
        Utility.waitForElementVisibility(passWord).sendKeys("test");
    }

    public void validateEbayOpen() {
        boolean ebayOpen = Utility.waitForElementVisibility(ebaySearchBox).isDisplayed();
        Assert.assertTrue(ebayOpen, "Not landed on correct page");
    }

    public void searchItem(String item) {
        Utility.waitForElementVisibility(ebaySearchBox).sendKeys(item);
        Utility.waitForElementVisibility(ebaySearchBox).sendKeys(Keys.ENTER);
    }
}
