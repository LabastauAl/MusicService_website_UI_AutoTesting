package pageObjects.sidebarsAndMenus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

public class TopMenu extends PageObjectBasics {
    public TopMenu(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@data-testid='login-button']")
    private WebElement loginButton;
    @FindBy(xpath = "//button[@data-testid='user-widget-link']")
    private WebElement userWidgetButton;
    @FindBy(xpath = "//div[@data-testid='context-menu']//li[2]")
    private WebElement userWidgetMenuProfileButton;
    @FindBy(xpath = "//div[@data-testid='context-menu']//li[5]")
    private WebElement userWidgetMenuLogoutButton;

    public void enterToLoginPage(){
        loginButton.click();
    }

    public void enterToProfilePage(){
        userWidgetButton.click();
        userWidgetMenuProfileButton.click();
    }

    public String getUserName(){
        return userWidgetButton.getAttribute("ariaLabel");
    }

    public void logOutFromAccount(){
        userWidgetButton.click();
        userWidgetMenuLogoutButton.click();
    }
}
