package com.sunpower.automation.edp.api.site.tests;

import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.enums.Steps;
import com.sunpower.automation.core.listeners.AutomationListener;
import com.sunpower.automation.core.testng.CustomReport;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.edp.api.base.EdpApiRestActions;
import com.sunpower.automation.edp.api.base.EdpApiTestBase;
import com.sunpower.automation.edp.api.businessflow.site.SiteApiBL;
import com.sunpower.automation.edp.api.data.site.SiteWeatherData;
import com.sunpower.automation.edp.api.enums.site.SiteEnum;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners({CustomReport.class, AutomationListener.class})
public class SiteWeatherTest extends EdpApiTestBase {

    SiteApiBL siteBL = SiteApiBL.getInstance();

    EdpApiRestActions restActions = EdpApiRestActions.getInstance(SiteApiBL.class);

    /**
     * C288288 - 200 Get current weather for a site
     *
     * @param site
     */
    @Test(dataProviderClass = SiteWeatherData.class, dataProvider = "siteCurrentWeather", priority = 0)
    public void testCurrentWeatherOfTheSite(Site site) {

        LogUtil.log(Steps.START, "Check the current weather of the site");
        restActions.url(SiteEnum.CURRENT_WEATHER).pathParam(site).get().doAssert().is200()
                .getObject("main.temp").hasProperValue("temp value should not be null or empty")
                .getObject("main.temp_min").hasProperValue("temp min value should not be null or empty")
                .getObject("main.temp_max").hasProperValue("temp max value should not be null or empty");
    }

    /**
     * C288317 - 200 Gets current weather data for a site -- v2
     *
     * @param site
     */
    @Test(dataProviderClass = SiteWeatherData.class, dataProvider = "siteCurrentWeatherNewEndpoint", priority = 1)
    public void testCurrentWeatherV2OfTheSite(Site site) {

        LogUtil.log(Steps.START, "Migrate new endpoint to leverage current weather context");
        restActions.url(SiteEnum.CURRENT_WEATHER_V2).pathParam(site).get().doAssert().is200()
                .getObject("temp").hasProperValue("temp value should not be null or empty")
                .getObject("feelslike").hasProperValue("feelslike value should not be null or empty")
                .getObject("humidity").hasProperValue("humidity value should not be null or empty");
    }

    /**
     * C288320 - Gets weather data for a site - v2
     *
     * @param site
     */
    @Test(dataProviderClass = SiteWeatherData.class, dataProvider = "historicalWeatherForSPFM", priority = 2)
    public void testHistoricalWeatherForSPFM(Site site) {

        LogUtil.log(Steps.START, "Historical weather data for mySunPower & SPFM");
        restActions.url(SiteEnum.CURRENT_WEATHER_V2).pathParam(site).queryParam(site).get().doAssert().is200()
                .getObject("temp").hasProperValue("temp value should not be null or empty")
                .getObject("feelslike").hasProperValue("feelslike value should not be null or empty")
                .getObject("humidity").hasProperValue("humidity value should not be null or empty");
    }

    /**
     * C728954 -Return  Google satellite image for a site
     *
     * @param site
     */
    @Test(dataProviderClass = SiteWeatherData.class, dataProvider = "getGoogleSatelliteImageForSite", priority = 3)
    public void testGoogleSatelliteImageForSite(Site site) {

        //When get a Google satellite image for a site by siteKey
        LogUtil.log(Steps.START, "Get a Google satellite image for a site");
        restActions.url(SiteEnum.SATELLITE_IMAGE).pathParam(site).get().doAssert().is200().getObject("siteKey")
                .isEqual("siteKey is not matching while checking in DB", siteBL.getSiteByKey());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288315 - Get all dealers for a given siteKey
     *
     * @param site
     */
    @Test(dataProviderClass = SiteWeatherData.class, dataProvider = "returnDealersAssigned", priority = 4)
    public void testReturnDealersAssignedSite(Site site) {

        LogUtil.log(Steps.START, "Return the dealers assigned to a site");
        restActions.url(SiteEnum.DEALERS).pathParam(site).get().doAssert().is200().getObject("[0]")
                .isEqualOnlyItems(siteBL.validatePartyFields("[0].partyId"), site.getOnlyItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288369 - Weather API to support sites outside USA using lat/long
     *
     * @param siteData
     */
    @Test(dataProviderClass = SiteWeatherData.class, dataProvider = "getSiteWeather", priority = 5)
    public void testSiteWeather(List<Site> siteData) {

        //Verify the site weather API
        LogUtil.log(Steps.START, "Get siteKey for site weather");
        restActions.url(SiteEnum.WEATHER_V2).pathParam(siteData.get(0)).queryParam(siteData.get(1)).get().doAssert().is200()
                .getObject("days.temp").hasProperValue("temp should not be null or empty")
                .getObject("days.tempmin").hasProperValue("temp min min value should not be null or empty")
                .getObject("days.tempmax").hasProperValue("temp max value should not be null or empty");

        //Verify the site currentWeather API
        LogUtil.log(Steps.START, "Get siteKey for current weather");
        restActions.url(SiteEnum.CURRENT_WEATHER_V2).pathParam(siteData.get(0)).get().doAssert().is200()
                .getObject("datetime").hasProperValue("datetime should not be null or empty");
    }
}
