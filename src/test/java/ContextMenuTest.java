import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class ContextMenuTest extends BaseTest {

    private final By HOTSPOT_LOCATOR = By.id("hot-spot");

    @Test
    public void checkContextMenu() {
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/context_menu");
        WebElement hotspot = driver.findElement(HOTSPOT_LOCATOR);
        action.contextClick(hotspot).perform();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertEquals(alert.getText(), "You selected a context menu", "Context menu test failed");
        alert.accept();
    }
}
