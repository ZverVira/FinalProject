import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class BBC1Task2 {
    private WebDriver driver;
    private String nameErrorMessage = "Name can't be blank";
    private String emailErrorMessage = "Email address can't be blank";
    private String questionBodyErrorMessage = "can't be blank";
    private String termsOfServiceCheckBoxErrorMessage = "must be accepted";
    private String longNameErrorMessage = "Name is too long (maximum is 255 characters)";

    private String longName = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq";

    private List<String> errorMessagesList = Arrays.asList(questionBodyErrorMessage, nameErrorMessage, emailErrorMessage, termsOfServiceCheckBoxErrorMessage);

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void testSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.bbc.com/");
    }

    /**
     * Add a test which verifies that user can submit a question to BBC:
     * <p>
     * Find a form (several text boxes and possibly check boxes, with some sort of Send/Submit button) that allows you to send a question to BBC. Note that this form sometimes moves around the BBC site - ask in chat if you cannot find it.
     * Below steps work as of 16.09.2020:
     * <p>
     * From BBC Home page go to News;
     * Click on "Coronavirus" tab, and then on "Your Coronavirus Stories" tab;
     * Go to “Coronavirus: Send us your questions”;
     * Scroll down to find the form.
     * <p>
     * <p>
     * Fill the form with information, but one of the required fields empty or with invalid data;
     * Click Send/Submit;
     * Verify that the submission did not work, either by checking for correct error message, or, failing that, that the form still contains entered data.
     * <p>
     * <p>
     * Add at least 2 more tests (use copy-paste). They should do the same as the one we just wrote, except they will cover different negative test cases.
     * Add one case for each required field/checkbox that leaves it empty. If Email is required - add a case where it is invalid.
     */
    @Test
    public void checksThatTheErrorMessagesAboutOutOfSymbolsLimitNameFieldAppearsOnSendUsQuestionForm() {
        Actions action = new Actions(driver);
        driver.findElement(By.xpath("//div[@id = 'orb-nav-links']//a[contains(@href,'https://www.bbc.com/news')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (driver.findElement(By.xpath("//div[contains(@class,'tp-modal')]")).isDisplayed()) {
            action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'tp-modal')]"))).build().perform();
            driver.findElement(By.xpath("//button[contains(@class,'tp-close tp-active')]")).click();
        }
        driver.findElement(By.xpath("//li[@class = 'gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__wide-menuitem-container']//a[@class = 'nw-o-link'][@href = '/news/coronavirus']")).click();
        driver.findElement(By.xpath("//li[@class = 'gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__secondary-menuitem-container']//a[@class = 'nw-o-link'][@href = '/news/have_your_say']")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement sendUsQuestionLink = driver.findElement(By.xpath("//a[@href= '/news/52143212']"));
        js.executeScript("arguments[0].scrollIntoView();", sendUsQuestionLink);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@href= '/news/52143212']")).click();
        WebElement sendUsQuestionForm = driver.findElement(By.xpath("//div[@class= 'embed-content-container']"));
        js.executeScript("arguments[0].scrollIntoView();", sendUsQuestionForm);
        WebElement questionField = driver.findElement(By.xpath("//div[@class = 'long-text-input-container']"));
        action.sendKeys(questionField, "Test Message").build().perform();
        WebElement nameField = driver.findElement(By.xpath("//input[@aria-label= 'Name']"));
        action.sendKeys(nameField, longName).build().perform();
        WebElement emailField = driver.findElement(By.xpath("//input[@aria-label= 'Email address']"));
        action.sendKeys(emailField, "test@test.com").build().perform();
        driver.findElement(By.xpath("//input[@type= 'checkbox']")).click();
        driver.findElement(By.xpath("//div[@class= 'embed-content-container']//button[@class = 'button']")).click();
        String errorMessageExpected = driver.findElement(By.xpath("//div[@class= 'input-error-message']")).getText();
        assertEquals(errorMessageExpected, longNameErrorMessage);
    }

    @Test
    public void checksThatTheErrorMessagesAboutEmailFieldCannotBeEmptyAppearsOnSendUsQuestionForm() {
        Actions action = new Actions(driver);
        driver.findElement(By.xpath("//div[@id = 'orb-nav-links']//a[contains(@href,'https://www.bbc.com/news')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (driver.findElement(By.xpath("//div[contains(@class,'tp-modal')]")).isDisplayed()) {
            action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'tp-modal')]"))).build().perform();
            driver.findElement(By.xpath("//button[contains(@class,'tp-close tp-active')]")).click();
        }
        driver.findElement(By.xpath("//li[@class = 'gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__wide-menuitem-container']//a[@class = 'nw-o-link'][@href = '/news/coronavirus']")).click();
        driver.findElement(By.xpath("//li[@class = 'gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__secondary-menuitem-container']//a[@class = 'nw-o-link'][@href = '/news/have_your_say']")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement sendUsQuestionLink = driver.findElement(By.xpath("//a[@href= '/news/52143212']"));
        js.executeScript("arguments[0].scrollIntoView();", sendUsQuestionLink);
        driver.findElement(By.xpath("//a[@href= '/news/52143212']")).click();
        WebElement sendUsQuestionForm = driver.findElement(By.xpath("//div[@class= 'embed-content-container']"));
        js.executeScript("arguments[0].scrollIntoView();", sendUsQuestionForm);
        WebElement questionField = driver.findElement(By.xpath("//div[@class = 'long-text-input-container']"));
        action.sendKeys(questionField, "Test Message").build().perform();
        WebElement nameField = driver.findElement(By.xpath("//input[@aria-label= 'Name']"));
        action.sendKeys(nameField, "Test Name").build().perform();
        driver.findElement(By.xpath("//input[@type= 'checkbox']")).click();
        driver.findElement(By.xpath("//div[@class= 'embed-content-container']//button[@class = 'button']")).click();
        String errorMessageExpected = driver.findElement(By.xpath("//div[@class= 'input-error-message']")).getText();
        assertEquals(errorMessageExpected, emailErrorMessage);
    }

    @Test
    public void checksThatTheNameFieldContentIsNotEmptyAfterClickingOnSubmitButtonInCaseOfError() {
        Actions action = new Actions(driver);
        driver.findElement(By.xpath("//div[@id = 'orb-nav-links']//a[contains(@href,'https://www.bbc.com/news')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (driver.findElement(By.xpath("//div[contains(@class,'tp-modal')]")).isDisplayed()) {
            action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'tp-modal')]"))).build().perform();
            driver.findElement(By.xpath("//button[contains(@class,'tp-close tp-active')]")).click();
        }
        driver.findElement(By.xpath("//li[@class = 'gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__wide-menuitem-container']//a[@class = 'nw-o-link'][@href = '/news/coronavirus']")).click();
        driver.findElement(By.xpath("//li[@class = 'gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__secondary-menuitem-container']//a[@class = 'nw-o-link'][@href = '/news/have_your_say']")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement sendUsQuestionLink = driver.findElement(By.xpath("//a[@href= '/news/52143212']"));
        js.executeScript("arguments[0].scrollIntoView();", sendUsQuestionLink);
        driver.findElement(By.xpath("//a[@href= '/news/52143212']")).click();
        WebElement sendUsQuestionForm = driver.findElement(By.xpath("//div[@class= 'embed-content-container']"));
        js.executeScript("arguments[0].scrollIntoView();", sendUsQuestionForm);
        WebElement questionField = driver.findElement(By.xpath("//div[@class = 'long-text-input-container']"));
        action.sendKeys(questionField, "Test Message").build().perform();
        WebElement nameField = driver.findElement(By.xpath("//input[@aria-label= 'Name']"));
        action.sendKeys(nameField, longName).build().perform();
        WebElement emailField = driver.findElement(By.xpath("//input[@aria-label= 'Email address']"));
        action.sendKeys(emailField, "test@test.com").build().perform();
        driver.findElement(By.xpath("//input[@type= 'checkbox']")).click();
        driver.findElement(By.xpath("//div[@class= 'embed-content-container']//button[@class = 'button']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@class= 'input-error-message']")), "Name is too long (maximum is 255 characters)"));
        String nameFieldContent = driver.findElement(By.xpath("//div[@class = 'text-input']//input[@aria-label = 'Name']")).getAttribute("value");
        assertTrue(!nameFieldContent.isEmpty());
    }

    @Test
    public void checksThatTheAllErrorMessagesAppearsIfAllRequiredFieldsAreNotFilledOnSendUsQuestionForm() {
        Actions action = new Actions(driver);
        driver.findElement(By.xpath("//div[@id = 'orb-nav-links']//a[contains(@href,'https://www.bbc.com/news')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (driver.findElement(By.xpath("//div[contains(@class,'tp-modal')]")).isDisplayed()) {
            action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'tp-modal')]"))).build().perform();
            driver.findElement(By.xpath("//button[contains(@class,'tp-close tp-active')]")).click();
        }
        driver.findElement(By.xpath("//li[@class = 'gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__wide-menuitem-container']//a[@class = 'nw-o-link'][@href = '/news/coronavirus']")).click();
        driver.findElement(By.xpath("//li[@class = 'gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__secondary-menuitem-container']//a[@class = 'nw-o-link'][@href = '/news/have_your_say']")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement sendUsQuestionLink = driver.findElement(By.xpath("//a[@href= '/news/52143212']"));
        js.executeScript("arguments[0].scrollIntoView();", sendUsQuestionLink);
        driver.findElement(By.xpath("//a[@href= '/news/52143212']")).click();
        WebElement sendUsQuestionForm = driver.findElement(By.xpath("//div[@class= 'embed-content-container']"));
        js.executeScript("arguments[0].scrollIntoView();", sendUsQuestionForm);
        driver.findElement(By.xpath("//div[@class= 'embed-content-container']//button[@class = 'button']")).click();
        List<WebElement> errorMessagesExpected = driver.findElements(By.xpath("//div[@class= 'input-error-message']"));
        List<String> errorMessagesExpectedTextList = new ArrayList<>();
        for (WebElement element : errorMessagesExpected) {
            errorMessagesExpectedTextList.add(element.getText());
        }
        assertEquals(errorMessagesExpectedTextList, errorMessagesList);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
