package com.sunpower.automation.edp.api.enums.search;

public enum SearchPathParams {

    SITE_KEY("siteKey"),

    INDEX_ID("indexId");

    private String pathParam;

    SearchPathParams(String pathParam) {
        this.pathParam = pathParam;
    }

    /**
     * Get PathParamKey
     * @return
     */
    public String getPathParamKey() {
        return pathParam;
    }
}
