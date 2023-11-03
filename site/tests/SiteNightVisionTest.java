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
import com.sunpower.automation.edp.api.data.site.SiteNightVisionData;
import com.sunpower.automation.edp.api.enums.site.SiteEnum;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners({CustomReport.class, AutomationListener.class})
public class SiteNightVisionTest extends EdpApiTestBase {

    EdpApiRestActions restActions = EdpApiRestActions.getInstance(SiteApiBL.class);

    /**
     * C288299 - Get available incentive plans for a given state
     *
     * @param site
     */
    @Test(dataProviderClass = SiteNightVisionData.class, dataProvider = "siteIncentiveState", priority = 0)
    public void testAvailableIncentivePlansForGivenState(Site site) {

        LogUtil.log(Steps.START, "Get available incentive plans for a given state and validate");
        restActions.url(SiteEnum.INCENTIVES).pathParam(site).get().doAssert().is200()
                .getObject("").hasProperValue("The values are should not be null or empty");
    }

    /**
     * C288374 - Update GET /v1/site/{siteKey}/panelEnergy to use Shared Library
     *
     * @param sites
     */
    @Test(dataProviderClass = SiteNightVisionData.class, dataProvider = "getSiteKeyForPanelEnergy", priority = 1)
    public void testSiteKeyForPanelEnergy(List<Site> sites) {

        //get response with invalid parameter
        LogUtil.log(Steps.START, "Verify panelEnergy by invalid parameter");
        restActions.url(SiteEnum.GET_PANEL_ENERGY).pathParam(sites.get(1)).get().doAssert().is404();

        //get response with invalid token
        LogUtil.log(Steps.START, "Verify panelEnergy by invalid Token");
        restActions.url(SiteEnum.GET_PANEL_ENERGY).pathParam(sites.get(0)).withInvalidToken()
                .get().doAssert().is401();

        //get response with valid parameter
        LogUtil.log(Steps.START, "Get siteKey for panelEnergy");
        restActions.url(SiteEnum.GET_PANEL_ENERGY).pathParam(sites.get(0)).get().doAssert()
                .is200().getObject("validated").hasProperValue("validated should not be null or empty");
    }

    /**
     * C288376 - Update GET /v2/site/{siteKey}/power
     *
     * @param sites
     */
    @Test(dataProviderClass = SiteNightVisionData.class, dataProvider = "getSiteKeyForPower", priority = 2)
    public void testSiteKeyForPower(List<Site> sites) {

        //get response with invalid interval
        LogUtil.log(Steps.START, "Get power with invalidInterval");
        restActions.url(SiteEnum.POWER).pathParam(sites.get(0)).queryParam(sites.get(1)).get().doAssert().is400();

        //get response with missing param
        LogUtil.log(Steps.START, "Get power with missing parameter");
        restActions.url(SiteEnum.POWER).pathParam(sites.get(0)).queryParam(sites.get(2)).get().doAssert().is400();

        //get response with invalid token
        LogUtil.log(Steps.START, "Get power with invalidToken");
        restActions.url(SiteEnum.POWER).pathParam(sites.get(0)).queryParam(sites.get(2))
                .withInvalidToken().get().doAssert().is401();

        //get response with valid parameter
        LogUtil.log(Steps.START, "Get power with valid parameter");
        restActions.url(SiteEnum.POWER).pathParam(sites.get(0)).queryParam(sites.get(3)).get().doAssert()
                .is200().getObject("items.timestamp").hasProperValue("timestamp should not be null or empty");
        LogUtil.log(Steps.END, "The API response matched with expected result");
    }

    /**
     * C288373 - Update GET /v2/site/{siteKey}/energy
     *
     * @param sites
     */
    @Test(dataProviderClass = SiteNightVisionData.class, dataProvider = "getSiteKeyForEnergy", priority = 3)
    public void testSiteKeyForEnergy(List<Site> sites) {

        //get response with invalid parameter
        LogUtil.log(Steps.START, "Get energy with invalidParameter");
        restActions.url(SiteEnum.ENERGY).pathParam(sites.get(0)).queryParam(sites.get(1)).get().doAssert().is400();

        //get response with invalid token
        LogUtil.log(Steps.START, "Get energy with invalidToken");
        restActions.url(SiteEnum.ENERGY).pathParam(sites.get(0)).queryParam(sites.get(2))
                .withInvalidToken().get().doAssert().is401();

        //get response with valid parameter
        LogUtil.log(Steps.START, "Get energy with valid parameter");
        restActions.url(SiteEnum.ENERGY).pathParam(sites.get(0)).queryParam(sites.get(2)).get().doAssert()
                .is200().getObject("items.timestamp").hasProperValue("timestamp should not be null or empty");
        LogUtil.log(Steps.END, "The API response matched with expected result ");
    }

