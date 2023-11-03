package com.sunpower.automation.edp.api.data.site;

import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.manager.ConfigManager;
import com.sunpower.automation.core.utils.JsonUtil;
import com.sunpower.automation.edp.api.data.AbstractDataProvider;
import org.testng.annotations.DataProvider;

import java.util.List;

public class SiteNightVisionData extends AbstractDataProvider {

    /**
     * Preparing data for site Incentive State
     *
     * @return
     */
    @DataProvider(name = "siteIncentiveState")
    public static Object[][] siteIncentiveState() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.siteIncentiveState"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for site Energy Period By Months
     *
     * @return
     */
    @DataProvider(name = "siteEnergyPeriodByMonths")
    public static Object[][] siteEnergyPeriodByMonths() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.siteEnergyPeriodByMonths"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for site Energy Period By Days
     *
     * @return
     */
    @DataProvider(name = "siteEnergyPeriodByDays")
    public static Object[][] siteEnergyPeriodByDays() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.siteEnergyPeriodByDays"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for site Power Period By Days
     *
     * @return
     */
    @DataProvider(name = "sitePowerPeriodByDays")
    public static Object[][] sitePowerPeriodByDays() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.sitePowerPeriodByDays"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for test Site Level Power
     *
     * @return
     */
    @DataProvider(name = "testSiteLevelPower")
    public static Object[][] testSiteLevelPower() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.testSiteLevelPower"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for get SiteKey For Panel Energy
     *
     * @return
     */
    @DataProvider(name = "getSiteKeyForPanelEnergy")
    public static Object[][] getSiteKKeyForPanelEnergy() {

        List<Site> site = JsonUtil.getListObject(ConfigManager.getValue(
                "site.getSiteKeyForPanelEnergy"), Site.class);
        return new Object[][]{{site}};
    }

    /**
     * Preparing data for get Site Key For Power
     *
     * @return
     */
    @DataProvider(name = "getSiteKeyForPower")
    public static Object[][] getSiteKeyForPower() {
        List<Site> site = JsonUtil.getListObject(ConfigManager.getValue("site.getSiteKeyForPower"), Site.class);
        return new Object[][]{{site}};
    }

    /**
     * Preparing data for get SiteKey For Energy
     *
     * @return
     */
    @DataProvider(name = "getSiteKeyForEnergy")
    public static Object[][] getSiteKeyForEnergy() {
        List<Site> site = JsonUtil.getListObject(ConfigManager.getValue(
                "site.getSiteKeyForEnergy"), Site.class);
        return new Object[][]{{site}};
    }

    /**
     * Preparing data for get Site Energy
     *
     * @return
     */
    @DataProvider(name = "getSiteEnergy")
    public static Object[][] getSiteEnergy() {

        List<Site> site = JsonUtil.getListObject(ConfigManager.getValue("site.getSiteEnergy"), Site.class);
        return new Object[][]{{site}};
    }

    /**
     * Preparing data for get Site Energy
     *
     * @return
     */
    @DataProvider(name = "loggerEndpointEnergy")
    public static Object[][] loggerEndpointEnergy() {

        List<Site> site = JsonUtil.getListObject(ConfigManager.getValue("site.loggerEndpointEnergy"), Site.class);
        return new Object[][]{{site}};
    }
}
