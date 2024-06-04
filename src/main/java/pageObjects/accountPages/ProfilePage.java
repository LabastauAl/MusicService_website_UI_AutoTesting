package pageObjects.accountPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

public class ProfilePage extends PageObjectBasics {
    public ProfilePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//span[@data-testid='entityTitle']")
    private WebElement profileNameButton;

    public String getProfileName(){
        return profileNameButton.getAttribute("textContent");
    }
}
