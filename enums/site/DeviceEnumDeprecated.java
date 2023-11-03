package com.sunpower.automation.edp.api.enums.site;

import com.sunpower.automation.edp.api.interfaces.EdpBaseEnum;

public enum DeviceEnumDeprecated implements EdpBaseEnum {

    TARGET_DEVICE_CONFIG("device/%s/config", "Gets target device config"),

    DEVICE_TREE("device/%s/tree", "Gets Device Tree"),

    DEVICE_STATUS("device/%s/status", "Gets Device Status"),

    DEVICE_CONFIG("device/%s/config", "Gets Device Config"),

    MODEL_NAME("device/%s/modelname", "Gets Model Name"),

    MODULE_MODEL_NAME("device/%s/modelnames", "Gets Module Model Name"),

    OPERATIONAL_LOGS("device/%s/commands/operational/%s", "Gets Operational logs"),

    AUDIT_LOGS("device/%s/commands/audit/%s", "Gets Operational logs");

    private String url;

    private String description;

    DeviceEnumDeprecated(String url, String description) {

        this.url = url;
        this.description = description;
    }

    @Override
    public String getUrlV1() {
        return formatEndPointUrl(url, V1);
    }

    @Override
    public String getUrlV2() {
        return formatEndPointUrl(url, V2);
    }

    @Override
    public String getUrlV1(String... value) {
        return formatEndPointUrl(url, V1, value);
    }

    @Override
    public String getUrlV2(String... value) {
        return formatEndPointUrl(url, V2, value);
    }
}
