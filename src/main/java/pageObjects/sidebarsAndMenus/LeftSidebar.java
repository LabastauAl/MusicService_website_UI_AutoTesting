package pageObjects.sidebarsAndMenus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObjectBasics;

import java.util.List;

public class LeftSidebar extends PageObjectBasics {
    public LeftSidebar(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@id='main']/div/div[2]/div[1]/nav/div[1]/ul/li[1]")
    private WebElement buttonStartPage;
    @FindBy(xpath = "//*[@id='main']/div/div[2]/div[1]/nav/div[1]/ul/li[2]")
    private WebElement buttonSearchPage;
    @FindBy(xpath = "//header/div/span/button[@data-encore-id='buttonTertiary']")
    private WebElement createPlaylistOrFolderButton;
    @FindBy(xpath = "//div[@data-placement='bottom-start']/ul/li[1]")
    private WebElement createPlaylistSubmenuButton;
    @FindBy(xpath = "//div[@data-testid='top-sentinel']/following-sibling::div[1]/li")
    private List<WebElement> listOfPlaylists;


    public void goToStartPage(){
        buttonStartPage.click();
    }
    public void goToSearchPage(){
        buttonSearchPage.click();
    }
    public void createNewPlaylist(){
        createPlaylistOrFolderButton.click();
        createPlaylistSubmenuButton.click();
    }
    public void openNewestEmptyPlaylist(){
        listOfPlaylists.get(0).click();
    }
    public int getAmountOfPlaylists(){
        return Integer.parseInt(listOfPlaylists.get(0).getAttribute("ariaSetSize"));
    }
}
