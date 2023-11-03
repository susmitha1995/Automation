package com.sunpower.automation.edp.api.businessflow.search;

import com.sunpower.automation.api.base.AbstractRestActions;
import com.sunpower.automation.api.edp.entity.data.Search;
import com.sunpower.automation.core.enums.LogLevel;
import com.sunpower.automation.core.utils.GsonUtils;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.dao.manager.EdpDao;
import com.sunpower.automation.edp.api.enums.search.SearchPathParams;
import com.sunpower.automation.edp.api.enums.search.SearchQueryParams;
import com.sunpower.automation.edp.api.interfaces.EdpHandler;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SearchApiBL extends AbstractRestActions implements EdpHandler {

    private static SearchApiBL searchApiBL = null;

    private Response response = null;

    EdpDao edpDao = EdpDao.getInstance();

    private SearchApiBL() {

    }

    /**
     * Get instance of SearchApiBL
     *
     * @return SearchApiBL
     */
    public static SearchApiBL getInstance() {

        if (searchApiBL == null) {
            searchApiBL = new SearchApiBL();
        }
        return searchApiBL;
    }

    /**
     * @param testData - prepare Query parameter based on testData
     * @return Map Value
     */
    @Override
    public Map<String, Object> prepareQueryParams(Object testData) {

        LogUtil.log("Prepare query parameter", LogLevel.HIGH);
        Search data = (Search) testData;
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(SearchQueryParams.DATALOGGER.getQueryParamKey(), data.getDatalogger());
        queryParams.put(SearchQueryParams.STREET.getQueryParamKey(), data.getStreet());
        queryParams.put(SearchQueryParams.Q.getQueryParamKey(), data.getQ());
        queryParams.put(SearchQueryParams.PG.getQueryParamKey(), data.getPg());
        queryParams.put(SearchQueryParams.SORT_FIELD.getQueryParamKey(), data.getSortfield());
        queryParams.put(SearchQueryParams.ORDER.getQueryParamKey(), data.getOrder());
        queryParams.values().removeAll(Collections.singleton(null));

        return queryParams;
    }

    /**
     * @param testData - prepare path parameter based on testData
     * @return Map - Query Path as a Map
     */
    @Override
    public Map<String, Object> preparePathParams(Object testData) {

        LogUtil.log("Prepare path parameter", LogLevel.HIGH);
        Search data = (Search) testData;
        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(SearchPathParams.INDEX_ID.getPathParamKey(), data.getIndexId());
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

    /**
     * @return
     */
    public TreeMap<String, Object> validateAddressFields(String key) {

        return GsonUtils.objectToMap(edpDao.getSiteBySiteKey(response.jsonPath().getJsonObject(key)));
    }

    /**
     * @return
     */
    public TreeMap<String, Object> validateDeviceFields(String key) {

        return GsonUtils.objectToMap(edpDao.getDeviceBySerialNumber(response.jsonPath().getJsonObject(key)));
    }

    /**
     * @param key
     * @return
     */
    public TreeMap<String, Object> validatePartyFields(String key) {

        return GsonUtils.objectToMap(edpDao.getPartyById(response.jsonPath().getJsonObject(key)));
    }

    /**
     * @param key
     * @return
     */
    public TreeMap<String, Object> validateOrganizationTypeFields(String key) {

        return GsonUtils.objectToMap(edpDao.getActiveAndOrganizationTypePartyByOrgName(response
                .jsonPath().getJsonObject(key)));
    }
}
