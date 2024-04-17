package kro.kr.tbell.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/kro/kr/tbell/features"},
        glue = "stepDefs"
)
public class CucumberRunner {

}
