import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NotificationMessageTest extends BaseTest {
    private final By clickHereLocator = By.linkText("Click here");
    private final By notificationLocator = By.id("flash");

    @Test
    public void notificationMessageTextTest() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");

        //Проверяем, что текст нотификации содержит "Action successful"
        for (int i = 0; i < 10; i++){
            driver.findElement(clickHereLocator).click();
            String notificationText = driver.findElement(notificationLocator).getText();
            softAssert.assertTrue(notificationText.contains("Action successful"));
        }

        softAssert.assertAll();
    }
}
