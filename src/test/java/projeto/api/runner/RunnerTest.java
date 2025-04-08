package projeto.api.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "projeto.api.steps",
    tags = "@usuario-inexistente", 
    plugin = {"pretty", "html:target/cucumber-reports"}
)
public class RunnerTest {

}

