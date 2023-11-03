package com.sunpower.automation.edp.api.site.tests;

import com.codeborne.selenide.impl.Lists;
import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.enums.Steps;
import com.sunpower.automation.core.listeners.AutomationListener;
import com.sunpower.automation.core.testng.CustomReport;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.edp.api.base.EdpApiRestActions;
import com.sunpower.automation.edp.api.base.EdpApiTestBase;
import com.sunpower.automation.edp.api.businessflow.site.SiteApiBL;
import com.sunpower.automation.edp.api.data.site.SiteV1Data;
import com.sunpower.automation.edp.api.enums.site.SiteEnum;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners({CustomReport.class, AutomationListener.class})
public class SiteV1Test extends EdpApiTestBase {

    SiteApiBL siteBL = SiteApiBL.getInstance();

    EdpApiRestActions restActions = EdpApiRestActions.getInstance(SiteApiBL.class);

    /**
     * C288298 - 200 Get all parties for a given siteKey
     *
     * @param site
     */
    @Test(dataProviderClass = SiteV1Data.class, dataProvider = "siteParties", priority = 0)
    public void testGetSiteParties(Site site) {

        LogUtil.log(Steps.START, "Get site parties and validate");
        restActions.url(SiteEnum.PARTY).pathParam(site).get().doAssert().is200().getObject("relations[0]")
                .isEqualOnlyItems(siteBL.validatePartyFields("relations[0].partyId"),
                        Lists.list("partyId", "displayName", "phone", "email", "relation", "partyType"));
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288289 - Retrieve ELH data source for a site
     *
     * @param sites
     */
    @Test(dataProviderClass = SiteV1Data.class, dataProvider = "siteSource", priority = 1)
    public void testGetSiteSource(List<Site> sites) {

        LogUtil.log(Steps.START, "Check site gets data from in ELH");
        restActions.url(SiteEnum.DATA_SOURCE).pathParam(sites.get(0)).get().doAssert().is200()
                .getObject("EDP").isEqual("Data source should be enable", true);

        LogUtil.log(Steps.START, "Check site not gets data from in ELH");
        restActions.url(SiteEnum.DATA_SOURCE).pathParam(sites.get(1)).get().doAssert().is200()
                .getObject("EDP").isEqual("Data source should be disable", false);
    }

    /**
     * C288311 - Get state of charge and charge capacity of all attached energy storage systems
     *
     * @param site
     */
    @Test(dataProviderClass = SiteV1Data.class, dataProvider = "specifiedMetric", priority = 2)
    public void testDataForSpecifiedMetric(Site site) {

        LogUtil.log(Steps.START, "Get data for specified metric(s) - response body");
        restActions.url(SiteEnum.METRIC_BATTERY).pathParam(site).get().doAssert().is200()
                .getObject("data[0]").isEqualOnlyItems(siteBL.validateDeviceFields("data[0].deviceKey"),
                        Lists.list("partyId", "deviceKey", "productModelName", "deviceSerialNumber", "deviceType"));
    }

    /**
     * C288332 - Get a site base on siteKey
     *
     * @param site
     */
    @Test(dataProviderClass = SiteV1Data.class, dataProvider = "addSystemSize", priority = 3)
    public void testAddSystemSizeToGetSite(Site site) {

        LogUtil.log(Steps.START, "Add system size to get size");
        restActions.url(SiteEnum.GET_SITE).pathParam(site).get().doAssert().is200()
                .getList("energyContracts.dsgn_sys_sz_w")
                .hasProperValue("dsgn_sys_sz_w value should not be null or empty");
    }

    /**
     * C288339 - Retrieves operational mode for a site.
     *
     * @param site
     */
    @Test(dataProviderClass = SiteV1Data.class, dataProvider = "verifyOperationalMode", priority = 4)
    public void testVerifyOperationalMode(Site site) {

        LogUtil.log(Steps.START, "Get Operational Mode EndPoint");
        restActions.url(SiteEnum.OPERATIONAL).pathParam(site).get().doAssert().is200();
    }

    /**
     * C288348 - calculates the system size, updates it and returns it.
     *
     * @param site
     */
    @Test(dataProviderClass = SiteV1Data.class, dataProvider = "systemSizeNewSites", priority = 5)
    public void testCalculateAndUpdateSystemSizeNewSites(Site site) {

        LogUtil.log(Steps.START, "Create endpoint to calculate and update System Size for new Sites");
        restActions.url(SiteEnum.SYSTEM_SIZE).pathParam(site)
                .post().doAssert().getObject("systemSize").isSizeNotZero("System Size should be above 0");
    }

    /**
     * C288354 - Returns LiveWire Kiosk metrics from a Kiosk GUID
     *
     * @param site
     */
    @Test(dataProviderClass = SiteV1Data.class, dataProvider = "liveWireKioskAPI", priority = 6)
    public void testSiteLiveWireKioskAPI(Site site) {

        LogUtil.log(Steps.START, "Verify Site LiveWire Kiosk API");
        restActions.url(SiteEnum.LWKIOSK).queryParam(siteBL.getAvailableKiosk(site))
                .get().doAssert().is200().getBodyAsString()
                .containsBody("The response should be contains XML file format", "<counter>");
    }

    /**
     * C288304 - Get current active (OPEN) alerts for the given siteKey
     *
     * @param site
     */
    @Test(dataProviderClass = SiteV1Data.class, dataProvider = "activeAlertsBySiteKey", priority = 7)
    public void testActiveAlertsBySiteKey(Site site) {

        LogUtil.log(Steps.START, "Get current active (OPEN) alerts for the given siteKey");
        restActions.url(SiteEnum.ACTIVE_ALERT).pathParam(site).get().doAssert().is200().getObject("items[0]")
                .isEqualOnlyItems(siteBL.validateDeviceFieldsBySerialNumber("items[0].deviceSerialNumber"), site.getOnlyItems());
    }

    /**
     * C288301 - Get current active (OPEN) alerts for the given siteKey
     *
     * @param sites
     */
    @Test(dataProviderClass = SiteV1Data.class, dataProvider = "UtilitiesByZipCode", priority = 8)
    public void testUtilitiesByZipCode(List<Site> sites) {

        LogUtil.log(Steps.START, "Get list of utilities for the given zip code");
        restActions.url(SiteEnum.UTILITY).queryParam(sites.get(0)).get().doAssert().is200().getObject("utilities[0]")
                .isEqualOnlyItems(siteBL.validateUtilityMappingBySiteKey(sites.get(1).getSiteKey()), sites.get(0).getOnlyItems());
    }

    /**
     * C288302 - Get current active (OPEN) alerts for the given siteKey
     *
     * @param site
     */
    @Test(dataProviderClass = SiteV1Data.class, dataProvider = "listOfTariffs", priority = 9)
    public void testTariffsUtilityAndTypeCombination(Site site) {

        LogUtil.log(Steps.START, "Get list of tariffs for the given utility and site type combination");
        restActions.url(SiteEnum.TARIFF).queryParam(site).get().doAssert().is200();
    }

    /**
     * C287994 - Get aggregate site base on siteKey
     *
     * @param site
     */
    @Test(dataProviderClass = SiteV1Data.class, dataProvider = "getAggregateSite", priority = 10)
    public void testAggregateSite(List<Site> site) {

        //when send an expansion siteKey
        LogUtil.log(Steps.START, "Get aggregate site by an expansion siteKey");
        restActions.url(SiteEnum.GET_SITE).pathParam(site.get(0)).get().doAssert().is200();

        //when send an aggregate siteKey
        LogUtil.log(Steps.START, "Get aggregate site by an aggregate siteKey");
        restActions.url(SiteEnum.GET_SITE).pathParam(site.get(1)).get().doAssert().is200();

        //when send an original  siteKey
        LogUtil.log(Steps.START, "Get aggregate site by an original siteKey");
        restActions.url(SiteEnum.GET_SITE).pathParam(site.get(2)).get().doAssert().is200();

        //when send a single  siteKey
        LogUtil.log(Steps.START, "Get aggregate site by a single siteKey");
        restActions.url(SiteEnum.GET_SITE).pathParam(site.get(3)).get().doAssert().is200().getObject("")
                .isEqualOnlyItems(siteBL.validateSiteBySiteKey(site.get(3).getSiteKey()), site.get(4).getOnlyItems());
    }
}
