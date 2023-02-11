package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class TabsHelper {


    public static void openTab(WebDriver driver) {
        driver.switchTo().newWindow(WindowType.TAB);
    }
    
    public static void switchingTabs(WebDriver driver, String window) {
        driver.switchTo().window(window);
    }
}
