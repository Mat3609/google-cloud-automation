package page.google;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.AbstractPage;

public class CloudGoogleHomePage extends AbstractPage {
    private final String URL = "https://cloud.google.com/";
    private final Logger logger = LogManager.getRootLogger();



    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchBtn;


    public CloudGoogleHomePage(WebDriver driver) {
        super(driver);
    }


    public CloudGoogleHomePage openPage() {
        driver.get(URL);
        logger.info("Page is open!");
        return this;
    }

    public SearchingResultsPage searchString(String searchQuery) {
        searchBtn.sendKeys(searchQuery, Keys.ENTER);
        return new SearchingResultsPage(driver);
    }
}
