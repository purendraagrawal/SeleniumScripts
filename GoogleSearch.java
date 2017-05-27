package SeleniumBasicLearning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GoogleSearch {
	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\puren\\Downloads\\Compressed\\geckodriver-v0.11.1-win64\\geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		WebDriver driver = new FirefoxDriver(capabilities);
		driver.get("http://www.google.co.in");
		driver.findElement(By.name("q")).sendKeys("Purendra Agrawal");
		driver.findElement(By.name("q")).submit();
		driver.close();
	}
}
