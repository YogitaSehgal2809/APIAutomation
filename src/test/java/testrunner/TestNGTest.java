package testrunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/java/features",
        glue = "stepdefinitions",
        plugin = {
                "pretty",
                "json:target/jsonReports/cucumber-testng-report.json",
                "html:target/cucumber-testng-html-report",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true
)
public class TestNGTest extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider()  // Set true for parallel execution
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
