package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",plugin = {"pretty","json:target/jsonReports/cucumber-report.json","html:target/cucumber-reports.html"},
        glue={"stepdefinitions"}
)

public class JUnitTest {

//    plugin = {"json:target/jsonReports/cucumber-report.json"} specifies to generate a json report in the folder
}
