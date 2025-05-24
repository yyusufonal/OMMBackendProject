package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/default-cucumber-reports.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", // Allure raporları için plugin
                "rerun:target/failedRerun.txt"
        },
        features = {"src/test/resources/features/api"},
        glue = {"stepdefinitions","hooks","utilities"},
        tags = "@API",
        dryRun =false



)
public class RunnerTest {
}
