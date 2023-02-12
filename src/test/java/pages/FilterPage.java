package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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
}
