package com.sunpower.automation.edp.api.interfaces;

import io.restassured.response.Response;

import java.util.Map;

public interface EdpHandler {

    /**
     * Prepare query parameter bases on testdata
     *
     * @param testData - prepare Query parameter based on testData
     * @return - Map type string as key and value as object
     */
    Map<String, Object> prepareQueryParams(Object testData);

    /**
     * Prepare path parameter bases on testdata
     *
     * @param testData - prepare path parameter based on testData
     * @return - Map type string as key and value as object
     */
    Map<String, Object> preparePathParams(Object testData);


    /**
     * @param response
     * @return
     */
    public void setResponse(Response response);

    /**
     * @return Response
     */
    public Response getResponse();
}
