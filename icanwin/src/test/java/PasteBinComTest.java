import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PasteBinComTest {

    private static WebDriver chromeDriver;
    private static WebDriverWait webDriverWait;

    public static WebElement getLocatedElement(By by) {
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @BeforeClass
    public void setUp() {
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        webDriverWait = new WebDriverWait(chromeDriver,10);
    }

    @Test
    public static void pasteBinComTest() {
        chromeDriver.get("https://pastebin.com");
        WebElement newPaste = getLocatedElement(By.id("paste_code"));
        newPaste.sendKeys("Hello from WebDriver");

        WebElement pasteExpirationButton = getLocatedElement(By.xpath("//span[contains(text(),'Never')]"));
        Select dropdownPasteExpiration = new Select(pasteExpirationButton
                .findElement(By.xpath("//select[@class='post_select select2_nosearch select2-hidden-accessible' and (@name = 'paste_expire_date')]")));
        dropdownPasteExpiration.selectByValue("10M");

        WebElement pasteName = getLocatedElement(By.name("paste_name"));
        pasteName.sendKeys("helloweb");

        chromeDriver.quit();
    }

}
