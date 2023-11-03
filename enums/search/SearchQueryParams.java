package com.sunpower.automation.edp.api.enums.search;

public enum SearchQueryParams {

    DATALOGGER("datalogger"),

    STREET("street"),

    Q("q"),

    PG("pg"),

    SORT_FIELD("sortfield"),

    ORDER("order");

    private String queryParam;

    SearchQueryParams(String queryParam) {
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
