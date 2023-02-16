package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.awt.*;
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
   String placeTypeCheck="//input[@name='$$']";
    @FindBy(xpath = "//footer//a[@aria-live='polite' or contains(text(),'Show homes')]")
    WebElement searchFilterButton;
    @FindBy(xpath = "//div[@data-testid='card-container']")
    List<WebElement> searchFilterResults;
    @FindBy(xpath = "//div[@data-section-id='OVERVIEW_DEFAULT']//ol//li//span")
    List<WebElement> searchAssertSecResult;

    public void openFilter()  {
        (driver.findElement(By.xpath(filterOpen))).click();

    }
    public void setPrice(String minPrice, String maxPrice)  {
        minPriceField.sendKeys(Keys.CONTROL, Keys.chord("a"));
        minPriceField.sendKeys(Keys.BACK_SPACE);
        minPriceField.sendKeys(minPrice);

        maxPriceField.sendKeys(Keys.CONTROL, Keys.chord("a"));
        maxPriceField.sendKeys(Keys.BACK_SPACE);
        maxPriceField.sendKeys(maxPrice);

        //this did not work
       // minPriceField.clear();
       // maxPriceField.clear();
       // Thread.sleep(5000);
       // minPriceField.sendKeys(minPrice);
       // maxPriceField.sendKeys(maxPrice);

    }
    public void setPlaceType(String placeType) throws Exception {
      //  updateXpathValue(placeTypeCheck,placeType);

        new BasePage(driver).scroll();
        if(elementExistsByXpath("//input[@name='" + placeType + "']//parent::span//span")) {
            click(driver.findElement(By.xpath("//input[@name='" + placeType + "']//parent::span//span")), "Click place type");
        }
    }
    public void setRoomsBedsBaths(String rooms, String beds, String baths) throws Exception {
        new BasePage(driver).scroll();
        click(driver.findElement(By.xpath("//div[@aria-label='Bedrooms']//div[@id='menuItemButton-"+rooms+"']")),"Select number of bedrooms");
        click(driver.findElement(By.xpath("//div[@aria-label='Beds']//div[@id='menuItemButton-"+beds+"']")),"Select number of  beds");
        click(driver.findElement(By.xpath("//div[@aria-label='Bathrooms']//div[@id='menuItemButton-"+baths+"']")),"Select number of bathrooms");

    }
    public void setPropertyType(String propertyType) throws Exception {
        new BasePage(driver).scroll();
        while(true) {
            if(elementExistsByXpath("//div[@id='chip-group-null']//div[text()='" + propertyType + "']//parent::div//parent::div//parent::div//parent::button")) {
                click(driver.findElement(By.xpath("//div[@id='chip-group-null']//div[text()='" + propertyType + "']//parent::div//parent::div//parent::div//parent::button")), "Select property type");
                break;
            }
            else{new BasePage(driver).scroll();}
        }
    }
    public void setAmenities(String essentials, String features, String location, String safety) throws Exception {

        new BasePage(driver).scroll();
        if(elementExistsByXpath("//section//h2[text()='Amenities']//parent::div//parent::section//button/span[text()='Show more']")) {
            click(driver.findElement(By.xpath("//section//h2[text()='Amenities']//parent::div//parent::section//button/span[text()='Show more']")), "Show more button for essentials");

        }
        while(true) {
            if(elementExistsByXpath("//input[@name='"+essentials+"']//parent::span//span")) {
                click(driver.findElement(By.xpath("//input[@name='"+essentials+"']//parent::span//span")), "Select essentials");
                break;
            }
            else{new BasePage(driver).scroll();}
        }
        while(true) {
            if(elementExistsByXpath("//input[@name='"+features+"']//parent::span//span")) {
                click(driver.findElement(By.xpath("//input[@name='"+features+"']//parent::span//span")), "Select features");
                break;
            }
            else{new BasePage(driver).scroll();}
        }
        while(true) {
            if(elementExistsByXpath("//input[@name='"+location+"']//parent::span//span")) {
                click(driver.findElement(By.xpath("//input[@name='"+location+"']//parent::span//span")), "Select location");
                break;
            }
            else{new BasePage(driver).scroll();}
        }
        while(true) {
            if(elementExistsByXpath("//input[@name='"+safety+"']//parent::span//span")) {
                click(driver.findElement(By.xpath("//input[@name='"+safety+"']//parent::span//span")), "Select safety");
                break;
            }
            else{new BasePage(driver).scroll();}
        }
    }
    public void setBookingOptions(String instantBook, String selfCheckk) throws Exception {
        new BasePage(driver).scroll();
            if (instantBook.equalsIgnoreCase("Yes")) {
                click(driver.findElement(By.xpath("//button[contains(@aria-describedby,'switch-ib')]")),"Instant book");
            }
            if (selfCheckk.equalsIgnoreCase("Yes")) {
                click(driver.findElement(By.xpath("//button[contains(@aria-describedby,'switch-amenities')]")),"Self check");
            }


    }
    public void setAccessibility(String entranceParking, String accessBedroom, String accessBaths, String addaptiveEquipment) throws Exception {
        new BasePage(driver).scroll();
        if((entranceParking!=null && !(entranceParking.isEmpty())) && (accessBedroom!=null && !(accessBedroom.isEmpty())) && (accessBaths!=null && !(accessBaths.isEmpty())) && (addaptiveEquipment!=null && !(addaptiveEquipment.isEmpty()))) {
            if (elementExistsByXpath("//section//h2[text()='Accessibility features']//parent::div//parent::section//button/span[text()='Show more']")) {
                click(driver.findElement(By.xpath("//section//h2[text()='Accessibility features']//parent::div//parent::section//button/span[text()='Show more']")), "Show more button for accessibility features");

            }
            if (entranceParking != null || !(entranceParking.isEmpty())) {
                while (true) {
                    if (elementExistsByXpath("//input[@name='" + entranceParking + "']//parent::span//span")) {
                        click(driver.findElement(By.xpath("//input[@name='" + entranceParking + "']//parent::span//span")), "Select guest entrance and parking");
                        break;
                    } else {
                        new BasePage(driver).scroll();
                    }
                }
            }
            if (accessBedroom != null || !(accessBedroom.isEmpty())) {
                while (true) {
                    if (elementExistsByXpath("//input[@name='" + accessBedroom + "']//parent::span//span")) {
                        click(driver.findElement(By.xpath("//input[@name='" + accessBedroom + "']//parent::span//span")), "Select bedroom");
                        break;
                    } else {
                        new BasePage(driver).scroll();
                    }
                }
            }
            if (accessBaths != null || !(accessBaths.isEmpty())) {
                while (true) {
                    if (elementExistsByXpath("//input[@name='" + accessBaths + "']//parent::span//span")) {
                        click(driver.findElement(By.xpath("//input[@name='" + accessBaths + "']//parent::span//span")), "Select baths");
                        break;
                    } else {
                        new BasePage(driver).scroll();
                    }
                }
            }
            if (addaptiveEquipment != null || !(addaptiveEquipment.isEmpty())) {
                while (true) {
                    if (elementExistsByXpath("//input[@name='" + addaptiveEquipment + "']//parent::span//span")) {
                        click(driver.findElement(By.xpath("//input[@name='" + addaptiveEquipment + "']//parent::span//span")), "Select addaptive equipment");
                        break;
                    } else {
                        new BasePage(driver).scroll();
                    }
                }
            }
        }
    }
    public void setTopTierStays(String superHost, String plus) throws Exception {
        new BasePage(driver).scroll();
        if (superHost.equalsIgnoreCase("Yes")) {
            click(driver.findElement(By.xpath("//button[contains(@aria-describedby,'switch-superhost')]")),"Select superhost");
        }
        if (plus.equalsIgnoreCase("Yes")) {
            click(driver.findElement(By.xpath("//button[contains(@aria-describedby,'switch-tier')]")),"Select airbnb plus");
        }
    }
    public void setHostLanguage(String lang) throws Exception {
        new BasePage(driver).scroll();
        if(elementExistsByXpath("//section//h2[text()='Host language']//parent::div//parent::section//button/span[text()='Show more']")) {
            click(driver.findElement(By.xpath("//section//h2[text()='Host language']//parent::div//parent::section//button/span[text()='Show more']")), "Show more button for host language");

        }
        while(true) {
            if(elementExistsByXpath("//input[@name='"+lang+"']//parent::span//span")) {
                click(driver.findElement(By.xpath("//input[@name='"+lang+"']//parent::span//span")), "Select host language");
                break;
            }
            else{new BasePage(driver).scroll();}
        }
    }
    public void filterSearch() throws Exception {
        click(searchFilterButton,"Click filter search");
        //click on one search result
        Thread.sleep(5000);
        if (searchFilterResults.size() >0){
            click(searchFilterResults.get(1), "Click second result");
        Thread.sleep(5000);

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
