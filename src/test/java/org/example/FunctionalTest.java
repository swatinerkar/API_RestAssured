package org.example;


import asserts.Assertions;
import constants.ApiConstants;
import constants.FileConstants;
import constants.StatusCode;
import lombok.extern.slf4j.Slf4j;
import requestUtils.Request;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * Tests for API.
 */

@Slf4j
public class FunctionalTest
{
    Response response;
    @BeforeMethod
    public void setup(){
        Request.init().setBaseUrl();
    }

    @Test(enabled = true)
    public void getTestWithPathParam(){
        Request request = Request.init().setEndPoint(ApiConstants.WITH_USERNAME_PATH_GET_REPOS).setPathParam(ApiConstants.PATH_PARAMS).build();
        response = request.get();
        Assert.assertEquals(response.getStatusCode(), StatusCode.SC_OK);

    }

    @Test(enabled = true)
    public void getTestWithoutPathParam(){
        Request request = Request.init().setEndPoint(ApiConstants.WITHOUT_PARAM_GET_ALL_REPOS).build();
        response = request.get();
        Assert.assertEquals(response.getStatusCode(), StatusCode.SC_OK);
        Assertions.assertJsonResponseSchema(response, FileConstants.RESPONSE_SCHEMA);
    }

    @Test(enabled = true)
    public void getTestWithoutPathParamWithQueryParams(){
        Request request = Request.init().setEndPoint(ApiConstants.WITHOUT_PARAM_GET_ALL_REPOS).setQueryParam(ApiConstants.QUERY_PARAMS).build();
        response = request.get();
        Assert.assertEquals(response.getStatusCode(), StatusCode.SC_OK);
        JsonPath jsonPath = response.jsonPath();
        int size = jsonPath.getList("id").size();
        Assert.assertEquals(size, request.getConfig().getConfig(ApiConstants.QUERY_PARAMS.toLowerCase()).getInt("per_page"));

    }

    @AfterMethod
    public void tearDown(){
        Request.init().clearAll();
    }

}
