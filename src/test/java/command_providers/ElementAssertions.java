package command_providers;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementAssertions {
    WebDriver driver;
    private By locator;

    public ElementAssertions(WebDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
    }

    public ElementAssertions elementIsDisplayed() {
        boolean displayed = driver.findElement(locator).isDisplayed();
        Assert.assertTrue("The expected element does not exist", displayed);
        return this;
    }
}
