import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Paths;
import java.util.HashMap;

public class BaseTest {
    protected WebDriver driver;
    protected final String DOWNLOAD_DIRECTORY = Paths.get("src/test/download").toAbsolutePath().toString();
    protected boolean isIncognitoEnabled() {
        return true;
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("download.default_directory", DOWNLOAD_DIRECTORY);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.directory_upgrade", true);
        chromePrefs.put("safebrowsing.enabled", true);
        options.setExperimentalOption("prefs", chromePrefs);
        if (isIncognitoEnabled()) {
            options.addArguments("--incognito");
        }
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}