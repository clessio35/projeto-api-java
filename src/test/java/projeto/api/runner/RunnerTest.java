package projeto.api.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "projeto.api.steps",
    tags = "@all", 
    plugin = {"pretty", "html:target/cucumber-reports", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"}
)
public class RunnerTest {

}

