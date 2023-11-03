package com.sunpower.automation.edp.api.data.site;

import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.manager.ConfigManager;
import com.sunpower.automation.core.utils.JsonUtil;
import com.sunpower.automation.edp.api.data.AbstractDataProvider;
import org.testng.annotations.DataProvider;

import java.util.List;

public class SiteNotesData extends AbstractDataProvider {

    /**
     * Preparing data for save Notes Field For Site Alerts
     *
     * @return
     */
    @DataProvider(name = "saveNotesFieldForSiteAlerts")
    public static Object[][] saveNotesFieldForSiteAlerts() {

        Site site = JsonUtil.getObject(ConfigManager.getValue(
                "site.saveNotesFieldForSiteAlerts"), Site.class);
        Site notes = JsonUtil.getObject(ConfigManager.getValue(
                "site.saveNotesFieldForPayload"), Site.class);

        return new Object[][]{{site, notes}};
    }

    /**
     * Preparing data for get Notes Field For Site Alerts
     *
     * @return
     */
    @DataProvider(name = "getNotesFieldForSiteAlerts")
    public static Object[][] getNotesFieldForSiteAlerts() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.getNotesFieldForSiteAlerts"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for edit Notes Field For Site Alerts
     *
     * @return
     */
    @DataProvider(name = "editNotesFieldForSiteAlerts")
    public static Object[][] editNotesFieldForSiteAlerts() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.editNotesFieldForSiteAlerts"), Site.class);
        Site rootNotes = JsonUtil.getObject(ConfigManager.getValue("site.editNotesForPayload"), Site.class);

        return new Object[][]{{site, rootNotes}};
    }
}
