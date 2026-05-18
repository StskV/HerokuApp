import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class HoversTest extends BaseTest {

    private final By PROFILE_LOCATOR = By.cssSelector(".figure");
    private final By USERNAME_LOCATOR = By.cssSelector(".figcaption h5");
    private final By PROFILE_LINK_LOCATOR = By.cssSelector(".figcaption a");
    private final By NOT_FOUND_TEXT_LOCATOR = By.cssSelector("h1");

    @Test
    public void checkHovers() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions action = new Actions(driver);
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/hovers");

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PROFILE_LOCATOR));
        int profilesCount = driver.findElements(PROFILE_LOCATOR).size();

        for (int i = 0; i < profilesCount; i++) {
            List<WebElement> profiles = driver.findElements(PROFILE_LOCATOR);
            WebElement profile = profiles.get(i);
            action.moveToElement(profile).perform();
            String expectedName = "name: user" + (i + 1);
            WebElement nameElement = profile.findElement(USERNAME_LOCATOR);
            wait.until(ExpectedConditions.visibilityOf(nameElement));
            softAssert.assertEquals(nameElement.getText(), expectedName, "Profile name test failed for profile " + (i + 1));
            WebElement profileLink = profile.findElement(PROFILE_LINK_LOCATOR);
            profileLink.click();
            String actualText = wait.until(ExpectedConditions.visibilityOfElementLocated(NOT_FOUND_TEXT_LOCATOR)).getText();
            softAssert.assertEquals(actualText, "Not Found", "Not found text is not displayed for profile " + (i + 1));
            driver.navigate().back();
            wait.until(ExpectedConditions.visibilityOfElementLocated(PROFILE_LOCATOR));
        }
        softAssert.assertAll();
    }
}
