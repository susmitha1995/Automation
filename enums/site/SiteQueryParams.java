package com.sunpower.automation.edp.api.enums.site;

public enum SiteQueryParams {

    SITE_KEY("siteKey"),

    DEVICE_KEY("deviceKey"),

    START_DATE("startDate"),

    END_DATE("endDate"),

    INTERVAL("interval"),

    LABELS("labels"),

    DATALOGGER("datalogger"),

    STREET("street"),

    CITY("city"),

    STATE("state"),

    UTILITY_ID("utilityId"),

    SITE_TYPE("siteType"),

    INCLUDE_INACTIVE("include-inactive"),

    ZIP_CODE("zipCode"),

    ID("id"),

    DATATYPE("dataType"),

    INPUT("input"),

    INCLUDE_RESOLVED("include-resolved");

    private String queryParam;

    SiteQueryParams(String queryParam) {
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
