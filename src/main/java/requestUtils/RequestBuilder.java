package requestUtils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import reports.ExtentLogger;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * RequestSpecification reference object has been held by this class
 */

@Slf4j
public class RequestBuilder {

    private static final RequestBuilder requestBuilder = new RequestBuilder();
    private Response response;
    private RequestSpecification requestSpecification;
    private final Map<String, Object> pathParamMap = new HashMap<>();
    private final Map<String, Object> queryParamMap = new HashMap<>();
    private RequestElements requestElements;

    private RequestBuilder(){}

    public static RequestBuilder getInstance() {
        return requestBuilder;
    }

    public RequestBuilder setRequest(RequestElements requestElements){
        this.requestSpecification = given();
        this.requestElements = requestElements;
        return this;
    }

    public RequestBuilder baseUri() {
        if(requestElements.getBaseUrl() != null || !(requestElements.getBaseUrl().isBlank()))
            requestSpecification.baseUri(requestElements.getBaseUrl());
        else
            throw new IllegalArgumentException("baseUrl is mandatory to be set on application.config file. Make sure \"baseurl\" is all lowercase");
        return this;
    }

    public RequestBuilder setPathParam(){
        if(requestElements.getPathParams() !=null)
            requestElements.getPathParams().forEach(e -> { pathParamMap.put(e.getKey(), e.getValue().unwrapped());});
        else
            pathParamMap.clear();
        requestSpecification.pathParams(pathParamMap);
        return this;
    }


    public RequestBuilder setQueryParam(){
        if(requestElements.getQueryParams() !=null)
            requestElements.getQueryParams().forEach(e -> { queryParamMap.put(e.getKey(), e.getValue().unwrapped());});
        else
            queryParamMap.clear();
        requestSpecification.queryParams(queryParamMap);
        return this;
    }

    public RequestBuilder setBody() {
        if(requestElements.getRequestBody() != null || !(requestElements.getRequestBody().isBlank()))
            requestSpecification.body(requestElements.getRequestBody());
        else
            throw new IllegalArgumentException("requestBody is properly set. Make sure to keep requestBody file under test/resources/body, save the exact file name under FileConstants file.");
        return this;
    }

    public Response getCall(){
        if(requestElements.getEndpoint() !=null || !(requestElements.getEndpoint().isBlank()))
            this.response = requestSpecification.log().all(true).get(requestElements.getEndpoint());
        responseLogs();
        return this.response;
    }

    public Response postCall() {
        if(requestElements.getEndpoint() !=null || !(requestElements.getEndpoint().isBlank()))
            this.response = requestSpecification.log().all(true).post(requestElements.getEndpoint());
        responseLogs();
        return this.response;
    }

    private void responseLogs(){
//        response.prettyPrint();
        log.info("RESPONSE:->\n"+response.statusCode()+"\n");
        ExtentLogger.logResponse(response.prettyPrint());
    }



    public void resetAllRequestElements() {
        pathParamMap.clear();
        queryParamMap.clear();
    }



}
