package steps.filter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Reporter;
import pages.FilterPage;
import steps.BaseSteps;

public class FilterSteps extends BaseSteps {
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
    @Given("User goes to airbnb.com website and clicks filter button")
    public void userGoesToAirbnbComWebsiteAndClicksFilterButton() throws Exception {
        gotoAirbnb(env);
        new FilterPage(driver).openFilter();

    }

    @When("User specifies min price {string} and max price {string}")
    public void userSpecifiesMinPriceAndMaxPrice(String minPrice, String maxPrice)  {
        new FilterPage(driver).setPrice(minPrice,maxPrice);
    }

    @And("User specifies the type {string} of the place")
    public void userSpecifiesTheTypeOfThePlace(String placeType) throws Exception {
        new FilterPage(driver).setPlaceType(placeType);
    }

    @And("User specifies number of rooms {string}, number of beds {string} and number of bathrooms {string}")
    public void userSpecifiesNumberOfRoomsNumberOfBedsAndNumberOfBathrooms(String rooms, String beds, String baths) throws Exception {
        new FilterPage(driver).setRoomsBedsBaths(rooms,beds,baths);
    }

    @And("User selects property type {string}")
    public void userSelectsPropertyType(String propertyType) throws Exception {
        new FilterPage(driver).setPropertyType(propertyType);
    }

    @And("User selects amenities: essentials {string}, features {string}, location {string} and safety {string}")
    public void userSelectsAmenitiesEssentialsFeaturesLocationAndSafety(String essentials, String features, String location, String safety) throws Exception {
        new FilterPage(driver).setAmenities(essentials,features,location,safety);
    }

    @And("User selects booking options: instant book {string} and self-check {string}")
    public void userSelectsBookingOptionsInstantBookAndSelfCheck(String instantBook, String selfCheckk) throws Exception {
        new FilterPage(driver).setBookingOptions(instantBook,selfCheckk);
    }

    @And("User selects accessibility features: guest entrance and parking {string}, bedroom {string}, bathroom {string} and adaptive equipment {string}")
    public void userSelectsAccessibilityFeaturesGuestEntranceAndParkingBedroomBathroomAndAdaptiveEquipment(String entranceParking, String accessBedroom, String accessBaths, String addaptiveEquipment) throws Exception {
        new FilterPage(driver).setAccessibility(entranceParking,accessBedroom,accessBaths,addaptiveEquipment);
    }

    @And("User selects top tier stays superhost {string} and airbnb plus {string}")
    public void userSelectsTopTierStaysSuperhostAndAirbnbPlus(String superHost, String plus) throws Exception {
        new FilterPage(driver).setTopTierStays(superHost,plus);
    }

    @And("User selects host language {string}")
    public void userSelectsHostLanguage(String lang) throws Exception {
        new FilterPage(driver).setHostLanguage(lang);
    }

    @And("User clicks show homes button to  search apartments for specified data")
    public void userClicksShowHomesButtonToSearchApartmentsForSpecifiedData() throws Exception {
        new FilterPage(driver).filterSearch();
    }
}
