package com.sunpower.automation.edp.api.enums.device;

public enum DeviceEnum {

    STATUS("/v1/device/{deviceId}/status", "Gets the  status of the specified device by serial number"),

    MODEL_NAME("/v1/device/{deviceId}/modelname", "Updates target module model name"),

    MODULE_MODELS("/v1/device/module-models", "get all module model details"),

    MODULE_MODELS_BY_NAME("/v1/device/module-models/{modelName}", "Get all module model details by name"),

    METER_TYPE("/v1/device/{deviceId}/metertype", "gets meter type"),

    CHARACTERISTIC("/v1/device/{deviceId}/characteristic", "Sets device characteristic according to user permission"),

    DEVICE_COMMAND("/v1/device/command", "sends a command to one or more data loggers(PVS)"),

    PANEL_COUNT("/v2/device/{deviceKey}/panelcount", "Set panel total count attached to a String invrter(DC Inverter)"),

    CONFIG_BY_DEVICE_ID("/v1/device/{deviceId}/config", "gets target device config"),

    POWER("/v1/device/{loggerSerialNumber}/power", "gets power production for inverters associated with logger from nightvision"),

    POWER_BY_DEVICE_ID("/v1/device/{deviceId}/power", "get power from the device "),

    ENERGY_LATEST("/v1/device/{loggerSerialNumber}/energy/latest", "Get latest lifetime energy production associated with logger from nightvision"),

    METER_ENERGY_LATEST("/v1/device/{loggerSerialNumber}/meter/{serialNumber}/energy/latest", "Gets latest raw meter lifetime energy production from Nightvision "),

    INVERTER_ENERGY_LATEST("/v1/device/{loggerSerialNumber}/inverter/{inverterSerialNumber}/energy/latest", "Get latest  raw inverter lifetime energy production from Nightvision"),

    INVERTER_POWER_LATEST("/v1/device/{loggerSerialNumber}/inverter/{inverterSerialNumber}/power/latest", "Get  the latest  power measurement for an inverter from an Nightvision"),

    METER_ENERGY("/v1/device/{loggerSerialNumber}/meter/{serialNumber}/energy", "Get meter energy production and lifetime energy production in date range from NightVision"),

    ENERGY("/v1/device/{loggerSerialNumber}/energy", "Gets energy production for a inverters associated with a logger in date range from NightVision"),

    MODEL_NAMES("/v1/device/{moduleType}/modelnames", "Get a list of  active module model names"),

    V2_TREE("/v2/device/{deviceId}/tree", "Gets target device tree (mdsOnly)"),

    COMMISSION_CANDIDATES("/v1/device/{deviceId}/commission/candidates", "Determines which devices are candidates for commissioning"),

    V1_TREE("/v1/device/{deviceId}/tree", "Gets target device tree"),

    INSTALL_METER_TYPE("/v1/device/{deviceKey}/installmetertype", "Sets device characteristics needed to determine installed meter type for a meter device"),

    INVERTER_ENERGY("/v1/device/{loggerSerialNumber}/inverter/{inverterSerialNumber}/energy", "Gets inverter energy production and lifetime energy production in date range from Nightvision");

    private String url;

    private String description;

    DeviceEnum(String url, String description) {

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
