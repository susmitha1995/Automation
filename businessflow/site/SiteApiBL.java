package com.sunpower.automation.edp.api.businessflow.site;

import com.sunpower.automation.api.edp.entity.dao.Device;
import com.sunpower.automation.api.edp.entity.dao.Subscription;
import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.enums.LogLevel;
import com.sunpower.automation.core.enums.Steps;
import com.sunpower.automation.core.utils.DateUtil;
import com.sunpower.automation.core.utils.GsonUtils;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.dao.manager.EdpDao;
import com.sunpower.automation.edp.api.enums.site.SiteBadReqQueryParams;
import com.sunpower.automation.edp.api.enums.site.SitePathParams;
import com.sunpower.automation.edp.api.enums.site.SiteQueryParams;
import com.sunpower.automation.edp.api.interfaces.EdpHandler;
import com.sunpower.automation.edp.api.payload.EdpPayload;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class SiteApiBL implements EdpHandler {

    private static SiteApiBL siteApiBL = null;

    private EdpDao edpDao = EdpDao.getInstance();

    private Response response = null;

    private SiteApiBL() {

    }

    /**
     * Get instance of SiteApiBL
     *
     * @return
     */
    public static SiteApiBL getInstance() {

        if (siteApiBL == null) {
            siteApiBL = new SiteApiBL();
        }
        return siteApiBL;
    }

    /**
     * @param testData - prepare Query parameter based on testData
     * @return
     */
    @Override
    public Map<String, Object> prepareQueryParams(Object testData) {

        LogUtil.log("Prepare query parameter", LogLevel.HIGH);
        Site data = (Site) testData;
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(SiteQueryParams.SITE_KEY.getQueryParamKey(), data.getSiteKey());
        queryParams.put(SiteQueryParams.DEVICE_KEY.getQueryParamKey(), data.getDeviceKey());
        queryParams.put(SiteQueryParams.START_DATE.getQueryParamKey(), data.getStartDate());
        queryParams.put(SiteQueryParams.END_DATE.getQueryParamKey(), data.getEndDate());
        queryParams.put(SiteQueryParams.INTERVAL.getQueryParamKey(), data.getInterval());
        queryParams.put(SiteQueryParams.DATALOGGER.getQueryParamKey(),
                data.getAddress() == null ? null : data.getAddress().getDatalogger());
        queryParams.put(SiteQueryParams.STREET.getQueryParamKey(),
                data.getAddress() == null ? null : data.getAddress().getStreet());
        queryParams.put(SiteQueryParams.CITY.getQueryParamKey(),
                data.getAddress() == null ? null : data.getAddress().getCity());
        queryParams.put(SiteQueryParams.STATE.getQueryParamKey(),
                data.getAddress() == null ? null : data.getAddress().getState());
        queryParams.put(SiteQueryParams.INCLUDE_INACTIVE.getQueryParamKey(),
                data.getAddress() == null ? null : data.getAddress().getIncludeInactive());
        queryParams.put(SiteQueryParams.UTILITY_ID.getQueryParamKey(), data.getUtilityId());
        queryParams.put(SiteQueryParams.SITE_TYPE.getQueryParamKey(), data.getSiteType());
        queryParams.put(SiteQueryParams.SITE_TYPE.getQueryParamKey(), data.getSiteType());
        queryParams.put(SiteQueryParams.ZIP_CODE.getQueryParamKey(), data.getZipCode());
        queryParams.put(SiteQueryParams.LABELS.getQueryParamKey(), data.getLabels());
        queryParams.put(SiteQueryParams.ID.getQueryParamKey(), data.getId());
        queryParams.put(SiteQueryParams.DATATYPE.getQueryParamKey(), data.getDataType());
        queryParams.put(SiteQueryParams.INPUT.getQueryParamKey(), data.getInput());
        queryParams.put(SiteQueryParams.INCLUDE_RESOLVED.getQueryParamKey(), data.getIncludeResolved());
        queryParams.values().removeAll(Collections.singleton(null));

        return queryParams;
    }

    /**
     * @param testData - prepare Bad Request Query parameter based on testData
     * @return
     */
    public Map<String, Object> prepareBadQueryParams(Object testData) {

        LogUtil.log("Prepare bad query parameter", LogLevel.HIGH);
        Site data = (Site) testData;
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(SiteBadReqQueryParams.STE_KEY.getQueryParamKey(), data.getSiteKey());
        queryParams.put(SiteBadReqQueryParams.START_DAT.getQueryParamKey(), data.getStartDate());
        queryParams.values().removeAll(Collections.singleton(null));

        return queryParams;
    }

    /**
     * @param testData - prepare path parameter based on testData
     * @return
     */
    @Override
    public Map<String, Object> preparePathParams(Object testData) {

        LogUtil.log("Prepare path parameter", LogLevel.HIGH);
        Site data = (Site) testData;
        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(SitePathParams.SITE_KEY.getPathParamKey(), data.getSiteKey());
        pathParams.put(SitePathParams.STATE.getPathParamKey(), data.getState());
        pathParams.put(SitePathParams.LOGGER_SERIAL_NUMBER.getPathParamKey(), data.getLoggerSerialNumber());
        pathParams.put(SitePathParams.PARTY_ID.getPathParamKey(), data.getPartyId());
        pathParams.put(SitePathParams.NEW_SITE_KEY.getPathParamKey(), data.getNewSiteKey());
        pathParams.put(SitePathParams.DEALER_ID.getPathParamKey(), data.getDealerId());
        pathParams.values().removeAll(Collections.singleton(null));

        return pathParams;
    }

    /**
     * setResponse
     *
     * @param response
     */
    @Override
    public void setResponse(Response response) {
        this.response = response;
    }

    /**
     * getResponse
     *
     * @return Response
     */
    @Override
    public Response getResponse() {
        return response;
    }

    /**
     *
     */
    public void validateErrorCodeField() {

        if (getResponse().jsonPath().getList("items").size() > 0) {
            Assert.assertTrue(getResponse().jsonPath().getList("items.ErrCd")
                            .stream().anyMatch(data -> (Integer) data > 0),
                    "'ErrCd' should not be null or empty");
        } else {
            LogUtil.log(Steps.END, "Need not validate 'Errcd' field since items are empty");
        }
    }

    /**
     * Validate the mandatory fields of two different Subscription
     */
    public TreeMap<String, Object> validateSubscriptionFields() {

        Subscription apiResult = getResponse().jsonPath().getObject("[0]", Subscription.class);
        Subscription dbResult = edpDao.getSubscriptionsBySiteKeyAndId(
                apiResult.getSiteKey(), apiResult.getSubscriptionId());
        TreeMap dbResultAsMap = GsonUtils.objectToMap(dbResult);
        dbResultAsMap.put("scheduleDay", Integer.parseInt((String) dbResultAsMap.get("scheduleDay")));
        return dbResultAsMap;
    }

    /**
     * Validate the address fields
     */
    public TreeMap<String, Object> validateAddressFields(String jsonKey) {

        return GsonUtils.objectToMap(edpDao.getAddressBySiteKey(getResponse().jsonPath().getJsonObject(jsonKey)));
    }

    /**
     * Validate the address fields
     */
    public TreeMap<String, Object> validateSiteAssignmentFields() {

        return GsonUtils.objectToMap(edpDao.getAssignmentByDeviceKey(getResponse().jsonPath()
                .getJsonObject("[0].deviceKey")));
    }

    /**
     * Validate the address fields
     */
    public TreeMap<String, Object> validateSiteCharacterFields(String jsonKey, String characterLabel) {

        Map<String, String> apiData = getResponse().jsonPath().getJsonObject(jsonKey);
        TreeMap<String, Object> dbResult = GsonUtils.objectToMap(edpDao
                .getSiteCharacterBySiteKey(apiData.get("siteKey"), characterLabel));

        return dbResult;
    }

    /**
     * Validate the address fields
     */
    public String validateNoteContent(String jsonKey) {

        return edpDao.getNoteByNoteId(getResponse().jsonPath().getJsonObject(jsonKey)).getContent();
    }

    /**
     * Prepare Notes Payload
     *
     * @param notes
     * @return
     */
    public String prepareNotesPayload(Site notes) {
        notes.getNotes().get(0).setNoteId(getResponse().jsonPath().getJsonObject("[0].noteId"));
        notes.getNotes().get(0).setContent(getResponse().jsonPath().getJsonObject("[0].content")
                + DateUtil.getCurrentDate("mmSS"));
        return EdpPayload.getInstance().createPayload(notes);
    }

    /**
     * Validate the Party fields
     */
    public TreeMap<String, Object> validatePartyFields(String jsonKey) {

        return GsonUtils.objectToMap(edpDao.getPartyById(getResponse().jsonPath().getJsonObject(jsonKey)));
    }

    /**
     * Validate the Device fields
     */
    public TreeMap<String, Object> validateDeviceFields(String jsonKey) {

        Device device = edpDao.getDeviceByDeviceKey(getResponse().jsonPath().getJsonObject(jsonKey));
        device.setDeviceType(device.getDeviceType().toLowerCase());
        return GsonUtils.objectToMap(device);
    }

    /**
     * @param site
     * @return
     */
    public Site getAvailableKiosk(Site site) {

        site.setId(edpDao.getAvailableKioskGuid(1).getKioskGuid());
        return site;
    }

    /**
     * @return - String siteKey
     */
    public String getSiteByKey() {

        return edpDao.getSiteBySiteKey(getResponse().jsonPath().getJsonObject("siteKey")).getSiteKey();
    }

    /**
     * @return
     */
    public TreeMap<String, Object> validateDeviceFieldsBySerialNumber(String jsonKey) {

        return GsonUtils.objectToMap(edpDao.getDeviceBySerialNumber(response.jsonPath().getJsonObject(jsonKey)));
    }

    /**
     * @return
     */
    public Map<String, Object> validateUtilityMappingBySiteKey(String siteKey) {

        return (Map) edpDao.getUtilityMappingBySiteKey(siteKey).stream().filter(data -> data.values()
                .contains(response.jsonPath().getJsonObject("utilities[0].utilityId"))).collect(Collectors.toList()).get(0);
    }

    /**
     * Validate the dealer fields
     */
    public TreeMap<String, Object> validateSiteBySiteKey(String siteKey) {

        return GsonUtils.objectToMap(edpDao.getSiteBySiteKey(siteKey));
    }

    /**
     * Validate the dealer fields
     * @return
     */
    public Map<String, Object> validateUtilityTariff(String jsonKey) {

        String siteKey = getResponse().jsonPath().getJsonObject(jsonKey);
        Map<String, Object> filterMapObj = new HashMap<>(edpDao.getUtilityAndTariffForSite(siteKey).get(siteKey));
        return filterMapObj;
    }

    /**
     *
     * @return
     */
    public Map<String, String> getDataSiteKeyAndSerialNumberForWhichSysSizeNotNull(){

        return edpDao.getAllSiteKeyAndSerialNumberForWhichSystemSizeIsNotNull();
    }

    /**
     *
     * @param siteKey
     * @return
     */
    public boolean checkSystemSizeIsNullInSiteTable(String siteKey){

        return edpDao.getSiteBySiteKey(siteKey).getSystemSize().equals("null");
    }
}
