package com.sunpower.automation.edp.api.enums.party;

public enum PartyEnum {

    USER("/v2/party/{parentId}/user", "Get all associated users for a party by party id"),

    V1_PARTY_BY_ID("/v1/party/{partyId}", "Get a party based on partyId"),

    V2_PARTY_BY_ID("/v2/party/{partyId}", "Updates a party in MDS, and Ping(SMS is not updated)"),

    ALERTS("v2/party/{parentId}/alerts", "Get party organization alerts"),

    PARTY_SITE("/v2/party/{partyId}/site", "Get all associated sites for a party by party id(No SMS)"),

    ORG_BY_PARTY_ID("/v1/party/organization/{partyId}", "Updates an organization party in MDS and optionally in SMS"),

    V1_PARTY("/v1/party", "Creates a Party"),

    V2_PARTY("/v2/party", "Creates a Party(No SMS)"),

    USER_CONFIG("/v1/party/{partyId}/userconfig", "Insert/update a mySunpower user config/preference item"),

    INTERNAL_USER("/v1/party/internaluser", "Creates or updates an employee in MDS, and Ping(No SMS)"),

    COUNT("v1/party/{parentId}/sites/count", "Get the number of sites from organization party"),

    PARTY_USER("/v1/party/user", "Creates a user (not Employee) in MDS, SMS(optionally), and Ping"),

    FEEDBACK("/v1/party/feedback", "Sends a feedback email"),

    GROUP("/v1/party/group", "Creates a group party under specified organization"),

    GROUP_BY_PARTY_ID("/v1/party/group/{partyId}", "Updates a group party in MDS and optionally in SMS"),

    NOTIFICATION_CONFIG("v1/party/notifications/config", "Creates a new notification configuration"),

    PARTY_ORG("/v1/party/organization", "Creates an organization party at a specified system org"),

    ORG_ALERTS_REPORT("/v1/party/organization/alerts/report", "Sends an organization Alerts Report to specified recipients"),

    USER_CONFIG_BY_PARTY_ID("/v1/party/{partyId}/userconfig", "Get mySunpower user configurations/preferences");;

    private String url;

    private String description;

    PartyEnum(String url, String description) {

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
