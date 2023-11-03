package com.sunpower.automation.edp.api.site.tests;

import com.codeborne.selenide.impl.Lists;
import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.enums.Steps;
import com.sunpower.automation.core.listeners.AutomationListener;
import com.sunpower.automation.core.testng.CustomReport;
import com.sunpower.automation.core.utils.GsonUtils;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.edp.api.base.EdpApiRestActions;
import com.sunpower.automation.edp.api.base.EdpApiTestBase;
import com.sunpower.automation.edp.api.businessflow.site.SiteApiBL;
import com.sunpower.automation.edp.api.data.site.SiteBadParamData;
import com.sunpower.automation.edp.api.data.site.SiteNightVisionData;
import com.sunpower.automation.edp.api.enums.site.SiteEnum;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners({CustomReport.class, AutomationListener.class})
public class SiteBadParameterTest extends EdpApiTestBase {

    EdpApiRestActions restActions = EdpApiRestActions.getInstance(SiteApiBL.class);

    /**
     * C288420 - Get non-existing site-device assignment by siteId - response error message and code
     * @param site
     */
    @Test(dataProviderClass = SiteBadParamData.class, dataProvider = "nonExistingSiteKey", priority = 0)
    public void testNonExistingSiteDeviceAssignmentSiteKey(Site site) {

        LogUtil.log(Steps.START, "Get non-existing site-device assignment by siteId");
        restActions.url(SiteEnum.ASSIGNMENT).queryParam(site).get().doAssert().is204();
    }

    /**
     * C288421 - Get non-existing site-device assignment by deviceKey - response error message and code
     * match the expected result
     * @param site
     */
    @Test(dataProviderClass = SiteBadParamData.class, dataProvider = "nonExistingDeviceKey", priority = 1)
    public void testNonExistingSiteDeviceAssignmentDeviceKey(Site site) {

        LogUtil.log(Steps.START, "Get non-existing site-device assignment by deviceKey");
        restActions.url(SiteEnum.ASSIGNMENT).queryParam(site).get().doAssert().is204();
    }

    /**
     * C288422 - Get non-existing site-device assignment by siteId and - response error message
     * and code match the expected result
     * @param site
     */
    @Test(dataProviderClass = SiteBadParamData.class, dataProvider = "nonExistingSiteKeyAndDeviceKey", priority = 2)
    public void testNonExistingSiteDeviceAssignmentSiteKeyAndDeviceKey(Site site) {

        LogUtil.log(Steps.START, "Get non-existing site-device assignment by Site key and deviceKey");
        restActions.url(SiteEnum.ASSIGNMENT).queryParam(site).get().doAssert().is204();
    }

