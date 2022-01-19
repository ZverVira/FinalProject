import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class BBC1Task1 {
    List<String> actual = Arrays.asList(
            "US urges Putin to take peaceful path on Ukraine",
            "'World's most valuable house' - which no one wanted to buy",
            "Former Vogue creative director Talley dies aged 73",
            "Britney issues cease and desist letter to sister",
            "Militant jailed for masterminding Bali bombings");

    private WebDriver driver;

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
     * Goes to BBC
     * Clicks on News
     * Checks the name of the headline article
     * (the one with the biggest picture and text) against a value specified in your test (hard-coded)
     */

    @Test
    public void checksTheTitleOfTheHeadlineArticle() {
        Actions action = new Actions(driver);
        driver.findElement(By.xpath("//div[@id = 'orb-nav-links']//a[contains(@href,'https://www.bbc.com/news')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (driver.findElement(By.xpath("//div[contains(@class,'tp-modal')]")).isDisplayed()) {
            action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'tp-modal')]"))).build().perform();
            driver.findElement(By.xpath("//button[contains(@class,'tp-close tp-active')]")).click();
        }
        String headlineArticleExpected = driver.findElement(By.xpath("//div[contains(@class,'gs-c-promo-body gs-u-display-none gs-u-display-inline-block@m gs-u-mt@xs gs-u-mt0@m gel-1/3@m')]//a[contains(@class,'gs-c-promo-heading gs-o-faux-block-link__overlay-link gel-paragon-bold nw-o-link-split__anchor')]")).getText();
        assertEquals(headlineArticleExpected, "Tonga undersea cable may take four weeks to repair");
    }

    /**
     * Goes to BBC
     * Clicks on News
     * Checks secondary article titles (the ones to the right and below the headline article)
     * against a List specified in your test (hard-coded).
     * Imagine that you are testing the BBC site on a test environment, and you know what the titles will be.
     * Your test, then, checks that these titles are in fact present on the page.
     * The test should pass if all the titles are found, and fail if they are not found.
     * It's normal that your test will fail the next day - this would not happen if we had a Test environment for BBC,
     * with a static database.
     */
    @Test
    public void checksTheTitlesOfTheSecondaryArticles() {
        Actions action = new Actions(driver);
        driver.findElement(By.xpath("//div[@id = 'orb-nav-links']//a[contains(@href,'https://www.bbc.com/news')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (driver.findElement(By.xpath("//div[contains(@class,'tp-modal')]")).isDisplayed()) {
            action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'tp-modal')]"))).build().perform();
            driver.findElement(By.xpath("//button[contains(@class,'tp-close tp-active')]")).click();
        }
        List<WebElement> expectedSecondaryArticlesTitlesList = driver.findElements(xpath("//h3[@class= 'gs-c-promo-heading__title gel-pica-bold nw-o-link-split__text']"));
        List<String> expectedSecondaryArticlesTitlesListText = new ArrayList<>();
        for (WebElement element : expectedSecondaryArticlesTitlesList) {
            expectedSecondaryArticlesTitlesListText.add(element.getText());
        }
        assertEquals(expectedSecondaryArticlesTitlesListText.subList(0, 5), actual);

    }

    /**
     * Goes to BBC
     * Clicks on News
     * Stores the text of the Category link of the headline article (e.g. World, Europe...)
     * Enter this text in the Search bar
     * Check the name of the first article against a specified value (hard-coded)
     */
    @Test
    public void checkTheTitleOfHeadlineArticleByCategoryLink() {
        Actions action = new Actions(driver);
        driver.findElement(By.xpath("//div[@id = 'orb-nav-links']//a[contains(@href,'https://www.bbc.com/news')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement categoryLink = driver.findElement(By.xpath("//ul[@class='gs-o-list-inline gs-o-list-inline--divided gel-brevier gs-u-mt-']//a[contains(@class,'gs-c-section-link gs-c-section-link--truncate nw-c-section-link nw-o-link nw-o-link--no-visited-state')]"));
        String categoryLinkText = categoryLink.getAttribute("href");
        driver.navigate().to(categoryLinkText);
        if (driver.findElement(By.xpath("//div[contains(@class,'tp-modal')]")).isDisplayed()) {
            action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'tp-modal')]"))).build().perform();
            driver.findElement(By.xpath("//button[contains(@class,'tp-close tp-active')]")).click();
        }
        WebElement headlineArticleExpected = driver.findElement(By.xpath("//div[contains(@class,'gs-c-promo-body gs-u-mt@xxs gs-u-mt@m gs-c-promo-body--primary gs-u-mt@xs gs-u-mt@s gs-u-mt@m gs-u-mt@xl gel-1/3@m gel-1/2@xl gel-1/1@xxl')]//a[contains(@class,'gs-c-promo-heading gs-o-faux-block-link__overlay-link gel-paragon-bold gs-u-mt+ nw-o-link-split__anchor')]//h3[@class='gs-c-promo-heading__title gel-paragon-bold gs-u-mt+ nw-o-link-split__text']"));
        assertEquals(headlineArticleExpected.getText(), "Tonga undersea cable may take four weeks to repair");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}


