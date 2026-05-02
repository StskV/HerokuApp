import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TyposTest extends BaseTest{
    private final By paragraphLocator = By.tagName("p");
    private final String expectedText = "Sometimes you'll see a typo, other times you won't.";

    @Test
    public void checkTypos() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/typos");

        //Проверка того, что текст параграфа Sometimes you'll see a typo, other times you won't.
        for (int i = 0; i < 10; i++) {
            WebElement secondParagraph = driver.findElements(paragraphLocator).get(1);
            softAssert.assertEquals(secondParagraph.getText(), expectedText);
            driver.navigate().refresh();
        }

        softAssert.assertAll();
    }
}
