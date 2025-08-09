package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import PageObjects.*;
import org.testng.Assert;

public class TC_03_searchforexistingEmployeeByRole extends BaseClass {

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
    public void searchByRole() {
        adminPage.goToAdminSection();
        adminPage.searchByUserRole("Admin");

        int expectedCount = adminPage.getRecordCountFromText();
        System.out.println("Expected count: " + expectedCount);
        int actualCount = adminPage.getDisplayedRowCount();
        System.out.println("Actual count: " + actualCount);
        Assert.assertEquals(actualCount, expectedCount, "Mismatch between displayed rows and record count");
        adminPage.refreshPage();
    }
}
