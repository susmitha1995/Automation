package com.sunpower.automation.edp.api.base;


import com.sunpower.automation.api.base.AbstractRestActions;
import com.sunpower.automation.api.enums.HttpOperations;
import com.sunpower.automation.api.utils.UserTokenGen;
import com.sunpower.automation.core.enums.LogLevel;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.core.utils.ParamUtil;
import com.sunpower.automation.core.utils.StringUtil;
import com.sunpower.automation.edp.api.interfaces.EdpBaseEnum;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.getProperty;

public class EdpApiRestActions extends AbstractRestActions {

    protected static Class<?> buzzLogicClazz;

    protected static EdpApiRestActions edpApiRestActions;

    private RequestSpecBuilder request = null;

    protected Response response = null;

    private String bearerToken = null;

    private String invalidToken = null;

    private EdpApiRestActions(Class<?> buzzLogicClazz) {
        this.buzzLogicClazz = buzzLogicClazz;
    }

    /**
     * @param buzzLogicClazz
     * @return
     */
    public static EdpApiRestActions getInstance(Class<?> buzzLogicClazz) {
        if (edpApiRestActions == null) {
            RestAssured.baseURI = EdpBaseEnum.ServerURL.valueOf(ParamUtil.getTestEnv().toUpperCase()).getServerURL();
            edpApiRestActions = new EdpApiRestActions(buzzLogicClazz);
        }
        return edpApiRestActions;
    }

    /**
     * Prepare End-point
     * @return
     */
    public EdpApiRestActions url(Enum url) {

        request = new RequestSpecBuilder();
        request.setBasePath(getEndpoint(url));
        return this;
    }

    /**
     * Prepare Path Parameter
     * @return
     */
    public EdpApiRestActions pathParam(Object testData) {

        request.addPathParams(getPathParamFromBuzzClass(testData));
        return this;
    }

    /**
     * Prepare Query Parameter
     * @return
     */
    public EdpApiRestActions queryParam(Object testData) {

        request.addQueryParams(getQueryParamFromBuzzClass(testData));
        return this;
    }

    /**
     * Prepare Bad Query Parameter
     * @return
     */
    public EdpApiRestActions badQueryParam(Object testData) {

        request.addQueryParams(getBadQueryParamFromBuzzClass(testData));
        return this;
    }

    /**
     * Prepare the body for the request call
     * @return
     */
    public EdpApiRestActions body(String body) {

        request.setBody(body);
        return this;
    }

    /**
     * Apply the 'Get' call for RequestSpecBuilder
     * @return
     */
    public EdpApiRestActions get() {

        return invoke(HttpOperations.GET);
    }

    /**
     * Apply the 'Post' call for RequestSpecBuilder
     * @return
     */
    public EdpApiRestActions post() {

        return invoke(HttpOperations.POST);
    }

    /**
     * Apply the 'Delete' call for RequestSpecBuilder
     * @return
     */
    public EdpApiRestActions delete() {

        return invoke(HttpOperations.DELETE);
    }

    /**
     * Apply the 'Patch' call for RequestSpecBuilder
     * @return
     */
    public EdpApiRestActions patch() {

        return invoke(HttpOperations.PATCH);
    }

    /**
     * Apply the 'Put' call for RequestSpecBuilder
     * @return
     */
    public EdpApiRestActions put() {

        return invoke(HttpOperations.PUT);
    }

    private EdpApiRestActions invoke(HttpOperations operations) {

        prepareHeader();
        response = submitRestRequest(operations, request.build());
        setResponseToBuzzClass(response);
        invalidToken = null;
        return this;
    }

    /**
     * Set Invalid token
     *
     * @return
     */
    public EdpApiRestActions withInvalidToken() {

        invalidToken = prepareToken() + RandomStringUtils.randomAlphabetic(10);
        return this;
    }

    /**
     * Get the instance of EdpAssertion by current response
     * @return RestAssert
     */
    public EdpAssertion doAssert() {
       return EdpAssertion.doAssert(response);
    }

    /**
     * Get response
     * @return
     */
    public Response getResponse() {
        return response;
    }

