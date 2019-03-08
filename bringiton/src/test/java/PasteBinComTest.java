import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class PasteBinComTest {

    private static WebDriver chromeDriver;

    private static WebDriverWait webDriverWait;

    private static SoftAssert softAssert;

    private static String newPasteInputString = "git config --global user.name  \"New Sheriff in Town\"" + "\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")" + "\n" +
            "git push origin master --force";

    private static String pasteNameInputString = "how to gain dominance among developers";

    private static String bashHighlightingColor = "rgba(194, 12, 185, 1)";

    public static WebElement getClickableElement(By by) {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement getLocatedElement(By by) {
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @BeforeClass
    public void setUp() {
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        webDriverWait = new WebDriverWait(chromeDriver, 10);
        softAssert = new SoftAssert();
    }

    @Test
    public void pasteBinComTest() {

        chromeDriver.get("https://pastebin.com");

        WebElement newPasteTextArea = getLocatedElement(By.id("paste_code"));
        newPasteTextArea.sendKeys(newPasteInputString);

        Select syntaxHighlightingSelect = new Select(getLocatedElement(By.xpath("//select[@class='post_select select2_search select2-hidden-accessible' and (@name = 'paste_format')]")));
        syntaxHighlightingSelect.selectByValue("8");

        Select pasteExpirationSelect = new Select(getLocatedElement(By.xpath("//select[@class='post_select select2_nosearch select2-hidden-accessible' and (@name = 'paste_expire_date')]")));
        pasteExpirationSelect.selectByValue("10M");

        WebElement pasteNameTextArea = getLocatedElement(By.name("paste_name"));
        pasteNameTextArea.sendKeys(pasteNameInputString);

        WebElement createNewPasteButton = getClickableElement(By.xpath("//input[@value='Create New Paste']"));
        createNewPasteButton.click();

        WebElement rawPasteDataTextArea = getLocatedElement(By.id("paste_code"));

        String rawPasteDataOutputString = rawPasteDataTextArea.getText();

        String outputHighlightingColor = getLocatedElement(By.cssSelector(".bash .kw2")).getCssValue("color");

        softAssert.assertEquals(chromeDriver.getTitle(), "[Bash] " + pasteNameInputString + " - Pastebin.com");
        softAssert.assertEquals(outputHighlightingColor, bashHighlightingColor);
        softAssert.assertEquals(rawPasteDataOutputString, newPasteInputString);

        chromeDriver.quit();

        softAssert.assertAll();

    }
}
