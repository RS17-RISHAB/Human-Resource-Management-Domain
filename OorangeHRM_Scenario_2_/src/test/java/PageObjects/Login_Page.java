package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_Page extends BasePage {

	public Login_Page(WebDriver driver) {
		super(driver);
		
	}

@FindBy(xpath="//input[@placeholder='Username']")
WebElement username ;

@FindBy(xpath="//input[@placeholder='Password']")
WebElement password ;


@FindBy(xpath="//button[normalize-space()='Login']")
WebElement login_button ;


public void enter_user( String name ) {
	
	username.sendKeys(name);
}

public void enter_password( String pass ) {
	
	password.sendKeys(pass);
}

public void login_button() {
	
	login_button.click();
}

}
