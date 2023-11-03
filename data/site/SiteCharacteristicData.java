package com.sunpower.automation.edp.api.data.site;

import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.manager.ConfigManager;
import com.sunpower.automation.core.utils.JsonUtil;
import com.sunpower.automation.edp.api.data.AbstractDataProvider;
import org.testng.annotations.DataProvider;

import java.util.List;

public class SiteCharacteristicData extends AbstractDataProvider {

    /**
     * Preparing data for site Character
     *
     * @return
     */
    @DataProvider(name = "siteCharacter")
    public static Object[][] siteCharacter() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.siteCharacter"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for verify PCS Characteristic
     *
     * @return
     */
    @DataProvider(name = "verifyPCSCharacteristic")
    public static Object[][] verifyPCSCharacteristic() {

        List<Site> site = JsonUtil.getListObject(ConfigManager.getValue(
                "site.verifyPCSCharacteristic"), Site.class);
        return new Object[][]{{site}};
    }

    /**
     * Preparing data for verify Site Characteristics
     *
     * @return
     */
    @DataProvider(name = "verifySiteCharacteristics")
    public static Object[][] verifySiteCharacteristics() {

        List<Site> updateCharacteristics = JsonUtil.getListObject(ConfigManager.getValue(
                "site.getSiteCharacteristicsPayload"), Site.class);
        List<Site> siteData = JsonUtil.getListObject(ConfigManager.getValue(
                "site.getSiteCharacteristicsBySiteKey"), Site.class);

        return new Object[][]{{updateCharacteristics, siteData}};
    }

    /**
     * Preparing data for update Panel Visibility
     *
     * @return
     */
    @DataProvider(name = "updatePanelVisibility")
    public static Object[][] updatePanelVisibility() {

        List<Site> updateCharacteristics = JsonUtil.getListObject(ConfigManager.getValue(
                "site.updatePanelVisibility"), Site.class);
        List<Site> site = JsonUtil.getListObject(ConfigManager.getValue(
                "site.updatePanelVisibilityBySiteKey"), Site.class);

        return new Object[][]{{updateCharacteristics, site}};
    }

    /**
     * Preparing data for update Panel Visibility
     *
     * @return
     */
    @DataProvider(name = "charHigherReserve")
    public static Object[][] charHigherReserve() {

        List<Site> charHigherReserve = JsonUtil.getListObject(ConfigManager.getValue("site.charHigherReserve"), Site.class);

        return new Object[][]{{charHigherReserve}};
    }
}
