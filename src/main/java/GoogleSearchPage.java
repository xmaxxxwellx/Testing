import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleSearchPage {

    private final WebDriver driver;

    By searchFieldLocator = By.id("lst-ib");
    By firstLinkBlock = By.className("g");
    By nextPageLocator = By.id("pnnext");
    By resultDomainLocator = By.tagName("cite");

    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;

        if(!"Google".equals(driver.getTitle())){
            throw new IllegalStateException("This is not a Google page!");
        }
    }

    public void searchForWord(String searchWord){
        driver.findElement(searchFieldLocator)
                .sendKeys(searchWord + "\n");
    }

    public String getTitleOfFirstLink(){
        driver.findElement(firstLinkBlock)
                .findElement(By.tagName("a"))
                .click();

        return driver.getTitle();
    }

    public boolean neededDomainSearch(String domainToLookFor){
        String str;
        boolean check = false;
            List<WebElement> elementsList = driver.findElements(resultDomainLocator);
            for (WebElement item : elementsList) {
                str = item.getText();
                check = str.contains(domainToLookFor);
                if(check){
                    break;
                }
            }
        return check;
    }

    public boolean neededDomainSearch(String domainToLookFor, int pageCount){
        String str;
        boolean check = false;
        for (int i = 0; i < pageCount; i++) {
            List<WebElement> elementsList = driver.findElements(resultDomainLocator);
            for (WebElement item : elementsList) {
                str = item.getText();
                check = str.contains(domainToLookFor);
                if(check){
                    break;
                }
            }
                if(check){
                break;
                }
                driver.findElement(nextPageLocator)
                        .click();
            }
        return check;
    }
}
