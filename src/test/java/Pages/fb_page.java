package Pages;

import Utils.Utility;

public class fb_page {

    private String userName = "//*[@id='email']";
    private String passWord = "//*[@id='pass']";

    public void enterUserName(String username){
        Utility.waitForElementVisibility(userName).sendKeys(username);
    }

    public void enterPassword(String passWord){
        Utility.waitForElementVisibility(userName).sendKeys(passWord);
    }

}
