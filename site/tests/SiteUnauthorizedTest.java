package com.sunpower.automation.edp.api.site.tests;

import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.enums.Steps;
import com.sunpower.automation.core.listeners.AutomationListener;
import com.sunpower.automation.core.testng.CustomReport;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.edp.api.base.EdpApiRestActions;
import com.sunpower.automation.edp.api.base.EdpApiTestBase;
import com.sunpower.automation.edp.api.businessflow.site.SiteApiBL;
import com.sunpower.automation.edp.api.data.site.SiteApiData;
import com.sunpower.automation.edp.api.enums.site.SiteEnum;
import com.sunpower.automation.edp.api.payload.EdpPayload;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({CustomReport.class, AutomationListener.class})
public class SiteUnauthorizedTest extends EdpApiTestBase {

    EdpApiRestActions restActions = EdpApiRestActions.getInstance(SiteApiBL.class);

    EdpPayload payload = EdpPayload.getInstance();

    /**
     * C288402 - Get site-device assignment by siteId Unauthorized - Status code matches the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "deviceAssignmentBySiteId", priority = 0)
    public void testUnauthorizedForSiteDeviceAssignmentBySiteId(Site site) {

        LogUtil.log(Steps.START, "Get site-device assignment by siteId Unauthorized - Status code matches");
        restActions.url(SiteEnum.ASSIGNMENT).queryParam(site).withInvalidToken().get().doAssert().is401();
    }

    /**
     * C288403 - Create site Unauthorized - Status code matches the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "createSitePayload", priority = 1)
    public void testUnauthorizedForCreateSite(Site site) {

        LogUtil.log(Steps.START, "Create site Unauthorized  - Status code matches the expected result");
        restActions.url(SiteEnum.ASSIGNMENT).withInvalidToken().post().doAssert().is401();
    }

    /**
     * C288404 - Update site Unauthorized  - Status code matches the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "updateSitePayload", priority = 2)
    public void testUnauthorizedForUpdateSite(Site site) {

        LogUtil.log(Steps.START, "Update site Unauthorized  - Status code matches the expected result");
        restActions.url(SiteEnum.ASSIGNMENT).withInvalidToken().body(payload.payloadForUpdateSite(site))
                .post().doAssert().is401();
    }

    /**
     * C288405 - Get site Unauthorized  - Status code matches the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "getSite", priority = 3)
    public void testUnauthorizedForGetSite(Site site) {

        LogUtil.log(Steps.START, "Get site Unauthorized  - Status code matches the expected result");
        restActions.url(SiteEnum.GET_SITE).pathParam(site).withInvalidToken().get().doAssert().is401();
    }

    /**
     * C288406 - "Get site Unauthorized  - Status code matches the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "siteParties", priority = 4)
    public void testUnauthorizedForSiteParties(Site site) {

        LogUtil.log(Steps.START, "Get site Unauthorized  - Status code matches the expected result");
        restActions.url(SiteEnum.PARTY).pathParam(site).withInvalidToken().get().doAssert().is401();
    }

    /**
     * C288407 - Get site incentives Unauthorized - Status code matches the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "siteIncentiveState", priority = 5)
    public void testUnauthorizedForSiteIncentives(Site site) {

        LogUtil.log(Steps.START, "Get site incentives Unauthorized - Status code matches the expected result");
        restActions.url(SiteEnum.INCENTIVES).pathParam(site).withInvalidToken().get().doAssert().is401();
    }

    /**
     * C288408 - Associate utility tariff to a site Unauthorized - Status code matches the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "siteIncentiveState", priority = 6)
    public void testUnauthorizedForUtilityTariff(Site site) {

        LogUtil.log(Steps.START, "Associate utility tariff to a site Unauthorized - Status code matches");
        restActions.url(SiteEnum.TARIFF).withInvalidToken().patch().doAssert().is401();
    }

    /**
     * C288409 - Get site utilities Unauthorized - Status code matches the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "siteIncentiveState", priority = 7)
    public void testUnauthorizedForSiteUtilities(Site site) {

        LogUtil.log(Steps.START, "Get site utilities Unauthorized - Status code matches the expected result");
        restActions.url(SiteEnum.UTILITY).withInvalidToken().get().doAssert().is401();
    }

    /**
     * C288410 - Get site tariffs Unauthorized - Status code matches the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "siteIncentiveState", priority = 8)
    public void testUnauthorizedForSiteTariffs(Site site) {

        LogUtil.log(Steps.START, "Get site tariffs Unauthorized - Status code matches the expected result");
        restActions.url(SiteEnum.TARIFF).withInvalidToken().get().doAssert().is401();
    }

    /**
     * C288411 - Get alerts from C3 Unauthorized - Status code matches the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "alertStatusField", priority = 9)
    public void testUnauthorizedForGetAlerts(Site site) {

        LogUtil.log(Steps.START, "Get alerts from C3 Unauthorized - Status code matches the expected result");
        restActions.url(SiteEnum.ALL_ALERT).pathParam(site).withInvalidToken().get().doAssert().is401();
    }

    /**
     * C288412 - Get active alerts from C3 Unauthorized - Status code matches the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "alertStatusField", priority = 10)
    public void testUnauthorizedForGetActiveAlerts(Site site) {

        LogUtil.log(Steps.START, "Get active alerts from C3 Unauthorized - Status code matches the expected result");
        restActions.url(SiteEnum.ALL_ALERT).pathParam(site).withInvalidToken().get().doAssert().is401();
    }

    /**
     * C288413 - Get energy for the period by months Unauthorized - Status code matches the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "siteEnergyPeriodByMonths", priority = 11)
    public void testUnauthorizedForGetEnergyMonths(Site site) {

        LogUtil.log(Steps.START, "Get energy for the period by months Unauthorized - Status code matches");
        restActions.url(SiteEnum.ENERGY).pathParam(site).queryParam(site).withInvalidToken().get().doAssert().is401();
    }

    /**
     * C288414 - Get energy for the period by days Unauthorized - Status code matches the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "siteEnergyPeriodByDays", priority = 12)
    public void testUnauthorizedForGetEnergyDays(Site site) {

        LogUtil.log(Steps.START, "Get energy for the period by days Unauthorized - Status code matches");
        restActions.url(SiteEnum.ENERGY).pathParam(site).queryParam(site).withInvalidToken().get().doAssert().is401();
    }

    /**
     * C288415 - Get power Unauthorized  - Status code matches the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "sitePowerPeriodByDays", priority = 13)
    public void testUnauthorizedForGetPower(Site site) {

        LogUtil.log(Steps.START, "Get power Unauthorized  - Status code matches the expected result");
        restActions.url(SiteEnum.POWER).pathParam(site).queryParam(site).withInvalidToken().get().doAssert().is401();
    }

    /**
     * C288416 - Get lifetime energy production Unauthorized - Status code matches the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "sitePowerPeriodByDays", priority = 14)
    public void testUnauthorizedForGetLifetimeEnergyProd(Site site) {

        LogUtil.log(Steps.START, "Get lifetime energy production Unauthorized - Status code matches");
        restActions.url(SiteEnum.POWER).pathParam(site).queryParam(site).withInvalidToken().get().doAssert().is401();
    }

    /**
     * C288419 - Get data for specified metric(s) Unauthorized - Status code matches the expected result
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "specifiedMetric", priority = 15)
    public void testUnauthorizedForGetDataSpecifiedMetric(Site site) {

        LogUtil.log(Steps.START, "Get data for specified metric(s) Unauthorized - Status code matches");
        restActions.url(SiteEnum.METRIC_BATTERY).pathParam(site).withInvalidToken().get().doAssert().is401();
    }
}
