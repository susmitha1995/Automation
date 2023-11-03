package com.sunpower.automation.edp.api.data.site;

import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.manager.ConfigManager;
import com.sunpower.automation.core.utils.JsonUtil;
import com.sunpower.automation.edp.api.data.AbstractDataProvider;
import org.testng.annotations.DataProvider;

import java.util.List;

public class SiteWeatherData extends AbstractDataProvider {

    /**
     * Preparing data for site Current Weather
     *
     * @return
     */
    @DataProvider(name = "siteCurrentWeather")
    public static Object[][] siteCurrentWeather() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.currentWeather"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for return Dealers Assigned
     *
     * @return
     */
    @DataProvider(name = "returnDealersAssigned")
    public static Object[][] returnDealersAssigned() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.returnDealersAssigned"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for site Current Weather New Endpoint
     *
     * @return
     */
    @DataProvider(name = "siteCurrentWeatherNewEndpoint")
    public static Object[][] siteCurrentWeatherNewEndpoint() {

        Site site = JsonUtil.getObject(ConfigManager.getValue(
                "site.siteCurrentWeatherNewEndpoint"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for historical Weather For SPFM
     *
     * @return
     */
    @DataProvider(name = "historicalWeatherForSPFM")
    public static Object[][] historicalWeatherForSPFM() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.historicalWeatherForSPFM"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for get Site Weather
     *
     * @return
     */
    @DataProvider(name = "getSiteWeather")
    public static Object[][] getSiteWeather() {
        List<Site> site = JsonUtil.getListObject(ConfigManager.getValue("site.getSiteWeather"), Site.class);
        return new Object[][]{{site}};
    }

    /**
     * Preparing data for google satellite image for a site
     *
     * @return
     */
    @DataProvider(name = "getGoogleSatelliteImageForSite")
    public static Object[][] getGoogleSatelliteImageForSite() {

        Site site = JsonUtil.getObject(ConfigManager.getValue(
                "site.getGoogleSatelliteImageForSite"), Site.class);
        return new Object[][]{{site}};
    }
}
