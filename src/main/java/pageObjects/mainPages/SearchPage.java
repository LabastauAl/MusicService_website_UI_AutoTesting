package pageObjects.mainPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

import java.util.List;

public class SearchPage extends PageObjectBasics {
    public SearchPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//form[@role='search']/..")
    private WebElement searchInputField;
    @FindBy(xpath = "//section[@data-testid='playlists-search-entity-shelf']/div[2]/div")
    private List<WebElement> playlistsSearchEntity;

    public void searchPlaylist(String playlistQuery){
        actions.moveToElement(searchInputField).sendKeys(playlistQuery).perform();
        //searchInputField.click();
        //searchInputField.sendKeys(playlistQuery);
        //searchInputField.sendKeys(Keys.ENTER);
    }

    public void enterToPlaylist(String desiredPlaylist){
        String tagAttribute = desiredPlaylist + "Spotify";
        for(int i = 0; i < playlistsSearchEntity.size(); i++){
            if(playlistsSearchEntity.get(i).getAttribute("textContent").equals(tagAttribute)){
                playlistsSearchEntity.get(i).click();
                break;
            }
        }
    }

}
