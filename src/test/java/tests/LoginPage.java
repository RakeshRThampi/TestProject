package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import actions.WebActions;

public class LoginPage {
	By byTitle = By.xpath("//div[@class='login_logo']");
	By byUserName = By.id("user-name");
	By byPassword = By.id("password");
	
	private WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getTitle() {
		return driver.findElement(byTitle).getText();
	}
	
	public void inputUser(String userName, String password) throws InterruptedException {
		WebActions.writeText(byUserName, userName);
		WebActions.writeText(byPassword, password);
		Thread.sleep(Duration.ofSeconds(1));
		WebActions.clear(byUserName);
		WebActions.clear(byPassword);
	}
}
