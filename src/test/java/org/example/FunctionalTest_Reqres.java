package org.example;

import TestUtil.BaseTest;
import annotations.FrameworkAnnotations;
import asserts.Assertions;
import constants.ApiConstants;
import constants.FileConstants;
import constants.StatusCode;
import fileUtils.FilesUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import random.RandomUtils;
import requestUtils.Request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@Slf4j
@FrameworkAnnotations(applicationUnderTest = {"REQRES"})
public class FunctionalTest_Reqres extends BaseTest {

    Response response;
    @Test(groups = {"reqres"}, enabled = false)
    public void getAllUsers(){
        response = Request.init().setEndPoint(ApiConstants.REQRES_WITHOUT_PARAM_GET_ALL_USERS).build().get();
        assertThat("Response Status code does not matches",response.getStatusCode(), is(StatusCode.SC_OK));
        Assertions.assertJsonResponseSchema(response, FileConstants.RESPONSE_SCHEMA_REQRES);
    }

    @Test(groups = {"reqres"}, enabled = false)
    public void getUserBasedOnPathParam(){
        Request request = Request.init().setEndPoint(ApiConstants.REQRES_WITH_PARAM_GET_USER).setPathParam(ApiConstants.PATH_PARAMS).build();
        response = request.get();
        assertThat("Response Status code does not matches",response.getStatusCode(), is(StatusCode.SC_OK));
        JsonPath jsonPath = response.jsonPath();
        assertThat("User id is not same as passed in path param", jsonPath.getInt("data.id"), is(request.getConfig().getConfig(ApiConstants.PATH_PARAMS.toLowerCase()).getInt("userid")));
    }

    @Test(groups = {"reqres"})
    public void createUser(){
        int empId = RandomUtils.getRandomNumber();
        String empDept = RandomUtils.getDeptName();
        String empName = RandomUtils.getFullName();

        String requestInString = FilesUtil
                .readJsonAndGetAsString(FileConstants.REQUEST_BODY_CREATE_USER)
                .replace("123",String.valueOf(empId))
                .replace("randomEmpDept", empDept)
                .replace("randomEmpName", empName);
        log.info("Updated Request Body: "+requestInString);

        Request request = Request.init().setEndPoint(ApiConstants.REQRES_CREATE_USER).setBody(requestInString).build();
        response = request.post();
        assertThat("Response status code is not as expected", response.getStatusCode(), is(StatusCode.CREATED));
        JsonPath jsonPath = response.jsonPath();
        assertThat("Employee department doesn't match",jsonPath.getString("createdAt") , containsString("2024-02-06"));

    }



}
