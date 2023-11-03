package com.sunpower.automation.edp.api.enums.party;

public enum PartyPathParams {

    PARENT_ID("parentId"),

    PARTY_ID("partyId");

    private String pathParam;

    PartyPathParams(String pathParam) {
        this.pathParam = pathParam;
    }

    /**
     * Get PathParam
     * @return
     */
    public String getPathParam() {
        return pathParam;
    }
}
