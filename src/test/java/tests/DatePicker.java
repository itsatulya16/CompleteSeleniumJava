package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DatePicker {
    public static void main(String[] args) {
        String dayToSelect = "16";
        String mothWantToSelect = "December";
        String yearToSelect = "2028";
        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/demo-site/datepicker/");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//iframe[@class='demo-frame lazyloaded']")));

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame lazyloaded']")));
        driver.findElement(By.xpath("//input[@id='datepicker']")).click();
//get month

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        while (true) {
            String month = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText().trim();
            String year = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText().trim();
            if (month.equals(mothWantToSelect) && year.equals(yearToSelect)) {
                List<WebElement> day = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr/td"));
                boolean dayFound = false;
                for (WebElement d : day) {
                    if (d.getText().trim().equals(dayToSelect)) {
                        System.out.println("day to be selected is: " + d.getText().trim());
                        d.click();
                        dayFound = true;
                        break;
                    }
                }
                if (dayFound){
                    break;
                }
            } else {
                webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Next']"))).click();
            }
        }

    }
}
