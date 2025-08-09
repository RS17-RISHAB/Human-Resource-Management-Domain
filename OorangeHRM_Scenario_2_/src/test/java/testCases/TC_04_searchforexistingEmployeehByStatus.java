package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import PageObjects.*;

public class TC_04_searchforexistingEmployeehByStatus extends BaseClass {

    Login_Page loginPage;
    Admin_Page adminPage;

    @BeforeClass
    public void setup() {
        launch_Url();
        openURL("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage = new Login_Page(driver);
        adminPage = new Admin_Page(driver);

        loginPage.enter_user("Admin");
        loginPage.enter_password("admin123");
        loginPage.login_button();
    }

    @Test
    public void searchByStatus() {
        adminPage.goToAdminSection();

        // Choose one: Either mouse-click-based or Actions-keyboard-based
        adminPage.searchByUserStatus("Enabled"); // Recommended
        // adminPage.searchByUserStatusWithActions(); // Optional version with Actions

        int expected = adminPage.getRecordCountFromText();
        System.out.println("Expected: " + expected);
        int actual = adminPage.getDisplayedRowCount();
        System.out.println("Actual: " + actual);
        Assert.assertEquals(actual, expected, "Mismatch in status record count");
        adminPage.refreshPage();
    }
}
