package com.cybertek.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://3.80.210.67:8000/api/spartans";


    @Test
    public void test1(){

        //send a request and save response inside the Response object
        Response response = RestAssured.get(url);

        //print response status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //print response body
        response.prettyPrint();


    }


}
