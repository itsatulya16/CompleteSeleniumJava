package tests;

import Pages.HomePage;
import org.testng.annotations.Test;

public class seleniumTest extends BaseClass {
    @Test
    void verifysearchBox() {
        test = extent.createTest("validate userName and password field", "so beautifully validated");
        HomePage homePage = new HomePage();
        homePage.searchItem("game");

    }

    @Test
    void validateEbayOpens() {
        test = extent.createTest("validate ebay opens");
        HomePage homePage = new HomePage();
        homePage.validateEbayOpen();

    }
}
