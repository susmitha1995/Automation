package com.sunpower.automation.edp.api.device.tests;

import com.sunpower.automation.api.edp.entity.data.Device;
import com.sunpower.automation.core.enums.Steps;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.edp.api.base.EdpApiRestActions;
import com.sunpower.automation.edp.api.base.EdpApiTestBase;
import com.sunpower.automation.edp.api.businessflow.device.DeviceApiBL;
import com.sunpower.automation.edp.api.data.DeviceApiData;
import com.sunpower.automation.edp.api.enums.device.DeviceEnum;
import org.testng.annotations.Test;

public class DeviceNVApiTest extends EdpApiTestBase {

    DeviceApiBL deviceBL = DeviceApiBL.getInstance();

    EdpApiRestActions restActions = EdpApiRestActions.getInstance(DeviceApiBL.class);

    /**
     * C503489 - 200 Get latest lifetime energy production for inverters associated with a logger from Nightvision.
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "latestEnergyProduction", priority = 0)
    public void testLatestEnergyProduction(Device device) {

        LogUtil.log(Steps.START, "Get the status of Specified device by serial number");
        restActions.url(DeviceEnum.ENERGY_LATEST).pathParam(device.getDeviceData()).queryParam(device.getDeviceData())
                .get().doAssert().is200().getObject("siteKey")
                .isEqual("The site key is not matching in the response", device.getDeviceData().getSiteKey())
                .getObject("loggerSerialNumber").isEqual("The Logger serial number is not matching in the response",
                        device.getDeviceData().getLoggerSerialNumber()).getList("inverters.lifetimeEnergyProduction")
                .hasProperValue("'lifetimeEnergyProduction' value is should not be null or empty");
    }

    /**
     * C503492 - 200 Get latest lifetime energy production for inverters associated with a logger from Nightvision.
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "latestMeterEnergyProduction", priority = 1)
    public void testLatestMeterEnergyProduction(Device device) {

        LogUtil.log(Steps.START, "Gets latest raw meter lifetime energy production from Nightvision");
        restActions.url(DeviceEnum.METER_ENERGY_LATEST).pathParam(device.getDevicePayload())
                .queryParam(device.getDevicePayload()).get().doAssert().is200().getObject("siteKey")
                .isEqual("The site key is not matching in the response", device.getDevicePayload().getSiteKey())
                .getObject("loggerSerialNumber").isEqual("The Logger serial number is not matching in the response",
                        device.getDevicePayload().getLoggerSerialNumber())
                .getObject("meterSerialNumber").isEqual("The Meter serial number is not matching in the response",
                        device.getDevicePayload().getSerialNumber())
                .getObject("item.lifetimeEnergy")
                .hasProperValue("'lifetimeEnergy' value is should not be null or empty");
    }

    /**
     * C503493 - 200 Gets latest raw inverter lifetime energy production from Nightvision
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "latestInverterEnergyProduction", priority = 2)
    public void testLatestInverterEnergyProduction(Device device) {

        LogUtil.log(Steps.START, "Gets latest raw inverter lifetime energy production from Nightvision");
        restActions.url(DeviceEnum.INVERTER_ENERGY_LATEST).pathParam(device.getDeviceData())
                .queryParam(device.getDeviceData()).get().doAssert().is200()
                .getObject("siteKey").isEqual("The site key is not matching in the response",
                        device.getDeviceData().getSiteKey())
                .getObject("loggerSerialNumber").isEqual("The Logger serial number is not matching in the response",
                        device.getDeviceData().getLoggerSerialNumber())
                .getObject("meterSerialNumber").isEqual("The Meter serial number is not matching in the response",
                        device.getDeviceData().getMeterSerialNumber())
                .getObject("item.lifetimeEnergyProduction")
                .hasProperValue("'lifetimeEnergyProduction' value is should not be null or empty");
    }

    /**
     * C503495 - 200 Get the latest power measurements for an inverter from Nightvision.
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "latestInverterPowerProduction", priority = 3)
    public void testLatestPowerProduction(Device device) {

        LogUtil.log(Steps.START, "Get the latest power measurements for an inverter from Nightvision.");
        restActions.url(DeviceEnum.INVERTER_POWER_LATEST).pathParam(device.getDeviceData())
                .queryParam(device.getDeviceData()).get().doAssert().is200()
                .getObject("siteKey").isEqual("The site key is not matching in the response",
                        device.getDeviceData().getSiteKey())
                .getObject("loggerSerialNumber").isEqual("The Logger serial number is not matching in the response",
                        device.getDeviceData().getLoggerSerialNumber())
                .getObject("inverterSerialNumber").isEqual("The Meter serial number is not matching in the response",
                        device.getDeviceData().getInverterSerialNumber())
                .getObject("item.acPowerProduction")
                .hasProperValue("'acPowerProduction' value is should not be null or empty");
    }

    /**
     * C503496 - 200 Gets power production for meters associated with a logger from Nightvision.
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "latestInverterPowerProductionInInterval",
            priority = 4)
    public void testPowerProductionInInterval(Device device) {

        LogUtil.log(Steps.START, " Gets power production for meters associated with a logger from Nightvision.");
        restActions.url(DeviceEnum.METER_ENERGY).pathParam(device.getDevicePayload())
                .queryParam(device.getDevicePayload()).get().doAssert().is200()
                .getObject("siteKey").isEqual("The site key is not matching in the response",
                        device.getDevicePayload().getSiteKey())
                .getObject("loggerSerialNumber").isEqual("The Logger serial number is not matching in the response",
                        device.getDevicePayload().getLoggerSerialNumber())
                .getObject("meterSerialNumber").isEqual("The Meter serial number is not matching in the response",
                        device.getDevicePayload().getSerialNumber())
                .isValid(deviceBL.assertAdjacentTimestampsMinutes(),
                        "Time difference is not 5 Minutes between adjacent timestamps");
    }
}
