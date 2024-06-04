package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PageObjectBasics {
    private WebDriver driver;
    protected Actions actions;

    public PageObjectBasics(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    protected boolean checkWebElementPresented(WebElement webElement) {
        try {
            webElement.isEnabled();
        } catch (NoSuchElementException exc) {
            return false;
        }
        return true;
    }

    protected boolean checkWebElementDisplayed(WebElement webElement) {
        try {
            webElement.isDisplayed();
        } catch (NoSuchElementException exc) {
            return false;
        }
        return true;
    }

    public WebElement waitForVisibility(WebElement element) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(element));
        } catch(TimeoutException exc){
            System.out.println("Element \"" + element + "\" is not visible on the page now");
            //System.out.println(exc);
        }
        return element;
    }

    public WebElement waitForClickable(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public List<WebElement> waitForVisibility(List<WebElement> listOfElements) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElements(listOfElements));
        return listOfElements;
    }

    public WebDriver getDriverInstance() {
        return driver;
    }

}
