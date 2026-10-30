import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By searchInput = By.cssSelector(
            "input[data-testid='typeahead-input'], " +
                    "input[placeholder='Search'], " +
                    "input[role='combobox']"
    );

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(30)
        );

        this.wait.ignoring(
                StaleElementReferenceException.class
        );
    }

    public void search(String searchTerm) {

        wait.until(d -> {
            try {
                for (WebElement element :
                        d.findElements(searchInput)) {

                    if (element.isDisplayed()
                            && element.isEnabled()) {

                        element.click();

                        element.sendKeys(
                                Keys.chord(Keys.CONTROL, "a")
                        );

                        element.sendKeys(searchTerm);
                        element.sendKeys(Keys.ENTER);

                        return true;
                    }
                }

                return false;

            } catch (StaleElementReferenceException e) {
                return false;
            }
        });

        wait.until(d ->
                d.getCurrentUrl().contains("/search/results/")
        );
    }

    public boolean isResultsPageOpened() {
        return driver.getCurrentUrl()
                .contains("/search/results/");
    }
}