package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BasePage;
import steps.BaseSteps;

import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.Set;

public class CategoryCarousel extends BaseSteps{
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
    @Parameters({"category","checkword"})

    public void categoryCarousel(String category,String checkword) throws Exception {
        while (true) {
            if(driver.findElement(By.xpath("//div[@id='categoryScroller']//label//span[text()='"+category+"']//parent::div//parent::span//parent::div//parent::label")).getAttribute("aria-hidden").equalsIgnoreCase("true")) {
                driver.findElement(By.xpath("//div[@id='categoryScroller']//label//span[text()='"+category+"']//parent::div//parent::span")).click();
                Thread.sleep(5000);
                //get second result and assert that it has word "view" in subtitle
                List<WebElement> lst= driver.findElements(By.xpath("//div[@data-testid='card-container']"));
                if (lst.size() >0){
                    String subtitle=driver.findElement(By.xpath("//div[@data-testid='card-container']//span[1]")).getText();
                    String sub=subtitle.toLowerCase();
                    Assert.assertTrue(sub.contains(checkword),"Verify that there is view word in subtitle");
                }
                else {
                    System.out.println("No results for selected category!");
                }
                break;
            }
            else{
                categoryCarouselScroll();
            }
            }
        }
    public void categoryCarouselScroll(){
        driver.findElement(By.xpath("//button[@aria-label='Next categories page']")).click();
    }
}
