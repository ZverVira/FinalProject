import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class BBC2Task2 {
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
     * Add a test that verifies that team scores display correctly:
     * <p>
     * From BBC Home page go to Sport;
     * Go to Football
     * Go to Scores and Fixtures
     * Search for a championship (e.g. Scottish Championship).
     * This value should be in your test, so that another test can search for a different value.
     * Select results for a month. Again, the month should be in your test.
     * Verify that 2 specific teams (specified in your test) played with a specific score (again, specified in your test);
     * Click on one of the team’s names;
     * Verify that the score at the center of the screen is also the one from your test (same value).
     * Both the numbers and the team names should be verified.
     * Note: you need text from :after element. Selenium doesn’t work with them properly – google how to get around that.
     */
    @Test
    public void verifyThatTeamScoresDisplayCorrectlyScottishChampionship() {
        String searchingChampionship = "Scottish Championship";
        String searchingMonth = "2021-12";
        String homeTeamExpected = "Kilmarnock";
        String awayTeamExpected = "Greenock Morton";
        String homeTeamScoreExpected = "1";
        String awayTeamScoreExpected = "1";
        String gameScoreTeam = homeTeamExpected + " " + homeTeamScoreExpected + " " + awayTeamScoreExpected + " " + awayTeamExpected;
        Actions action = new Actions(driver);
        driver.findElement(By.xpath("//div[@id = 'orb-nav-links']//a[contains(@href,'https://www.bbc.com/sport')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//li[contains(@class,'sp-c-sport-navigation__item ')]//a[contains(@data-stat-title,'Football')]")).click();
        driver.findElement(By.xpath("//li[contains(@class,'sp-c-sport-navigation__item ')]//a[contains(@data-stat-title,'Scores & Fixtures')]")).click();
        WebElement searchField = driver.findElement(By.xpath("//input[contains(@id,'downshift-0-input')]"));
        action.sendKeys(searchField, searchingChampionship).build().perform();
        WebElement championshipMenuItem = driver.findElement(By.xpath("//a[contains(@class,'sp-c-search__result-item')]"));
        action.moveToElement(championshipMenuItem).click().build().perform();
        driver.findElement(By.xpath("//li[@class='sp-c-date-picker-timeline__item']//a[contains(@href,'scores-fixtures/" + searchingMonth + "')]")).click();
        new WebDriverWait(driver, 30)
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//article[@class = 'sp-c-fixture'][@data-event-id = 'EFBO2209365']"));
        js.executeScript("arguments[0].scrollIntoView();", element);
        String homeTeamActual = driver.findElement(By.xpath("//span[@class= 'gs-u-display-none gs-u-display-block@m qa-full-team-name sp-c-fixture__team-name-trunc'][contains(@data-reactid,'2.0.0.2.0.$0Scottish ChampionshipWednesday-29th-December.2.$EFBO2209365-wrapper.0.0.0.0.0.0.1')]")).getText();
        String awayTeamActual = driver.findElement(By.xpath("//span[@class = 'gs-u-display-none gs-u-display-block@m qa-full-team-name sp-c-fixture__team-name-trunc'][contains(@data-reactid,'2.0.0.2.0.$0Scottish ChampionshipWednesday-29th-December.2.$EFBO2209365-wrapper.0.0.0.2.0.0.1')]")).getText();
        String homeTeamScoreActual = driver.findElement(By.xpath("//span[@class= 'sp-c-fixture__number sp-c-fixture__number--home sp-c-fixture__number--ft'][contains(@data-reactid,'2.0.0.2.0.$0Scottish ChampionshipWednesday-29th-December.2.$EFBO2209365-wrapper.0.0.0.0.1.0')]")).getText();
        String awayTeamScoreActual = driver.findElement(By.xpath("//li[@class = 'gs-o-list-ui__item gs-u-pb-']//span[contains(@data-reactid,'2.0.0.2.0.$0Scottish ChampionshipWednesday-29th-December.2.$EFBO2209365-wrapper.0.0.0.2.1.0')]")).getText();
        assertEquals(homeTeamActual + " " + homeTeamScoreActual + " " + awayTeamScoreActual + " " + awayTeamActual, gameScoreTeam);
    }

    @Test
    public void verifyThatTeamScoresDisplayCorrectlyOnTeamPageForScottishChampionship() {
        String searchingChampionship = "Scottish Championship";
        String searchingMonth = "2021-12";
        Actions action = new Actions(driver);
        driver.findElement(By.xpath("//div[@id = 'orb-nav-links']//a[contains(@href,'https://www.bbc.com/sport')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//li[contains(@class,'sp-c-sport-navigation__item ')]//a[contains(@data-stat-title,'Football')]")).click();
        driver.findElement(By.xpath("//li[contains(@class,'sp-c-sport-navigation__item ')]//a[contains(@data-stat-title,'Scores & Fixtures')]")).click();
        WebElement searchField = driver.findElement(By.xpath("//input[contains(@id,'downshift-0-input')]"));
        action.sendKeys(searchField, searchingChampionship).build().perform();
        WebElement championshipMenuItem = driver.findElement(By.xpath("//a[contains(@class,'sp-c-search__result-item')]"));
        action.moveToElement(championshipMenuItem).click().build().perform();
        driver.findElement(By.xpath("//li[@class='sp-c-date-picker-timeline__item']//a[contains(@href,'scores-fixtures/" + searchingMonth + "')]")).click();
        new WebDriverWait(driver, 30)
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//article[@class = 'sp-c-fixture'][@data-event-id = 'EFBO2209365']"));
        js.executeScript("arguments[0].scrollIntoView();", element);
        String homeTeamActual = driver.findElement(By.xpath("//span[@class= 'gs-u-display-none gs-u-display-block@m qa-full-team-name sp-c-fixture__team-name-trunc'][contains(@data-reactid,'2.0.0.2.0.$0Scottish ChampionshipWednesday-29th-December.2.$EFBO2209365-wrapper.0.0.0.0.0.0.1')]")).getText();
        String awayTeamActual = driver.findElement(By.xpath("//span[@class = 'gs-u-display-none gs-u-display-block@m qa-full-team-name sp-c-fixture__team-name-trunc'][contains(@data-reactid,'2.0.0.2.0.$0Scottish ChampionshipWednesday-29th-December.2.$EFBO2209365-wrapper.0.0.0.2.0.0.1')]")).getText();
        String homeTeamScoreActual = driver.findElement(By.xpath("//span[@class= 'sp-c-fixture__number sp-c-fixture__number--home sp-c-fixture__number--ft'][contains(@data-reactid,'2.0.0.2.0.$0Scottish ChampionshipWednesday-29th-December.2.$EFBO2209365-wrapper.0.0.0.0.1.0')]")).getText();
        String awayTeamScoreActual = driver.findElement(By.xpath("//li[@class = 'gs-o-list-ui__item gs-u-pb-']//span[contains(@data-reactid,'2.0.0.2.0.$0Scottish ChampionshipWednesday-29th-December.2.$EFBO2209365-wrapper.0.0.0.2.1.0')]")).getText();
        driver.findElement(By.xpath("//span[contains(@data-reactid,'2.0.0.2.0.$0Scottish ChampionshipWednesday-29th-December.2.$EFBO2209365-wrapper.0.0.0.0.0.0.1')]")).click();
        new WebDriverWait(driver, 30)
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        String homeTeamActualTeamPage = driver.findElement(By.xpath("//span[contains(@data-reactid,'0.0.1.0.0.0.0.1')]")).getText();
        String awayTeamActualTeamPage = driver.findElement(By.xpath("//span[contains(@data-reactid,'0.0.1.0.2.0.0.1')]")).getText();
        String homeTeamScoreActualTeamPage = driver.findElement(By.xpath("//span[contains(@data-reactid,'0.0.1.0.0.1.0')]")).getText();
        String awayTeamScoreActualTeamPage = driver.findElement(By.xpath("//span[contains(@data-reactid,'0.0.1.0.2.1.0')]")).getText();
        assertEquals(homeTeamActualTeamPage + " " + homeTeamScoreActualTeamPage + " " + awayTeamScoreActualTeamPage + " " + awayTeamActualTeamPage, homeTeamActual + " " + homeTeamScoreActual + " " + awayTeamScoreActual + " " + awayTeamActual);
    }


    /**
     * Add 4 more tests (use copy-paste). They should all do the same thing with different data (championship, month, teams, score).
     */
    @Test
    public void verifyThatTeamScoresDisplayCorrectlyOnTeamPageForChampionsLeague() {
        String searchingChampionship = "Champions League";
        String searchingMonth = "2021-10";
        Actions action = new Actions(driver);
        driver.findElement(By.xpath("//div[@id = 'orb-nav-links']//a[contains(@href,'https://www.bbc.com/sport')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//li[contains(@class,'sp-c-sport-navigation__item ')]//a[contains(@data-stat-title,'Football')]")).click();
        driver.findElement(By.xpath("//li[contains(@class,'sp-c-sport-navigation__item ')]//a[contains(@data-stat-title,'Scores & Fixtures')]")).click();
        WebElement searchField = driver.findElement(By.xpath("//input[contains(@id,'downshift-0-input')]"));
        action.sendKeys(searchField, searchingChampionship).build().perform();
        WebElement championshipMenuItem = driver.findElement(By.xpath("//a[contains(@class,'sp-c-search__result-item')]"));
        action.moveToElement(championshipMenuItem).click().build().perform();
        driver.findElement(By.xpath("//li[@class='sp-c-date-picker-timeline__item']//a[contains(@href,'scores-fixtures/" + searchingMonth + "')]")).click();
        new WebDriverWait(driver, 30)
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//article[@class = 'sp-c-fixture'][@data-event-id = 'EFBO2244617']"));
        js.executeScript("arguments[0].scrollIntoView();", element);
        String homeTeamActual = driver.findElement(By.xpath("//span[contains(@data-reactid,'2.0.0.2.0.$0Champions LeagueWednesday-20th-October.2.$EFBO2244617-wrapper.0.0.0.0.0.0.1')]")).getText();
        String awayTeamActual = driver.findElement(By.xpath("//span[contains(@data-reactid,'2.0.0.2.0.$0Champions LeagueWednesday-20th-October.2.$EFBO2244617-wrapper.0.0.0.2.0.0.1')]")).getText();
        String homeTeamScoreActual = driver.findElement(By.xpath("//span[contains(@data-reactid,'2.0.0.2.0.$0Champions LeagueWednesday-20th-October.2.$EFBO2244617-wrapper.0.0.0.0.1.0')]")).getText();
        String awayTeamScoreActual = driver.findElement(By.xpath("//span[contains(@data-reactid,'2.0.0.2.0.$0Champions LeagueWednesday-20th-October.2.$EFBO2244617-wrapper.0.0.0.2.1.0')]")).getText();
        driver.findElement(By.xpath("//span[contains(@data-reactid,'2.0.0.2.0.$0Champions LeagueWednesday-20th-October.2.$EFBO2244617-wrapper.0.0.0.0.0.0.1')]")).click();
        new WebDriverWait(driver, 30)
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        String homeTeamActualTeamPage = driver.findElement(By.xpath("//span[contains(@data-reactid,'0.0.1.0.0.0.0.1')]")).getText();
        String awayTeamActualTeamPage = driver.findElement(By.xpath("//span[contains(@data-reactid,'0.0.1.0.2.0.0.1')]")).getText();
        String homeTeamScoreActualTeamPage = driver.findElement(By.xpath("//span[contains(@data-reactid,'0.0.1.0.0.1.0')]")).getText();
        String awayTeamScoreActualTeamPage = driver.findElement(By.xpath("//span[contains(@data-reactid,'0.0.1.0.2.1.0')]")).getText();
        assertEquals(homeTeamActualTeamPage + " " + homeTeamScoreActualTeamPage + " " + awayTeamScoreActualTeamPage + " " + awayTeamActualTeamPage, homeTeamActual + " " + homeTeamScoreActual + " " + awayTeamScoreActual + " " + awayTeamActual);
    }

    @Test
    public void verifyThatTeamScoresDisplayCorrectlyOnTeamPageForBayernMunich() {
        String searchingChampionship = "Bayern Munich";
        String searchingMonth = "2021-11";
        Actions action = new Actions(driver);
        driver.findElement(By.xpath("//div[@id = 'orb-nav-links']//a[contains(@href,'https://www.bbc.com/sport')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//li[contains(@class,'sp-c-sport-navigation__item ')]//a[contains(@data-stat-title,'Football')]")).click();
        driver.findElement(By.xpath("//li[contains(@class,'sp-c-sport-navigation__item ')]//a[contains(@data-stat-title,'Scores & Fixtures')]")).click();
        WebElement searchField = driver.findElement(By.xpath("//input[contains(@id,'downshift-0-input')]"));
        action.sendKeys(searchField, searchingChampionship).build().perform();
        WebElement championshipMenuItem = driver.findElement(By.xpath("//a[contains(@class,'sp-c-search__result-item')]"));
        action.moveToElement(championshipMenuItem).click().build().perform();
        driver.findElement(By.xpath("//li[@class='sp-c-date-picker-timeline__item']//a[contains(@href,'/scores-fixtures/" + searchingMonth + "')]")).click();
        new WebDriverWait(driver, 30)
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//article[@class = 'sp-c-fixture'][@data-event-id = 'EFBO2244635']"));
        js.executeScript("arguments[0].scrollIntoView();", element);
        String homeTeamActual = driver.findElement(By.xpath("//span[contains(@data-reactid,'2.0.0.2.0.$0Champions LeagueTuesday-2nd-November.2.$EFBO2244635-wrapper.0.0.0.0.0.0.1')]")).getText();
        String awayTeamActual = driver.findElement(By.xpath("//span[contains(@data-reactid,'2.0.0.2.0.$0Champions LeagueTuesday-2nd-November.2.$EFBO2244635-wrapper.0.0.0.2.0.0.1')]")).getText();
        String homeTeamScoreActual = driver.findElement(By.xpath("//span[contains(@data-reactid,'2.0.0.2.0.$0Champions LeagueTuesday-2nd-November.2.$EFBO2244635-wrapper.0.0.0.0.1.0')]")).getText();
        String awayTeamScoreActual = driver.findElement(By.xpath("//span[contains(@data-reactid,'2.0.0.2.0.$0Champions LeagueTuesday-2nd-November.2.$EFBO2244635-wrapper.0.0.0.2.1.0')]")).getText();
        driver.findElement(By.xpath("//span[contains(@data-reactid,'2.0.0.2.0.$0Champions LeagueTuesday-2nd-November.2.$EFBO2244635-wrapper.0.0.0.0.0.0.1')]")).click();
        new WebDriverWait(driver, 30)
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        String homeTeamActualTeamPage = driver.findElement(By.xpath("//span[contains(@data-reactid,'0.0.1.0.0.0.0.1')]")).getText();
        String awayTeamActualTeamPage = driver.findElement(By.xpath("//span[contains(@data-reactid,'0.0.1.0.2.0.0.1')]")).getText();
        String homeTeamScoreActualTeamPage = driver.findElement(By.xpath("//span[contains(@data-reactid,'0.0.1.0.0.1.0')]")).getText();
        String awayTeamScoreActualTeamPage = driver.findElement(By.xpath("//span[contains(@data-reactid,'0.0.1.0.2.1.0')]")).getText();
        assertEquals(homeTeamActualTeamPage + " " + homeTeamScoreActualTeamPage + " " + awayTeamScoreActualTeamPage + " " + awayTeamActualTeamPage, homeTeamActual + " " + homeTeamScoreActual + " " + awayTeamScoreActual + " " + awayTeamActual);
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
