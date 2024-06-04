package pageObjects.accountPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

import java.util.List;
import java.util.Random;

public class PlaylistPage extends PageObjectBasics {
    public PlaylistPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//section[@data-testid='playlist-page']//span[@data-testid='entityTitle']")
    private WebElement playlistTitle;
    @FindBy(xpath = "//input[@data-testid='playlist-edit-details-name-input']")
    private WebElement titleInputField;
    @FindBy(xpath = "//button[@data-testid='playlist-edit-details-save-button']")
    private WebElement saveChangesButton;
    @FindBy(xpath = "//div[@class='contentSpacing']//div/div/input[@role='searchbox']")
    private WebElement searchSongsInputFiled;
    @FindBy(xpath = "//div[@data-testid='playlist-inline-curation-loaded-results']//div[@aria-rowindex]")
    private List<WebElement> searchingResults;
    @FindBy(xpath = "//button[@data-testid='add-to-playlist-button']")
    private List<WebElement> addSongButton;
    @FindBy(xpath = "//div[@data-testid='playlist-tracklist']//div[@data-testid='tracklist-row']/div[1]/div")
    private List<WebElement> playSongButton;
    @FindBy(xpath = "//div[@data-testid='playlist-tracklist']//a[@data-testid='internal-track-link']")
    private List<WebElement> songNames;

    private String playingSongName;

    public String getPlaylistTitle(){
        return playlistTitle.getText();
    }


    public void renameOfPlaylist(String newTitle) {
        playlistTitle.click();
        titleInputField.clear();
        titleInputField.sendKeys(newTitle);
        saveChangesButton.click();
    }

    public void searchBand(String bandName) {
        searchSongsInputFiled.click();
        searchSongsInputFiled.clear();
        searchSongsInputFiled.sendKeys(bandName);
    }

    public void chooseFromSearchResultList(String searchQuery) {
        String searchAttr;
        for (int i = 0; i < searchingResults.size(); i++) {
            searchAttr = searchingResults.get(i).getAttribute("textContent");
            if (searchAttr.contains(searchQuery) && searchAttr.contains("Исполнитель")
                    || searchAttr.contains(searchQuery) && searchAttr.contains("Artist")) {       //The attribute content "Исполнитель" should change for another localisation
                searchingResults.get(i).click();
                break;
            }
        }
    }

    public void addSongsToPlayList() {
        for (int i = 0; i < addSongButton.size(); i++) {
            actions.moveToElement(addSongButton.get(i)).pause(500).perform();
            addSongButton.get(i).click();
        }
    }

    // Play song and get info methods
    public void getSongsAmount() {
        System.out.println("Amount of songs in the current playlist: " + playSongButton.size());
    }

    public void playSongFromCurrentPlayList() {
        Random ran = new Random();
        int randomSong = ran.nextInt(playSongButton.size());
        actions.moveToElement(playSongButton.get(randomSong)).pause(2000).click().perform();
        playingSongName = songNames.get(randomSong).getText();
        randomSong = randomSong + 1;
        System.out.println("The number and name of playing song from current Playlist are: " + (randomSong) + " - " + playingSongName);

    }

    public String getPlayingSongName() {
        return playingSongName;
    }

    public void compareSongName(String titleOfPlayingSongInPlayer) {
        boolean comp = false;

        if (playingSongName.equals(titleOfPlayingSongInPlayer))
            comp = true;
        System.out.println("Compare of titles - " + comp);
        System.out.println("The title of currently playing song by playlist is - " + playingSongName);
        System.out.println("The title of currently playing song by player panel is - " + titleOfPlayingSongInPlayer);
    }
}
