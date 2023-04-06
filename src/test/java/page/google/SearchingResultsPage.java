package page.google;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.AbstractPage;
import waits.Wait;

import java.util.List;

public class SearchingResultsPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    @FindBy(xpath = "//div[@class='gsc-thumbnail-inside']//b[text()='Google Cloud Pricing Calculator']")
    WebElement titlePage;

    public SearchingResultsPage(WebDriver driver) {
        super(driver);
    }

    public int getSearchResults() {
        Wait.elementToBeClickable(titlePage);
        List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='gsc-webResult gsc-result']"));
        return searchResults.size();
    }

}
