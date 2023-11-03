package com.sunpower.automation.edp.api.enums.device;

public enum DeviceQueryParams {

    DEVICE_ID("deviceId"),

    SMS_ENABLED("sms-enabled"),

    MODULE_TYPE("module-type"),

    WATTAGE_MIN("wattage-min"),

    WATTAGE_MAX("wattage-max"),

    WATTAGE("wattage"),

    ACTIVE("active"),

    BUSINESS_UNIT("business-unit"),

    LABELS("labels"),

    SITE_KEY("siteKey"),

    START_DATE("startDate"),

    END_DATE("endDate"),

    INTERVAL("interval"),

    DATA_TYPE("dataType"),

    RETRIEVE_LAST_DATA("retrieveLastData"),

    RETRIEVE_STATUS("retrieveStatus"),

    DEVICE_TYPE("deviceType"),

    MODEL_NAME("model-name"),

    MI_TYPE("mi-type");

    private String queryParam;

    DeviceQueryParams(String queryParam) {
        this.queryParam = queryParam;
    }

    /**
     * Get Query ParamKey
     * @return
     */
    public String getQueryParamKey() {
        return queryParam;
    }
}
