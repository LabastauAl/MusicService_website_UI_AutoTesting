package pageObjects.sidebarsAndMenus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

public class CookiesMenu extends PageObjectBasics {
    public CookiesMenu(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='onetrust-close-btn-container']/button")
    private WebElement closeCookiesButton;

    public void closeCookies() {
        if (checkWebElementDisplayed(closeCookiesButton) == true) {
            closeCookiesButton.click();
        } else {
            System.out.println("Cookies are already closed");
        }
    }
}
