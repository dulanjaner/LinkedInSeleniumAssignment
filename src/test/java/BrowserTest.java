import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrowserTest {

    @Test
    public void checkBrowserOpens() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.linkedin.com/login");

            Assert.assertTrue(
                    driver.getCurrentUrl().contains("linkedin.com"),
                    "LinkedIn page did not open"
            );
        } finally {
            driver.quit();
        }
    }
}
