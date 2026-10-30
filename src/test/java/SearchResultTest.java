import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

import java.time.Duration;
import java.util.List;

@Listeners(TestListener.class)
public class SearchResultTest extends BaseTest {

    @Test
    public void openFirstSearchResultTest() {

        String username =
                System.getenv("LINKEDIN_USERNAME");

        String password =
                System.getenv("LINKEDIN_PASSWORD");

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

        /*
         * First try to find a job result.
         */
        By jobResults = By.cssSelector(
                "a[href*='/jobs/view/'], " +
                        "a[href*='/jobs/search-results/']"
        );

        WebElement firstResult = wait.until(d -> {

            List<WebElement> elements =
                    d.findElements(jobResults);

            for (WebElement element : elements) {

                if (element.isDisplayed()
                        && element.isEnabled()
                        && element.getAttribute("href") != null) {

                    return element;
                }
            }

            return null;
        });

        String resultUrl = firstResult.getAttribute("href");

        Assert.assertNotNull(
                resultUrl,
                "The first job result does not have a URL"
        );

        String originalUrl = driver.getCurrentUrl();

        firstResult.click();

        wait.until(d ->
                !d.getCurrentUrl().equals(originalUrl)
                        || d.getCurrentUrl().contains("/jobs/view/")
        );

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(
                currentUrl.contains("/jobs/view/")
                        || !currentUrl.equals(originalUrl),
                "The selected search result did not open"
        );

        System.out.println(
                "Selected result URL: " + resultUrl
        );

        System.out.println(
                "Current browser URL: " + currentUrl
        );
    }
}