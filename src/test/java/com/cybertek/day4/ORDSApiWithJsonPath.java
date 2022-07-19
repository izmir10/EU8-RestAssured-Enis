package com.cybertek.day4;

import com.cybertek.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to Countries")
    @Test
    public void test1(){

        Response response = get("/countries");

        //get the second country name with Jsonpath

        //to use jsonpath we assign response to JsonPath

        JsonPath jsonPath = response.jsonPath();

        String secondCountryName =  jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        List <String> countryIDs = jsonPath.getList("items.country_id");
        System.out.println("countryIDs = " + countryIDs);

        //get all country names where their region id is equal to 2
        List<String> countryNamesWithRegionId2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("countryNamesWithRegionId2 = " + countryNamesWithRegionId2);

    }


    @DisplayName("GET request to /employees with query param")
    @Test
    public void test2(){

        Response response = given().queryParam("limit", 107)
        .when().get("/employees");

        //get me all email of employees who is working as IT_PROG
        JsonPath jsonPath = response.jsonPath();

        List<String> emailsOfITProgrammers = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println("emailsOfITProgrammers = " + emailsOfITProgrammers);

        //get me first name of employees who is making more than 10000
        List<String> employeesEarnMoreThan10K = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("employeesEarnMoreThan10K = " + employeesEarnMoreThan10K);


        //get the max salary first_name
        String kingFirstName = jsonPath.getString("items.max {it.salary}.first_name");
        String kingNameWithPathMethod = response.path("items.max {it.salary}.first_name");
        System.out.println("kingFirstName = " + kingFirstName);
        System.out.println("kingNameWithPathMethod = " + kingNameWithPathMethod);

    }

}
