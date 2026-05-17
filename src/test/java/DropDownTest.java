import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class DropDownTest extends BaseTest {
    private final By DROPDOWN_LOCATOR = By.id("dropdown");
    private final List<String> EXPECTED_DROPDOWN_TEXT = Arrays.asList(
            "Please select an option",
            "Option 1",
            "Option 2"
    );

    @Test
    public void checkDropdownElements() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/dropdown");

        Select select = new Select(driver.findElement(DROPDOWN_LOCATOR));
        List<WebElement> dropdownElements = select.getOptions();

        //Проверка соответствия количества дропдаунов и текста
        softAssert.assertEquals(dropdownElements.size(), EXPECTED_DROPDOWN_TEXT.size());

        //Проверка того, что текст в дропдаунах верный
        for (int i = 0; i < dropdownElements.size(); i++) {
            softAssert.assertEquals(EXPECTED_DROPDOWN_TEXT.get(i), dropdownElements.get(i).getText());
        }

        //Проверка того, что выбран первый вариант дропдауна
        select.selectByVisibleText("Option 1");
        softAssert.assertEquals(select.getFirstSelectedOption().getText(), EXPECTED_DROPDOWN_TEXT.get(1));

        //Проверка того, что выбран второй вариант дропдауна
        select.selectByVisibleText("Option 2");
        softAssert.assertEquals(select.getFirstSelectedOption().getText(), EXPECTED_DROPDOWN_TEXT.get(2));

        softAssert.assertAll();
    }
}
