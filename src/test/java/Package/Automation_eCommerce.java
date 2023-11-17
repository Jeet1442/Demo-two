package Package;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import PageObjects.LandingPage;

public class Automation_eCommerce {

	@Test
	public void E_Comerce() throws InterruptedException {
		
		//WebDriver driver= new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\debjeet\\eclipse-workspace\\E2E_Udemy\\chromedriver.exe");
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--remote-allow-origins=*");
		
		WebDriver driver=new ChromeDriver(opt);
		driver.manage().window().maximize();
		
		LandingPage landing = new LandingPage(driver);
		
		landing.goTO();
		landing.LoginApplication("saha.debjeet123@gmail.com", "Debjeet123");
		
		
		Thread.sleep(5000);
		List<WebElement> items = driver.findElements(By.cssSelector(".col-md-6"));
		
		for(int i=0; i<items.size();i++) {
			String name = driver.findElements(By.cssSelector("b")).get(i+1).getText();
			System.out.println(name);
			
			if(name.equalsIgnoreCase("ZARA COAT 3")) {
				driver.findElement(By.cssSelector(".card-body button:last-of-type")).click();
				break;
			}
		}
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(7));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@routerlink,'cart')]")));
		driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]")).click();
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'ta-item')][2]")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		
		driver.findElement(By.cssSelector(".action__submit ")).click();
		
		

	}

}
