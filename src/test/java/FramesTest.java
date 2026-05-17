import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class FramesTest extends BaseTest {

    private final By TEXT_AREA_LOCATOR = By.id("tinymce");
    private final String EXPECTED_TEXT = "Your content goes here.";

    @Test
    public void checkIframeText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/iframe");
        driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));
        boolean isTextCorrect = wait.until(ExpectedConditions.textToBePresentInElementLocated(TEXT_AREA_LOCATOR, EXPECTED_TEXT));
        assertTrue(isTextCorrect, "iFrame text test failed");
        driver.switchTo().defaultContent();
    }
}