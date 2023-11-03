package com.sunpower.automation.edp.api.device.tests;

import com.sunpower.automation.api.edp.entity.response.device.ErrorResponse;
import com.sunpower.automation.core.enums.LogLevel;
import com.sunpower.automation.core.enums.Steps;
import com.sunpower.automation.core.utils.JsonUtil;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.edp.api.base.EdpApiTestBase;
import com.sunpower.automation.edp.api.businessflow.device.DeviceApiBL;
import com.sunpower.automation.edp.api.enums.ResponseEnum;
import com.sunpower.automation.edp.api.enums.site.DeviceEnumDeprecated;
import org.testng.annotations.Test;


public class DeviceApiUnauthorizedTest extends EdpApiTestBase {

    DeviceApiBL deviceApiBL = DeviceApiBL.getInstance();

    String token = "invalid";

    String deviceId = "ZT992019230700A0060";

    /**
     * Get V1 Device Tree
     */
    @Test(priority = 1)
    public void verifyDeviceTree() {

        LogUtil.log(Steps.START, "Get V1 Device Tree");
        ErrorResponse data = deviceApiBL.getDeviceApiCollectionBL().getMethod(token,
                DeviceEnumDeprecated.DEVICE_TREE.getUrlV1(deviceId), ResponseEnum.UNAUTHORISED, ErrorResponse.class);
        LogUtil.consoleLog("Unauthorized  V1 Device Tree response: " + JsonUtil.toJson(data), LogLevel.LOW);

        LogUtil.log(Steps.START, "Get V2 Device Tree");
        data = deviceApiBL.getDeviceApiCollectionBL().getMethod(token,
                DeviceEnumDeprecated.DEVICE_TREE.getUrlV2(deviceId), ResponseEnum.UNAUTHORISED,
                ErrorResponse.class);
        LogUtil.consoleLog("Unauthorized  V2 Device Tree response: " + JsonUtil.toJson(data), LogLevel.LOW);
    }

    /**
     * Get V1 Device Status
     */
    @Test(priority = 2)
    public void verifyDeviceStatus() {

        LogUtil.log(Steps.START, "Get V1 Device Status");
        ErrorResponse data = deviceApiBL.getDeviceApiCollectionBL().getMethod(token,
                DeviceEnumDeprecated.DEVICE_STATUS.getUrlV1(deviceId), ResponseEnum.UNAUTHORISED, ErrorResponse.class);
        LogUtil.consoleLog("Unauthorized  V1 Device Status response: " + JsonUtil.toJson(data), LogLevel.LOW);

    }

    /**
     * Get V1 Device Config
     */
    @Test(priority = 3)
    public void verifyDeviceConfig() {

        LogUtil.log(Steps.START, "Get V1 Device Config");
        ErrorResponse data = deviceApiBL.getDeviceApiCollectionBL().getMethod(token,
                DeviceEnumDeprecated.DEVICE_CONFIG.getUrlV1(deviceId), ResponseEnum.UNAUTHORISED, ErrorResponse.class);
        LogUtil.consoleLog("Unauthorized  V1 Device Config response: " + JsonUtil.toJson(data), LogLevel.LOW);

    }

    /**
     * Get V1 Device Model Name
     */
    @Test(priority = 4)
    public void verifyModelName() {

        LogUtil.log(Steps.START, "Get V1 Device Model Name");
        ErrorResponse data = deviceApiBL.getDeviceApiCollectionBL().getMethod(token,
                DeviceEnumDeprecated.MODEL_NAME.getUrlV1(deviceId), ResponseEnum.UNAUTHORISED, ErrorResponse.class);
        LogUtil.consoleLog("Unauthorized V1 Device Model Name response: " + JsonUtil.toJson(data), LogLevel.LOW);

    }

    /**
     * Get V1 C Module Device Model Name
     */
    @Test(priority = 5)
    public void verifyCModuleModelName() {

        LogUtil.log(Steps.START, "Get V1 C Module Device Model Name");
        ErrorResponse data = deviceApiBL.getDeviceApiCollectionBL().getMethod(token,
                DeviceEnumDeprecated.MODULE_MODEL_NAME.getUrlV1("c"), ResponseEnum.UNAUTHORISED, ErrorResponse.class);
        LogUtil.consoleLog("Unauthorized V1 C Module Device Model Name response: " + JsonUtil.toJson(data),
                LogLevel.LOW);

    }

