import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ButtonsTest {
    public static void main(String[] args) {
        // Встановлення ChromeDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Відкриваємо сторінку
            driver.get("https://demoqa.com/elements");

            // Натискаємо на "Buttons"
            WebElement buttonsElement = driver.findElement(By.xpath("//span[text()='Buttons']"));
            buttonsElement.click();

            // Клікаємо кнопку "Click Me"
            WebElement clickMeButton = driver.findElement(By.xpath("//button[text()='Click Me']"));
            clickMeButton.click();

            // Зчитуємо текст повідомлення
            WebElement messageElement = driver.findElement(By.id("dynamicClickMessage"));
            String messageText = messageElement.getText();
            System.out.println("Message text: " + messageText);
        } finally {
            // Закриваємо браузер
            driver.quit();
        }
    }
}