    /**
     * set prepare header
     */
    private void prepareHeader() {

        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        headers.put("authorization", StringUtil.hasProperValue(invalidToken) ? invalidToken : prepareToken());
        request.addHeaders(headers);
    }

    /**
     * Get end point from the Enum Parameter
     *
     * @param endPoint
     * @return
     */
    private static String getEndpoint(Enum endPoint) {

        try {
            return (String) endPoint.getDeclaringClass().getMethod("getPathParam").invoke(endPoint);
        } catch (NoSuchMethodException noSuchMethodException) {
            LogUtil.log("'getPathParam()' is mandatory in " + Enum.class.getCanonicalName(),
                    LogLevel.HIGH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Accessing business class fields for Preparing Path Parameter
     *
     * @param testData
     * @return HashMap<String, String>
     */
    private static HashMap<String, String> getPathParamFromBuzzClass(Object testData) {

        try {
            Object instance = buzzLogicClazz.getMethod("getInstance").invoke(buzzLogicClazz);
            return (HashMap<String, String>) buzzLogicClazz.getMethod("preparePathParams", Object.class)
                    .invoke(instance, testData);

        } catch (InvocationTargetException ex) {
            ex.getTargetException().printStackTrace();
        } catch (NoSuchMethodException noSuchMethodException) {
            LogUtil.log("'getInstance() and preparePathParams()' methods are mandatory in "
                    + buzzLogicClazz.getCanonicalName(), LogLevel.HIGH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Set response to business class's for accessing in BL methods
     *
     * @param response
     */
    private static void setResponseToBuzzClass(Response response) {

        try {
            Object instance = buzzLogicClazz.getMethod("getInstance").invoke(buzzLogicClazz);
             buzzLogicClazz.getMethod("setResponse", Response.class)
                    .invoke(instance, response);

        } catch (NoSuchMethodException noSuchMethodException) {
            LogUtil.log("'setResponse() ' method is mandatory in "
                            + buzzLogicClazz.getClass().getCanonicalName(),
                    LogLevel.HIGH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Accessing business class fields for Preparing Query Parameter
     *
     * @param testData
     * @return
     */
    private static HashMap<String, String> getQueryParamFromBuzzClass(Object testData) {
        try {
            Object instance = buzzLogicClazz.getMethod("getInstance").invoke(buzzLogicClazz);
            return (HashMap<String, String>) buzzLogicClazz.getMethod("prepareQueryParams", Object.class)
                    .invoke(instance, testData);

        } catch (NoSuchMethodException noSuchMethodException) {
            LogUtil.log("'getInstance() and prepareQueryParams()' methods are mandatory in "
                            + buzzLogicClazz.getClass().getCanonicalName(),
                    LogLevel.HIGH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Accessing business class fields for Preparing Bad Query Parameter
     *
     * @param testData
     * @return
     */
    private static HashMap<String, String> getBadQueryParamFromBuzzClass(Object testData) {
        try {
            Object instance = buzzLogicClazz.getMethod("getInstance").invoke(buzzLogicClazz);
            return (HashMap<String, String>) buzzLogicClazz.getMethod("prepareBadQueryParams", Object.class)
                    .invoke(instance, testData);

        } catch (NoSuchMethodException noSuchMethodException) {
            LogUtil.log("'getInstance() and prepareBadQueryParams()' methods are mandatory in "
                            + buzzLogicClazz.getClass().getCanonicalName(),
                    LogLevel.HIGH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Prepare token
     *
     * @return
     */
    private String prepareToken() {

        if (!StringUtil.hasProperValue(bearerToken)) {

            bearerToken = UserTokenGen.getInstance(getProperty("environment") != null
                            ? getProperty("environment").toUpperCase() : "QA")
                    .getEmployeeToken(System.getProperty("monitorUserName"), System.getProperty(
                            "monitorPassword"));
            RestAssured.baseURI = EdpBaseEnum.ServerURL.valueOf(
                    ParamUtil.getTestEnv().toUpperCase()).getServerURL();
            return bearerToken;
        } else {
            return bearerToken;
        }
    }
}
