package com.sunpower.automation.edp.api.data;


import com.sunpower.automation.api.edp.entity.data.Device;
import com.sunpower.automation.core.manager.ConfigManager;
import com.sunpower.automation.core.utils.JsonUtil;

public class AbstractDataProvider {


    /**
     * Prepare EDP API root data
     * @param deviceDetail
     * @return
     */
    public static Device getEdpApiData(String deviceDetail) {

        Device edpApiData = new Device();
        Device deviceData = null;

        if (deviceDetail != null) {
            deviceData = JsonUtil.getObject(ConfigManager.getValue(deviceDetail), Device.class);
        }

        if (deviceData != null) {
            edpApiData.setDeviceData(deviceData);
        }

        return edpApiData;
    }
}
