package com.sunpower.automation.edp.api.device.tests;

import com.sunpower.automation.api.edp.entity.data.Device;
import com.sunpower.automation.core.enums.Steps;
import com.sunpower.automation.core.utils.GsonUtils;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.edp.api.base.EdpApiRestActions;
import com.sunpower.automation.edp.api.base.EdpApiTestBase;
import com.sunpower.automation.edp.api.businessflow.device.DeviceApiBL;
import com.sunpower.automation.edp.api.data.DeviceApiData;
import com.sunpower.automation.edp.api.enums.device.DeviceEnum;
import com.sunpower.automation.edp.api.payload.EdpPayload;
import org.testng.annotations.Test;

import java.util.List;

public class DeviceApiTest extends EdpApiTestBase {

    DeviceApiBL deviceBL = DeviceApiBL.getInstance();

    EdpApiRestActions restActions = EdpApiRestActions.getInstance(DeviceApiBL.class);

    EdpPayload edpPayload = EdpPayload.getInstance();

    /**
     * C287960 - 200 Device status - Get the status of Specified device by serial number
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "statusSpecifiedDeviceSerialNumber", priority = 0)
    public void getStatusSpecifiedDeviceSerialNumber(Device device) {

        LogUtil.log(Steps.START, "Get the status of Specified device by serial number");
        restActions.url(DeviceEnum.STATUS).pathParam(device.getDeviceData()).get().doAssert().is200();
    }

    /**
     * C287963- 200 Update target module modelname
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "targetModuleModelName", priority = 1)
    public void testTargetModuleModelName(Device device) {

        LogUtil.log(Steps.START, "Get the status of Specified device by serial number");
        String payload = edpPayload.createPayload(device.getDeviceData()
                .getModelPayload()).replace("modelName", "modelname");
        restActions.url(DeviceEnum.MODEL_NAME).pathParam(device.getDeviceData())
                .body(payload).patch().doAssert().is200().getObject("moduleModelName")
                .isEqual("'moduleModelName is not matching'",
                        deviceBL.validateDeviceCharFields("MODULE_TYPE_NAME").getCharValue());

        LogUtil.log(Steps.START, "Request the end point when sms-enabled as true");
        restActions.url(DeviceEnum.MODEL_NAME).pathParam(device.getDeviceData())
                .queryParam(device.getDeviceData())
                .body(payload).patch().doAssert().is200().getObject("moduleModelName")
                .isEqual("'moduleModelName is not matching'",
                        deviceBL.validateDeviceCharFields("MODULE_TYPE_NAME").getCharValue());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C287985 - 200 PATCH Updates target module model name
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "updateModuleModelName", priority = 2)
    public void updatesTargetModuleModelName(Device device) {

        LogUtil.log(Steps.START, "Request the end point when sms-enabled as true");
        restActions.url(DeviceEnum.MODEL_NAME).pathParam(device.getDeviceData())
                .queryParam(device.getDeviceData())
                .body(edpPayload.createPayload(device.getDeviceData().getModelPayload())
                        .replace("modelName", "modelname"))
                .patch().doAssert().is200().getObject("moduleModelName")
                .isEqual("'moduleModelName is not matching'",
                        deviceBL.validateDeviceCharFields("MODULE_TYPE_NAME").getCharValue());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C287964 - 200 GetModule modelname
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "getModuleName", priority = 3)
    public void getModuleModelName(Device device) {

        LogUtil.log(Steps.START, "Get Module model name");
        restActions.url(DeviceEnum.MODEL_NAME).pathParam(device.getDeviceData())
                .get().doAssert().is200().getObject("modelName").isEqual("'moduleModelName is not matching'",
                        deviceBL.validateDeviceCharFields(device.getDeviceData().getDeviceId(), "MODULE_TYPE_NAME")
                                .getCharValue());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }


    /**
     * C287965 - 200 Get all module model details
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "getAllModuleModelName", priority = 4)
    public void getAllModuleModelDetails(Device device) {

        LogUtil.log(Steps.START, "Get all module model details");
        restActions.url(DeviceEnum.MODULE_MODELS).get().doAssert().is200();
        deviceBL.validateTheModuleKeyInResponse();

        LogUtil.log(Steps.START, "Get all module model details and request with query params");
        restActions.url(DeviceEnum.MODULE_MODELS).pathParam(device.getDeviceData())
                .queryParam(device.getDeviceData()).get().doAssert().is200()
                .isValid(deviceBL.validateModuleModelData(device),
                        "Response's values are not matching with requested parameter");
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288021 - 200 Get all module model details by name
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "getModuleByName", priority = 5)
    public void getModuleModelDetailsByName(Device device) {

        LogUtil.log(Steps.START, "Get all module model details by name");
        restActions.url(DeviceEnum.MODULE_MODELS_BY_NAME).pathParam(device.getDeviceData())
                .queryParam(device.getDeviceData()).get().doAssert().is200()
                .isValid(deviceBL.validateModuleModelData(device),
                        "Response's values are not matching with requested parameter");
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C287968 - 200 Set Meter type V1-
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "setMeterTypeVersionOne", priority = 6)
    public void testSetMeterTypeVersionOne(List<Device> device) {

        LogUtil.log(Steps.START, "Verify Set Meter type V1");
        restActions.url(DeviceEnum.METER_TYPE).pathParam(device.get(1))
                .queryParam(device.get(1))
                .body(edpPayload.createPayload(device.get(0)))
                .patch().doAssert().is200().getObject("meterType").isEqual("'Meter type is not matching'",
                        deviceBL.validateDeviceCharFields(device.get(1).getDeviceId(), "DEVICE_SUB_TYPE")
                                .getCharValue());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C287969 - 200 Get Meter type V1
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "getMeterTypeVersionOne", priority = 7)
    public void testGetMeterTypeVersionOne(Device device) {

        LogUtil.log(Steps.START, "Verify Get Meter type V1");
        restActions.url(DeviceEnum.METER_TYPE).pathParam(device.getDeviceData())
                .get().doAssert().is200().getObject("type").isEqual("'Meter type is not matching'",
                        deviceBL.validateDeviceCharFields(device.getDeviceData().getDeviceId(), "DEVICE_SUB_TYPE")
                                .getCharValue());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C287980 - 200 Sets device characteristics according to user permissions
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "setsDeviceCharacteristics", priority = 8)
    public void testSetsDeviceCharacteristics(Device device) {

        LogUtil.log(Steps.START, "Sets device characteristics according to user permissions");
        restActions.url(DeviceEnum.CHARACTERISTIC).pathParam(device.getDeviceData())
                .body(edpPayload.payloadForDeviceCharacteristics(device.getDeviceData().getDevicePayload()))
                .patch().doAssert().is200().isValid(deviceBL.validateDeviceCharacters(device),
                        "Response's values are not matching with  DB data");
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C287981 - 200 Retreives device characteristics according to user permissions
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "retrieveDeviceCharacteristics", priority = 9)
    public void retrieveDeviceCharacteristics(Device device) {

        LogUtil.log(Steps.START, "Retrieve device characteristics according to user permissions");
        restActions.url(DeviceEnum.CHARACTERISTIC).pathParam(device.getDeviceData()).get()
                .doAssert().is200().isValid(deviceBL.validateDeviceCharacters(device),
                        "Response's values are not matching with  DB data");

        LogUtil.log(Steps.START, "Retrieve device characteristics with query parameter");
        restActions.url(DeviceEnum.CHARACTERISTIC).pathParam(device.getDeviceData())
                .queryParam(device.getDeviceData()).get().doAssert().is200()
                .isValid(deviceBL.validateDeviceCharacters(device),
                        "Response's values are not matching with  DB data");
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C287986 - 200 Updates target module model name
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "updatesTargetModuleName", priority = 10)
    public void updatesTargetModuleName(Device device) {

        LogUtil.log(Steps.START, "Request the end point when sms-enabled as false");
        restActions.url(DeviceEnum.MODEL_NAME).pathParam(device.getDeviceData())
                .queryParam(device.getDeviceData())
                .body(edpPayload.createPayload(device.getDeviceData().getModelPayload())
                        .replace("modelName", "modelname"))
                .patch().doAssert().is200().getObject("moduleModelName")
                .isEqual("'moduleModelName is not matching'",
                        deviceBL.validateDeviceCharFields("MODULE_TYPE_NAME").getCharValue());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C287962 - 200 get target Device config
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "getTargetDeviceConfig", priority = 11)
    public void testTargetDeviceConfig(Device device) {

        LogUtil.log(Steps.START, "Get target device config");
        restActions.url(DeviceEnum.CONFIG_BY_DEVICE_ID).pathParam(device.getDeviceData()).get().doAssert().is200();
    }

    /**
     * C288015 - 200 Update /v1/device/{logger-serial-number}/power
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "getDevicePower", priority = 12)
    public void testDevicePower(Device device) {

        LogUtil.log(Steps.START, "Get the status of Specified data by serial number");
        restActions.url(DeviceEnum.POWER).pathParam(device.getDeviceData())
                .queryParam(device.getDeviceData())
                .get().doAssert().is200();
    }

    /**
     * C288016 - 200 GET /v1/device/{deviceid}/power
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "powerDevice", priority = 13)
    public void testPowerDevice(List<Device> device) {

        LogUtil.log(Steps.START, "Get the power from the device");
        restActions.url(DeviceEnum.POWER_BY_DEVICE_ID).pathParam(device.get(0))
                .queryParam(device.get(1))
                .get().doAssert().is200();
    }

    /**
     * C288017 - 200 Update /v1/device/{logger-serial-number}/energy
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "getDeviceEnergy", priority = 14)
    public void testDeviceEnergy(List<Device> device) {

        LogUtil.log(Steps.START, "Get the device energy");
        restActions.url(DeviceEnum.ENERGY).pathParam(device.get(0)).queryParam(device.get(1))
                .get().doAssert().is200();
    }

    /**
     * C288018 - Return "MODULE_RATED_POWER_WATTS" in device characteristic
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "getModuleRatedPowerWatts", priority = 15)
    public void testModuleRatedPowerWatts(Device device) {

        LogUtil.log(Steps.START, "Get all module rated power watts items by device key");
        restActions.url(DeviceEnum.CHARACTERISTIC).pathParam(device.getDeviceData())
                .get().doAssert().is200();
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288019 - Add new Module Names to module-models endpoint
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "getDeviceModuleModels",
            description = "To verify device module API by adding new module names to module-models endpoint",
            priority = 16)
    public void testDeviceModuleModels(Device device) {

        LogUtil.log(Steps.START, "To verify device module API by adding new module names to module-models endpoint");
        restActions.url(DeviceEnum.MODULE_MODELS).get().doAssert().is200();
        deviceBL.validateTheModuleKeyInResponse();

        LogUtil.log(Steps.START, "Get all the module model details by name");
        restActions.url(DeviceEnum.MODULE_MODELS_BY_NAME).pathParam(device.getDeviceData())
                .get().doAssert().is200();
        deviceBL.validateTheModuleKeyInResponse();
    }

    /**
     * C503509 - 200 Get a list of active module model names
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "getActiveModule",
            description = "Get the list of active modules model names based on a given type", priority = 17)
    public void testActiveModule(Device device) {

        LogUtil.log(Steps.START, "Get the list of active modules model names based on a given type");
        restActions.url(DeviceEnum.MODEL_NAMES).pathParam(device.getDeviceData()).get().doAssert().is200();
    }

    /**
     * C287996 - GET Module Names to module-models endpoint
     *
     * @param device
     */
    @Test(dataProviderClass = DeviceApiData.class, dataProvider = "modulesModels", priority = 18)
    public void testModuleModel(Device device) {

        LogUtil.log(Steps.START, "Module Names to module-models endpoint");
        restActions.url(DeviceEnum.MODULE_MODELS).queryParam(device).get().doAssert().is200().getObject("[0]")
                .isEqual(deviceBL.convertTypeOfMapValue(GsonUtils.objectToMap(device.getDevicePayload())));
    }
}
