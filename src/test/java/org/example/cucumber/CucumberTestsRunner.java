package org.example.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "D:\\InstagramChecker\\src\\test\\java\\org\\example\\cucumber",
        glue = "org.example.cucumber.steps",
        tags = "@tag3")
public class CucumberTestsRunner {

}
