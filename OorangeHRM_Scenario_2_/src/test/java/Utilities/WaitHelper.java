package Utilities;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    public WaitHelper(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    // Wait for element to be visible using By locator
    public WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element to be visible using WebElement
    public WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Wait for element to be clickable using By locator
    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Wait for element to be clickable using WebElement
    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Wait for presence of element in the DOM
    public WebElement waitForPresenceOfElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait for specific title to be present
    public boolean waitForTitleContains(String partialTitle) {
        return wait.until(ExpectedConditions.titleContains(partialTitle));
    }

    // Wait for alert to be present
    public Alert waitForAlertPresent() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }
}
