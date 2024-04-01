package com.labcorp.restassured.runner;

//import io.cucumber.testng.CucumberOptions;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features= {"src/test/resources/features/petstore.feature"},
        glue= {"com.labcorp.restassured.stepdef"},
        //tags= "@retrieveUser",    // based on tags scenarios will run
        monochrome=true, dryRun=false
)

public  class  TestRunner  {

}