import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class DynamicControlsTest extends BaseTest {

    private final By REMOVE_BUTTON = By.xpath("//button[text()='Remove']");
    private final By MESSAGE_LOCATOR = By.id("message");
    private final By CHECKBOX_LOCATOR = By.cssSelector("input[type='checkbox']");
    private final By INPUT_FIELD_LOCATOR = By.cssSelector("#input-example input");
    private final By ENABLE_BUTTON = By.xpath("//button[text()='Enable']");
    private final String REMOVED_TEXT = "It's gone!";
    private final String ENABLED_TEXT = "It's enabled!";


    @Test
    public void checkDynamicControls() {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.findElement(REMOVE_BUTTON).click();
        wait.until(ExpectedConditions.textToBe(MESSAGE_LOCATOR, REMOVED_TEXT));
        softAssert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(CHECKBOX_LOCATOR)), "Checkbox is still displayed");
        WebElement inputField = driver.findElement(INPUT_FIELD_LOCATOR);
        softAssert.assertFalse(inputField.isEnabled(), "Input is not disabled");
        driver.findElement(ENABLE_BUTTON).click();
        wait.until(ExpectedConditions.textToBe(MESSAGE_LOCATOR, ENABLED_TEXT));
        softAssert.assertTrue(inputField.isEnabled(), "Input is not enabled");
        softAssert.assertAll();
    }
}
