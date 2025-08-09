package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class Admin_Page {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By menuOptions = By.cssSelector("ul.oxd-main-menu li span");
    private By usernameField = By.xpath("//label[text()='Username']/../following-sibling::div//input");
    private By searchButton = By.xpath("//button[normalize-space()='Search']");
    private By resetButton = By.xpath("//button[normalize-space()='Reset']");
    private By userRoleDropdown = By.xpath("//label[text()='User Role']/../following-sibling::div//div[@class='oxd-select-text-input']");
    private By userStatusDropdown = By.xpath("//label[text()='Status']/../following-sibling::div//div[@class='oxd-select-text-input']");
    private By dropdownOptions = By.cssSelector("div[role='listbox'] > div");
    private By resultsText = By.cssSelector("div.orangehrm-horizontal-padding.orangehrm-vertical-padding span.oxd-text--span");
    private By resultRows = By.cssSelector(".oxd-table-card");

    public Admin_Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 🔹 Navigate to Admin section
    public void goToAdminSection() {
        List<WebElement> menuItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(menuOptions));
        for (WebElement item : menuItems) {
            if (item.getText().equalsIgnoreCase("Admin")) {
                item.click();
                break;
            }
        }
    }

    // 🔹 Search by Username
    public void searchByUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
        driver.findElement(searchButton).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(resultRows));
    }

    // 🔹 Search by User Role (Click-based)
    public void searchByUserRole(String role) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown));
        dropdown.click();

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownOptions));
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(role)) {
                option.click();
                break;
            }
        }

        driver.findElement(searchButton).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(resultRows));
    }

    // 🔹 Search by User Role (Keyboard using Actions)
    public void searchByUserRoleWithActions() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown));
        dropdown.click();

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).pause(Duration.ofMillis(300)).sendKeys(Keys.ENTER).perform();

        driver.findElement(searchButton).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(resultRows));
    }

    // 🔹 Search by User Status
    public void searchByUserStatus(String status) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(userStatusDropdown));
        dropdown.click();

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownOptions));
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(status)) {
                option.click();
                break;
            }
        }

        driver.findElement(searchButton).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(resultRows));
    }

    // 🔹 Get record count from UI text (e.g., "Records Found (5)")
    public int getRecordCountFromText() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(resultsText)).getText();
        return Integer.parseInt(text.split("\\(")[1].split("\\)")[0]);
    }

    // 🔹 Get displayed row count in table
    public int getDisplayedRowCount() {
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(resultRows));
        return rows.size();
    }

    // 🔹 Refresh page
    public void refreshPage() {
        driver.navigate().refresh();
    }

    // 🔹 Reset search form
    public void resetSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(resetButton)).click();
    }
}
