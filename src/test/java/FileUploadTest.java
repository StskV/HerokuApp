import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class FileUploadTest extends BaseTest {

    private final By FILE_UPLOAD_BUTTON = By.id("file-upload");
    private final String FILE_PATH = Paths.get("src/test/resources/fileUpload.txt").toAbsolutePath().toString();
    private final By SUBMIT_BUTTON = By.id("file-submit");
    private final By UPLOADED_FILE = By.id("uploaded-files");
    private final String EXPECTED_FILE_NAME = "fileUpload.txt";

    @Test
    public void checkFileUpload() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/upload");
        wait.until(ExpectedConditions.presenceOfElementLocated(FILE_UPLOAD_BUTTON)).sendKeys(FILE_PATH);
        driver.findElement(SUBMIT_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(UPLOADED_FILE));
        String actualFileName = driver.findElement(UPLOADED_FILE).getText();
        assertEquals(actualFileName, EXPECTED_FILE_NAME, "File upload test failed");
    }
}
