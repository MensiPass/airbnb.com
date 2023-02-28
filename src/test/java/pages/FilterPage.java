package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.List;
import java.util.Set;

public class FilterPage extends BasePage {
    public FilterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    String filterOpen = "//button//span[text()='Filters']//parent::span//parent::button"; //flex date button in calendar
    @FindBy(css="input[id='price_filter_min']")
    WebElement minPriceField;
    @FindBy(css="input[id='price_filter_max']")
    WebElement maxPriceField;
    String minPriceScrollButton="//button[@aria-label='Minimum Price']";
   String maxPriceScrollButton="//button[@aria-label='Minimum Price']";
   String placeTypeCheck="//input[@name='$$']";
    String placeRooms="//div[@aria-label='Bedrooms']//div[@id='menuItemButton-$$']";
    String placeBeds= "//div[@aria-label='Beds']//div[@id='menuItemButton-$$']";
    String placeBaths="//div[@aria-label='Bathrooms']//div[@id='menuItemButton-$$']";
    String propertyFieldType="//div[@id='chip-group-null']//div[text()='$$']//parent::div//parent::div//parent::div//parent::button";
    String amenitiesMoreButton="//section//h2[text()='Amenities']//parent::div//parent::section//button/span[text()='Show more']";
    String essentialsField="//input[@name='$$']//parent::span//span";
    String featuresField="//input[@name='$$']//parent::span//span";
    String amenitiesLocation="//input[@name='$$']//parent::span//span";
    String amenitiesSafety="//input[@name='$$']//parent::span//span";
    @FindBy(xpath="//button[contains(@aria-describedby,'switch-ib')]")
    WebElement instantBookSwitch;
    @FindBy(xpath="//button[contains(@aria-describedby,'switch-amenities')]")
    WebElement selfCheckField;
    String accesibilityMoreButton="//section//h2[text()='Accessibility features']//parent::div//parent::section//button/span[text()='Show more']";
    String entranceField="//input[@name='$$']//parent::span//span";
    @FindBy(xpath="//button[contains(@aria-describedby,'switch-superhost')]")
    WebElement superHostCheck;
    @FindBy(xpath="//button[contains(@aria-describedby,'switch-tier')]")
    WebElement hostPlus;
    String hostLangMore="//section//h2[text()='Host language']//parent::div//parent::section//button/span[text()='Show more']";
    String langOption="//input[@name='$$']//parent::span//span";
    @FindBy(xpath = "//footer//a[@aria-live='polite' or contains(text(),'Show homes')]")
    WebElement searchFilterButton;
    @FindBy(xpath = "//div[@data-testid='card-container']")
    List<WebElement> searchFilterResults;
    @FindBy(xpath = "//div[@data-section-id='OVERVIEW_DEFAULT']//ol//li//span")
    List<WebElement> searchAssertSecResult;

