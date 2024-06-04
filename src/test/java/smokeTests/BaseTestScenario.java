package smokeTests;

import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.accountPages.LoginPage;
import pageObjects.accountPages.ProfilePage;
import pageObjects.mainPages.SearchPage;
import pageObjects.mainPages.SpotifyPlaylist;
import pageObjects.mainPages.StartPage;
import pageObjects.sidebarsAndMenus.CookiesMenu;
import pageObjects.sidebarsAndMenus.LeftSidebar;
import pageObjects.sidebarsAndMenus.PlayerPanel;
import pageObjects.sidebarsAndMenus.TopMenu;
import parentTestClass.TestBasics;


public class BaseTestScenario extends TestBasics {
    private final String DESIRED_PLAYLIST = "Metal Essentials";
    private final String AMOUNT_OF_SONGS = "100";
    private static StartPage startPage;
    private static SpotifyPlaylist spotifyPlaylist;
    private static LeftSidebar leftSidebar;
    private static SearchPage searchPage;
    private static LoginPage loginPage;
    private static PlayerPanel playerPanel;
    private static TopMenu topMenu;
    private static ProfilePage profilePage;
    private static CookiesMenu cookiesMenu;

    @BeforeTest(description = "Activation of instances of all pages and elements participating in testing", enabled = true)
    public void activateTestPages() {
        System.out.println("Webdriver instance " + "\"" + getClass() + "\" class. " + driver);

        startPage = new StartPage(driver);
        spotifyPlaylist = new SpotifyPlaylist(driver);
        leftSidebar = new LeftSidebar(driver);
        searchPage = new SearchPage(driver);
        loginPage = new LoginPage(driver);
        playerPanel = new PlayerPanel(driver);
        topMenu = new TopMenu(driver);
        profilePage = new ProfilePage(driver);
        cookiesMenu = new CookiesMenu(driver);
    }

    @BeforeClass(description = "Enter to the site and validate start page title", enabled = true)
    public void enterToPage() throws InterruptedException {
        driver.get(PAGE_URL);
        //Thread.sleep(2000);
        cookiesMenu.closeCookies();
        Assert.assertEquals(driver.getTitle(), "Spotify - Web Player: Music for everyone");
    }

    @Test(description = "Checking the Spotify's Playlists section presence on the main page. Validation the amount of Playlists", priority = 1, enabled = true)
    public void getAmountOfStartPagePlaylists() throws InterruptedException {
        System.out.println("\t*** Test 1 ***");
        boolean playlistPresence = false;
        playlistPresence = startPage.searchPlaylistsSection();
        if (playlistPresence == true) {
            startPage.showAllPlaylists();
            Thread.sleep(2000);
            Assert.assertEquals(startPage.getAmountOfPlaylists(), 10);

            startPage.clickOnPlaylist();
            System.out.println("The title of current Spotify's Playlist is: " + spotifyPlaylist.getPlaylistName());
            System.out.println("The number of songs in current Spotify's Playlist - " + spotifyPlaylist.getNumberOfSongs());

            Assert.assertTrue(driver.getCurrentUrl().contains("https://open.spotify.com/playlist/"));
        } else {
            System.out.println("There are no Spotify playlists on start page");
            //leftSidebar.goToStartPage();
        }

    }

    @Test(description = "Checking the search system and searching for the Spotify's playlists", priority = 2, enabled = true)
    public void playListVerifying() throws InterruptedException {
        System.out.println("\t*** Test 2 ***");
        leftSidebar.goToSearchPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://open.spotify.com/search");
        searchPage.searchPlaylist(DESIRED_PLAYLIST);
        searchPage.enterToPlaylist(DESIRED_PLAYLIST);
        Thread.sleep(2000);

        String name = spotifyPlaylist.getPlaylistName();
        String amount = spotifyPlaylist.getNumberOfSongs();
        System.out.println("The title of desired playlist is: " + name);
        System.out.println("The amount of songs in found playlist - " + amount);
        Assert.assertEquals(amount, AMOUNT_OF_SONGS);
        Assert.assertEquals(name, DESIRED_PLAYLIST);
        Assert.assertEquals(driver.getTitle(), DESIRED_PLAYLIST + " | Spotify Playlist");
    }

    @Test(description = "Choosing random song in found playlist pressing play button and entering the login page", priority = 3, enabled = true)
    public void pressRandomSongToPlay() throws InterruptedException {
        System.out.println("\t*** Test 3 ***");
        spotifyPlaylist.ascendToHeader();
        spotifyPlaylist.clickToPlayRandomSong();
        Thread.sleep(1000);
        Assert.assertTrue(driver.getCurrentUrl().contains("https://accounts.spotify.com/"));
    }

    @Test(description = "Logging to existing account with correct credentials and validation of found playlist", priority = 4, enabled = true)
    public void loginToAccount() throws InterruptedException {
        System.out.println("\t*** Test 4 ***");
        loginPage.setUserName(USER_NAME);
        loginPage.setPassword(PASSWORD);
        loginPage.setCheckboxOff();
        loginPage.loginEnter();
        Thread.sleep(3000);
        Assert.assertTrue(driver.getCurrentUrl().contains("https://open.spotify.com/playlist/"));
        Assert.assertEquals(spotifyPlaylist.getPlaylistName(), DESIRED_PLAYLIST);
    }

    @Test(description = "Checking the name of playing song", priority = 5, enabled = true)
    public void checkTheSong() throws InterruptedException {
        System.out.println("\t*** Test 5 ***");
        System.out.println("The current playing song name is: "+ playerPanel.getCurrentSongPlayer());
        Thread.sleep(2000);
        playerPanel.pauseCurrentSong();
        Assert.assertEquals(playerPanel.getCurrentSongPlayer(), spotifyPlaylist.getSongForPlay());
}

    @Test(description = "Verifying profile name on the account page", priority = 6, enabled = true)
    public void profileNameVerifying() throws InterruptedException {
        System.out.println("\t*** Test 6 ***");
        topMenu.enterToProfilePage();
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("https://open.spotify.com/user/"));
        Assert.assertEquals(driver.getTitle(), "Spotify – " + profilePage.getProfileName());
        System.out.println("The current name of user is: " + profilePage.getProfileName());
        Assert.assertEquals(profilePage.getProfileName(), topMenu.getUserName());
    }

    @AfterClass(description = "Log out the account and return to start page", enabled = true)
    public void afterTestCasesActions() throws InterruptedException {
        topMenu.logOutFromAccount();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://open.spotify.com/");
        Assert.assertTrue(playerPanel.signUpButtonPresented());
        System.out.println("Test cases in " + "\"" + getClass() + "\" class finished");
    }

    @AfterTest(enabled = false)
    public void quitWebDriver() {
        driver.quit();
        System.out.println(driver);
    }

}
