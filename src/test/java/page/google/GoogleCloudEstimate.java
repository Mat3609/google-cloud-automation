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


public class GoogleCloudEstimate extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//div[contains(text(),'Provisioning model: Regular')]")
    private WebElement provisioningModelRegular;

    @FindBy(xpath = "//div[contains(text(),'Instance type: n1-standard-8')]")
    private WebElement instanceType;

    @FindBy(xpath = "//div[contains(text(),'Region: Frankfurt')]")
    private WebElement region;

    @FindBy(xpath = "//div[contains(text(),'Local SSD: 2x375 GiB')]")
    private WebElement localSSD;

    @FindBy(xpath = "//div[contains(text(),' Commitment term: 1 Year')]")
    private WebElement commitmentTerm;

    @FindBy(xpath = "//b[contains(text(),'Total Estimated Cost')]")
    private WebElement totalCostText;

    @FindBy(xpath = "//*[@id='Email Estimate']/span")
    private WebElement emailEstimateBtn;

    @FindBy(xpath = "//md-input-container//label[contains(text(),'Email')]/following-sibling::input")
    private WebElement emailField;

    @FindBy(xpath = "//button[contains(text(),'Send Email')]")
    private WebElement sendEmailBtn;

    private EmailHomePage emailHomePage;

    public GoogleCloudEstimate(WebDriver driver) {
        super(driver);
    }


    public GoogleCloudEstimate sendEstimateByEmail() {
        emailHomePage = new EmailHomePage(driver)
                .openPageInNewTab()
                .copyATemporaryEmailAddress();

        TabsHelper.switchingTabs(driver, GoogleCloudPage.handleCloud);

        Wait.frameToBeAvailableAndSwitchToIt(GoogleCloudPage.IFRAME_1);
        Wait.frameToBeAvailableAndSwitchToIt(GoogleCloudPage.IFRAME_2);

        Wait.elementToBeClickable(emailEstimateBtn).click();
        Wait.elementToBeClickable(emailField).sendKeys(Keys.CONTROL, "v");
        Wait.elementToBeClickable(sendEmailBtn).click();
        return this;
    }


    public boolean compareCostByEmailAndCalculator() {
        String cloudCost = RegexHelper.changeText(totalCostText, "(?<=USD)(.*)(?=per)");

        TabsHelper.switchingTabs(driver, EmailHomePage.HANDLE_EMAIL);

        String emailCost = emailHomePage.getCost();
        System.out.println(emailCost);
        System.out.println(cloudCost);

        return cloudCost.equals(emailCost);
    }
}