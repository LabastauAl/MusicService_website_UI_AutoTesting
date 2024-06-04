package smokeTests;

import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.mainPages.SearchPage;
import pageObjects.mainPages.SpotifyPlaylist;
import pageObjects.sidebarsAndMenus.CookiesMenu;
import pageObjects.sidebarsAndMenus.LeftSidebar;
import parentTestClass.TestBasics;

public class GettingSongsFromPlaylist extends TestBasics {
    private final String DESIRED_PLAYLIST = "Rock Classics";
    private final String AMOUNT_OF_SONGS = "200";
    private static SpotifyPlaylist spotifyPlaylist;
    private static LeftSidebar leftSidebar;
    private static SearchPage searchPage;
    private static CookiesMenu cookiesMenu;

    @BeforeTest(description = "Activation of instances of all pages and elements participating in testing", enabled = true)
    public void activateTestPages() {
        System.out.println("Webdriver instance " + "\"" + getClass() + "\" class. " + driver);

        spotifyPlaylist = new SpotifyPlaylist(driver);
        leftSidebar = new LeftSidebar(driver);
        searchPage = new SearchPage(driver);
        cookiesMenu = new CookiesMenu(driver);

    }

    @BeforeClass(description = "Enter to the site and validate start page title", enabled = true)
    public void enterToPage() throws InterruptedException {
        driver.get(PAGE_URL);
        //Thread.sleep(2000);
        cookiesMenu.closeCookies();
        Assert.assertEquals(driver.getTitle(), "Spotify - Web Player: Music for everyone");
    }

    @Test(description = "Searching for desired Spotify's playlist", priority = 1, enabled = true)
    void playlistVerifying() throws InterruptedException {
        System.out.println("\t*** Test 1 ***");
        leftSidebar.goToSearchPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://open.spotify.com/search");
        searchPage.searchPlaylist(DESIRED_PLAYLIST);
        searchPage.enterToPlaylist(DESIRED_PLAYLIST);
        Thread.sleep(2000);

        String name = spotifyPlaylist.getPlaylistName();
        String amount = spotifyPlaylist.getNumberOfSongs();
        System.out.println("The title of found desired playlist is: " + name);
        System.out.println("The amount of songs in found playlist - " + amount);
        Assert.assertEquals(amount, AMOUNT_OF_SONGS);
        Assert.assertEquals(name, DESIRED_PLAYLIST);
        Assert.assertEquals(driver.getTitle(), DESIRED_PLAYLIST + " | Spotify Playlist");
    }

    @Test(description = "Extracting all song titles from desired Spotify's playlist", priority = 2, enabled = true)
    void gettingAllSongs() {
        System.out.println("\t*** Test 2 ***");
        spotifyPlaylist.getFullListOfSongs();
    }

    @AfterClass(enabled = true)
    void afterTestCasesActions() {
        System.out.println("Test cases in " + "\"" + getClass() + "\" class finished");
    }

    @AfterTest(enabled = true)
    void quitWebDriver() {
        driver.quit();
        System.out.println(driver);
    }
}
