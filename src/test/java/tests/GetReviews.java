package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ApartmentPage;
import steps.BaseSteps;

public class GetReviews extends BaseSteps {
    @BeforeMethod
    public void setup() throws Exception {
        init("CHROME");
        gotoAirbnb("AIRBNB");
    }
    @AfterMethod
    @Parameters({"quit"})
    public void tearDown(String quit){
        if(quit.equalsIgnoreCase("yes")) {
            quit();
        }
    }
    @Test(enabled=true)
    public void getReviews() throws InterruptedException {
        //click on some apartment on homepage and return its reviews
        ApartmentPage apartment=new ApartmentPage(driver);
        String [] rates=apartment.apartmentReviews();
        if(rates.length>0) {
           // Assert.assertTrue((rates[0].trim()).equalsIgnoreCase("5.0"));
            Assert.assertTrue((rates[1].trim()).contains("reviews"));
        }
    }
}