    /**
     * et V1 E Module Device Model Name
     */
    @Test(priority = 6)
    public void verifyEModuleModelName() {

        LogUtil.log(Steps.START, "Get V1 E Module Device Model Name");
        ErrorResponse data = deviceApiBL.getDeviceApiCollectionBL().getMethod(token,
                DeviceEnumDeprecated.MODULE_MODEL_NAME.getUrlV1("e"), ResponseEnum.UNAUTHORISED, ErrorResponse.class);
        LogUtil.consoleLog("Unauthorized V1 E Module Device Model Name response: " + JsonUtil.toJson(data),
                LogLevel.LOW);

    }

    /**
     * Get V1 D Module Device Model Name
     */
    @Test(priority = 7)
    public void verifyDModuleModelName() {

        LogUtil.log(Steps.START, "Get V1 D Module Device Model Name");
        ErrorResponse data = deviceApiBL.getDeviceApiCollectionBL().getMethod(token,
                DeviceEnumDeprecated.MODULE_MODEL_NAME.getUrlV1("d"), ResponseEnum.UNAUTHORISED, ErrorResponse.class);
        LogUtil.consoleLog("Unauthorized V1 D Module Device Model Name response: " + JsonUtil.toJson(data),
                LogLevel.LOW);

    }

    /**
     * Get V1 Commercial Module Device Model Name
     */
    @Test(priority = 8)
    public void verifyCommericalModuleModelName() {

        LogUtil.log(Steps.START, "Get V1 Commercial Module Device Model Name");
        ErrorResponse data = deviceApiBL.getDeviceApiCollectionBL().getMethod(token,
                DeviceEnumDeprecated.MODULE_MODEL_NAME.getUrlV1("commercial"),
                ResponseEnum.UNAUTHORISED, ErrorResponse.class);
        LogUtil.consoleLog("Unauthorized V1 Commercial Module Device Model Name response: " + JsonUtil.toJson(data),
                LogLevel.LOW);

    }

    /**
     * Get V1 String Module Device Model Name
     */
    @Test(priority = 9)
    public void verifyStringModuleModelName() {

        LogUtil.log(Steps.START, "Get V1 String Module Device Model Name");
        ErrorResponse data = deviceApiBL.getDeviceApiCollectionBL().getMethod("token",
                DeviceEnumDeprecated.MODULE_MODEL_NAME.getUrlV1("string"),
                ResponseEnum.UNAUTHORISED, ErrorResponse.class);
        LogUtil.consoleLog("Unauthorized V1 String Module Device Model Name response: " + JsonUtil.toJson(data),
                LogLevel.LOW);

    }

    /**
     * Get V1 Operational logs
     */
    @Test(priority = 10)
    public void verifyOperationalLogs() {

        LogUtil.log(Steps.START, "Get V1 Operational logs");
        ErrorResponse data = deviceApiBL.getDeviceApiCollectionBL().getMethod("token",
                DeviceEnumDeprecated.OPERATIONAL_LOGS.getUrlV1(deviceId, "d3873045-b116-44af-bb07-96821744a929"),
                ResponseEnum.UNAUTHORISED,
                ErrorResponse.class);
        LogUtil.consoleLog("Unauthorized V1 Operational logs response: " + JsonUtil.toJson(data),
                LogLevel.LOW);

    }

    /**
     * Get V1 Audit logs
     */
    @Test(priority = 11)
    public void verifyAuditLogs() {

        LogUtil.log(Steps.START, "Get V1 Audit logs");
        ErrorResponse data = deviceApiBL.getDeviceApiCollectionBL().getMethod("token",
                DeviceEnumDeprecated.AUDIT_LOGS.getUrlV1(deviceId, "d3873045-b116-44af-bb07-96821744a929"),
                ResponseEnum.UNAUTHORISED, ErrorResponse.class);
        LogUtil.consoleLog("Unauthorized V1 Audit logs response: " + JsonUtil.toJson(data),
                LogLevel.LOW);

    }
}
