package com.sunpower.automation.edp.api.enums.site;

public enum SitePathParams {

    SITE_KEY("siteKey"),

    STATE("state"),

    LOGGER_SERIAL_NUMBER("loggerSerialNumber"),

    PARTY_ID("partyId"),

    INCLUDE_RESOLVED("include-resolved"),

    NEW_SITE_KEY("newSiteKey"),

    DEALER_ID("dealerId");

    private String pathParam;

    SitePathParams(String pathParam) {
        this.pathParam = pathParam;
    }

    /**
     * Get Path ParamKey
     * @return
     */
    public String getPathParamKey() {
        return pathParam;
    }
}
