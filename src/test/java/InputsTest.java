import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InputsTest extends BaseTest {
    private final By inputsLocator = By.tagName("input");

    @Test
    public void checkInputsWithNonNumericValues() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement inputsElement = driver.findElement(inputsLocator);

        //Проверка того, что поле не принимает буквенные значения
        inputsElement.sendKeys("q");
        String actualValue = inputsElement.getAttribute("value");
        Assert.assertEquals(actualValue, "");
    }

    @Test
    public void checkInputsWithNumericValues() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement inputsElement = driver.findElement(inputsLocator);

        //Проверка того, что поле принимает численные значения
        inputsElement.sendKeys("10");
        String actualValue = inputsElement.getAttribute("value");
        Assert.assertEquals(actualValue, "10");

        //Проверка того, что при использовании arrow up число увеличилось
        inputsElement.sendKeys(Keys.ARROW_UP);
        actualValue = inputsElement.getAttribute("value");
        Assert.assertEquals(actualValue, "11");

        //Проверка того, что при использовании arrow down число уменьшилось
        inputsElement.sendKeys(Keys.ARROW_DOWN);
        actualValue = inputsElement.getAttribute("value");
        Assert.assertEquals(actualValue, "10");
    }
}
