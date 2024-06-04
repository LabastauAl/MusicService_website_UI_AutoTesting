package pageObjects.accountPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

public class LoginPage extends PageObjectBasics {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@data-testid='login-username']")
    private WebElement userInputField;
    @FindBy(xpath = "//input[@data-testid='login-password']")
    private WebElement passwordInputField;
    @FindBy(xpath = "//button[@data-testid='login-button']")
    private WebElement loginButton;
    @FindBy(xpath = "//input[@data-testid='login-remember']/following-sibling::span")
    private WebElement loginCheckbox;

    @FindBy(xpath = "//div[@data-encore-id='banner']")
    private WebElement warningBanner;

    @FindBy(css = "div[data-testid='spotify-logo'] > svg")
    private WebElement spotifyLogo;

    public LoginPage setUserName(String loginEmail) {
        userInputField.clear();
        userInputField.sendKeys(loginEmail);
        return this;
    }

    public LoginPage setPassword(String loginPassword) {
        passwordInputField.clear();
        passwordInputField.sendKeys(loginPassword);
        return this;
    }

    public LoginPage setCheckboxOff() {
        loginCheckbox.click();
        return this;
    }

    public LoginPage loginEnter() {
        loginButton.click();
        return this;
    }

    public WebElement getWarningBanner() {
        return warningBanner;
    }

    public void pressSpotifyLogo(){
        System.out.println("Spotify logo is enabled - " + spotifyLogo.isEnabled());
        System.out.println("Spotify logo is displayed - " + spotifyLogo.isDisplayed());
        spotifyLogo.click();
    }

}
