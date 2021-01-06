import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = {"com.examples.cucumber"},
        plugin = { "json:target/cucumber.json", "pretty",
        "html:target/cucumber-reports" })

public class runCucumberTests {

}
