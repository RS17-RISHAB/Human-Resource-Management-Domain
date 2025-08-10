package Login_Test;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import Excel_Utilities.ExcelUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class LoginTest {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/LoginTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void setupBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @DataProvider(name = "LoginData")
    public Object[][] loginData() throws EncryptedDocumentException, IOException {
        return ExcelUtil.getData("src/test/resources/LoginData.xlsx", "Sheet1");
    }

    @Test(dataProvider = "LoginData")
    public void testLogin(String username, String password) throws IOException {
        test = extent.createTest("Login Test with: " + username + "/" + password);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        try {
            Thread.sleep(2000); // Wait for page to load

            driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            Thread.sleep(2000); // Wait for response

            boolean loginSuccess = driver.getCurrentUrl().contains("dashboard");
            captureScreenshot(username);

            if (username.equals("Admin") && password.equals("admin123")) {
                // VALID login case
                Assert.assertTrue(loginSuccess, "Valid login failed!");
                test.pass("Login successful with valid credentials.");
                logout();
            } else {
                // INVALID login case: check for error message
                WebElement errorMsg = driver.findElement(By.xpath("//p[contains(text(),'Invalid credentials')]"));
                Assert.assertTrue(errorMsg.isDisplayed(), "Error message not displayed for invalid login!");
                test.pass("Invalid login correctly showed error message.");
            }

        } catch (Exception e) {
            test.fail("Exception occurred: " + e.getMessage());
        }
    }

    public void captureScreenshot(String username) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("Screenshots" + File.separator + username + "_screenshot.png");

        Files.createDirectories(dest.getParentFile().toPath()); // Ensure the folder exists
        Files.copy(src.toPath(), dest.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        test.addScreenCaptureFromPath(dest.getAbsolutePath());
    }

    public void logout() {
        try {
            driver.findElement(By.className("oxd-userdropdown-name")).click();
            Thread.sleep(1000);
            driver.findElement(By.linkText("Logout")).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            test.warning("Logout skipped or failed.");
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}
