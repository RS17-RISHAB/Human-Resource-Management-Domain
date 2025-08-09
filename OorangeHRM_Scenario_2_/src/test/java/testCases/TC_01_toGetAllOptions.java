package testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.Login_Page;

public class TC_01_toGetAllOptions {
	
	public WebDriver driver ;
	@BeforeClass
	public void setup() {
		driver  = new ChromeDriver() ;
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		
	}
	@Test
	public void  options() throws InterruptedException {
		
		
		Login_Page page = new Login_Page(driver) ;
		page.enter_user("Admin");
		page.enter_password("admin123");
		page.login_button();
		Thread.sleep(5000);
		List<WebElement> options = driver.findElements(By.xpath("//div/ul/li[@class='oxd-main-menu-item-wrapper']"));
		System.out.println("Total number of Options are " + options.size());
		for (WebElement option : options) {
		    String name = option.getText();
		    System.out.println(name);
		}
	
		for( int i = 0 ;i< options.size(); i++) {
			String names = options.get(i).getText() ;
			if (names.equalsIgnoreCase("Admin")) {
				options.get(i).click();  
				break;
		    }
		
		}
		
		
	}

	
}
