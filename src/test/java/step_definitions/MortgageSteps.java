package step_definitions;

import command_providers.ActOn;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import page_objects.Home;
import page_objects.RealApr;
import utilities.ReadConfigFiles;

import java.util.List;
import java.util.Map;

public class MortgageSteps {
    private static Logger LOGGER = LogManager.getLogger(MortgageSteps.class);
    WebDriver driver = Hooks.driver;

    @Given("^a user is in the mortgage calculator home page$")
    public void a_user_is_in_the_mortgage_calculator_home_page() {
        ActOn.browser(driver).openBrowser(ReadConfigFiles.getPropertyValues("MortgageAppURL"));
        LOGGER.info("User is in the Mortgage Calculator Home Page");
    }

    @And("^user navigate to Real Apr page$")
    public void user_navigate_to_real_apr_page() {
        new Home(driver)
                .mouseHoverToRates()
                .navigateToRealApr()
                .waitForPageToLoad();
        LOGGER.info("Navigated to Real Apr Page");
    }

    @When("^user clicks on Calculate button upon entering the data$")
    public void user_clicks_on_calculate_button_upon_entering_the_data(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        for (Map<String, String> cells: data) {
            new RealApr(driver)
                    .typeHomePrice(cells.get("HomePrice"))
                    .typeDownPayment(cells.get("DownPayment"))
                    .typeInterestRate(cells.get("InterestRate"))
                    .clickOnCalculateButton();
        }
        LOGGER.info("Real APR rate is calculated upon entering the data");
    }

    @Then("^the real apr rate is \"(.+?)\"$")
    public void the_real_apr_rate_is(String realApr) {
        new RealApr(driver)
                .validateRealAprRate(realApr);
        LOGGER.info(String.format("Real APR rate: %s is validated", realApr));
    }
}
