import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class NotificationMessageTest extends BaseTest {
    private final By CLICK_HERE_LOCATOR = By.linkText("Click here");
    private final By NOTIFICATION_LOCATOR = By.id("flash");

    @Test
    public void notificationMessageTextTest() {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");

        //Проверяем, что текст нотификации содержит "Action successful"
        for (int i = 0; i < 10; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(CLICK_HERE_LOCATOR)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(NOTIFICATION_LOCATOR));
            String notificationText = driver.findElement(NOTIFICATION_LOCATOR).getText();
            softAssert.assertTrue(notificationText.contains("Action successful"));
        }
        softAssert.assertAll();
    }
}
