package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steps.BaseSteps;

import java.util.List;
import java.util.Set;

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
        List<WebElement> apartmentList= driver.findElements(By.xpath("//div[@data-testid='card-container']"));
        if (apartmentList.size() >0) {
            apartmentList.get(2).click();//get third apartment form the list
            Thread.sleep(5000);
            Set<String> handles=driver.getWindowHandles();
            for(String actual: handles) {
                if(!actual.equalsIgnoreCase(driver.getWindowHandle())) {
                    driver.switchTo().window(actual);
                }
            }
            if(driver.findElement(By.xpath("//div[@data-plugin-in-point-id='TITLE_DEFAULT']//button[contains(@aria-label,'Rated')]"))!=null) {
                driver.findElement(By.xpath("//div[@data-plugin-in-point-id='TITLE_DEFAULT']//button[contains(@aria-label,'Rated')]")).click();
                String rate = driver.findElement(By.xpath("//div[@role='dialog']//h2")).getText();
                String[] ratereviews = rate.split("·");
                System.out.println("The selected stay has " + ratereviews[0] + " star rate and " + ratereviews[1] + " reviews.");
                Assert.assertTrue((ratereviews[0].trim()).equalsIgnoreCase("5.0"));
                Assert.assertTrue((ratereviews[1].trim()).equalsIgnoreCase("8 reviews"));
            }
        }
    }
}
