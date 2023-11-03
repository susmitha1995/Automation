package com.sunpower.automation.edp.api.enums.site;

public enum SiteEnum {

    ASSIGNMENT("/v1/site/assignment", "Get site-device assignment record using site key or device key."),

    SITE("/v2/site", "Creates a site in MDS only"),

    GET_SITE("/v1/site/{siteKey}", "Get a site base on siteKey"),

    GET_PANEL_ENERGY("/v1/site/{siteKey}/panelEnergy", "Validates if the site panel energy is present or not"),

    POWER("/v2/site/{siteKey}/power", "Gets power production for a site"),

    ENERGY("/v2/site/{siteKey}/energy", "Get energy production for a site"),

    OPERATIONAL("/v1/site/{siteKey}/operational-mode", "Retrieves operational mode for a site."),

    PARTY("/v1/site/{siteKey}/party", "Get party by site key"),

    CHARACTERISTIC("/v1/site/{siteKey}/characteristic", "Retrieves the specified characteristic values for the given site"),

    DATA_SOURCE("/v1/site/{siteKey}/datasource", "Retrieve ELH data source for a site"),

    CURRENT_WEATHER("/v1/site/{siteKey}/currentweather", "Return current weather for a site"),

    CURRENT_WEATHER_V2("/v2/site/{siteKey}/currentweather", "Gets current weather data for a site"),

    WEATHER_V2("/v2/site/{siteKey}/weather", "Get V2 weather of the site"),

    SUBSCRIPTIONS("/v1/site/{siteKey}/subscriptions", "Retrieves all of the active subscriptions for a specific site"),

    INCENTIVES("/v1/site/incentives/{state}", "Get available incentive plans for a given state"),

    METRIC_BATTERY("/v1/site/{siteKey}/metric/battery", "Get state of charge and charge capacity of all attached energy storage systems"),

    METRIC("/v1/site/{siteKey}/metric", "Get data for specified metric(s)"),

    ADDRESS("/v1/site/address", "Find sites using a normalized address"),

    DEALERS("/v1/site/{siteKey}/dealers", "Get all dealers for a given siteKey"),

    TARIFF("/v1/site/tariff", "Get list of tariffs for the given utility and site type combination"),

    STORED_UTILITY_AND_TARIFF("/v1/site/{siteKey}/tariff", "Get stored utility and tariff for the given Site"),

    UTILITY("/v1/site/utility", "Get list of utilities for the given zip code"),

    ALL_ALERT("/v1/site/{siteKey}/alert", "Get all alerts for the given siteKey"),

    ACTIVE_ALERT("/v1/site/{siteKey}/alert/active", "Get current active (OPEN) alerts for the given siteKey, excluding the last 48 hours"),

    NOTES("/v1/site/{siteKey}/notes", "Update or Get site notes"),

    SYSTEM_SIZE("/v1/site/{siteKey}/system-size", "For a given site, calculates the system size, updates it and returns it in the response."),

    LAYOUT("/v1/site/{siteKey}/layout", "Returns stored layout for the given site"),

    LAYOUT_FILE("/v1/site/{siteKey}/layout/file", "Upload or delete a file to given site"),

    LWKIOSK("/v1/site/lwkiosk", "Returns LiveWire Kiosk metrics from a Kiosk GUID"),

    SERVICE_CODES("/v1/site/{siteKey}/service-codes", "Retrieves the service codes occurred during the last 60 days in any device in the site"),

    DEVICE_POWER("/v1/device/{loggerSerialNumber}/power", "Get device power with invalid interval"),

    DEVICE_ENERGY("/v1/device/{loggerSerialNumber}/energy", "Get device energy with invalid interval"),

    STORAGE_ALERTS("v1/site/{siteKey}/storage/alerts", "Get Storage Alerts"),

    SATELLITE_IMAGE("/v1/site/{siteKey}/satellite", "Get Google satellite image for a site"),

    DEALERS_BY_ID("/v1/site/{siteKey}/dealers/{dealerId}", "Get dealer information in relation to a site"),

    INSTANT_REPORT("/v1/site/{siteKey}/instant-report","Sends a report to specified recipients"),

    ADDRESS_PLACES("/v1/site/address/places","Search for a place/address"),

    SITE_ASSIGNMENT("/v1/site/assignment/{siteKey}/{loggerSerialNumber}", "Soft-deletes site assignment data for a given site and datalogger (PVS)");

    private String url;

    private String description;

    SiteEnum(String url, String description) {

        this.url = url;
        this.description = description;
    }

    /**
     * Get Path Param
     * @return
     */
    public String getPathParam() {
        return url;
    }
}
