package testNgData;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import utils.ExcelUtil;

public class LoginTestTest {

private WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\anna.zhuravleva\\Documents\\Alrquitectura\\WebDrivers\\Chrome Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.freecrm.com/");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getDataFromExcel(){
		Object data[][] = ExcelUtil.getTestData("login");
		return data;
	}
	
	@Test(dataProvider = "getDataFromExcel")
	public void loginTest(String username, String password) {
		driver.findElement(By.xpath("//input[@name='username' and @type='text']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password' and @type='password']")).sendKeys(password);
		//login button
		WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));
		loginButton.submit();
	}
}
