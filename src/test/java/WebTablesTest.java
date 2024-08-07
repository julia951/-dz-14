import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class WebTablesTest {

    @Test
    public void seleniumTest() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://demoqa.com/webtables");

        driver.manage().window().maximize();


        driver.findElement(By.id("addNewRecordButton")).click();


        driver.findElement(By.id("firstName")).sendKeys("John");
        driver.findElement(By.id("lastName")).sendKeys("Doe");
        driver.findElement(By.id("userEmail")).sendKeys("john.doe@example.com");
        driver.findElement(By.id("age")).sendKeys("30");
        driver.findElement(By.id("salary")).sendKeys("100000");
        driver.findElement(By.id("department")).sendKeys("Engineering");

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        Thread.sleep(1000);
        boolean recordAdded = driver.findElements(By.xpath("//div[contains(@class, 'rt-tr-group')]"))
                .stream().anyMatch(row -> row.getText().contains("John") && row.getText().contains("Doe"));
        System.out.println("Record added: " + recordAdded);

        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='John']/ancestor::div[contains(@class, 'rt-tr')]/descendant::span[@title='Edit']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
  WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.clear();
        lastNameField.sendKeys("Smith");
        driver.findElement(By.id("submit")).click();

        Thread.sleep(1000);
        boolean recordUpdated = driver.findElements(By.xpath("//div[contains(@class, 'rt-tr-group')]"))
                .stream().anyMatch(row -> row.getText().contains("John") && row.getText().contains("Smith"));
        System.out.println("Record updated: " + recordUpdated);

        driver.quit();
    }
}
