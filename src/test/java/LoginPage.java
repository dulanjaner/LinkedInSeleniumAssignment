import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By usernameField = By.cssSelector("input[type='email']");
    private final By passwordField = By.cssSelector("input[type='password']");
    private final By signInButton =
            By.xpath("//button[normalize-space(.)='Sign in']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void open() {
        driver.get("https://www.linkedin.com/login");
    }

    public void login(String username, String password) {
        var emailInput = wait.until(driver ->
                driver.findElements(usernameField).stream()
                        .filter(element -> element.isDisplayed())
                        .findFirst()
                        .orElse(null)
        );
        emailInput.clear();
        emailInput.sendKeys(username);

        var passwordInput = wait.until(driver ->
                driver.findElements(passwordField).stream()
                        .filter(element -> element.isDisplayed())
                        .findFirst()
                        .orElse(null)
        );
        passwordInput.clear();
        passwordInput.sendKeys(password);

        var visibleSignIn = wait.until(driver ->
                driver.findElements(signInButton).stream()
                        .filter(button -> button.isDisplayed() && button.isEnabled())
                        .findFirst()
                        .orElse(null)
        );
        visibleSignIn.click();
    }
}
