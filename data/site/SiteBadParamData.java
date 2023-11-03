package com.sunpower.automation.edp.api.data.site;

import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.manager.ConfigManager;
import com.sunpower.automation.core.utils.JsonUtil;
import com.sunpower.automation.edp.api.data.AbstractDataProvider;
import org.testng.annotations.DataProvider;

public class SiteBadParamData extends AbstractDataProvider {

    /**
     * Preparing data for nonExisting SiteKey
     * @return
     */
    @DataProvider(name = "nonExistingSiteKey")
    public static Object[][] nonExistingSiteKey() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadParam.nonExistingSiteKey"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for nonExisting DeviceKey
     * @return
     */
    @DataProvider(name = "nonExistingDeviceKey")
    public static Object[][] nonExistingDeviceKey() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadParam.nonExistingDeviceKey"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for nonExisting SiteKey And DeviceKey
     * @return
     */
    @DataProvider(name = "nonExistingSiteKeyAndDeviceKey")
    public static Object[][] nonExistingSiteKeyAndDeviceKey() {

        Site site = JsonUtil.getObject(ConfigManager.getValue(
                "siteBadParam.nonExistingSiteKeyAndDeviceKey"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for get Site
     * @return
     */
    @DataProvider(name = "getSite")
    public static Object[][] getSite() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadParam.getSite"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for get Parties
     * @return
     */
    @DataProvider(name = "getParties")
    public static Object[][] getParties() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadParam.getParties"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for get Site Incentives
     * @return
     */
    @DataProvider(name = "getSiteIncentives")
    public static Object[][] getSiteIncentives() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadParam.getSiteIncentives"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for get Site Utilities
     * @return
     */
    @DataProvider(name = "getSiteUtilities")
    public static Object[][] getSiteUtilities() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadParam.getSiteUtilities"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for get Site Tariffs
     * @return
     */
    @DataProvider(name = "getSiteTariffs")
    public static Object[][] getSiteTariffs() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadParam.getSiteTariffs"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for alerts With Invalid SiteKey
     * @return
     */
    @DataProvider(name = "alertsWithInvalidSiteKey")
    public static Object[][] alertsWithInvalidSiteKey() {

        Site site = JsonUtil.getObject(ConfigManager.getValue(
                "siteBadParam.alertsWithInvalidSiteKey"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for active Alerts With Invalid SiteKey
     * @return
     */
    @DataProvider(name = "activeAlertsWithInvalidSiteKey")
    public static Object[][] activeAlertsWithInvalidSiteKey() {

        Site site = JsonUtil.getObject(ConfigManager.getValue(
                "siteBadParam.activeAlertsWithInvalidSiteKey"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for energy Period By Months
     * @return
     */
    @DataProvider(name = "energyPeriodByMonths")
    public static Object[][] energyPeriodByMonths() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadParam.energyPeriodByMonths"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for power Period By Months
     * @return
     */
    @DataProvider(name = "powerPeriodByMonths")
    public static Object[][] powerPeriodByMonths() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadParam.powerPeriodByMonths"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for specified Metric Invalid Key
     * @return
     */
    @DataProvider(name = "specifiedMetricInvalidKey")
    public static Object[][] specifiedMetricInvalidKey() {

        Site site = JsonUtil.getObject(ConfigManager.getValue(
                "siteBadParam.specifiedMetricInvalidKey"), Site.class);

        return new Object[][]{{site}};
    }
}
