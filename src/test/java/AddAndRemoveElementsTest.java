import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddAndRemoveElementsTest extends BaseTest {
    private final By addButtonLocator = By.xpath("//button[text()='Add Element']");
    private final By deleteButtonLocator = By.xpath("//button[text()='Delete']");

    @Test
    public void checkAddElements() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        //Проверка того, что добавлено 2 элемента Delete
        driver.findElement(addButtonLocator).click();
        driver.findElement(addButtonLocator).click();
        int countDelete = driver.findElements(deleteButtonLocator).size();
        Assert.assertEquals(countDelete, 2);

        //Проверка того, что после удаления остался 1 элемент Delete
        driver.findElement(deleteButtonLocator).click();
        countDelete = driver.findElements(deleteButtonLocator).size();
        Assert.assertEquals(countDelete, 1);
    }
}