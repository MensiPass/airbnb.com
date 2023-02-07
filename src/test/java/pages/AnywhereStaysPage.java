package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class AnywhereStaysPage extends BasePage {
    public AnywhereStaysPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath ="//div[@data-testid='little-search']/button/div[text()='Anywhere']/parent::button")
    WebElement anywhereButton;
    @FindBy(xpath ="//div[@id='locationInspirationsSectionID']//span[text()='Southeast Asia']//parent::button//div")
    WebElement regionField;
    @FindBy(xpath ="//button[@aria-label='Move forward to switch to the next month.']")
    WebElement nextArrowCalendar;
    String date = "//div[@data-testid='calendar-day-$$']"; //date to select
    @FindBy(xpath = "//div[@aria-label='Calendar']") //dropdown for calendar container
    List<WebElement> calendar;
    @FindBy(xpath = "//div[@data-testid='structured-search-input-field-split-dates-0']")
    WebElement dateInput; //input field for checkin date,click to open calendar
    @FindBy(css = "[id='bigsearch-query-location-input']") //location input field
    WebElement locationInput;
    String destinationLocation1 = "//div[@id='bigsearch-query-location-suggestion-0']//div[text()='$$']";//first recommendation
    String destinationLocation2 = "//div[@id='bigsearch-query-location-suggestion-1']//div[text()='$$']";//second recommendation
    @FindBy (xpath="//div[@data-testid='structured-search-input-field-guests-button']")
    WebElement guestsInput;
    @FindBy(xpath = "//button[@data-testid='stepper-adults-increase-button']")
    WebElement adultsAdd;
    @FindBy(xpath = "//button[@data-testid='stepper-children-increase-button']")
    WebElement childrenAdd;
    @FindBy(xpath = "//button[@data-testid='stepper-infants-increase-button']")
    WebElement infantsAdd;
    @FindBy(xpath = "//button[@data-testid='stepper-pets-increase-button']")
    WebElement petsAdd;
    @FindBy(xpath = "//button[@data-testid='structured-search-input-search-button']")
    WebElement searchButton;
    @FindBy(xpath = "//div[@data-testid='little-search']/button[1]/div")
    String searchResults;


    public void selectRegion(String region) throws Exception {
        click(anywhereButton);
        click(regionField);
    }
    public void setRegionLocation(String region, String location) throws Exception {
        if(region!=null || !(region.isEmpty())) {
            selectRegion(region);
        }
        else if(location!=null || !(location.isEmpty())) {
            enterLocation(location);
            selectRecommendedLocation(location);
        }
    }
    public void enterLocation(String location) throws Exception {
        typeText(locationInput, location, "Location Input");
        Thread.sleep(1000);
    }
    public void selectRecommendedLocation(String location) throws Exception {
        if (elementExistsByXpath(updateXpathValue(destinationLocation1, location))) {
            click(driver.findElement(By.xpath(updateXpathValue(destinationLocation1, location))), "Location Recommendation 1 - " + location);
        } else {
            click(driver.findElement(By.xpath(updateXpathValue(destinationLocation2, location))), "Location Recommendation 2 - " + location);
        }
    }
    public void setChechInCheckOut(String checkin, String checkout) throws Exception {
        openCalendar();

        String checkInXpath = updateXpathValue(date, checkin);
        String checkOutXpath = updateXpathValue(date, checkout);

        while (true) {
            if (elementExistsByXpath(checkInXpath)) {
                driver.findElement(By.xpath(checkInXpath)).click();
                break;
            } else {
                clickCalendarNext();
            }
        }

        while (true) {
            if (elementExistsByXpath(checkOutXpath)) {
                if ((driver.findElement(By.xpath(checkOutXpath))).isDisplayed()) {
                    driver.findElement(By.xpath(checkOutXpath)).click();
                    break;
                }
                else {

                        clickCalendarNext();
                    }
            }
            else {
                clickCalendarNext();
            }
        }
    }
    public void openCalendar() throws Exception {
        if (calendar.size() == 1){
            if(!calendar.get(0).isDisplayed()){
                click(dateInput, "Check In - Check Out Calendar");
                Thread.sleep(1000);
            }
        } else {
            if(!calendar.get(1).isDisplayed()){
                click(dateInput, "Check In - Check Out Calendar");
                Thread.sleep(1000);
            }
        }
    }

    public void clickCalendarNext() throws Exception {
        click(nextArrowCalendar, "Calendar Next Arrow");
    }
    public void addGuests(String adults, String children, String infants, String pets) throws Exception {
        click(guestsInput,"Click guests input field");
        Thread.sleep(2000);
        addAdults(adults);
        addChildren(children);
        addInfants(infants);
        addPets(pets);
    }
    public void addAdults(String adults) throws Exception {
        for (int i = 0; i < Integer.parseInt(adults); i++) {
            click(adultsAdd, "Click + Adults");
        }
    }
    public void addChildren(String children) throws Exception {
        for (int i = 0; i < Integer.parseInt(children); i++) {
            click(childrenAdd, "Click + Children");
        }
    }
    public void addInfants(String infants) throws Exception {
        for (int i = 0; i < Integer.parseInt(infants); i++) {
            click(infantsAdd, "Click + Infants");
        }
    }
    public void addPets(String pets) throws Exception {
        for (int i = 0; i < Integer.parseInt(pets); i++) {
            click(petsAdd, "Click + Pets");
        }
    }
    public void search() throws Exception {
        click(searchButton,"Click search");
        Thread.sleep(5000);
        String url=driver.getCurrentUrl();
        String res=(driver.findElement(By.xpath("//div[@data-testid='little-search']//button[1]//div"))).getText();
        res=res.replace(" ","-");
        Assert.assertTrue(url.contains(res),"Verify that url for destination contains: "+res);
    }
}
