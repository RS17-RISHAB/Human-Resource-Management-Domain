package PageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	
	public WebDriver driver ;
	@BeforeClass
	public void launch_Url() {
		 if (driver == null) { 
		driver  = new ChromeDriver() ;
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
	}
	}
	  public void openURL(String url) {
	        driver.get(url);
	    }
	   
	
	public void login() {
		Login_Page pg  = new Login_Page(driver);
		pg.enter_user("Admin");
		pg.enter_password("admin123");
		pg.login_button();
	}
	
//	public void ManagmentPage() throws InterruptedException {
//		Admin_ManagmentPage pg = new  Admin_ManagmentPage(driver);
//		pg.options();
//		
//	}
	
	
}
