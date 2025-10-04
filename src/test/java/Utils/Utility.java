package Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Utility extends BaseClass {
    /*
     * This method is implemented with explicit wait
     * */
    public static WebElement getWaitExplicit(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    /*
     * This method is  used to element visiblity, implemented with Fluent wait
     * */
    public static WebElement waitForElementVisibility(String xpath) {
        if (xpath == null || xpath.isEmpty()) {
            throw new IllegalArgumentException("XPath cannot be null or empty");
        }
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(2))
                .ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    /*
     * This method is used to wait until element is ready to click
     * */
    public static WebElement waitForElementToBeClickable(String xpath) {
        if (xpath == null || xpath.isEmpty()) {
            throw new IllegalArgumentException("XPath cannot be null or empty");
        }
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(2))
                .ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    // This method is used to take screen shot of page
    public static FileInputStream takeScreenShot() throws FileNotFoundException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        return new FileInputStream(srcFile);
    }

    // this methos is for fetching value from properties file
    public static String getValue(String value) {
        Properties properties = new Properties();
        FileInputStream fs = null;
        try {
            fs = new FileInputStream("config/selenium.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            properties.load(fs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties.getProperty(value);

    }
}
