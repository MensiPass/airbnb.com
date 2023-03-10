package runners;
import io.cucumber.java.nl.Stel;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features="src/test/java/features",
        glue="steps/Anywhere",
        tags="  @AnyweekStays",
        plugin={"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
public class AnyweekRunner extends AbstractTestNGCucumberTests{
    @Override
    @DataProvider(parallel=true)
    public Object[][] scenarios(){
        return super.scenarios();
    }
}
