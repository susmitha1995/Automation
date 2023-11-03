package com.sunpower.automation.edp.api.data.site;

import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.manager.ConfigManager;
import com.sunpower.automation.core.utils.JsonUtil;
import com.sunpower.automation.edp.api.data.AbstractDataProvider;
import org.testng.annotations.DataProvider;

import java.util.List;

public class SiteBadReqData extends AbstractDataProvider {

    /**
     * Preparing data for without Query
     *
     * @return
     */
    @DataProvider(name = "withoutQuery")
    public static Object[][] withoutQuery() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadReq.withoutQuery"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for incorrect Query
     *
     * @return
     */
    @DataProvider(name = "incorrectQuery")
    public static Object[][] incorrectQuery() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadReq.incorrectQuery"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for duplicate Site
     *
     * @return
     */
    @DataProvider(name = "duplicateSite")
    public static Object[][] duplicateSite() {

        Site duplicateSite = JsonUtil.getObject(ConfigManager.getValue("siteBadReq.duplicateSite"), Site.class);

        return new Object[][]{{duplicateSite}};
    }

    /**
     * Preparing data for without SiteName
     *
     * @return
     */
    @DataProvider(name = "withoutSiteName")
    public static Object[][] withoutSiteName() {

        List<Site> createSite = JsonUtil.getListObject(ConfigManager.getValue("siteBadReq.withoutSiteName"), Site.class);

        return new Object[][]{{createSite}};
    }

    /**
     * Preparing data for without City
     *
     * @return
     */
    @DataProvider(name = "withoutCity")
    public static Object[][] withoutCity() {

        List<Site> createSite = JsonUtil.getListObject(ConfigManager.getValue("siteBadReq.withoutCity"), Site.class);

        return new Object[][]{{createSite}};
    }


    /**
     * Preparing data for without Address
     *
     * @return
     */
    @DataProvider(name = "withoutAddress")
    public static Object[][] withoutAddress() {

        List<Site> createSite = JsonUtil.getListObject(ConfigManager.getValue("siteBadReq.withoutAddress"), Site.class);

        return new Object[][]{{createSite}};
    }

    /**
     * Preparing data for update Site Without City
     *
     * @return
     */
    @DataProvider(name = "updateSiteWithoutCity")
    public static Object[][] updateSiteWithoutCity() {

        List<Site> createSite = JsonUtil.getListObject(ConfigManager.getValue("siteBadReq.updateSiteWithoutCity"), Site.class);

        return new Object[][]{{createSite}};
    }


    /**
     * Preparing data for update Site Without Address
     *
     * @return
     */
    @DataProvider(name = "updateSiteWithoutAddress")
    public static Object[][] updateSiteWithoutAddress() {

        Site createSite = JsonUtil.getObject(ConfigManager.getValue("siteBadReq.updateSiteWithoutAddress"), Site.class);

        return new Object[][]{{createSite}};
    }

    /**
     * Preparing data for site With Invalid SiteKey
     *
     * @return
     */
    @DataProvider(name = "siteWithInvalidSiteKey")
    public static Object[][] siteWithInvalidSiteKey() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadReq.siteWithInvalidSiteKeyErrorMsg"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for siteParties With Invalid SiteKey
     *
     * @return
     */
    @DataProvider(name = "sitePartiesWithInvalidSiteKey")
    public static Object[][] sitePartiesWithInvalidSiteKey() {

        Site site = JsonUtil.getObject(ConfigManager
                .getValue("siteBadReq.sitePartiesWithInvalidSiteKeyErrorMsg"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for site Incentives With Invalid State
     *
     * @return
     */
    @DataProvider(name = "siteIncentivesWithInvalidState")
    public static Object[][] siteIncentivesWithInvalidState() {

        Site site = JsonUtil.getObject(ConfigManager.getValue(
                "siteBadReq.siteIncentivesWithInvalidStateErrorMsg"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for site With Invali dUtilityId
     *
     * @return
     */
    @DataProvider(name = "siteWithInvalidUtilityId")
    public static Object[][] siteWithInvalidUtilityId() {

        Site site = JsonUtil.getObject(ConfigManager.getValue(
                "siteBadReq.siteWithInvalidUtilityIdErrorMsg"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for utilities Empty ZipCode
     *
     * @return
     */
    @DataProvider(name = "utilitiesEmptyZipCode")
    public static Object[][] utilitiesEmptyZipCode() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadReq.utilitiesEmptyZipCodeErrorMsg"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for tariffs Without SiteType
     *
     * @return
     */
    @DataProvider(name = "tariffsWithoutSiteType")
    public static Object[][] tariffsWithoutSiteType() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadReq.tariffsWithoutSiteType"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for alerts With Invalid SiteKey
     *
     * @return
     */
    @DataProvider(name = "alertsWithInvalidSiteKey")
    public static Object[][] alertsWithInvalidSiteKey() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadReq.alertsWithInvalidSiteKey"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for energy With Invalid SiteKey By Month
     *
     * @return
     */
    @DataProvider(name = "energyWithInvalidSiteKeyByMonth")
    public static Object[][] energyWithInvalidSiteKeyByMonth() {

        Site site = JsonUtil.getObject(ConfigManager
                .getValue("siteBadReq.energyWithInvalidSiteKeyByMonth"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for energy With Invalid SiteKey By Days
     *
     * @return
     */
    @DataProvider(name = "energyWithInvalidSiteKeyByDays")
    public static Object[][] energyWithInvalidSiteKeyByDays() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadReq.energyWithInvalidSiteKeyByDays"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for power With Invalid SiteKey By Days
     *
     * @return
     */
    @DataProvider(name = "powerWithInvalidSiteKeyByDays")
    public static Object[][] powerWithInvalidSiteKeyByDays() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadReq.powerWithInvalidSiteKeyByDays"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for specified Metric Invalid SiteKey
     *
     * @return
     */
    @DataProvider(name = "specifiedMetricInvalidSiteKey")
    public static Object[][] specifiedMetricInvalidSiteKey() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadReq.specifiedMetricInvalidSiteKey"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for active Alerts With Invalid SiteKey
     *
     * @return
     */
    @DataProvider(name = "activeAlertsWithInvalidSiteKey")
    public static Object[][] activeAlertsWithInvalidSiteKey() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("siteBadReq.activeAlertsWithInvalidSiteKey"), Site.class);

        return new Object[][]{{site}};
    }
}
