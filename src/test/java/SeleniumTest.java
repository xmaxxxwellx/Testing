import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class SeleniumTest {
    private static WebDriver driver;
    static GoogleSearchPage testPage;

    static final String SEARCH_WORD = "automation";

    @BeforeClass
    public static void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com.ua");
        testPage = PageFactory.initElements(driver, GoogleSearchPage.class);
    }

    @Test
    public void FirstTest(){
        testPage.searchForWord(SEARCH_WORD);
        Assert.assertTrue(testPage
                .getTitleOfFirstLink()
                .toLowerCase()
                .contains(SEARCH_WORD));
    }

    @Test
    public void SecondTest(){
        testPage.searchForWord(SEARCH_WORD);
        Assert.assertTrue(testPage.neededDomainSearch("www.usatoday.com", 5));
    }

    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }
}