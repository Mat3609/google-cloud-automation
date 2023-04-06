package waits;


import driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wait {
    private static WebDriver driver = DriverSingleton.getDriver();
    public static final Duration LITLE_TIME_OUT = Duration.ofSeconds(10);
    public static final Duration SHORT_TIME_OUT = Duration.ofSeconds(30);
    public static final Duration MEDIUM_TIME_OUT = Duration.ofSeconds(60);
    public static final Duration LONG_TIME_OUT = Duration.ofSeconds(120);



    public static void frameToBeAvailableAndSwitchToIt(WebElement webElement) {
        new WebDriverWait(driver, MEDIUM_TIME_OUT)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement));
    }

    public static WebElement elementToBeClickable(WebElement webElement) {
        new WebDriverWait(driver, LITLE_TIME_OUT)
                .until(ExpectedConditions.elementToBeClickable(webElement));
        return webElement;
    }

    public static void elementPresence(By by) {
        new WebDriverWait(driver, SHORT_TIME_OUT)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }
    

    public static ExpectedCondition<Boolean> JQueryAJAXsCompleted() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };
    }

}