    public void openFilter()  {
        (driver.findElement(By.xpath(filterOpen))).click();

    }
    public void setPrice(String minPrice, String maxPrice) throws InterruptedException {
        WebElement slider=driver.findElement(By.xpath(minPriceScrollButton));  //"//button[@aria-label='Minimum Price']"));
        Thread.sleep(3000);
       // Actions actions = new Actions(driver);

        if((minPrice!=null && !(minPrice.isEmpty()))) {
            if (driver.findElement(By.xpath(minPriceScrollButton)).getAttribute("aria-valuenow").equalsIgnoreCase(minPrice)) {
                minPriceField.sendKeys(Keys.CONTROL, Keys.chord("a"));
                minPriceField.sendKeys(Keys.BACK_SPACE);
                minPriceField.sendKeys(minPrice);

            } else {
                new BasePage(driver).setAttribute(driver.findElement(By.xpath("//button[@aria-label='Minimum Price']")), "aria-valuenow", minPrice);
                minPriceField.sendKeys(Keys.CONTROL, Keys.chord("a"));
                minPriceField.sendKeys(Keys.BACK_SPACE);
                minPriceField.sendKeys(minPrice);
                //actions.dragAndDropBy(slider, 3, 0).build().perform();
            }
        }
        if((maxPrice!=null && !(maxPrice.isEmpty()))) {
            if (driver.findElement(By.xpath(maxPriceScrollButton)).getAttribute("aria-valuenow").equalsIgnoreCase(maxPrice)) {
                maxPriceField.sendKeys(Keys.CONTROL, Keys.chord("a"));
                maxPriceField.sendKeys(Keys.BACK_SPACE);
                maxPriceField.sendKeys(minPrice);
            } else {
                new BasePage(driver).setAttribute(driver.findElement(By.xpath(maxPriceScrollButton)), "aria-valuenow", maxPrice);
                maxPriceField.sendKeys(Keys.CONTROL, Keys.chord("a"));
                maxPriceField.sendKeys(Keys.BACK_SPACE);
                maxPriceField.sendKeys(maxPrice);
                //actions.dragAndDropBy(slider, 3, 0).build().perform();
            }
        }
        Thread.sleep(2000);
        // did not work
       // minPriceField.clear();
       // maxPriceField.clear();
       // Thread.sleep(5000);
       // minPriceField.sendKeys(minPrice);
       // maxPriceField.sendKeys(maxPrice);
    }
    public void setPlaceType(String placeType) throws Exception {
        String placetype=updateXpathValue(placeTypeCheck,placeType);
        new BasePage(driver).scroll();
        Thread.sleep(2000);
        if((placeType!=null && !(placeType.isEmpty()))) {
            if (elementExistsByXpath(placetype)) {
                driver.findElement(By.xpath(placetype)).click();
            }
        }
    }
    public void setRoomsBedsBaths(String rooms, String beds, String baths) throws Exception {
        String placerooms=updateXpathValue(placeRooms,rooms);
        String placebeds=updateXpathValue(placeBeds,beds);
        String placebaths=updateXpathValue(placeBaths,baths);

        new BasePage(driver).scroll();
        if((rooms!=null && !(rooms.isEmpty()))) {
            if (elementExistsByXpath(placerooms)) {
                click(driver.findElement(By.xpath(placerooms)), "Select number of bedrooms");
            }
        }
        if((beds!=null && !(beds.isEmpty()))) {
            if (elementExistsByXpath(placebeds)) {
                click(driver.findElement(By.xpath(placebeds)), "Select number of  beds");
            }
        }
        if((baths!=null && !(baths.isEmpty()))) {
            if (elementExistsByXpath(placebaths)) {
                click(driver.findElement(By.xpath(placebaths)), "Select number of bathrooms");
            }
        }
    }
    public void setPropertyType(String propertyType) throws Exception {
        String proptype=updateXpathValue(propertyFieldType,propertyType);
        new BasePage(driver).scroll();
        if((propertyType!=null && !(propertyType.isEmpty()))) {
            while (true) {
                if (elementExistsByXpath(proptype)) {
                    click(driver.findElement(By.xpath(proptype)), "Property type click");
                    break;
                } else {
                    new BasePage(driver).scroll();
                }
            }
        }
    }
    public void setAmenities(String essentials, String features, String location, String safety) throws Exception {
        new BasePage(driver).scroll();
        if(elementExistsByXpath(amenitiesMoreButton)) {
            click(driver.findElement(By.xpath(amenitiesMoreButton)), "Show more button for essentials");
        }
        String essCheck=updateXpathValue(essentialsField,essentials);
        String featuresCheck=updateXpathValue(featuresField,features);
        String locationCheck=updateXpathValue(amenitiesLocation,location);
        String safetyCheck=updateXpathValue(amenitiesSafety,safety);
        if((essentials!=null && !(essentials.isEmpty()))) {
            while (true) {
                if (elementExistsByXpath(essCheck)) {
                    click(driver.findElement(By.xpath(essCheck)), "Select essentials");
                    break;
                } else {
                    new BasePage(driver).scroll();
                }
            }
        }
        if((features!=null && !(features.isEmpty()))) {
            while (true) {
                if (elementExistsByXpath(featuresCheck)) {
                    click(driver.findElement(By.xpath(featuresCheck)), "Select features");
                    break;
                } else {
                    new BasePage(driver).scroll();
                }
            }
        }
        if((location!=null && !(location.isEmpty()))) {
            while (true) {
                if (elementExistsByXpath(locationCheck)) {
                    click(driver.findElement(By.xpath(locationCheck)), "Select location");
                    break;
                } else {
                    new BasePage(driver).scroll();
                }
            }
        }
        if((safety!=null && !(safety.isEmpty()))) {
            while (true) {
                if (elementExistsByXpath(safetyCheck)) {
                    click(driver.findElement(By.xpath(safetyCheck)), "Select safety");
                    break;
                } else {
                    new BasePage(driver).scroll();
                }
            }
        }
    }
    public void setBookingOptions(String instantBook, String selfCheck) throws Exception {
        new BasePage(driver).scroll();
        if((instantBook!=null && !(instantBook.isEmpty()))) {
            if (instantBook.equalsIgnoreCase("Yes")) {
                click(instantBookSwitch, "Instant book");
            }
        }
        if((selfCheck!=null && !(selfCheck.isEmpty()))) {
            if (selfCheck.equalsIgnoreCase("Yes")) {
                click(selfCheckField, "Self check");
            }
        }
    }
    public void setAccessibility(String entranceParking, String accessBedroom, String accessBaths, String addaptiveEquipment) throws Exception {
        new BasePage(driver).scroll();
        if (elementExistsByXpath(accesibilityMoreButton)) {
            click(driver.findElement(By.xpath(accesibilityMoreButton)), "Show more button for accessibility features");
        }
        String entranceParkingField = updateXpathValue(entranceField, entranceParking);
        String accessBedroomField = updateXpathValue(entranceField, accessBedroom);
        String accessBathroomField = updateXpathValue(entranceField, accessBaths);
        String addaptiveField = updateXpathValue(entranceField, addaptiveEquipment);
        if((entranceParking!=null && !(entranceParking.isEmpty()))) {
            while (true) {
                if (elementExistsByXpath(entranceParkingField)) {
                    click(driver.findElement(By.xpath(entranceParkingField)), "Select guest entrance and parking");
                    break;
                } else {
                    new BasePage(driver).scroll();
                }
            }
        }
         if (accessBedroom!=null && !(accessBedroom.isEmpty())){
             while (true) {
                 if (elementExistsByXpath(accessBedroomField)) {
                     click(driver.findElement(By.xpath(accessBedroomField)), "Select bedroom");
                     break;
                 } else {
                     new BasePage(driver).scroll();
                 }
             }
        }
        if (accessBaths!=null && !(accessBaths.isEmpty())) {
            while (true) {
                if (elementExistsByXpath(accessBathroomField)) {
                    click(driver.findElement(By.xpath(accessBathroomField)), "Select baths");
                    break;
                } else {
                    new BasePage(driver).scroll();
                }
            }
        }
         if (addaptiveEquipment!=null && !(addaptiveEquipment.isEmpty())) {
             while (true) {
                 if (elementExistsByXpath(addaptiveField)) {
                     click(driver.findElement(By.xpath(addaptiveField)), "Select addaptive equipment");
                     break;
                 } else {
                     new BasePage(driver).scroll();
                 }
             }
         }
    }
    public void setTopTierStays(String superHost, String plus) throws Exception {
        new BasePage(driver).scroll();
        if((superHost!=null && !(superHost.isEmpty()))) {
            if (superHost.equalsIgnoreCase("Yes")) {
                click(superHostCheck, "Select superhost");
            }
        }
        if((plus!=null && !(plus.isEmpty()))) {
            if (plus.equalsIgnoreCase("Yes")) {
                click(hostPlus, "Select airbnb plus");
            }
        }
    }
    public void setHostLanguage(String lang) throws Exception {
        new BasePage(driver).scroll();
        if((lang!=null && !(lang.isEmpty()))) {
            if (elementExistsByXpath(hostLangMore)) {
                click(driver.findElement(By.xpath(hostLangMore)), "Show more button for host language");

            }
            String languageH = updateXpathValue(langOption, lang);
            while (true) {
                if (elementExistsByXpath(languageH)) {
                    click(driver.findElement(By.xpath(languageH)), "Select host language");
                    break;
                } else {
                    new BasePage(driver).scroll();
                }
            }
        }
    }
    public void filterSearch() throws Exception {click(searchFilterButton,"Click filter search");//click on one search result
       Thread.sleep(5000);
       if (searchFilterResults.size() >0){
           click(searchFilterResults.get(1), "Click second result");
           Thread.sleep(3000);
           Set<String> handles=driver.getWindowHandles();
           for(String actual: handles) {
               if(!actual.equalsIgnoreCase(driver.getWindowHandle())) {
                   driver.switchTo().window(actual);
               }
           }
       //System.out.println(driver.getCurrentUrl());
       //assert some parameters
       for(int i = 0; i<searchAssertSecResult.size(); i++){
           if(searchAssertSecResult.get(i).getText().contains("bedrooms") || searchAssertSecResult.get(i).getText().contains("bedroom"))
           {
               String[] numroom=searchAssertSecResult.get(i).getText().split(" ");
               Assert.assertTrue(Integer.parseInt(numroom[0])>=1,"Verify that there are correct number of rooms");
           }
           else if(searchAssertSecResult.get(i).getText().contains("beds") ||searchAssertSecResult.get(i).getText().contains("bed") )
           {
               String[] numbeds=searchAssertSecResult.get(i).getText().split(" ");
               Assert.assertTrue(Integer.parseInt(numbeds[0])>=1,"Verify that there are correct number of beds");
           }
           else if(searchAssertSecResult.get(i).getText().contains("bath") || searchAssertSecResult.get(i).getText().contains("baths"))
           {
               String[] numbath=searchAssertSecResult.get(i).getText().split(" ");
               Assert.assertTrue(Integer.parseInt(numbath[0])>=1,"Verify that there are correct number of baths");
           }
       }
       }
       else {
           System.out.println("No results for selected filters!");
       }
    }
}
