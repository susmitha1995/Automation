package com.sunpower.automation.edp.api.data.site;

import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.manager.ConfigManager;
import com.sunpower.automation.core.utils.JsonUtil;
import com.sunpower.automation.edp.api.data.AbstractDataProvider;
import org.testng.annotations.DataProvider;

import java.util.List;

public class SiteV1Data extends AbstractDataProvider {

    /**
     * Preparing data for site Parties
     *
     * @return
     */
    @DataProvider(name = "siteParties")
    public static Object[][] siteParties() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.siteParties"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for site DataSource
     *
     * @return
     */
    @DataProvider(name = "siteSource")
    public static Object[][] siteSource() {

        List<Site> site = JsonUtil.getListObject(ConfigManager.getValue("site.dataFromELH"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for specified Metric
     *
     * @return
     */
    @DataProvider(name = "specifiedMetric")
    public static Object[][] specifiedMetric() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.specifiedMetric"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for add System Size
     *
     * @return
     */
    @DataProvider(name = "addSystemSize")
    public static Object[][] addSystemSize() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.addSystemSize"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for verify Operational Mode
     *
     * @return
     */
    @DataProvider(name = "verifyOperationalMode")
    public static Object[][] verifyOperationalMode() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.operationalModeSiteKey"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for verify Assignments By SiteKey
     *
     * @return
     */
    @DataProvider(name = "verifyAssignmentsBySiteKey")
    public static Object[][] verifyAssignmentsBySiteKey() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.verifyAssignmentsBySiteKey"), Site.class);
        return new Object[][]{{site}};
    }

    /**
     * Preparing data for system Size New Sites
     *
     * @return
     */
    @DataProvider(name = "systemSizeNewSites")
    public static Object[][] systemSizeNewSites() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.systemSizeNewSites"), Site.class);
        return new Object[][]{{site}};
    }

    /**
     * Preparing data for live Wir eKiosk API
     *
     * @return
     */
    @DataProvider(name = "liveWireKioskAPI")
    public static Object[][] liveWireKioskAPI() {

        Site site = new Site();

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for verify Metric Battery Api
     *
     * @return
     */
    @DataProvider(name = "verifyMetricBatteryApi")
    public static Object[][] verifyMetricBatteryApi() {

        List<Site> site = JsonUtil.getListObject(ConfigManager.getValue(
                "site.verifyMetricBatteryApi"), Site.class);
        return new Object[][]{{site}};
    }

    /**
     * Preparing data for getSite
     * @return
     */
    @DataProvider(name = "getAggregateSite")
    public static Object[][] getAggregateSite() {

        List<Site> site = JsonUtil.getListObject(ConfigManager.getValue("site.getAggregateSite"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for getSite
     * @return
     */
    @DataProvider(name = "activeAlertsBySiteKey")
    public static Object[][] activeAlertsBySiteKey() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.activeAlertsBySiteKey"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for getSite
     * @return
     */
    @DataProvider(name = "listOfTariffs")
    public static Object[][] listOfTariffs() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.listOfTariffs"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for getSite
     * @return
     */
    @DataProvider(name = "UtilitiesByZipCode")
    public static Object[][] UtilitiesByZipCode() {

        List<Site> sites = JsonUtil.getListObject(ConfigManager.getValue("site.UtilitiesByZipCode"), Site.class);

        return new Object[][]{{sites}};
    }

    /**
     * Preparing data for getSite
     * @return
     */
    @DataProvider(name = "moduleLayouts")
    public static Object[][] moduleLayouts() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.moduleLayouts"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for getSite
     * @return
     */
    @DataProvider(name = "reportSpecifiedRecipients")
    public static Object[][] reportSpecifiedRecipients() {

        List<Site> sites = JsonUtil.getListObject(ConfigManager.getValue("site.reportSpecifiedRecipients"), Site.class);

        return new Object[][]{{sites}};
    }

    /**
     * Preparing data for getSite
     * @return
     */
    @DataProvider(name = "savedFileLayout")
    public static Object[][] savedFileLayout() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.savedFileLayout"), Site.class);

        return new Object[][]{{site}};
    }
}
