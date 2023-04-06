package page.email;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import page.AbstractPage;
import util.RegexHelper;
import util.TabsHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waits.Wait;

public class EmailHomePage extends AbstractPage {
    private String url = "https://yopmail.com/ru/";
    public static String HANDLE_EMAIL;
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//iframe[@name='ifmail']")
    public WebElement frame;

    @FindBy(xpath = "//*[@id='listeliens']/a[1]/div[2]/h3")
    private WebElement createTemporaryEmailBtn;

    @FindBy(id = "cprnd")
    private WebElement copyEmailBtn;

    @FindBy(xpath = "/html/body/div/div[2]/main/div/div[2]/div/div[1]/div[2]/button[2]")
    private WebElement checkEmailBtn;

    @FindBy(xpath = "//h3[contains(text(),'USD 4,024.56')]")
    private WebElement totalCost;



    public EmailHomePage(WebDriver driver) {
        super(driver);
    }



    public EmailHomePage openPageInNewTab() {
        TabsHelper.openTab(driver);
        driver.get(url);
        HANDLE_EMAIL = driver.getWindowHandle();
        return this;
    }


    public EmailHomePage copyATemporaryEmailAddress() {
        Wait.elementToBeClickable(createTemporaryEmailBtn);
        createTemporaryEmailBtn.click();
        copyEmailBtn.click();
        return this;
    }



    public String getCost() {
        goToMessagePage();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.navigate().refresh();
        Wait.frameToBeAvailableAndSwitchToIt(frame);
        Wait.elementToBeClickable(totalCost);
        return RegexHelper.changeText(totalCost, "(?<=USD)(.*)(?=)");
    }


    public EmailHomePage goToMessagePage() {
        checkEmailBtn.click();
        return this;
    }
}
