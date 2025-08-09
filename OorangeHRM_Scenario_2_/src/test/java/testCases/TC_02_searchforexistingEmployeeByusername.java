package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import PageObjects.*;

public class TC_02_searchforexistingEmployeeByusername extends BaseClass {

    Login_Page loginPage;
    Admin_Page adminPage;

    @BeforeClass
    public void setup() {
        launch_Url();
        openURL("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage = new Login_Page(driver);
        adminPage = new  Admin_Page(driver);

        loginPage.enter_user("Admin");
        loginPage.enter_password("admin123");
        loginPage.login_button();
    }

    @Test
    public void searchForExistingEmployee() throws InterruptedException {
        adminPage.goToAdminSection();
        adminPage.searchByUsername("Admin");
        int expectedCount = adminPage.getRecordCountFromText();
        System.out.println(expectedCount);
        int actualCount = adminPage.getDisplayedRowCount();
        System.out.println(actualCount);
        Assert.assertEquals(actualCount, expectedCount, "Mismatch between displayed rows and record count");
        adminPage.refreshPage();
    }
}
