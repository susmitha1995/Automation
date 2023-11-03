package com.sunpower.automation.edp.api.data.site;

import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.manager.ConfigManager;
import com.sunpower.automation.core.utils.JsonUtil;
import com.sunpower.automation.edp.api.data.AbstractDataProvider;
import org.testng.annotations.DataProvider;

public class SiteAssignmentData extends AbstractDataProvider {

    /**
     * Preparing data for
     *
     * @return
     */
    @DataProvider(name = "deviceAssignmentBySiteId")
    public static Object[][] deviceAssignmentBySiteId() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.deviceAssignmentBySiteId"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for device Assignment By DeviceKey
     *
     * @return
     */
    @DataProvider(name = "deviceAssignmentByDeviceKey")
    public static Object[][] deviceAssignmentByDeviceKey() {

        Site site = JsonUtil.getObject(ConfigManager.getValue("site.deviceAssignmentByDeviceKey"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for device Assignment By SiteId And Device Key
     *
     * @return
     */
    @DataProvider(name = "deviceAssignmentBySiteIdAndDeviceKey")
    public static Object[][] deviceAssignmentBySiteIdAndDeviceKey() {

        Site site = JsonUtil.getObject(ConfigManager.getValue(
                "site.deviceAssignmentBySiteIdAndDeviceKey"), Site.class);

        return new Object[][]{{site}};
    }

    /**
     * Preparing data for create Site Payload
     *
     * @return
     */
    @DataProvider(name = "createSitePayload")
    public static Object[][] createSitePayload() {

        Site createSite = JsonUtil.getObject(ConfigManager.getValue("site.createSitePayload"), Site.class);

        return new Object[][]{{createSite}};
    }

    /**
     * Preparing data for update Site Payload
     *
     * @return
     */
    @DataProvider(name = "updateSitePayload")
    public static Object[][] updateSitePayload() {

        Site updateSite = JsonUtil.getObject(ConfigManager.getValue("site.updateSitePayload"), Site.class);

        return new Object[][]{{updateSite}};
    }

    /**
     * Preparing data for time zone When Create Site
     *
     * @return
     */
    @DataProvider(name = "timezoneWhenCreateSite")
    public static Object[][] timezoneWhenCreateSite() {

        Site createSite = JsonUtil.getObject(ConfigManager.getValue(
                "site.timezoneWhenCreateSitePayload"), Site.class);

        return new Object[][]{{createSite}};
    }
}
