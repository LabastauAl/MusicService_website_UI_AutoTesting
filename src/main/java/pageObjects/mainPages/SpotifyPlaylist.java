package pageObjects.mainPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

import java.util.List;
import java.util.Random;

public class SpotifyPlaylist extends PageObjectBasics {
    public SpotifyPlaylist(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@data-testid='entityTitle']/h1")
    private WebElement playlistName;
    @FindBy(xpath = "//div[@class='main-view-container__mh-footer-container']")
    private WebElement footer;
    @FindBy(xpath = "//div[@data-testid='tracklist-row']/div[1]")
    private List<WebElement> playButton;
    @FindBy(xpath = "//div[@data-testid='tracklist-row']/div[2]/div/a[@data-testid='internal-track-link']")
    private List<WebElement> namesOfSongs;
    @FindBy(xpath = "//p/a[@data-encore-id='type']")
    private WebElement popupEnterButton;
    @FindBy(xpath = "//div[@data-testid='playlist-tracklist']//div[@data-testid='top-sentinel']/../div[2]/div")
    private List<WebElement> listOfSongs;

    private static String songForPlay;

    public String getPlaylistName() {
        return playlistName.getText();
    }

    public String  getNumberOfSongs() {
        actions.moveToElement(footer).pause(500).perform();
        return playButton.get(playButton.size() - 1).getAttribute("textContent");
    }

    public void ascendToHeader() {
        actions.moveToElement(playlistName).perform();
    }

    public void clickToPlayRandomSong() throws InterruptedException {
        Random random = new Random();
        int playSongNumber = random.nextInt(namesOfSongs.size());
        actions.moveToElement(namesOfSongs.get(playSongNumber)).pause(3000).perform();
        songForPlay = namesOfSongs.get(playSongNumber).getText();
        System.out.println("Title of song for playing - " + "\"" + songForPlay + "\"");
        playButton.get(playSongNumber).click();
        Thread.sleep(1500);
        popupEnterButton.click();
    }

    public String getSongForPlay() {
        return songForPlay;
    }


    public void getFullListOfSongs() {
        int counter = 0;
        String songNumber = null;
        String ariaRowIndex = null;
        String maxAriaRowIndex = null;
        boolean pointer;

        actions.moveToElement(footer).pause(1000).perform();
        maxAriaRowIndex = listOfSongs.get(listOfSongs.size() - 1).getAttribute("ariaRowIndex");
        actions.moveToElement(playlistName).pause(1000).perform();

        for (int i = 0; i < listOfSongs.size(); i++) {
            counter++;
            songNumber = playButton.get(i).getText();
            System.out.print(counter + "\t");
            //if (songNumber.equals("") == false)
                System.out.print(songNumber + " - ");
            //else System.out.print(playButton.get(i).getAttribute("textContent"));
            System.out.println(namesOfSongs.get(i).getText());
        }
        actions.moveToElement(listOfSongs.get(listOfSongs.size() - 1)).perform();
        do {
            System.out.println("Size of list is: " + listOfSongs.size());
            counter = 0;
            pointer = false;
            for (int i = 0; i < listOfSongs.size(); i++) {
                if (pointer == false) {
                    counter++;
                    songNumber = playButton.get(i).getText();
                    pointer = songNumber.equals("");
                } else {
                    counter++;
                    songNumber = playButton.get(i).getText();
                    ariaRowIndex = listOfSongs.get(i).getAttribute("ariaRowIndex");
                    System.out.print(counter + "\t");
                    System.out.print(songNumber + " - ");
                    System.out.println(namesOfSongs.get(i).getText());
                }
            }
            actions.moveToElement(listOfSongs.get(listOfSongs.size() - 1)).perform();
        } while (ariaRowIndex.equals(maxAriaRowIndex) != true);
    }


}
