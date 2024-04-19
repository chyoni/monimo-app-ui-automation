package kro.kr.tbell.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "kro.kr.tbell.stepdefinitions",
        plugin = {"pretty", "html:build/cucumber-reports"}
)
public class CucumberRunner {

}
