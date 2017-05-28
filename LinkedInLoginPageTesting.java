package SeleniumBasicLearning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedInLoginPageTesting {
	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\puren\\Downloads\\Compressed\\geckodriver-v0.11.1-win64\\geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		WebDriver driver = new FirefoxDriver(capabilities);
		driver.get("https://www.linkedin.com");
		String title = driver.getTitle();
		// Method Verify the title of the page
		verifyTitle(title);
		// Methods verifies the sign in Button of the page
		verifySignInButtonDisables(driver);
		verifySignInButtonEnables(driver);
		invalidUsernameAndPassword(driver);
		driver.close();
	}

	// Verify the title of the Linkedin Website
	public static void verifyTitle(String title) {
		if (title.equals("LinkedIn: Log In or Sign Up"))
			System.out.println("Pass Test Case 1");
		else
			System.out.println("Fail Test Case 1");
	}

	// Verify the sign in button without entering the username and password
	public static void verifySignInButtonDisables(WebDriver driver) {
		String username = driver.findElement(By.id("login-email")).getText();
		String password = driver.findElement(By.id("login-password")).getText();
		if (username.equals("") && password.equals("")) {
			if (!(driver.findElement(By.id("login-submit")).isEnabled()))
				System.out.println("Pass test case 2");
			else
				System.out.println("Fail test case 2");
		} else
			System.out.println("Please clear the values of username or password before test this");
	}

	// Verify the sign in button after entering the username and password
	public static void verifySignInButtonEnables(WebDriver driver) {
		driver.findElement(By.id("login-email")).sendKeys("username");
		driver.findElement(By.id("login-password")).sendKeys("password");
		String username = driver.findElement(By.id("login-email")).getText();
		String password = driver.findElement(By.id("login-password")).getText();
		if (username.equals("") && password.equals("")) {
			if ((driver.findElement(By.id("login-submit")).isEnabled()))
				System.out.println("Pass test case 3");
			else
				System.out.println("Fail test case 3");
		} else
			System.out.println("Please clear the values of username or password before test this");
	}

	public static void invalidUsernameAndPassword(WebDriver driver) {
		driver.findElement(By.id("login-submit")).click();
		WebElement myDynamicElement = new WebDriverWait(driver, 10)
				.until(ExpectedConditions.presenceOfElementLocated(By.id("global-alert-queue")));
		if (driver.getCurrentUrl().equals("https://www.linkedin.com/uas/login-submit"))
			System.out.println("Invalid Username and password Validation Pass");
		else
			System.out.println("Fail Test Case 4 ");
	}
}