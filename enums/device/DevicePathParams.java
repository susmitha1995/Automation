package com.sunpower.automation.edp.api.enums.device;

public enum DevicePathParams {

    DEVICE_ID("deviceId"),

    MODEL_NAME("modelName"),

    DEVICE_KEY("deviceKey"),

    LOGGER_SERIAL_NUMBER("loggerSerialNumber"),

    METER_SERIAL_NUMBER("meterSerialNumber"),

    INVERTER_SERIAL_NUMBER("inverterSerialNumber"),

    MODULE_TYPE("moduleType"),

    SERIAL_NUMBER("serialNumber");

    private String pathParam;

    DevicePathParams(String pathParam) {
        this.pathParam = pathParam;
    }

    /**
     * Get Path ParamKey
     * @return
     */
    public String getPathParamKey() {
        return pathParam;
    }
}
