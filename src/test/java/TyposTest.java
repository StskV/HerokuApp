import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TyposTest extends BaseTest {
    private final By PARAGRAPH_LOCATOR = By.tagName("p");
    private final String EXPECTED_TEXT = "Sometimes you'll see a typo, other times you won't.";

    @Test
    public void checkTypos() {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/typos");

        //Проверка того, что текст параграфа Sometimes you'll see a typo, other times you won't.
        for (int i = 0; i < 10; i++) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PARAGRAPH_LOCATOR));
            WebElement secondParagraph = driver.findElements(PARAGRAPH_LOCATOR).get(1);
            softAssert.assertEquals(secondParagraph.getText(), EXPECTED_TEXT);
            driver.navigate().refresh();
        }
        softAssert.assertAll();
    }
}
