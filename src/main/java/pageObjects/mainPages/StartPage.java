package pageObjects.mainPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

import java.util.List;
import java.util.Random;

public class StartPage extends PageObjectBasics {
    public StartPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//section[@data-testid='component-shelf']//h2/a")
    private List<WebElement> popularSections;
    //@FindBy(xpath = "//div/a[@title='Rock Classics']")
    //private WebElement startPageCard;
    @FindBy(xpath = "//section[@data-testid='home-page']//a/span[@data-encore-id='text']")
    private WebElement showAllButton;
    @FindBy(xpath = "//div[@data-encore-id='card']")
    //"//div[@data-testid='card-click-handler']/preceding-sibling::div[1]/a"
    private List<WebElement> cardsFromPopularSections;

    public boolean searchPlaylistsSection() {
        for (int i = 0; i < popularSections.size(); i++) {
            if (popularSections.get(i).getText().equals("Spotify Playlists") || popularSections.get(i).getText().equals("Плейлисты Spotify")) {
                return true;
            }
        }
        return false;
    }

    public void showAllPlaylists() {
        for (int i = 0; i < popularSections.size(); i++) {
            if (popularSections.get(i).getText().equals("Spotify Playlists") || popularSections.get(i).getText().equals("Плейлисты Spotify")) {
                popularSections.get(i).click();
                break;
            }
        }
    }

    public void clickOnPlaylist() {
        for (int i = 0; i < cardsFromPopularSections.size(); i++) {
            if (cardsFromPopularSections.get(i).getAttribute("textContent").contains("Rock Classics")) {
                actions.moveToElement(cardsFromPopularSections.get(i)).pause(500).perform();
                cardsFromPopularSections.get(i).click();
                break;
            }
            if (i == cardsFromPopularSections.size() - 1) {
                Random random = new Random();
                int chosenNumber = random.nextInt(cardsFromPopularSections.size());
                actions.moveToElement(cardsFromPopularSections.get(chosenNumber)).pause(500).perform();
                cardsFromPopularSections.get(chosenNumber).click();
            }
        }
    }

    public int getAmountOfPlaylists() {
        return cardsFromPopularSections.size();
    }

}
