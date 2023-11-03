package com.sunpower.automation.edp.api.businessflow.device;

import com.sunpower.automation.api.base.AbstractRestActions;
import com.sunpower.automation.api.edp.entity.dao.DeviceCharacter;
import com.sunpower.automation.api.edp.entity.data.Device;
import com.sunpower.automation.core.enums.LogLevel;
import com.sunpower.automation.core.utils.DateUtil;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.dao.manager.EdpDao;
import com.sunpower.automation.edp.api.base.EdpAssertion;
import com.sunpower.automation.edp.api.enums.device.DevicePathParams;
import com.sunpower.automation.edp.api.enums.device.DeviceQueryParams;
import com.sunpower.automation.edp.api.interfaces.EdpHandler;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceApiBL extends AbstractRestActions implements EdpHandler {

    private static DeviceApiBL deviceApiBL = null;

    private EdpDao edpDao = EdpDao.getInstance();

    private Response response = null;

    private DeviceApiBL() {

    }

    /**
     * Get instance of DeviceApiBL
     *
     * @return
     */
    public static DeviceApiBL getInstance() {

        if (deviceApiBL == null) {
            deviceApiBL = new DeviceApiBL();
        }
        return deviceApiBL;
    }

    /**
     * @param testData - prepare Query parameter based on testData
     * @return
     */
    @Override
    public Map<String, Object> prepareQueryParams(Object testData) {

        LogUtil.log("Prepare query parameter", LogLevel.HIGH);
        Device data = (Device) testData;
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(DeviceQueryParams.SITE_KEY.getQueryParamKey(), data.getSiteKey());
        queryParams.put(DeviceQueryParams.DATA_TYPE.getQueryParamKey(), data.getDataType());
        queryParams.put(DeviceQueryParams.START_DATE.getQueryParamKey(), data.getStartDate());
        queryParams.put(DeviceQueryParams.END_DATE.getQueryParamKey(), data.getEndDate());
        queryParams.put(DeviceQueryParams.INTERVAL.getQueryParamKey(), data.getInterval());
        queryParams.put(DeviceQueryParams.DEVICE_ID.getQueryParamKey(), data.getQueryDeviceId());
        queryParams.put(DeviceQueryParams.SMS_ENABLED.getQueryParamKey(), data.getSmsEnabled());
        queryParams.put(DeviceQueryParams.MODULE_TYPE.getQueryParamKey(),
                data.getDevicePayload() != null ? data.getDevicePayload().getModuleType() : null);
        queryParams.put(DeviceQueryParams.MODEL_NAME.getQueryParamKey(),
                data.getDevicePayload() != null ? data.getDevicePayload().getModelName() : null);
        queryParams.put(DeviceQueryParams.WATTAGE_MIN.getQueryParamKey(),
                data.getDevicePayload() != null ? data.getDevicePayload().getWattageMin() : null);
        queryParams.put(DeviceQueryParams.WATTAGE_MAX.getQueryParamKey(),
                data.getDevicePayload() != null ? data.getDevicePayload().getWattageMax() : null);
        queryParams.put(DeviceQueryParams.ACTIVE.getQueryParamKey(),
                data.getDevicePayload() != null ? data.getDevicePayload().getActive() : null);
        queryParams.put(DeviceQueryParams.BUSINESS_UNIT.getQueryParamKey(),
                data.getDevicePayload() != null ? data.getDevicePayload().getBusinessUnit() : null);
        queryParams.put(DeviceQueryParams.MI_TYPE.getQueryParamKey(),
                data.getDevicePayload() != null ? data.getDevicePayload().getMiType() : null);
        queryParams.put(DeviceQueryParams.WATTAGE.getQueryParamKey(),
                data.getDevicePayload() != null ? data.getDevicePayload().getWattage() : null);
        if (data.getDevicePayload() != null && data.getDevicePayload().getItem() != null
                && data.getDevicePayload().getItem().getLabels().size() > 0) {
            String labels = data.getDevicePayload().getItem().getLabels().toString();
            queryParams.put(DeviceQueryParams.LABELS.getQueryParamKey(), labels.substring(1, labels.length() - 1));
        }
        queryParams.put(DeviceQueryParams.RETRIEVE_LAST_DATA.getQueryParamKey(), data.getRetrieveLastData());
        queryParams.put(DeviceQueryParams.RETRIEVE_STATUS.getQueryParamKey(), data.getRetrieveStatus());
        queryParams.put(DeviceQueryParams.DEVICE_TYPE.getQueryParamKey(), data.getDeviceType());
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
        Device data = (Device) testData;
        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(DevicePathParams.DEVICE_ID.getPathParamKey(), data.getDeviceId());
        pathParams.put(DevicePathParams.MODEL_NAME.getPathParamKey(), data.getDevicePayload() == null
                ? null : data.getDevicePayload().getModelName());
        pathParams.put(DevicePathParams.DEVICE_KEY.getPathParamKey(), data.getDeviceKey());
        pathParams.put(DevicePathParams.SERIAL_NUMBER.getPathParamKey(), data.getSerialNumber());
        pathParams.put(DevicePathParams.LOGGER_SERIAL_NUMBER.getPathParamKey(), data.getLoggerSerialNumber());
        pathParams.put(DevicePathParams.METER_SERIAL_NUMBER.getPathParamKey(), data.getDevicePayload() == null
                ? null : data.getDevicePayload().getMeterSerialNumber());
        pathParams.put(DevicePathParams.INVERTER_SERIAL_NUMBER.getPathParamKey(), data.getInverterSerialNumber());
        pathParams.put(DevicePathParams.MODULE_TYPE.getPathParamKey(), data.getModuleType());
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
     * Get Instance of DeviceApiCollectionBL
     *
     * @return
     */
    public DeviceApiCollectionBL getDeviceApiCollectionBL() {

        return DeviceApiCollectionBL.createInstance();
    }

    /**
     *
     */
    public DeviceCharacter validateDeviceCharFields(String characterLabel) {

        return edpDao.getDeviceCharacterByDeviceKey(getResponse()
                .jsonPath().getJsonObject("deviceKey"), characterLabel);
    }

    /**
     *
     */
    public DeviceCharacter validateDeviceCharFields(String deviceKey, String characterLabel) {

        return edpDao.getDeviceCharacterByDeviceKey(deviceKey, characterLabel);
    }

    /**
     * Validate the Module model data with requested test data
     */
    public boolean validateModuleModelData(Device testData) {

        return getResponse().jsonPath().getList("").stream().allMatch(data ->
                ((HashMap) data).get("moduleType").equals(testData.getDeviceData().getDevicePayload().getModuleType())
                        && (Integer) ((HashMap) data).get("wattage") > Integer.parseInt(testData.getDeviceData()
                        .getDevicePayload().getWattageMin())
                        && ((HashMap) data).get("active").equals(Boolean.valueOf(testData.getDeviceData()
                        .getDevicePayload().getActive()))
                        && ((HashMap) data).get("businessUnit").equals(testData.getDeviceData()
                        .getDevicePayload().getBusinessUnit()));
    }

    /**
     *
     */
    public void validateTheModuleKeyInResponse() {

        EdpAssertion.doAssert(getResponse()).responseBodyRootContainsKeys(Arrays.asList(new String[]{
                "modelName", "moduleType", "miType", "wattage", "active"}));
    }

    /**
     * Validate the device character api response with database result
     */
    public boolean validateDeviceCharacters(Device edpApiData) {

        List<DeviceCharacter> dbResult = edpDao.getDeviceCharactersByDeviceKey(
                edpApiData.getDeviceData().getDeviceId());
        return getResponse().jsonPath().getList("items").stream()
                .allMatch(apiData -> dbResult.stream().allMatch(dbData -> {
                    if (dbData.getCharLabel().equals(((HashMap) apiData).get("label"))) {
                        return dbData.getCharValue().equals(((HashMap) apiData).get("value"));
                    }
                    return true;
                }));
    }

    /**
     * Asserting timestamp of 5 min interval in the response
     *
     */
    public boolean assertAdjacentTimestampsMinutes() {

        LogUtil.log("Time Difference between adjacent timestamps: 5 Minutes", LogLevel.LOW);
        for (int i = 0; i <= response.jsonPath().getList("items.timestamp").size(); i++) {
            String timeStampOne = response.getBody().jsonPath().getString("items[" + i + "].timestamp");
            String timeStampTwo = response.getBody().jsonPath().getString("items[" + (i + 1) + "].timestamp");
            if (timeStampOne != null && timeStampTwo != null) {
                if (DateUtil.getUTCTimeDifferenceMinutes(timeStampOne, timeStampTwo) != 5) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Casting the type "Wattage" and "active"
     * "Wattage" to Integer and "active" to Boolean
     */
    public Map<String, Object> convertTypeOfMapValue(Map<String, Object> data){

        if(data.containsKey("wattage") && data.containsKey("active")){
            data.put("wattage", Integer.parseInt((String) data.get("wattage")));
            data.put("active", Boolean.valueOf((String)data.get("active")));
        }
        return data;
    }
}
