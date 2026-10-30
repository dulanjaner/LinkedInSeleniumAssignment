import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class LoginPageCheck extends BaseTest {

    @Test
    public void checkEmailFields() {
        driver.get("https://www.linkedin.com/login");

        List<WebElement> fields =
                driver.findElements(By.cssSelector("input[type='email']"));

        System.out.println("Email fields found: " + fields.size());

        for (int i = 0; i < fields.size(); i++) {
            System.out.println(
                    "Field " + (i + 1)
                            + " displayed: " + fields.get(i).isDisplayed()
            );
        }
    }
}