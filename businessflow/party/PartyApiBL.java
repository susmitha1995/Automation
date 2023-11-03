package com.sunpower.automation.edp.api.businessflow.party;

import com.sunpower.automation.api.base.AbstractRestActions;
import com.sunpower.automation.api.edp.entity.data.Party;
import com.sunpower.automation.core.enums.LogLevel;
import com.sunpower.automation.core.utils.GsonUtils;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.core.utils.StringUtil;
import com.sunpower.automation.dao.manager.EdpDao;
import com.sunpower.automation.edp.api.enums.party.PartyPathParams;
import com.sunpower.automation.edp.api.enums.party.PartyQueryParams;
import com.sunpower.automation.edp.api.interfaces.EdpHandler;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PartyApiBL extends AbstractRestActions implements EdpHandler {

    private static PartyApiBL partyApiBL = null;

    private Response response = null;

    private EdpDao edpDao = EdpDao.getInstance();

    private PartyApiBL() {

    }

    /**
     * Get instance of Party API
     *
     * @return PartyApiBL
     */
    public static PartyApiBL getInstance() {

        if (partyApiBL == null) {
            partyApiBL = new PartyApiBL();
        }
        return partyApiBL;
    }

    /**
     * @param testData - prepare Query parameter based on testData
     * @return Map value
     */
    @Override
    public Map<String, Object> prepareQueryParams(Object testData) {

        LogUtil.log("Prepare query parameter", LogLevel.HIGH);
        Party data = (Party) testData;
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(PartyQueryParams.ORDER.getQueryParam(), data.getOrder());
        queryParams.put(PartyQueryParams.ALERT_STATUS.getQueryParam(), data.getAlertStatus());
        queryParams.put(PartyQueryParams.SITE_KEY.getQueryParam(), data.getSiteKey());
        queryParams.put(PartyQueryParams.APP_NAME.getQueryParam(), data.getAppName());

        queryParams.values().removeAll(Collections.singleton(null));

        return queryParams;
    }

    /**
     * @param testData - prepare path parameter based on testData
     * @return Map value
     */
    @Override
    public Map<String, Object> preparePathParams(Object testData) {

        LogUtil.log("Prepare path parameter", LogLevel.HIGH);
        Party data = (Party) testData;
        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(PartyPathParams.PARENT_ID.getPathParam(), data.getParentId());
        pathParams.put(PartyPathParams.PARTY_ID.getPathParam(), data.getPartyId());
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
     * Validate the Party fields
     */
    public TreeMap<String, Object> validatePartyFields(String key) {

        return GsonUtils.objectToMap(edpDao.getPartyById(getResponse().jsonPath().getJsonObject(key)));
    }

    /**
     * @param userConfig
     * @return
     */
    public boolean isUserConfCommentsUpdated(Party userConfig) {

        List userConfigApiResult = (List) getResponse().jsonPath().getList("appRating");
        return ((HashMap) userConfigApiResult.get(userConfigApiResult.size() - 1))
                .get("comment").equals(userConfig.getAppRating().getComment());

    }

    /**
     * Validate the Organization alerts report in the response
     */
    public boolean validateOrgAlertsReport() {

        if (getResponse().jsonPath().getJsonObject("") != null) {
            HashMap result = (HashMap) getResponse().jsonPath().getJsonObject("");
            if (getResponse().statusCode() == 202) {

                return result.containsKey("ResponseMetadata")
                        && StringUtil.hasProperValue((String) result.get("ResponseMetadata"))
                        && result.containsKey("MD5OfMessageBody")
                        && StringUtil.hasProperValue((String) result.get("MD5OfMessageBody"))
                        && result.containsKey("MD5OfMessageAttributes")
                        && StringUtil.hasProperValue((String) result.get("MD5OfMessageAttributes"))
                        && result.containsKey("MessageId")
                        && StringUtil.hasProperValue((String) result.get("MessageId"));
            } else if (getResponse().statusCode() == 429) {
                return result.get("message").equals("Too Many Requests");
            } else {
                return (getResponse().statusCode() == 202 || getResponse().statusCode() == 429);
            }
        }
        return false;
    }

    /**
     * @return
     */
    public TreeMap<String, Object> validateSiteFields(String key) {

        return GsonUtils.objectToMap(edpDao.getSiteBySiteKey(response.jsonPath().getJsonObject(key)));
    }
}
