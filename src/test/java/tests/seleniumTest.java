package tests;

import Pages.HomePage;
import org.testng.annotations.Test;

public class seleniumTest extends BaseClass {
    @Test
    void test1() {
        test = extent.createTest("validate first test field", "so beautifully validated");
        HomePage.setUsername();
        HomePage.setPassWord();

    }

    @Test
    void test2() {
        test = extent.createTest("validate second test field", "so beautifully validated");
        HomePage.setUsername();
        HomePage.setPassWord();

    }

    @Test
    void test3() {
        test = extent.createTest("validate third test field", "so beautifully validated");
        HomePage.setUsername();
        HomePage.setPassWord();

    }

    @Test
    void validateEbayOpens() {
        test = extent.createTest("validate ebay opens");
        HomePage homePage = new HomePage();
        homePage.validateEbayOpen();

    }
}
