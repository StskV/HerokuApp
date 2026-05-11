import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataTablesTest extends BaseTest {
    private final By firstRowLocator = By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td[position()]");
    private final List<String> expectedFirstRowData = Arrays.asList(
            "Smith",
            "John",
            "jsmith@gmail.com",
            "$50.00",
            "http://www.jsmith.com"
    );

    @Test
    public void checkDataTableValues() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/tables");

        List<WebElement> firstRowElements = driver.findElements(firstRowLocator);
        List <String> firstRowActualText = new ArrayList<>();

        for (WebElement cell: firstRowElements) {
            firstRowActualText.add(cell.getText());
        }

        //Сравниваем данных в первой строке с ожидаемым
        softAssert.assertEquals(firstRowActualText.subList(0,5), expectedFirstRowData);

        softAssert.assertAll();
    }
}
