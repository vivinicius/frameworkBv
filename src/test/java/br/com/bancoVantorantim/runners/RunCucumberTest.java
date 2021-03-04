package br.com.bancoVantorantim.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;


@CucumberOptions(
        features = {"src/test/resources/features/"},
        plugin = {"json:target/cluecumber-report/json/cucumber.json", "junit:target/junit.xml"},
        glue = {"br.com.bancoVantorantim.steps"},
        tags = "@BancoBV"
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {


    //Set parallel to false for default execution
    //Set the Thread limit using the mvn option "-Ddataproviderthreadcount=<numberOfThreads>"
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

