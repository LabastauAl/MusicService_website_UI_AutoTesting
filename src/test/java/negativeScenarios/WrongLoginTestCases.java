package negativeScenarios;

import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.accountPages.LoginPage;
import pageObjects.sidebarsAndMenus.CookiesMenu;
import pageObjects.sidebarsAndMenus.PlayerPanel;
import pageObjects.sidebarsAndMenus.TopMenu;
import parentTestClass.TestBasics;
import utils.TestDataReader;

public class WrongLoginTestCases extends TestBasics {
    private static LoginPage loginPage;
    private static TopMenu topMenu;
    private static CookiesMenu cookiesMenu;
    private static PlayerPanel playerPanel;

    @BeforeTest(description = "Activation of instances of all pages and elements participating in testing", enabled = true)
    public void activateTestPages() {
        System.out.println("Webdriver instance " + "\"" + getClass() + "\" class. " + driver);

        topMenu = new TopMenu(driver);
        loginPage = new LoginPage(driver);
        cookiesMenu = new CookiesMenu(driver);
        playerPanel = new PlayerPanel(driver);
    }

    @BeforeClass(description = "Enter to the site and validate start page title", enabled = true)
    public void enterToPage() {
        driver.get(PAGE_URL);
        cookiesMenu.closeCookies();
        Assert.assertEquals(driver.getTitle(), "Spotify - Web Player: Music for everyone");
    }

    @Test(description = "Checking enter the account with wrong credential values", dataProvider = "wrong credentials", enabled = true)
    public static void logInToAccount(String userName, String password) throws InterruptedException {
        System.out.println("\t*** Wrong credentials test ***");

        topMenu.enterToLoginPage();
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.setCheckboxOff();
        loginPage.loginEnter();
        Thread.sleep(1000);

        Boolean warningBanner = loginPage.getWarningBanner().isDisplayed();
        if (warningBanner == true)
            System.out.println("Warning banner is presented on the account login form with\nLogin: " + userName + ", Password: " + password);
        Assert.assertTrue(warningBanner, "The web element is not presented on the web page");
        driver.navigate().refresh();
    }

    @DataProvider(name = "wrong credentials")
    public Object[][] setWrongCredentials() {
        return new Object[][]{
                {"wrong@userName", "wrongPassword"},
                {TestDataReader.readProperty("user_name"), ""},
                {"", TestDataReader.readProperty("password")},
                {TestDataReader.readProperty("user_name"), TestDataReader.readProperty("password") + '1'},
                {TestDataReader.readProperty("user_name"), ' ' + TestDataReader.readProperty("password")}
        };
    }

    @AfterClass(description = "Return to start page", enabled = true)
    public void returnToStartPage() throws InterruptedException {
        loginPage.pressSpotifyLogo();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://open.spotify.com/");
        System.out.println("The Sign up button is presented - " + playerPanel.signUpButtonPresented());
        Assert.assertTrue(playerPanel.signUpButtonPresented());
        System.out.println("Test cases in " + "\"" + getClass() + "\" class finished");
    }

    @AfterTest(enabled = true)
    public void quitDriver() {
        driver.quit();
        System.out.println(driver);
    }
}
