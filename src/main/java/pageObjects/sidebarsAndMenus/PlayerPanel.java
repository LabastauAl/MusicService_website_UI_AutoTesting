package pageObjects.sidebarsAndMenus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

public class PlayerPanel extends PageObjectBasics {
    public PlayerPanel(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//footer[@data-testid='now-playing-bar']//div[@data-testid='player-controls']")
    private WebElement playerBar;
    @FindBy(xpath = "//footer[@data-testid='now-playing-bar']//button[@data-testid='control-button-playpause']")
    private WebElement playerPlayPauseButton;

    @FindBy(xpath = "//footer[@data-testid='now-playing-bar']//a[@data-testid='context-item-link']")
    private WebElement currentSong;

    @FindBy(xpath = "//footer[@data-testid='now-playing-bar']//div[@data-testid='signup-bar']/button[@data-encore-id='buttonPrimary']")
    private WebElement signUpButton;

    public void pauseCurrentSong() {
        playerPlayPauseButton.click();
    }

    public String getCurrentSongPlayer() {
        String curSongPlayer = currentSong.getText();
        return curSongPlayer;
    }

    public WebElement getPlayerBar() {
        return playerBar;
    }

    public boolean signUpButtonPresented() {
        return checkWebElementPresented(signUpButton);
    }
    public boolean signUpButtonDisplayed(){
        return checkWebElementDisplayed(signUpButton);
    }

    public boolean checkPlayPauseButton() {
        return checkWebElementPresented(playerPlayPauseButton);
    }

}
