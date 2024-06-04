package parentTestClass;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.TestDataReader;
import utils.WebDriverSingleton;

public class TestBasics {
    protected static WebDriver driver = null;
    protected static String BROWSER_TYPE = null;
    protected static String DRIVER_PATH = null;
    protected static String PAGE_URL = null;
    protected static String USER_NAME = null;
    protected static String PASSWORD = null;

    protected TestBasics(){

        if(BROWSER_TYPE == null)
            BROWSER_TYPE = TestDataReader.readProperty("browser_type");
        if(DRIVER_PATH == null)
            DRIVER_PATH = TestDataReader.readProperty("driver_path");
        if(PAGE_URL == null)
            PAGE_URL = TestDataReader.readProperty("page_url");
        if(USER_NAME == null)
            USER_NAME = TestDataReader.readProperty("user_name");
        if(PASSWORD == null)
            PASSWORD = TestDataReader.readProperty("password");

        driver = WebDriverSingleton.setupDriver(BROWSER_TYPE, DRIVER_PATH);
    }

    @BeforeSuite(enabled = true)
    protected void somePreparingActions() {
        System.out.println("Test suite started from test-class: " + getClass());
    }
}
