import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

@Listeners(TestListener.class)
public class SearchTest extends BaseTest {

    @Test
    public void searchTest() throws Exception {

        String username = System.getenv("LINKEDIN_USERNAME");
        String password = System.getenv("LINKEDIN_PASSWORD");

        Assert.assertNotNull(
                username,
                "LinkedIn username is not configured"
        );

        Assert.assertFalse(
                username.isBlank(),
                "LinkedIn username is empty"
        );

        Assert.assertNotNull(
                password,
                "LinkedIn password is not configured"
        );

        Assert.assertFalse(
                password.isBlank(),
                "LinkedIn password is empty"
        );

        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();
        loginPage.login(username, password);

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(60)
        );

        wait.until(
                ExpectedConditions.urlContains("/feed")
        );

        SearchPage searchPage = new SearchPage(driver);

        searchPage.search("software testing");

        wait.until(
                ExpectedConditions.urlContains(
                        "/search/results/"
                )
        );

        Assert.assertTrue(
                searchPage.isResultsPageOpened(),
                "LinkedIn search results page did not open"
        );

        Path screenshotFolder = Path.of("screenshots");

        Files.createDirectories(screenshotFolder);

        Path screenshotPath =
                screenshotFolder.resolve("search-results.png");

        Path temporaryScreenshot =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE)
                        .toPath();

        Files.copy(
                temporaryScreenshot,
                screenshotPath,
                StandardCopyOption.REPLACE_EXISTING
        );

        System.out.println(
                "Search results page opened successfully."
        );

        System.out.println(
                "Screenshot saved to: "
                        + screenshotPath.toAbsolutePath()
        );
    }
}