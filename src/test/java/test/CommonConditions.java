package test;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import util.TestListener;
import waits.Wait;



@Listeners({TestListener.class})
public class CommonConditions {
    protected static WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void createDriver() {
        driver = DriverSingleton.getDriver();
        driver.manage().timeouts().pageLoadTimeout(Wait.SHORT_TIME_OUT);
    }

    @AfterTest(alwaysRun = true)
    public void browserClose() {
        DriverSingleton.closeDriver();
    }
}
