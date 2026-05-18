import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddAndRemoveElementsTest extends BaseTest {
    private final By ADD_BUTTON_LOCATOR = By.xpath("//button[text()='Add Element']");
    private final By DELETE_BUTTON_LOCATOR = By.xpath("//button[text()='Delete']");

    @Test
    public void checkAddElements() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        //Проверка того, что добавлено 2 элемента Delete
        driver.findElement(ADD_BUTTON_LOCATOR).click();
        driver.findElement(ADD_BUTTON_LOCATOR).click();
        int countDelete = driver.findElements(DELETE_BUTTON_LOCATOR).size();
        Assert.assertEquals(countDelete, 2);

        //Проверка того, что после удаления остался 1 элемент Delete
        driver.findElement(DELETE_BUTTON_LOCATOR).click();
        countDelete = driver.findElements(DELETE_BUTTON_LOCATOR).size();
        Assert.assertEquals(countDelete, 1);
    }
}