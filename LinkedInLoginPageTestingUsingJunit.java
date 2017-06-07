package SeleniumBasicLearning.SeleniumBasicLearning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkedInLoginPageTestingUsingJunit {
	public static WebDriver driver;

	@Test(priority=1)
	public static void launchBrowser() {
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\puren\\Downloads\\Compressed\\geckodriver-v0.11.1-win64\\geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		driver = new FirefoxDriver(capabilities);
	}

	// Verify the title of the Linkedin Website
	@Test(priority=2)
	public static void verifyTitle() {
		driver.get("https://www.linkedin.com");
		String title = driver.getTitle();
		Assert.assertEquals("LinkedIn: Log In or Sign Up", title);
	}

	@Test(priority=3)
	// Verify the sign in button without entering the username and password
	public static void verifySignInButtonDisables() {
		String username = driver.findElement(By.id("login-email")).getText();
		String password = driver.findElement(By.id("login-password")).getText();
		if (username.equals("") || password.equals(""))
			Assert.assertEquals(false, driver.findElement(By.id("login-submit")).isEnabled());
	}

	// Verify the sign in button after entering the username and password
	@Test(priority=4)
	public static void verifySignInButtonEnables() {
		driver.findElement(By.id("login-email")).sendKeys("username");
		driver.findElement(By.id("login-password")).sendKeys("password");
		String username = driver.findElement(By.id("login-email")).getText();
		String password = driver.findElement(By.id("login-password")).getText();
		if (username.equals("") && password.equals("")) {
			if ((driver.findElement(By.id("login-submit")).isEnabled()))
				Assert.assertEquals(true, driver.findElement(By.id("login-submit")).isEnabled());
		}
	}

	@Test(priority=5)
	public static void invalidUsernameAndPassword() {
		driver.findElement(By.id("login-submit")).click();
		WebElement myDynamicElement = new WebDriverWait(driver, 10)
				.until(ExpectedConditions.presenceOfElementLocated(By.id("global-alert-queue")));
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals("https://www.linkedin.com/uas/login-submit", currentURL);
	}
}