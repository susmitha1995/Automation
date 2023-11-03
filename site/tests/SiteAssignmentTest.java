package com.sunpower.automation.edp.api.site.tests;

import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.enums.Steps;
import com.sunpower.automation.core.listeners.AutomationListener;
import com.sunpower.automation.core.testng.CustomReport;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.edp.api.base.EdpApiRestActions;
import com.sunpower.automation.edp.api.base.EdpApiTestBase;
import com.sunpower.automation.edp.api.businessflow.site.SiteApiBL;
import com.sunpower.automation.edp.api.data.site.SiteAssignmentData;
import com.sunpower.automation.edp.api.enums.site.SiteEnum;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({CustomReport.class, AutomationListener.class})
public class SiteAssignmentTest extends EdpApiTestBase {

    SiteApiBL siteBL = SiteApiBL.getInstance();

    EdpApiRestActions restActions = EdpApiRestActions.getInstance(SiteApiBL.class);

    /**
     * C288292 - Get site-device assignment by siteId - response body includes expected properties & values
     *
     * @param site
     */
    @Test(dataProviderClass = SiteAssignmentData.class, dataProvider = "deviceAssignmentBySiteId", priority = 0)
    public void testSiteDeviceAssignmentBySiteId(Site site) {

        LogUtil.log(Steps.START, "Get site-device assignment by Site key");
        restActions.url(SiteEnum.ASSIGNMENT).queryParam(site).get().doAssert().is200()
                .getObject("[0]").isEqualOnlyItems(siteBL.validateSiteAssignmentFields(), site.getOnlyItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288293 -Get site-device assignment by deviceKey - response body includes expected properties & values
     *
     * @param site
     */
    @Test(dataProviderClass = SiteAssignmentData.class, dataProvider = "deviceAssignmentByDeviceKey", priority = 1)
    public void testSiteDeviceAssignmentByDeviceKey(Site site) {

        LogUtil.log(Steps.START, "Get site-device assignment by Device key");
        restActions.url(SiteEnum.ASSIGNMENT).queryParam(site).get().doAssert().is200()
                .getObject("[0]").isEqualOnlyItems(siteBL.validateSiteAssignmentFields(), site.getOnlyItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288294 - Get site-device assignment by siteId and deviceKey
     * response body includes expected properties & values
     *
     * @param site
     */
    @Test(dataProviderClass = SiteAssignmentData.class, dataProvider = "deviceAssignmentBySiteIdAndDeviceKey",
            priority = 2)
    public void testSiteDeviceAssignmentBySiteIdAndDeviceKey(Site site) {

       LogUtil.log(Steps.START, "Get site-device assignment by Site key And Device Key");
       restActions.url(SiteEnum.ASSIGNMENT).queryParam(site).get().doAssert().is200().getObject("[0].siteKey")
               .isEqual("Site key should match with response", site.getSiteKey())
               .getObject("[0].deviceKey").isEqual("Device key should match with response", site.getDeviceKey())
               .getObject("[0]").isEqualOnlyItems(siteBL.validateSiteAssignmentFields(), site.getOnlyItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }
}
