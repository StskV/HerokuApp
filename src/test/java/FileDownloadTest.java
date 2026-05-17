import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static helpers.FileHelper.deleteFile;
import static helpers.FileHelper.waitForFileDownload;
import static org.testng.Assert.assertTrue;

public class FileDownloadTest extends BaseTest {

    private final By DOWNLOAD_LINKS_LOCATOR = By.cssSelector(".example a[href*='download']");

    @Override
    protected boolean isIncognitoEnabled() {
        return false;
    }

    @Test
    public void checkFileDownload() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/download");
        List<WebElement> downloadLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DOWNLOAD_LINKS_LOCATOR));
        WebElement firstLink = downloadLinks.get(0);
        String firstLinkName = firstLink.getText().trim();
        firstLink.click();
        File downloadedFile = new File(DOWNLOAD_DIRECTORY + File.separator + firstLinkName);
        boolean isDownloaded = waitForFileDownload(downloadedFile, Duration.ofSeconds(15));
        assertTrue(isDownloaded, "Downloaded file is not found");
        deleteFile(downloadedFile);
    }
}

