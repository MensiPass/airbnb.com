package steps;

import org.openqa.selenium.WebDriver;
import selenium.DriverManager;
import selenium.DriverManagerFactory;

import java.time.Duration;

public class BaseSteps {
    DriverManager driverManager;
    public WebDriver driver;
    public void init(String browser) throws Exception {
        driverManager= DriverManagerFactory.getDriverManager(browser);
        driver=driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    public void quit(){
        driverManager.quitDriver();
    }
    public void gotoAirbnb(String env) throws Exception {
        env=env.toUpperCase();
        switch(env){
            case "AIRBNB": { driver.get("https://www.airbnb.com/");} break;
            default: throw new Exception("Enviroment "+env+" not supported!");
        }
    }
}
