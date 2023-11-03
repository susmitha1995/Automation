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
import com.sunpower.automation.edp.api.data.site.SiteBadReqData;
import com.sunpower.automation.edp.api.enums.site.SiteEnum;
import com.sunpower.automation.edp.api.payload.EdpPayload;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners({CustomReport.class, AutomationListener.class})
public class SiteBadRequestTest extends EdpApiTestBase {

    EdpApiRestActions restActions = EdpApiRestActions.getInstance(SiteApiBL.class);

    EdpPayload payload = EdpPayload.getInstance();

    /**
     * C288379 - Get site-device assignment without query - response error
     * message and code match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "withoutQuery", priority = 0)
    public void testDeviceAssignmentWithoutQuery(Site site) {

        LogUtil.log(Steps.START, "Get site-device assignment without query");
        restActions.url(SiteEnum.ASSIGNMENT).get().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288380 - Get site-device assignment by deviceKey with incorrect query -
     * response error message and code match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "incorrectQuery", priority = 1)
    public void testDeviceAssignmentIncorrectQuery(Site site) {

        LogUtil.log(Steps.START, "Get site-device assignment incorrect query");
        restActions.url(SiteEnum.ASSIGNMENT).queryParam(site).get().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288381 - Create duplicate site - response error message and code match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "duplicateSite", priority = 2)
    public void testCreateDuplicateSite(Site site) {

        LogUtil.log(Steps.START, "Create duplicate site  - response error message and code match the expected result");
        restActions.url(SiteEnum.SITE).body(payload.createPayload(site)).post().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288382 - Create site without siteName - response error message and code match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "withoutSiteName", priority = 3)
    public void testCreateSiteWithoutSiteName(List<Site> site) {

        LogUtil.log(Steps.START, "Create site without siteName - response error message and code match");
        restActions.url(SiteEnum.SITE).body(payload.createPayload(site.get(0))).post().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site.get(1)), Lists.list("message", "code"));
    }

    /**
     * C288383 - Create site without siteName - response error message and code match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "withoutCity", priority = 4)
    public void testCreateSiteWithoutCity(List<Site> site) {

        LogUtil.log(Steps.START, "Create site without city - response error message and code match");
        restActions.url(SiteEnum.SITE).body(payload.createPayload(site.get(0))).post().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site.get(1)), Lists.list("message", "code"));
    }

    /**
     * C288384 - Create site without address - response error message and code match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "withoutAddress", priority = 5)
    public void testCreateSiteWithoutAddress(List<Site> site) {

        LogUtil.log(Steps.START, "Create site without address - response error message and code match");
        restActions.url(SiteEnum.SITE).body(payload.createPayload(site.get(0))).post().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site.get(1)), Lists.list("message", "code"));
    }

    /**
     * C288385 - Update site without city - response error message and code match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "updateSiteWithoutCity", priority = 6)
    public void testUpdateSiteWithoutCity(List<Site> site) {

        LogUtil.log(Steps.START, "Update site without city -response error message and code match");
        restActions.url(SiteEnum.SITE).body(payload.createPayload(site.get(0))).patch().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site.get(1)), Lists.list("message", "code"));
    }

    /**
     * C288386 - Update site without address - response error message and code match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "updateSiteWithoutAddress", priority = 7)
    public void testUpdateSiteWithoutAddress(Site site) {

        LogUtil.log(Steps.START, "Update site without address - response error message and code match");
        restActions.url(SiteEnum.SITE).body(payload.createPayload(site)).patch().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288387 - Get site with invalid siteKey - response error message and code match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "siteWithInvalidSiteKey", priority = 8)
    public void testGetSiteWithInvalidSiteKey(Site site) {

        LogUtil.log(Steps.START, "Get site with invalid siteKey - response error message and code match");
        restActions.url(SiteEnum.GET_SITE).pathParam(site).get().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288388 - Get site parties with invalid siteKey - response error message and code match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "sitePartiesWithInvalidSiteKey", priority = 9)
    public void testGetSitePartiesWithInvalidSiteKey(Site site) {

        LogUtil.log(Steps.START, "Get site parties with invalid siteKey - response error message and code match");
        restActions.url(SiteEnum.PARTY).pathParam(site).get().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288389 - Get site incentives with invalid state - response error message and code match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "siteIncentivesWithInvalidState", priority = 10)
    public void testGetSiteIncentivesWithInvalidState(Site site) {

        LogUtil.log(Steps.START, "Get site incentives with invalid state - response error message and code match");
        restActions.url(SiteEnum.INCENTIVES).pathParam(site).get().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288390 - Associate utility tariff to a site with invalid utilityId -
     * response error message and code match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "siteWithInvalidUtilityId", priority = 11)
    public void testTariffSiteWithInvalidUtilityId(Site site) {

        LogUtil.log(Steps.START, "Associate utility tariff to a site with invalid utilityId ");
        restActions.url(SiteEnum.TARIFF).queryParam(site).get().doAssert().is404().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288391 - Get site utilities with empty zipCode - response error message and code match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "utilitiesEmptyZipCode",
            priority = 12)
    public void testSiteUtilitiesEmptyZipCode(Site site) {

        LogUtil.log(Steps.START, "Get site utilities with empty zipCode - response error message and code match");
        restActions.url(SiteEnum.UTILITY).queryParam(site).get().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288392 - Get site tariffs without siteType - response error message and code match the
     * expected
     * result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "tariffsWithoutSiteType", priority = 13)
    public void testSiteTariffsWithoutSiteType(Site site) {

        LogUtil.log(Steps.START, "Get site tariffs without siteType - response error message and code match");
        restActions.url(SiteEnum.TARIFF).queryParam(site).get().doAssert().is404().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288393 - Get alerts from C3 with invalid siteKey - response error message and code match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "alertsWithInvalidSiteKey", priority = 14)
    public void testAlertsWithInvalidSiteKey(Site site) {

        LogUtil.log(Steps.START, "Get alerts from C3 with invalid siteKey - response error message and code match");
        restActions.url(SiteEnum.ALL_ALERT).pathParam(site).get().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288394 - Get active alerts from C3 with invalid siteKey - response error message and code
     * match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "activeAlertsWithInvalidSiteKey", priority = 15)
    public void testActiveAlertsWithInvalidSiteKey(Site site) {

        LogUtil.log(Steps.START, "Get active alerts from C3 with invalid siteKey - response error message and code");
        restActions.url(SiteEnum.ACTIVE_ALERT).pathParam(site).get().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288395 - Get energy for the period by months with invalid siteKey -
     * response error message and code match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "energyWithInvalidSiteKeyByMonth", priority = 16)
    public void testEnergyWithInvalidSiteKeyByMonth(Site site) {

        LogUtil.log(Steps.START, "Get energy for the period by months with invalid siteKey");
        restActions.url(SiteEnum.ENERGY).pathParam(site).queryParam(site).get().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288396 - Get energy for the period by days with invalid siteKey -
     * response error message and code match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "energyWithInvalidSiteKeyByDays", priority = 17)
    public void testEnergyWithInvalidSiteKeyByDays(Site site) {

        LogUtil.log(Steps.START, "Get energy for the period by days with invalid siteKey "
                + "- response error message and code match the expected result");
        restActions.url(SiteEnum.ENERGY).pathParam(site).queryParam(site).get().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288397 - Get power with invalid siteKey - response error message and code match the
     * expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "powerWithInvalidSiteKeyByDays", priority = 18)
    public void testPowerWithInvalidSiteKeyByDays(Site site) {

        LogUtil.log(Steps.START, "Get power with invalid siteKey - response error message and code match");
        restActions.url(SiteEnum.POWER).pathParam(site).queryParam(site).get().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }

    /**
     * C288401 - Get data for specified metric(s) with invalid siteKey -
     * response error message and code match the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteBadReqData.class, dataProvider = "specifiedMetricInvalidSiteKey", priority = 19)
    public void testDataForSpecifiedMetricInvalidSiteKey(Site site) {

        LogUtil.log(Steps.START, "Get data for specified metric(s) with invalid siteKey-error message and code match");
        restActions.url(SiteEnum.METRIC).pathParam(site).post().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
    }
}
