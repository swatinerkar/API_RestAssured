package requestUtils;


import config.ConfigReader;
import constants.ApiConstants;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigValue;

import java.util.Map;
import java.util.Set;

/**
 * A Builder class to build request elements using this class
 */
public class RequestElementsBuilder {
    private static final Config CONFIG = ConfigReader.getInstance().getConfig();
    private static final RequestElementsBuilder requestElementsBuilder = new RequestElementsBuilder();
    private String baseUrl;
    private String endPoint;
    private Set<Map.Entry<String, ConfigValue>> pathParams = null;
    private Set<Map.Entry<String, ConfigValue>> queryParams = null;

    public static RequestElementsBuilder getInstance(){
        return requestElementsBuilder;
    }

    public RequestElementsBuilder setBaseURL(){
        this.baseUrl = CONFIG.getString(ApiConstants.BASEURL.toLowerCase());
        if(this.baseUrl == null || baseUrl.equalsIgnoreCase("") || baseUrl.isBlank())
            throw new IllegalArgumentException("Check the key/value in the \"application.conf\" file. Make sure \"baseurl\" is in lowercase. \"baseurl\" can not be null.");
        return this;
    }

    public RequestElementsBuilder setEndPoint(String endPoint){
       this.endPoint = CONFIG.getString(endPoint.toLowerCase());
        if(this.endPoint == null || this.endPoint.equalsIgnoreCase("") || this.endPoint.isBlank())
            throw new IllegalArgumentException("Check the key/value in the \"application.conf\" file. Make sure \"endpoint\" is in lowercase. \"endpoint\" can not be null.");
        return this;
    }

    public RequestElements buildRequestElements(){
//            we need to add headers, query params, path params, formParams etc.
        return RequestElements.builder().setBaseUrl(this.baseUrl).setEndpoint(this.endPoint).setPathParams(this.pathParams).setQueryParams(this.queryParams).build();
    }

    public RequestElementsBuilder setPathParam(String param) {
        this.pathParams = CONFIG.getConfig(param.toLowerCase()).entrySet();
        if(this.pathParams.contains(null) || this.pathParams.isEmpty())
            throw new IllegalArgumentException("Check the key/value in the \"application.conf\" file. Make sure \"pathparam\" is in lowercase. \"pathparam\" can not be empty. \nFormat \"pathParam name\" = \"pathParamValue\". \nMake sure pathParam name is same as param mentioned in endpoint.");
        return this;

    }

    public RequestElementsBuilder setQueryParam(String queryParams) {
        this.queryParams = CONFIG.getConfig(queryParams.toLowerCase()).entrySet();
        if(this.queryParams.isEmpty())
            throw new IllegalArgumentException("Check the key/value in the \"application.conf\" file. Make sure \"queryparam\" is in lowercase. \"queryparam\" can not be empty. \nFormat \"queryParam name\" = \"queryParamValue\".");
        return this;
    }

    public void resetAllRequestElements() {
        this.baseUrl=null;
        this.endPoint=null;
        this.pathParams=null;
        this.queryParams=null;
        buildRequestElements();
    }


}
