package com.labcorp.restassured.stepdef;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import org.junit.Assert;

public class StepDefinition {
    private  static  final  String  BASE_URL  =  "https://petstore.swagger.io/v2";
    RequestSpecification request;
    private  static  Response response;

    //Could use simple json or gson or other JSON APIs to create Json String
    //Could read values from the feature file as shown in the feautre file.
    //Keeping it simple by hard coding
    private String requestBody = "["+
       "{" +
            "\"id\": 101 ," +
            "\"username\": \"sdutta\" ," +
            "\"firstName\": \"shathi\" ," +
            "\"lastName\": \"test\" ," +
            "\"email\": \"s.test@gmail.com\", " +
            "\"password\": \"password\" ," +
            "\"phone\": \"123456789\" ," +
            "\"userStatus\": 0"  +
        "}" +
   "]";

    private String invalidRequestBody = "["+
            "{" +
            "\"id\": \"abc\" ," +
            "\"username\": \"sdutta\" ," +
            "\"firstName\": \"shathi\" ," +
            "\"lastName\": \"test\" ," +
            "\"email\": \"s.test@gmail.com\", " +
            "\"password\": \"password\" ," +
            "\"phone\": \"123456789\" ," +
            "\"userStatus\": 0"  +
            "}" +
            "]";

    @Given("Post User API")
    public void post_user_api(){
        RestAssured.baseURI  =  BASE_URL;
        request  =  RestAssured.given();
        request.header("Content-Type",  "application/json");

    }

    @When("Provide {string} request body")
    public void invoke_with_request_body(String isValid) {
        if(isValid.equalsIgnoreCase("valid")) {
            response = request.body(requestBody)
                    .post("/user/createWithArray");
        }else{
            response = request.body(invalidRequestBody)
                    .post("/user/createWithArray");
        }
    }

    @Then("Status code equals {int}")
    public void post_statuscode_equals(int arg) {
        Assert.assertEquals(arg, response.getStatusCode());

    }


    @Given("GET User API")
    public void get_user_api(){
        RestAssured.baseURI  =  BASE_URL;
        request  =  RestAssured.given();
        //request.header("Content-Type",  "application/json");

    }

    //user name can be read from feature file
    @When("Retrieve user with username {string}")
    public void invoke_with_username(String userName) {
        response = request.get("/user/"+userName);

    }

    //The response json can be parsed using JSON APIS to validate the data of the json elements.
    @Then("Get Status code equals {int}")
    public void Get_statuscode_equals(int arg) {
        Assert.assertEquals(arg, response.getStatusCode());
        if(response.getStatusCode() == 200){
            Assert.assertTrue(response.getBody().toString().contains("shathi"));
        }

    }



}
