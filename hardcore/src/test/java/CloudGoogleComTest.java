import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class CloudGoogleComTest {

    private static WebDriver chromeDriver;

    private static WebDriverWait webDriverWait;

    private static Actions actions;

    public static WebElement getClickableElement(By by) {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement getClickableElement(WebElement webElement) {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static WebElement getClickableElement(long timeOutInSeconds, WebDriver driver, By by) {
        return new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement getLocatedElement(By by) {
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @BeforeClass
    public void setUp() {
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        webDriverWait = new WebDriverWait(chromeDriver, 11);
        actions = new Actions(chromeDriver);
    }

    @Test
    public static void cloudGoogleComTest() {

        chromeDriver.get("https://cloud.google.com");

        WebElement exploreAllProductsButton = getClickableElement(By.xpath("//a[@track-name='exploreProducts']"));
        exploreAllProductsButton.click();

        WebElement seePricingButton = getClickableElement(By.xpath("//a[@track-name='seePricing']"));
        seePricingButton.click();

        WebElement calculatorsLinkText = getClickableElement(By.xpath("//a[@track-name='pricingNav/calculators']"));
        calculatorsLinkText.click();

        WebElement childFrame = getLocatedElement(By.id("idIframe"));
        chromeDriver.switchTo().frame(childFrame);

        WebElement computeEngineButton = getClickableElement(By.xpath("//md-tab-item[@aria-controls='tab-content-1']"));
        computeEngineButton.click();

        WebElement numberOfInstancesContainer = getLocatedElement(By.xpath("//input[@ng-model='listingCtrl.computeServer.quantity']"));
        numberOfInstancesContainer.sendKeys("4");

        WebElement operationSystemDropDown = getClickableElement(By.id("select_58"));
        operationSystemDropDown.click();
        WebElement operationSystemDropDownChoice = getClickableElement(By.id("select_option_48"));
        operationSystemDropDownChoice.click();

        WebElement VmClassDropDown = getClickableElement(By.id("select_value_label_41"));
        VmClassDropDown.click();
        WebElement VmClassDropDownChoice = getClickableElement(By.id("select_option_60"));
        VmClassDropDownChoice.click();

        WebElement instanceTypeDropDown = getClickableElement(By.id("select_93"));
        instanceTypeDropDown.click();
        WebElement instanceTypeDropDownChoice = getClickableElement(By.id("select_option_70"));
        instanceTypeDropDownChoice.click();

        WebElement addGpuCheckBox = getClickableElement(By.xpath("//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']"));
        addGpuCheckBox.click();

        WebElement numberOfGpuDropDown = getClickableElement(By.id("select_value_label_327"));
        numberOfGpuDropDown.click();
        WebElement numberOfGpuDropDownChoice = getClickableElement(By.id("select_option_334"));
        numberOfGpuDropDownChoice.click();

        WebElement GpuTypeDropDown = getClickableElement(By.id("select_value_label_328"));
        GpuTypeDropDown.click();
        WebElement GpuTypeDropDownChoice = getClickableElement(By.id("select_option_341"));
        GpuTypeDropDownChoice.click();

        WebElement localSsdDropdown = getLocatedElement(By.id("select_value_label_43"));
        actions.moveToElement(localSsdDropdown).perform();
        getClickableElement(localSsdDropdown).click();
        WebElement localSsdDropdownChoice = getClickableElement(By.id("select_option_182"));
        localSsdDropdownChoice.click();

        WebElement dataCenterLocationDropdown = getClickableElement(By.id("select_value_label_44"));
        actions.moveToElement(dataCenterLocationDropdown).perform();
        getClickableElement(dataCenterLocationDropdown).click();
        WebElement dataCenterLocationDropdownChoice = getClickableElement(By.id("select_option_196"));
        dataCenterLocationDropdownChoice.click();

        WebElement committedUsageDropdown = getClickableElement(By.id("select_value_label_45"));
        actions.moveToElement(committedUsageDropdown).perform();
        getClickableElement(committedUsageDropdown).click();
        WebElement committedUsageDropdownChoice = getClickableElement(By.id("select_option_100"));
        committedUsageDropdownChoice.click();

        WebElement addToEstimate = getClickableElement(By.xpath("//*[@ng-disabled='ComputeEngineForm.$invalid || listingCtrl.unsuportedVmType']"));
        addToEstimate.click();

        WebElement emailEstimateButton = getClickableElement(By.id("email_quote"));
        emailEstimateButton.click();

        ((JavascriptExecutor) chromeDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(chromeDriver.getWindowHandles());
        chromeDriver.switchTo().window(tabs.get(1));
        chromeDriver.get("https://10minutemail.com");

        WebElement tenMinuteMailAddressElement = getLocatedElement(By.id("mailAddress"));
        String tenMinuteMailAddressString = tenMinuteMailAddressElement.getAttribute("value");

        chromeDriver.switchTo().window(tabs.get(0));

        chromeDriver.switchTo().frame(childFrame);

        WebElement googleCloudEmailContainer = getLocatedElement(By.xpath("//input[@ng-model='emailQuote.user.email']"));
        googleCloudEmailContainer.sendKeys(tenMinuteMailAddressString);

        WebElement sendEmailButton = getClickableElement(By.xpath("//button[text()[contains(.,'Send Email')]]"));
        sendEmailButton.click();

        chromeDriver.switchTo().window(tabs.get(1));

        WebElement emailMessage = getClickableElement(50, chromeDriver, By.id("ui-id-1"));
        emailMessage.click();

        WebElement priceTableData = getLocatedElement(By.xpath("//td//h3[text()[contains(.,'USD')]]"));
        String totalEstimatedMonthlyCost = priceTableData.getAttribute("innerText");

        chromeDriver.quit();

        assertEquals(totalEstimatedMonthlyCost, "USD 1,187.77");

    }

}