package smokeTests;

import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.accountPages.LoginPage;
import pageObjects.accountPages.PlaylistPage;
import pageObjects.sidebarsAndMenus.CookiesMenu;
import pageObjects.sidebarsAndMenus.LeftSidebar;
import pageObjects.sidebarsAndMenus.PlayerPanel;
import pageObjects.sidebarsAndMenus.TopMenu;
import parentTestClass.TestBasics;

public class ProfilePlaylistsTests extends TestBasics {
    private static LoginPage loginPage;
    private static TopMenu topMenu;
    private static CookiesMenu cookiesMenu;
    private static PlayerPanel playerPanel;
    private static LeftSidebar leftSidebar;
    private static PlaylistPage playlistPage;

    @BeforeTest(description = "Activation of instances of all pages and elements participating in testing", enabled = true)
    public void activateTestPages() {
        System.out.println("Webdriver instance " + "\"" + getClass() + "\" class. " + driver);

        topMenu = new TopMenu(driver);
        loginPage = new LoginPage(driver);
        cookiesMenu = new CookiesMenu(driver);
        playerPanel = new PlayerPanel(driver);
        leftSidebar = new LeftSidebar(driver);
        playlistPage = new PlaylistPage(driver);

    }

    @BeforeClass(description = "Enter to the site and validate start page title", enabled = true)
    public void enterToPage() throws InterruptedException {
        driver.get(PAGE_URL);
        cookiesMenu.closeCookies();
        Assert.assertEquals(driver.getTitle(), "Spotify - Web Player: Music for everyone");
    }

    @Test(description = "Log in to account and verify that entering completed correctly", priority = 1, enabled = true)
    public void logInToAccount() throws InterruptedException {
        System.out.println("\t*** Test 1 ***");
        topMenu.enterToLoginPage();
        Thread.sleep(1000);
        Assert.assertTrue(driver.getCurrentUrl().contains("https://accounts.spotify.com/"));
        loginPage.setUserName(USER_NAME);
        loginPage.setPassword(PASSWORD);
        loginPage.setCheckboxOff();
        loginPage.loginEnter();
        Thread.sleep(2000);
        System.out.println("Player bar is displayed - " + playerPanel.getPlayerBar().isDisplayed());
        System.out.println("Signup button is presented - " + playerPanel.signUpButtonPresented());
        Assert.assertTrue(playerPanel.getPlayerBar().isDisplayed());
    }

    @Test(description = "Checking the initial amount of playlists, create new playlist and verify new amount", priority = 2, enabled = true)
    public void createNewPlaylist() throws InterruptedException {
        System.out.println("\t*** Test 2 ***\nThe results of \"Create new playlist\" testcase");
        int amountOfPlaylists = leftSidebar.getAmountOfPlaylists();
        System.out.println("The initial amount of playlists in active account: " + amountOfPlaylists);
        //Thread.sleep(3000);
        leftSidebar.createNewPlaylist();
        Thread.sleep(3000);
        System.out.println("Previous amount of playlist was " + amountOfPlaylists + ". Current amount of playlists: " + leftSidebar.getAmountOfPlaylists());
        Assert.assertEquals(leftSidebar.getAmountOfPlaylists(), amountOfPlaylists+1);
    }

    @Test(description = "Rename recently created playlist and verify old and new titles", priority = 3, enabled = true)
    public void renameNewPlaylist() throws InterruptedException {
        System.out.println("\t*** Test 3 ***");
        String oldTitle = playlistPage.getPlaylistTitle();
        String newTitle = "Metal_Playlist_2024-" + (int)(Math.random()*2024);
        playlistPage.renameOfPlaylist(newTitle);
        Thread.sleep(2000);
        System.out.println("Initial title of created playlist is: " + oldTitle + ". Current title of this playlist: " + newTitle);
        Assert.assertNotEquals(oldTitle, playlistPage.getPlaylistTitle());
        leftSidebar.goToStartPage();
    }

    @Test(description = "Adding songs to recently created playlist", priority = 4, enabled = true)
    public void addSongsToNewPlaylist() throws InterruptedException {
        System.out.println("\t*** Test 4 ***");
        String bandName = "Immortal";
        leftSidebar.openNewestEmptyPlaylist();
        playlistPage.searchBand(bandName);
        playlistPage.chooseFromSearchResultList(bandName);
        playlistPage.addSongsToPlayList();
        Thread.sleep(1000);
        playlistPage.getSongsAmount();
        playlistPage.playSongFromCurrentPlayList();
        Thread.sleep(3000);
        Assert.assertEquals(playlistPage.getPlayingSongName(), playerPanel.getCurrentSongPlayer());
        playlistPage.compareSongName(playerPanel.getCurrentSongPlayer());
    }
    @AfterClass(description = "Log out the account and return to start page", enabled = true)
    void afterTestCasesActions() throws InterruptedException {
        topMenu.logOutFromAccount();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://open.spotify.com/");
        Assert.assertTrue(playerPanel.signUpButtonPresented());
        System.out.println("Test cases in " + "\"" + getClass() + "\" class finished");
    }

    @AfterTest(enabled = false)
    void quitWebDriver() {
        driver.quit();
        System.out.println(driver);
    }
}
