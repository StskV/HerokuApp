import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CheckBoxeTest extends BaseTest {
    private final By checkboxLocator = By.cssSelector("[type=checkbox]");

    @Test
    public void checkUncheckedAndCheckedCheckboxes() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        List<WebElement> checkboxes = driver.findElements(checkboxLocator);
        WebElement firstCheckbox = checkboxes.get(0);
        WebElement secondCheckbox = checkboxes.get(1);

        //Проверка того, что первый чекбокс не выбран
        softAssert.assertFalse(firstCheckbox.isSelected());

        //Проверка того, что первый чекбокс выбран
        firstCheckbox.click();
        softAssert.assertTrue(firstCheckbox.isSelected());

        //Проверка того, что второй чекбокс выбран
        softAssert.assertTrue(secondCheckbox.isSelected());

        //Проверка того, что второй чекбокс не выбран
        secondCheckbox.click();
        softAssert.assertFalse(secondCheckbox.isSelected());

        softAssert.assertAll();
    }
}
