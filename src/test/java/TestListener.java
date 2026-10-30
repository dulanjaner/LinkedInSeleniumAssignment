import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Object testInstance = result.getInstance();

        if (!(testInstance instanceof BaseTest)) {
            System.out.println(
                    "Screenshot skipped: Test does not extend BaseTest."
            );
            return;
        }

        WebDriver driver = ((BaseTest) testInstance).getDriver();

        if (driver == null) {
            System.out.println(
                    "Screenshot skipped: WebDriver is null."
            );
            return;
        }

        try {
            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Path screenshotDirectory = Paths.get("screenshots");
            Files.createDirectories(screenshotDirectory);

            String fileName = result.getName()
                    + "_" + System.currentTimeMillis() + ".png";

            Path destination = screenshotDirectory.resolve(fileName);

            Files.copy(
                    source.toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println(
                    "Failure screenshot saved: "
                            + destination.toAbsolutePath()
            );

        } catch (IOException | RuntimeException e) {
            System.err.println(
                    "Failed to capture screenshot: " + e.getMessage()
            );
        }
    }
}