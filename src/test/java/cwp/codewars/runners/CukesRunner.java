package cwp.codewars.runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt",
        "pretty" },
        features = "src/test/resources/features",
        glue="cwp/codewars/step_definitions",
       // dryRun = false,
        tags="@Admin"
)

public class CukesRunner {
}
