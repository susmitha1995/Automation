package com.sunpower.automation.edp.api.device.tests;

import com.sunpower.automation.api.edp.entity.data.Device;
import com.sunpower.automation.core.listeners.AutomationListener;
import com.sunpower.automation.core.testng.CustomReport;
import com.sunpower.automation.edp.api.base.EdpApiRestActions;
import com.sunpower.automation.edp.api.base.EdpApiTestBase;
import com.sunpower.automation.edp.api.businessflow.device.DeviceApiBL;
import com.sunpower.automation.edp.api.data.DeviceBadReqData;
import com.sunpower.automation.edp.api.enums.device.DeviceEnum;
import com.sunpower.automation.edp.api.payload.EdpPayload;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;


@Listeners({CustomReport.class, AutomationListener.class})
public class DeviceBadRequestTest extends EdpApiTestBase {

    EdpApiRestActions restActions = EdpApiRestActions.getInstance(DeviceApiBL.class);

    EdpPayload payload = EdpPayload.getInstance();


    /**
     * C288027 - Command "GET_DEVICE_DISCOVERY" (invalid command type) - response error message and
     * code match the result
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceBadReqData.class, dataProvider = "updateDeviceDiscovery", priority = 0)
    public void testDeviceDiscovery(Device device) {

        restActions.url(DeviceEnum.DEVICE_COMMAND)
                .body(payload.createPayload(device.getDevicePayload())).post().doAssert().is400();
    }

    /**
     * C288036 - Set Panel count - response error message and code match the expected result
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceBadReqData.class, dataProvider = "updatePanelCount", priority = 1)
    public void setPanelCount(Device device) {

        restActions.url(DeviceEnum.PANEL_COUNT).pathParam(device.getDeviceData())
                .body(payload.createPayload(device.getDeviceData().getDevicePayload()))
                .patch().doAssert().is400();
    }

    /**
     * C288037 - Set Meter type - response error message and code match the expected result
     *
     * @param edpApi
     */
    @Test(dataProviderClass = DeviceBadReqData.class, dataProvider = "setMeterType", priority = 2)
    public void testSetMeterType(List<Device> edpApi) {

        restActions.url(DeviceEnum.METER_TYPE).pathParam(edpApi.get(1))
                .body(payload.createPayload(edpApi.get(0))).patch().doAssert().is400();
    }
}
