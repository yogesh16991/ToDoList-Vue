package runner;

import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/feature",
        glue = {"stepDefinitions"}
        ,dryRun=false
        , snippets = CAMELCASE
        ,plugin = {"pretty","html:target/cucumber-reports.html"},
        monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