    /**
     * C288423 - Get site - response error message and code match the expected result
     * @param site
     */
    @Test(dataProviderClass = SiteBadParamData.class, dataProvider = "getSite", priority = 3)
    public void testSiteWithInvalidSiteKey(Site site) {

        LogUtil.log(Steps.START, "Get Site with invalid siteKey");
        restActions.url(SiteEnum.GET_SITE).pathParam(site).get().doAssert().is404().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288424 - Get site parties - response error message and code match the expected result
     * @param site
     */
    @Test(dataProviderClass = SiteBadParamData.class, dataProvider = "getParties", priority = 4)
    public void testPartiesWithInvalidSiteKey(Site site) {

        LogUtil.log(Steps.START, "Get site parties  - response error message and code match the expected result");
        restActions.url(SiteEnum.PARTY).pathParam(site).get().doAssert().is404().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288425 - Get site incentives - response error message and code match the expected result
     * @param site
     */
    @Test(dataProviderClass = SiteBadParamData.class, dataProvider = "getSiteIncentives", priority = 5)
    public void testSiteIncentives(Site site) {

        LogUtil.log(Steps.START, "Get site incentives  - response error message and code match the expected result");
        restActions.url(SiteEnum.INCENTIVES).pathParam(site).get().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288426 - Get site utilities - response error message and code match the expected result
     * @param site
     */
    @Test(dataProviderClass = SiteBadParamData.class, dataProvider = "getSiteUtilities", priority = 6)
    public void testSiteUtilities(Site site) {

        LogUtil.log(Steps.START, "Get site utilities  - response error message and code match the expected result");
        restActions.url(SiteEnum.UTILITY).queryParam(site).get().doAssert().is404().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288427 - Get site tariffs - response error message and code match the expected result
     * @param site
     */
    @Test(dataProviderClass = SiteBadParamData.class, dataProvider = "getSiteTariffs", priority = 7)
    public void testSiteTariffs(Site site) {

        LogUtil.log(Steps.START, "Get site tariffs  - response error message and code match the expected result");
        restActions.url(SiteEnum.TARIFF).queryParam(site).get().doAssert().is404().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288428 - Get alerts from C3 - response error message and code match the expected result
     * @param site
     */
    @Test(dataProviderClass = SiteBadParamData.class, dataProvider = "alertsWithInvalidSiteKey", priority = 8)
    public void testAlertsWithInvalidSiteKey(Site site) {

        LogUtil.log(Steps.START, "Get alerts from C3  - response error message and code match the expected result");
        restActions.url(SiteEnum.ALL_ALERT).pathParam(site).get().doAssert().is404().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288429 - Get active alerts from C3 - response error message and code match the expected result
     * @param site
     */
    @Test(dataProviderClass = SiteBadParamData.class, dataProvider = "activeAlertsWithInvalidSiteKey", priority = 9)
    public void testActiveAlertsWithInvalidSiteKey(Site site) {

        LogUtil.log(Steps.START, "Get active alerts from C3  - response error message and code match");
        restActions.url(SiteEnum.ACTIVE_ALERT).pathParam(site).get().doAssert().is404().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288430 - Get energy for the period by months - response error message and code match the expected result
     * @param site
     */
    @Test(dataProviderClass = SiteBadParamData.class, dataProvider = "energyPeriodByMonths", priority = 10)
    public void testEnergyPeriodByMonthsWithInvalidKey(Site site) {

        LogUtil.log(Steps.START, "Get energy for the period by months - response error message and code match");
        restActions.url(SiteEnum.ENERGY).pathParam(site).queryParam(site).get().doAssert().is404().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288431 - Get power - response error message and code match the expected result
     * @param site
     */
    @Test(dataProviderClass = SiteBadParamData.class, dataProvider = "powerPeriodByMonths", priority = 11)
    public void testPowerPeriodByMonthsWithInvalidKey(Site site) {

        LogUtil.log(Steps.START, "Get power - response error message and code match the expected result");
        restActions.url(SiteEnum.POWER).pathParam(site).queryParam(site).get().doAssert().is404().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288434 - Get data for specified metric(s) - response error message and code match the expected result
     * @param site
     */
    @Test(dataProviderClass = SiteBadParamData.class, dataProvider = "specifiedMetricInvalidKey", priority = 12)
    public void testSpecifiedMetricWithInvalidSiteKey(Site site) {

        LogUtil.log(Steps.START, "Get data for specified metric(s) - response error message and code match");
        restActions.url(SiteEnum.METRIC_BATTERY).pathParam(site).get().doAssert().is404().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C497772 - 400 Logger endpoint - energy
     *
     * @param sites
     */
    @Test(dataProviderClass = SiteNightVisionData.class, dataProvider = "loggerEndpointEnergy", priority = 13)
    public void testLoggerEndpointEnergy(List<Site> sites) {

        LogUtil.log(Steps.START, "Request the endpoint with invalid start date");
        restActions.url(SiteEnum.DEVICE_ENERGY).pathParam(sites.get(0)).queryParam(sites.get(1)).get().doAssert().is400()
                .getObject("").isEqualOnlyItems(GsonUtils.objectToMap(sites.get(1)), Lists.list("message", "code"));
        restActions.url(SiteEnum.DEVICE_ENERGY).pathParam(sites.get(0)).queryParam(sites.get(2)).get().doAssert().is400()
                .getObject("").isEqualOnlyItems(GsonUtils.objectToMap(sites.get(2)), Lists.list("message", "code"));

        LogUtil.log(Steps.START, "Request the endpoint with invalid end date");
        restActions.url(SiteEnum.DEVICE_ENERGY).pathParam(sites.get(0)).queryParam(sites.get(3)).get().doAssert().is400()
                .getObject("").isEqualOnlyItems(GsonUtils.objectToMap(sites.get(3)), Lists.list("message", "code"));
        restActions.url(SiteEnum.DEVICE_ENERGY).pathParam(sites.get(0)).queryParam(sites.get(4)).get().doAssert().is400()
                .getObject("").isEqualOnlyItems(GsonUtils.objectToMap(sites.get(4)), Lists.list("message", "code"));

        LogUtil.log(Steps.START, "Request the endpoint with invalid interval");
        restActions.url(SiteEnum.DEVICE_ENERGY).pathParam(sites.get(0)).queryParam(sites.get(5)).get().doAssert().is400()
                .getObject("").isEqualOnlyItems(GsonUtils.objectToMap(sites.get(5)), Lists.list("message", "code"));
    }
}