    /**
     * C288285 - Site Level - Energy
     *
     * @param sites
     */
    @Test(dataProviderClass = SiteNightVisionData.class, dataProvider = "getSiteEnergy", priority = 4)
    public void testSiteEnergy(List<Site> sites) {

        //get site energy
        LogUtil.log(Steps.START, "Get test site energy");
        restActions.url(SiteEnum.ENERGY).pathParam(sites.get(0)).queryParam(sites.get(1)).get()
                .doAssert().is200().getObject("items.energyProduction")
                .hasProperValue("energyProduction value should not be null or empty");
    }

    /**
     * C288305 - Get energy for the period by months - response body includes expected properties & values
     *
     * @param site
     */
    @Test(dataProviderClass = SiteNightVisionData.class, dataProvider = "siteEnergyPeriodByMonths", priority = 5)
    public void testEnergyForThePeriodByMonths(Site site) {

        LogUtil.log(Steps.START, "Get energy for the period by Months and validate");
        restActions.url(SiteEnum.ENERGY).pathParam(site).queryParam(site).get()
                .doAssert().is200().getList("items.energyProduction")
                .hasProperValue("energyProduction value should not be null or empty")
                .getList("items.energyGrid").hasProperValue("energyGrid should not be null or empty");
    }

    /**
     * C288306 - Get energy for the period by days - response body includes expected properties & values
     *
     * @param site
     */
    @Test(dataProviderClass = SiteNightVisionData.class, dataProvider = "siteEnergyPeriodByDays", priority = 6)
    public void testEnergyForThePeriodByDays(Site site) {

        LogUtil.log(Steps.START, "Get energy for the period by Days and validate");
        restActions.url(SiteEnum.ENERGY).pathParam(site).queryParam(site).get()
                .doAssert().is200().getList("items.energyProduction")
                .hasProperValue("energyProduction value should not be null or empty")
                .getList("items.energyGrid").hasProperValue("energyGrid should not be null or empty");
    }

    /**
     * C288307 - Get power - response body includes expected properties & values
     *
     * @param site
     */
    @Test(dataProviderClass = SiteNightVisionData.class, dataProvider = "sitePowerPeriodByDays", priority = 7)
    public void testPowerForThePeriodByDays(Site site) {

        LogUtil.log(Steps.START, "Gets the power production for the given site and validate");
        restActions.url(SiteEnum.POWER).pathParam(site).queryParam(site).get()
                .doAssert().is200().getList("items.acPowerProductionAverage")
                .hasProperValue("acPowerProductionAverage value should not be null or empty")
                .getList("items.acPowerProductionMax").hasProperValue("acPowerProductionMax should not be null");
    }

    /**
     * C288330 - Site Level - Power
     *
     * @param site
     */
    @Test(dataProviderClass = SiteNightVisionData.class, dataProvider = "testSiteLevelPower", priority = 8)
    public void testSiteLevelPower(Site site) {

        //1. Send request with valid param.
       LogUtil.log(Steps.START, "Site Level - Power");
        restActions.url(SiteEnum.POWER).pathParam(site).queryParam(site).get()
                .doAssert().is200().getList("items.acPowerProductionAverage")
                .hasProperValue("'acPowerProductionAverage' is should not be null or empty");

        //2. Send request with invalid param.
        restActions.url(SiteEnum.POWER).pathParam(site).badQueryParam(site)
                .get().doAssert().is400().getObject("")
                .isEqualOnlyItems(GsonUtils.objectToMap(site), Lists.list("message", "code"));
        //3. Send request with invalid token
        restActions.url(SiteEnum.POWER).pathParam(site).withInvalidToken().get().doAssert().is401();
    }
}
