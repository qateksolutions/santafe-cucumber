package utilities;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenCapture {
    private static final Logger LOGGER = LogManager.getLogger(ScreenCapture.class);

    public static void getScreenShot(WebDriver driver) {
        //Generating the filename with timestamp
        String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());

        File screenshotFile =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File("screenshot/" + fileName + ".png"));
        } catch (Exception e) {
            LOGGER.error("Screenshot Exception is: " + e.getMessage());
        }
    }
}
