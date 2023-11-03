package com.sunpower.automation.edp.api.data.site;

import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.manager.ConfigManager;
import com.sunpower.automation.core.utils.JsonUtil;
import com.sunpower.automation.edp.api.data.AbstractDataProvider;
import org.testng.annotations.DataProvider;

import java.util.List;

public class SiteApiData extends AbstractDataProvider {


    /**
     * Preparing data for site Subscriptions
     *
     * @return
     */
    @DataProvider(name = "siteSubscriptions")
    public static Object[][] siteSubscriptions() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.siteSubscriptions"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for last Digits Address
     *
     * @return
     */
    @DataProvider(name = "lastDigitsAddress")
    public static Object[][] lastDigitsAddress() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.lastDigitsAddress"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for view Layout Permission
     *
     * @return
     */
    @DataProvider(name = "viewLayoutPermission")
    public static Object[][] viewLayoutPermission() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.viewLayoutPermission"), Site.class);
        return new Object[][]{{site}};
    }

    /**
     * Preparing data for delete Panel Layout
     *
     * @return
     */
    @DataProvider(name = "deletePanelLayout")
    public static Object[][] deletePanelLayout() {

        List<Site> site = JsonUtil.getListObject(ConfigManager.getValue("site.deletePanelLayout"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for site Service Codes
     *
     * @return
     */
    @DataProvider(name = "siteServiceCodes")
    public static Object[][] siteServiceCodes() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.siteServiceCodes"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for site Service Codes
     *
     * @return
     */
    @DataProvider(name = "dealerInformation")
    public static Object[][] dealerInformation() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.dealerInformation"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for device assignment by siteId
     *
     * @return
     */
    @DataProvider(name = "deviceAssignmentBySiteId")
    public static Object[][] deviceAssignmentBySiteId() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.deviceAssignmentBySiteId"), Site.class);
        return new Object[][] {{site}};
    }

    /**
     * Preparing data for create site unauthorized
     *
     * @return
     */
    @DataProvider(name = "createSitePayload")
    public static Object[][] createSitePayload() {

        Site createSite = JsonUtil.getObject(ConfigManager.getValue("site.createSitePayload"), Site.class);
        return new Object[][] {{createSite}};
    }

    /**
     * Preparing data to update Site unauthorized
     *
     * @return
     */
    @DataProvider(name = "updateSitePayload")
    public static Object[][] updateSitePayload() {

        Site updateSite = JsonUtil.getObject(ConfigManager.getValue("site.updateSitePayload"), Site.class);
        return new Object[][] {{updateSite}};
    }

    /**
     * Preparing data for site unauthorized
     *
     * @return
     */
    @DataProvider(name = "getSite")
    public static Object[][] getSite() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.getSite"), Site.class);
        return new Object[][] {{site}};
    }

    /**
     * Preparing data for site party unauthorized
     *
     * @return
     */
    @DataProvider(name = "siteParties")
    public static Object[][] siteParties() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.siteParties"), Site.class);
        return new Object[][] {{site}};
    }

    /**
     * Preparing data for site incentive unauthorized
     *
     * @return
     */
    @DataProvider(name = "siteIncentiveState")
    public static Object[][] siteIncentiveState() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.siteIncentiveState"), Site.class);
        return new Object[][] {{site}};
    }

    /**
     * Preparing data for alert status field
     *
     * @return
     */
    @DataProvider(name = "alertStatusField")
    public static Object[][] alertStatusField() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.alertStatusField"), Site.class);
        return new Object[][]{{site}};
    }

    /**
     * Preparing data for site energy period by months
     *
     * @return
     */
    @DataProvider(name = "siteEnergyPeriodByMonths")
    public static Object[][] siteEnergyPeriodByMonths() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.siteEnergyPeriodByMonths"), Site.class);
        return new Object[][] {{site}};
    }

    /**
     * Preparing data for site energy period by days
     *
     * @return
     */
    @DataProvider(name = "siteEnergyPeriodByDays")
    public static Object[][] siteEnergyPeriodByDays() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.siteEnergyPeriodByDays"), Site.class);
        return new Object[][] {{site}};
    }

    /**
     * Preparing data for site power period by days
     *
     * @return
     */
    @DataProvider(name = "sitePowerPeriodByDays")
    public static Object[][] sitePowerPeriodByDays() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.sitePowerPeriodByDays"), Site.class);
        return new Object[][] {{site}};
    }

    /**
     * Preparing data for specific metric
     *
     * @return
     */
    @DataProvider(name = "specifiedMetric")
    public static Object[][] specifiedMetric() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.specifiedMetric"), Site.class);
        return new Object[][] {{site}};
    }

    /**
     * Preparing data for device power with invalid interval
     *
     * @return
     */
    @DataProvider(name = "getDevicePowerWithInvalidInterval")
    public static Object[][] getDevicePowerWithInvalidInterval() {

        List<Site> site = JsonUtil.getListObject(ConfigManager.getValue(
                "site.getDevicePowerWithInvalidInterval"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for device energy with invalid interval
     *
     * @return
     */
    @DataProvider(name = "getDeviceEnergyWithInvalidInterval")
    public static Object[][] getDeviceEnergyWithInvalidInterval() {

        List<Site> site = JsonUtil.getListObject(ConfigManager.getValue(
                "site.getDeviceEnergyWithInvalidInterval"), Site.class);

        return new Object[][]{{site}};
    }


    /**
     * Preparing data for alerts
     *
     * @return
     */
    @DataProvider(name = "getAlertsBySiteKey")
    public static Object[][] getAlertsBySiteKey() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.getAlertsBySiteKey"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data
     * @return
     */
    @DataProvider(name = "storedUtilityAndTariff")
    public static Object[][] storedUtilityAndTariff() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.storedUtilityAndTariff"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data
     * @return
     */
    @DataProvider(name = "updateStoredUtilityAndTariff")
    public static Object[][] updateStoredUtilityAndTariff() {

        List<Site> sites = JsonUtil.getListObject(ConfigManager.getValue("site.updateStoredUtilityAndTariff"), Site.class);

        return new Object[][]{{sites}};
    }

    /**
     * Preparing data
     * @return
     */
    @DataProvider(name = "pdfFileValidation")
    public static Object[][] pdfFileValidation() {

        List<Site> sites = JsonUtil.getListObject(ConfigManager.getValue("site.pdfFileValidation"), Site.class);

        return new Object[][]{{sites}};
    }

    /**
     * Preparing data
     * @return
     */
    @DataProvider(name = "searchForPlaceAddress")
    public static Object[][] searchForPlaceAddress() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.searchForPlaceAddress"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data
     * @return
     */
    @DataProvider(name = "retrievesLastDaysAnyDevice")
    public static Object[][] retrievesLastDaysAnyDevice() {

        List<Site> sites = JsonUtil.getListObject(ConfigManager.getValue("site.retrievesLastDaysAnyDevice"), Site.class);

        return new Object[][]{{sites}};
    }
}
