package step_definitions;

import command_providers.ActOn;
import command_providers.AssertThat;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ReadConfigFiles;

import java.util.List;
import java.util.Map;

public class LoginSteps {
    private static final By FullName = By.id("name");
    private static final By Password = By.id("password");
    private static final By Login = By.id("login");
    private static final By Logout = By.id("logout");
    private static final By InvalidPassword = By.xpath("//*[@id='pageLogin']/form//div[text()='Password is invalid']");
    private static Logger LOGGER = LogManager.getLogger(LoginSteps.class);
    WebDriver driver = Hooks.driver;

    @Given("^a user is on the login page$")
    public void navigateToLoginPage() {
        ActOn.browser(driver).openBrowser(ReadConfigFiles.getPropertyValues("TestAppURL"));
        LOGGER.info("User is in the Login Page");
    }

    @When("^user enters username \"(.+?)\" and password \"(.+?)\"$")
    public void enterUserCredentials(String userName, String password) {
        ActOn.element(driver, FullName).setValue(userName);
        ActOn.element(driver, Password).setValue(password);
        LOGGER.info("User has entered credentials");
    }

    @When("^user click on login button upon entering credentials$")
    public void userClickOnLoginUponEnteringCredentials(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        for (Map<String, String> cells: data) {
            ActOn.element(driver, FullName).setValue(cells.get("username"));
            ActOn.element(driver, Password).setValue(cells.get("password"));
            LOGGER.info("User has entered credentials");

            ActOn.element(driver, Login).click();
            LOGGER.info("User clicked on the login button");
        }
    }

    @And("^click on the login button$")
    public void clickOnLogin() {
        ActOn.element(driver, Login).click();
        LOGGER.info("User clicked on the login button");
    }

    @Then("^user is navigated to home page$")
    public void validateUserIsLoggedInSuccessfully() {
        AssertThat.elementAssertions(driver, Logout).elementIsDisplayed();
        LOGGER.info("User is on the Home Page");
    }

    @Then("^user is failed to login$")
    public void validateUserIsFailedToLogin() {
        AssertThat.elementAssertions(driver, InvalidPassword).elementIsDisplayed();
        LOGGER.info("User is still on the login page");
    }
}
