package command_providers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitFor {
    private static final Logger LOGGER = LogManager.getLogger(WaitFor.class);

    private WebDriver driver;
    private By locator;

    public WaitFor(WebDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
    }

    public WaitFor waitForElementToBeDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this;
    }

    public WaitFor fixWait(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            LOGGER.error("Wait Exception is: " + e.getMessage());
        }
        return this;
    }
}
