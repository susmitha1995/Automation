package com.sunpower.automation.edp.api.enums.party;

public enum PartyQueryParams {

    ORDER("order"),

    ALERT_STATUS("alertStatus"),

    PARTY_ID("partyId"),

    SITE_KEY("siteKey"),

    APP_NAME("appName");;

    private String queryParam;

    PartyQueryParams(String queryParam) {
        this.queryParam = queryParam;
    }

    /**
     * Get QueryParam
     * @return
     */
    public String getQueryParam() {
        return queryParam;
    }
}
