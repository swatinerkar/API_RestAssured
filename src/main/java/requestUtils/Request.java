package requestUtils;

import config.ConfigReader;
import com.typesafe.config.Config;
import io.restassured.response.Response;

public class Request {

    private static final Request request = new Request();

    private RequestElements requestElements;

    private final RequestElementsBuilder requestElementsBuilder = RequestElementsBuilder.getInstance();

    private final RequestBuilder requestBuilder = RequestBuilder.getInstance();

    private Request(){}

    public static Request init() {
        return request;
    }

    public Request setEndPoint(String endPoint){
        requestElementsBuilder.setEndPoint(endPoint);
        return this;
    }

    public Request setBaseUrl(){
        requestElementsBuilder.setBaseURL();
        return this;
    }

    public Request build(){
        this.requestElements = requestElementsBuilder.buildRequestElements();
        return this;
    }

    public Response get(){
        return requestBuilder.setRequest(this.requestElements).baseUri().setPathParam().setQueryParam().getCall();
    }


    public Request setPathParam(String pathParam) {
        requestElementsBuilder.setPathParam(pathParam);
        return this;
    }

    public Request setQueryParam(String queryParams) {
        requestElementsBuilder.setQueryParam(queryParams);
        return this;
    }

    public void clearAll() {
        requestElementsBuilder.resetAllRequestElements();
        requestBuilder.resetAllRequestElements();
    }

    public Config getConfig() {
        return ConfigReader.getInstance().getConfig();
    }
}
