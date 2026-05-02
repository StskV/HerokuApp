import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class DropDownTest extends BaseTest {
    private final By dropdownLocator = By.id("dropdown");
    private final List<String> expectedDropdownText = Arrays.asList(
            "Please select an option",
            "Option 1",
            "Option 2"
    );

    @Test
    public void checkDropdownElements() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/dropdown");

        Select select = new Select(driver.findElement(dropdownLocator));
        List<WebElement> dropdownElements = select.getOptions();

        //Проверка соответствия количества дропдаунов и текста
        softAssert.assertEquals(dropdownElements.size(), expectedDropdownText.size());

        //Проверка того, что текст в дропдаунах верный
        for (int i = 0; i < dropdownElements.size(); i++) {
            softAssert.assertEquals(expectedDropdownText.get(i), dropdownElements.get(i).getText());
        }

        //Проверка того, что выбран первый вариант дропдауна
        select.selectByVisibleText("Option 1");
        softAssert.assertEquals(select.getFirstSelectedOption().getText(), expectedDropdownText.get(1));

        //Проверка того, что выбран второй вариант дропдауна
        select.selectByVisibleText("Option 2");
        softAssert.assertEquals(select.getFirstSelectedOption().getText(), expectedDropdownText.get(2));

        softAssert.assertAll();
    }
}
