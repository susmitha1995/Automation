package com.sunpower.automation.edp.api.enums.site;

public enum SiteBadReqQueryParams {

    STE_KEY("steKey"),

    START_DAT("startDat");

    private String queryParam;

    SiteBadReqQueryParams(String queryParam) {
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
