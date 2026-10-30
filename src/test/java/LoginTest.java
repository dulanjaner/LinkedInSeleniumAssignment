import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
        String username = System.getenv("LINKEDIN_USERNAME");
        String password = System.getenv("LINKEDIN_PASSWORD");

        Assert.assertNotNull(username, "LinkedIn username is not configured");
        Assert.assertNotNull(password, "LinkedIn password is not configured");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(username, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        boolean loggedIn = wait.until(
                ExpectedConditions.urlContains("/feed")
        );

        Assert.assertTrue(loggedIn, "LinkedIn feed page did not open");

    }
        @Test
        public void invalidLoginTest() {
            String username = System.getenv("LINKEDIN_USERNAME");

            Assert.assertNotNull(username, "LinkedIn username is not configured");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.open();
            loginPage.login(username, "InvalidPasswordForTest123!");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            var errorMessage = wait.until(driver ->
                    driver.findElements(org.openqa.selenium.By.xpath(
                                    "//*[not(*) and contains(" +
                                            "translate(normalize-space(.), " +
                                            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), " +
                                            "'wrong email or password')]"
                            )).stream()
                            .filter(element -> element.isDisplayed())
                            .findFirst()
                            .orElse(null)
            );

            Assert.assertTrue(errorMessage.isDisplayed(),
                    "Login error message was not displayed");
        }
    }
