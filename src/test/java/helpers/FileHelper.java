package helpers;

import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.time.Duration;

public class FileHelper {
    public static boolean waitForFileDownload(File file, Duration timeout) {
        Wait<File> fileWait = new FluentWait<>(file)
                .withTimeout(timeout)
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        try {
            return fileWait.until(f -> f.exists() && f.length() > 0 && !f.getName().endsWith(".crdownload"));
        } catch (Exception e) {
            return false;
        }
    }

    public static void deleteFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }
}
