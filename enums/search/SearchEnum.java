package com.sunpower.automation.edp.api.enums.search;

public enum SearchEnum {

    SITES_ADDRESS("v1/search/sites/address", "Find sites using a normalized address"),

    INDEX_SITE_V1("v1/search/index/site", "Searching on a site in V1"),

    INDEX_SITE_V2("v2/search/index/site", "Endpoint for searching on a site"),

    GLOBAL("/v1/search/global", "Endpoint for searching across all indices device/site/user/orgs"),

    INDEX("/v1/search/index/{indexId}", "Index search");

    private String url;

    private String description;

    SearchEnum(String url, String description) {

        this.url = url;
        this.description = description;
    }

    /**
     * Get PathParam
     * @return
     */
    public String getPathParam() {
        return url;
    }
}

