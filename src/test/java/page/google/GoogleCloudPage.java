package page.google;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import page.AbstractPage;
import waits.Wait;


public class GoogleCloudPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private static final String GOOGLE_CLOUD_URL = "https://cloud.google.com/products/calculator";
    public static String handleCloud;

    @FindBy(xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    public static WebElement IFRAME_1;

    @FindBy(xpath = "//iframe[@id='myFrame']")
    public static WebElement IFRAME_2;

    @FindBy(id = "input_95")
    private WebElement instancesArea;

    @FindBy(id = "select_120")
    private WebElement seriesDropDown;

    @FindBy(xpath = "//div[contains(text(),'N1')]")
    private WebElement seriesBtn;

    @FindBy(id = "select_value_label_91")
    private WebElement machineTypeDropDown;

    @FindBy(xpath = "//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']/div")
    private WebElement machineTypeBtn;

    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']/div/following-sibling::div")
    private WebElement addGPUsBtn;

    @FindBy(xpath = "//md-select[@placeholder='GPU type']")
    private WebElement gpuTypeDropDown;

    @FindBy(xpath = "//md-option[@value='NVIDIA_TESLA_P100']")
    private WebElement gpuTypeBtn;

    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    private WebElement numberOfGPUsDropDown;

    @FindBy(xpath = "//md-option[@value='1'][@ng-disabled='item.value != 0 && item.value < listingCtrl.minGPU']")
    private WebElement numberOfGPUsBtn;

    @FindBy(xpath = "//md-select[@placeholder='Local SSD']/md-select-value/span/div[@class='md-text ng-binding']")
   private WebElement localSSdDropDown;

    @FindBy(xpath = "//md-option/div[contains(text(),'2x375 GB')]")
    private WebElement localSSdBtn;

    @FindBy(xpath = "//md-select[@placeholder='Datacenter location']/md-select-value/span/div[@class='md-text ng-binding']")
    private WebElement datacenterLocationDropDown;

    @FindBy(xpath = "//md-select-menu[@class='_md md-overflow']//md-option/div[contains(text(),'Frankfurt (europe-west3)')]")
    private WebElement datacenterLocationBtn;

    @FindBy(id = "select_value_label_94")
    private WebElement  commitUsageDropDown;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option[@class='md-ink-ripple']/div[contains(text(),'1 Year')]")
    private WebElement commitUsageBtn;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[contains(text(),'Add to Estimate')]")
    private WebElement addToEstimateBtn;


    public GoogleCloudPage(WebDriver driver) {
        super(driver);
    }


    public GoogleCloudPage openPage() {
        driver.get(GOOGLE_CLOUD_URL);
        handleCloud = driver.getWindowHandle();
        return this;
    }


    public GoogleCloudPage fillInTheFields(String instances) {
                 switchToFrame()
                .setInstances(instances)
                .setSeriesAndMachineType()
                .addGPUs()
                .setLocalSSD()
                .setDataCenterLocation()
                .setCommitUsage();
        return this;
    }

    public GoogleCloudPage switchToFrame() {
        Wait.frameToBeAvailableAndSwitchToIt(IFRAME_1);
        Wait.frameToBeAvailableAndSwitchToIt(IFRAME_2);
        return this;
    }

    public GoogleCloudPage setInstances(String number) {
        Wait.elementToBeClickable(instancesArea);
        instancesArea.sendKeys(number);
        return this;
    }

    public GoogleCloudPage setSeriesAndMachineType() {
        seriesDropDown.click();
        Wait.elementToBeClickable(seriesBtn);
        seriesBtn.click();
        machineTypeDropDown.click();
        Wait.elementToBeClickable(machineTypeBtn);
        machineTypeBtn.click();
        return this;
    }



    public GoogleCloudPage addGPUs() {
        addGPUsBtn.click();
        Wait.elementToBeClickable(gpuTypeDropDown);
        gpuTypeDropDown.click();
        Wait.elementToBeClickable(gpuTypeBtn);
        gpuTypeBtn.click();
        Wait.elementToBeClickable(numberOfGPUsDropDown);
        numberOfGPUsDropDown.click();
        Wait.elementToBeClickable(numberOfGPUsBtn);
        numberOfGPUsBtn.click();
        return this;
    }

    public GoogleCloudPage setLocalSSD() {
        Wait.elementToBeClickable(localSSdDropDown);
        localSSdDropDown.click();
        Wait.elementToBeClickable(localSSdBtn);
        localSSdBtn.click();
        return this;
    }

    public GoogleCloudPage setDataCenterLocation() {
        Wait.elementToBeClickable(datacenterLocationDropDown);
        datacenterLocationDropDown.click();
        Wait.elementToBeClickable(datacenterLocationBtn);
        datacenterLocationBtn.click();
        return this;
    }

    public GoogleCloudPage setCommitUsage() {
        Wait.elementToBeClickable(commitUsageDropDown);
        commitUsageDropDown.click();
        Wait.elementToBeClickable(commitUsageBtn);
        commitUsageBtn.click();
        return this;
    }

    public GoogleCloudEstimate addToEstimate() {
        Wait.elementToBeClickable(addToEstimateBtn);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", addToEstimateBtn);
        return new GoogleCloudEstimate(driver);
    }

}
