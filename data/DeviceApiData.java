package com.sunpower.automation.edp.api.data;

import com.sunpower.automation.api.edp.entity.data.Device;
import com.sunpower.automation.core.manager.ConfigManager;
import com.sunpower.automation.core.utils.JsonUtil;
import org.testng.annotations.DataProvider;

import java.util.List;

public class DeviceApiData extends AbstractDataProvider {

    /**
     * Preparing data for EDP API
     * @return - Object[][] data
     */
    @DataProvider(name = "getEdpApiData")
    public static Object[][] getEdpApiData() {

        Device edpApiData = getEdpApiData("deviceApiData");
        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for Status Specified Device SerialNumber
     * @return
     */
    @DataProvider(name = "statusSpecifiedDeviceSerialNumber")
    public static Object[][] statusSpecifiedDeviceSerialNumber() {

        Device edpApiData = getEdpApiData("device.statusSpecifiedDeviceSerialNumber");
        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for Target Module Model Name
     * @return
     */
    @DataProvider(name = "targetModuleModelName")
    public static Object[][] targetModuleModelName() {

        Device edpApiData = getEdpApiData("device.targetModuleModelName");

        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for Update Module Model Name
     * @return
     */
    @DataProvider(name = "updateModuleModelName")
    public static Object[][] updateModuleModelName() {

        Device edpApiData = getEdpApiData("device.updateModuleModelName");

        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for Get Module Name
     * @return
     */
    @DataProvider(name = "getModuleName")
    public static Object[][] getModuleName() {

        Device edpApiData = getEdpApiData("device.getModuleName");

        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for Get All Module ModelName
     * @return
     */
    @DataProvider(name = "getAllModuleModelName")
    public static Object[][] getAllModuleModelName() {

        Device edpApiData = getEdpApiData("device.getAllModuleModelName");

        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for Get Module By Name
     * @return
     */
    @DataProvider(name = "getModuleByName")
    public static Object[][] getModuleByName() {

        Device edpApiData = getEdpApiData("device.getModuleByName");

        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for Set Meter Type Version One
     * @return
     */
    @DataProvider(name = "setMeterTypeVersionOne")
    public static Object[][] setMeterTypeVersionOne() {

        List<Device> edpApiData =  JsonUtil.getListObject(ConfigManager
                .getValue("device.setMeterTypeVersionOne"), Device.class);

        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for Get Meter Type Version One
     * @return
     */
    @DataProvider(name = "getMeterTypeVersionOne")
    public static Object[][] getMeterTypeVersionOne() {

        Device edpApiData = getEdpApiData("device.getMeterTypeVersionOne");

        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for Sets Device Characteristics
     * @return
     */
    @DataProvider(name = "setsDeviceCharacteristics")
    public static Object[][] setsDeviceCharacteristics() {

        Device edpApiData = getEdpApiData("device.setsDeviceCharacteristics");

        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for Retrieve Device Characteristics
     * @return
     */
    @DataProvider(name = "retrieveDeviceCharacteristics")
    public static Object[][] retrieveDeviceCharacteristics() {

        Device edpApiData = getEdpApiData("device.retrieveDeviceCharacteristics");

        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for Updates Target ModuleName
     * @return
     */
    @DataProvider(name = "updatesTargetModuleName")
    public static Object[][] updatesTargetModuleName() {

        Device edpApiData = getEdpApiData("device.updatesTargetModuleName");

        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for Get Target Device Config
     * @return
     */
    @DataProvider(name = "getTargetDeviceConfig")
    public static Object[][] getTargetDeviceConfig() {

        Device edpApiData = getEdpApiData("device.getTargetDeviceConfig");
        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for get Device Power
     * @return
     */
    @DataProvider(name = "getDevicePower")
    public static Object[][] getDevicePower() {

        Device edpApiData = getEdpApiData("device.getDevicePower");
        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for get Power device
     * @return
     */
    @DataProvider(name = "powerDevice")
    public static Object[][] powerDevice() {

        List<Device> edpApiData = JsonUtil.getListObject(ConfigManager.getValue(
                "device.powerDevice"), Device.class);
        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for latest Energy Production
     * @return
     */
    @DataProvider(name = "latestEnergyProduction")
    public static Object[][] latestEnergyProduction() {

        Device edpApiData = JsonUtil.getObject(ConfigManager.getValue("device.latestEnergyProduction"), Device.class);
        return new Object[][]{{edpApiData}};
    }

    /**
     * @return
     */
    @DataProvider(name = "latestMeterEnergyProduction")
    public static Object[][] latestMeterEnergyProduction() {

        Device edpApiData = JsonUtil.getObject(ConfigManager.getValue(
                "device.latestMeterEnergyProduction"), Device.class);
        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for latest Inverter Energy Production
     * @return
     */
    @DataProvider(name = "latestInverterEnergyProduction")
    public static Object[][] latestInverterEnergyProduction() {

        Device edpApiData = JsonUtil.getObject(ConfigManager.getValue(
                "device.latestInverterEnergyProduction"), Device.class);
        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for latest Inverter Power Production
     * @return
     */
    @DataProvider(name = "latestInverterPowerProduction")
    public static Object[][] latestInverterPowerProduction() {

        Device edpApiData = JsonUtil.getObject(ConfigManager.getValue(
                "device.latestInverterPowerProduction"), Device.class);
        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for latest Inverter Power Production In Interval
     * @return
     */
    @DataProvider(name = "latestInverterPowerProductionInInterval")
    public static Object[][] latestInverterPowerProductionInInterval() {

        Device edpApiData = JsonUtil.getObject(ConfigManager.getValue(
                "device.latestInverterPowerProductionInInterval"), Device.class);
        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for get Device Energy
     * @return
     */
    @DataProvider(name = "getDeviceEnergy")
    public static Object[][] getDeviceEnergy() {

        List<Device> edpApiData = JsonUtil.getListObject(
                ConfigManager.getValue("device.getDeviceEnergy"), Device.class);
        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for get Module Rated Power Watts
     * @return
     */
    @DataProvider(name = "getModuleRatedPowerWatts")
    public static Object[][] getModuleRatedPowerWatts() {

        Device edpApiData = getEdpApiData("device.getModuleRatedPowerWatts");
        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for get Device Module Models
     * @return
     */
    @DataProvider(name = "getDeviceModuleModels")
    public static Object[][] getDeviceModuleModels() {

        Device edpApiData = getEdpApiData("device.getDeviceModuleModels");
        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for get Active Module
     * @return
     */
    @DataProvider(name = "getActiveModule")
    public static Object[][] getActiveModule() {

        Device edpApiData = getEdpApiData("device.getActiveModule");
        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing data for get Target Device Tree
     * @return
     */
    @DataProvider(name = "getTargetDeviceTree")
    public static Object[][] getTargetDeviceTree() {

        List<Device> edpApiData = JsonUtil.getListObject(
                ConfigManager.getValue("device.getTargetDeviceTree"), Device.class);
        return new Object[][]{{edpApiData}};
    }

    /**
     * Create device candidates for commissioning
     * @return
     */
    @DataProvider(name = "createDeviceCandidatesForCommissioning")
    public static Object[][] createDeviceCandidatesForCommissioning() {

        List<Device> edpApiData = JsonUtil.getListObject(
                ConfigManager.getValue("device.createDeviceCandidatesForCommissioning"), Device.class);

        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing list for target device tree
     * @return
     */
    @DataProvider(name = "getTargetDeviceTreeList")
    public static Object[][] getTargetDeviceTreeList() {

        Device edpApiData = getEdpApiData("device.getTargetDeviceTreeList");
        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing  Sets device characteristics needed to determine Installed Meter Type for a meter device
     * @return
     */
    @DataProvider(name = "deviceCharacteristics")
    public static Object[][] deviceCharacteristics() {

        List<Device> edpApiData = JsonUtil.getListObject(
                ConfigManager.getValue("device.deviceCharacteristics"), Device.class);

        return new Object[][]{{edpApiData}};
    }

    /**
     * Preparing  Sets device characteristics needed to determine Installed Meter Type for a meter device
     * @return
     */
    @DataProvider(name = "modulesModels")
    public static Object[][] modulesModels() {

        List<Device> edpApiData = JsonUtil.getListObject(ConfigManager.getValue("device.modulesModels"), Device.class);

        return new Object[][]{{edpApiData.get(0)}, {edpApiData.get(1)}, {edpApiData.get(2)}, {edpApiData.get(4)}, {edpApiData.get(5)},
                {edpApiData.get(5)}};
    }
}
