package com.sunpower.automation.edp.api.data;

import com.sunpower.automation.api.edp.entity.data.Device;
import com.sunpower.automation.core.manager.ConfigManager;
import com.sunpower.automation.core.utils.JsonUtil;
import org.testng.annotations.DataProvider;

import java.util.List;

import static com.sunpower.automation.edp.api.data.AbstractDataProvider.getEdpApiData;

public class DeviceBadReqData {

    /**
     * Preparing data for update Device Discovery
     * @return
     */
    @DataProvider(name = "updateDeviceDiscovery")
    public static Object[][] updateDeviceDiscovery() {

        Device edpApiData = getEdpApiData("device.updateDeviceDiscovery");
        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for update Panel Count
     * @return
     */
    @DataProvider(name = "updatePanelCount")
    public static Object[][] updatePanelCount() {

        Device edpApiData = getEdpApiData("device.updatePanelCount");
        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for set Meter Type
     * @return
     */
    @DataProvider(name = "setMeterType")
    public static Object[][] setMeterType() {

        List<Device> edpApiData = JsonUtil.getListObject(ConfigManager.getValue("device.setMeterType"), Device.class);

        return new Object[][]{{edpApiData}};
    }

}

