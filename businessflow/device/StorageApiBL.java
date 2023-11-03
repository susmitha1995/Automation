package com.sunpower.automation.edp.api.businessflow.device;

import com.sunpower.automation.api.base.AbstractRestActions;
import com.sunpower.automation.api.edp.entity.data.Device;
import com.sunpower.automation.core.enums.LogLevel;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.edp.api.enums.site.SitePathParams;
import com.sunpower.automation.edp.api.interfaces.EdpHandler;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc

/**
 * The Class StorageApiBL.
 */
public class StorageApiBL extends AbstractRestActions implements EdpHandler {

    /**
     * The storage.
     */
    private static StorageApiBL storage = null;

    private Response response = null;

    /**
     * Instantiates a new storage api BL.
     */
    private StorageApiBL() {

    }

    /**
     * Gets the single instance of StorageApiBL.
     *
     * @return single instance of StorageApiBL
     */
    public static StorageApiBL getInstance() {

        if (storage == null) {
            storage = new StorageApiBL();
        }
        return storage;
    }

    /**
     * Prepare query params.
     *
     * @param testData - prepare Query parameter based on testData
     * @return the map
     */
    @Override
    public Map<String, Object> prepareQueryParams(Object testData) {
        LogUtil.log("Prepare query parameter", LogLevel.HIGH);
        Device data = (Device) testData;
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(SitePathParams.INCLUDE_RESOLVED.getPathParamKey(), data.getIncludeResolved());
        queryParams.values().removeAll(Collections.singleton(null));
        return queryParams;
    }

    /**
     * Prepare path params.
     *
     * @param testData - prepare path parameter based on testData
     * @return the map
     */
    @Override
    public Map<String, Object> preparePathParams(Object testData) {

        LogUtil.log("Prepare path parameter", LogLevel.HIGH);
        Device data = (Device) testData;
        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(SitePathParams.SITE_KEY.getPathParamKey(), data.getSiteKey());
        pathParams.values().removeAll(Collections.singleton(null));
        pathParams.values().removeAll(Collections.singleton(null));
        return pathParams;
    }

    @Override
    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public Response getResponse() {
        return response;
    }
}
