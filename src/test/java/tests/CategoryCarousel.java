package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steps.BaseSteps;
import org.testng.annotations.BeforeMethod;
import java.util.List;

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
    String categoryNext="//button[@aria-label='Next categories page']";
    String catScroll="//div[@id='categoryScroller']//label//span[text()='$$']//parent::div//parent::span//parent::div//parent::label";
    String catLabel="//div[@id='categoryScroller']//label//span[text()='$$']//parent::div//parent::span";
    String catList="//div[@data-testid='card-container']";
    String catSubtitle="//div[@data-testid='card-container']//span[1]";
    @Test(enabled=true)
    @Parameters({"category","checkword"})

    public void categoryCarousel(String category,String checkword) throws Exception {
        String catS=updateXpathValue(catScroll,category);
        String catSLab=updateXpathValue(catLabel,category);
        while (true) {
            if(driver.findElement(By.xpath(catS)).getAttribute("aria-hidden").equalsIgnoreCase("true")) {
                driver.findElement(By.xpath(catSLab)).click();
                Thread.sleep(2000);
                //get second result and assert that it has word "view" in subtitle
                List<WebElement> lst= driver.findElements(By.xpath(catList));
                if (lst.size() >0){
                    String subtitle=driver.findElement(By.xpath(catSubtitle)).getText();
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
        driver.findElement(By.xpath(categoryNext)).click();
    }
    public String updateXpathValue(String xpath, String value){
        return xpath.replace("$$", value);
    }
}
