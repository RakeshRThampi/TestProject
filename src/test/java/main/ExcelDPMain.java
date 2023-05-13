package main;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import tests.LoginPage;
import utils.DataProviders;

public class ExcelDPMain {
	static WebDriver driver;
	static LoginPage login;
	public static DataProviders dp;

	@BeforeTest
	@Parameters({"url"})
	public void initBrowser(String url) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		System.out.println("ExcelDPMain::BeforeTest:: Browser Initialized");
		login = new LoginPage(driver);
		dp = new DataProviders();
	}
	public static WebDriver getDriver() {
		if(driver != null) {
			return driver;
		}	else	{
			System.out.println("ExcelDPMain:: Web driver is null");	};
			return null;
	}
	@Test(priority=0)
	public void checkTitle() {
		dp.setExcelFileAndSheet( "F:\\IDEs\\Eclipse\\eclipse-workspace\\ExcelPlayDP\\src\\test\\resources\\testdata\\users.xlsx", "users");
		String expTitle = "Swag Labs";
		Assert.assertEquals(login.getTitle(), expTitle);
		System.out.println("ExcelDPMain::checkTitle:: Actual : " + login.getTitle() + ", Expected : " + expTitle);
	}

	@Test(priority=1, dataProviderClass = DataProviders.class, dataProvider = "sauceusers")
	public void checkUsers( @Optional String slNum, String userName, String password,  @Optional String stats) throws InterruptedException {
		login.inputUser(userName, password);
	}
	
	@Test(enabled = false, priority=2, dataProviderClass = DataProviders.class, dataProvider="rrt")
	public void sampleDP(String name) {
		System.out.println("Name = "+name);
	}

	@AfterTest
	public void tearDown() {
		if(driver != null) {
			driver.quit();		}
	} 
}
