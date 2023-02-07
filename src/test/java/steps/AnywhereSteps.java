package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Reporter;
import pages.AnywhereStaysPage;

public class AnywhereSteps extends BaseSteps {
    String browser= Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
    String quit=Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("quit");
    String env=Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    @Before
    public void setup() throws Exception {
        init(browser);
    }
    @After
    public void tearDown(){
        if(quit.equalsIgnoreCase("quit")){
            quit();
        }
    }
    @Given("User navigate to airbnb website")
    public void userNavigateToAirbnbWebsite() throws Exception {
        gotoAirbnb(env);
    }

    @When("User selects region {string} or types a location {string}")
    public void userSelectsRegionOrTypesALocation(String region, String location) throws Exception {
        new AnywhereStaysPage(driver).setRegionLocation(region,location);
    }

    @And("User selects check-in date {string} and check-out date {string}")
    public void userSelectsCheckInDateAndCheckOutDate(String checkin, String checkout) throws Exception {
        new AnywhereStaysPage(driver).setChechInCheckOut(checkin,checkout);
    }

    @And("User adds guests, adults {string} child {string}, infants {string} and pets {string}")
    public void userAddsGuestsAdultsChildInfantsAndPets(String adults, String children, String infants, String pets) throws Exception {
        new AnywhereStaysPage(driver).addGuests(adults,children,infants,pets);
    }

    @And("User clicks search button")
    public void userClicksSearchButton() throws Exception {
        new AnywhereStaysPage(driver).search();
    }
}
