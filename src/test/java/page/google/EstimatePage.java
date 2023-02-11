package page.google;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import page.AbstractPage;
import page.email.EmailHomePage;
import util.RegexHelper;
import util.TabsHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waits.Wait;


public class EstimatePage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    String handle = driver.getWindowHandle();

    @FindBy(xpath = "//div[contains(text(),'Provisioning model: Regular')]")
    WebElement provisioningModelRegular;

    @FindBy(xpath = "//div[contains(text(),'Instance type: n1-standard-8')]")
    WebElement instanceType;

    @FindBy(xpath = "//div[contains(text(),'Region: Frankfurt')]")
    WebElement region;

    @FindBy(xpath = "//div[contains(text(),'Local SSD: 2x375 GiB')]")
    WebElement localSSD;

    @FindBy(xpath = "//div[contains(text(),' Commitment term: 1 Year')]")
    WebElement commitmentTerm;

    @FindBy(xpath = "//b[contains(text(),'Total Estimated Cost')]")
    WebElement totalCostText;


    @FindBy(xpath = "//*[@id='Email Estimate']/span")
    WebElement emailEstimate;

    @FindBy(xpath = "//md-input-container//label[contains(text(),'Email')]/following-sibling::input")
    WebElement emailField;

    @FindBy(xpath = "//button[contains(text(),'Send Email')]")
    WebElement sendEmailBtn;

    EmailHomePage emailHomePage;

    public EstimatePage(WebDriver driver) {
        super(driver);
    }


    public EstimatePage sendEstimateByEmail() {
        emailHomePage = new EmailHomePage(driver)
                .openPageInNewTab()
                .copyATemporaryEmailAddress();

        TabsHelper.switchingTabs(driver, handle);

        Wait.frameToBeAvailableAndSwitchToIt(CalculatorPage.IFRAME_1);
        Wait.frameToBeAvailableAndSwitchToIt(CalculatorPage.IFRAME_2);

        Wait.elementToBeClickable(emailEstimate).click();
        Wait.elementToBeClickable(emailField).sendKeys(Keys.CONTROL, "v");
        Wait.elementToBeClickable(sendEmailBtn).click();
        return this;
    }


    public boolean compareCostByEmailAndCalculator() {
        String calcCost = RegexHelper.getText(totalCostText, "(?<=USD)(.*)(?=per)");

        TabsHelper.switchingTabs(driver, emailHomePage.HANDLE_EMAIL);

        String emailCost = emailHomePage.getCost();
        System.out.println(emailCost);
        System.out.println(calcCost);

        return calcCost.equals(emailCost);
    }
}