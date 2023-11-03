package com.sunpower.automation.edp.api.site.tests;

import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.enums.Steps;
import com.sunpower.automation.core.listeners.AutomationListener;
import com.sunpower.automation.core.testng.CustomReport;
import com.sunpower.automation.core.utils.GsonUtils;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.edp.api.base.EdpApiRestActions;
import com.sunpower.automation.edp.api.base.EdpApiTestBase;
import com.sunpower.automation.edp.api.businessflow.site.SiteApiBL;
import com.sunpower.automation.edp.api.data.site.SiteNotesData;
import com.sunpower.automation.edp.api.data.site.SiteV1Data;
import com.sunpower.automation.edp.api.enums.site.SiteEnum;
import com.sunpower.automation.edp.api.payload.EdpPayload;
import org.assertj.core.util.Lists;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners({CustomReport.class, AutomationListener.class})
public class SiteNotesTest extends EdpApiTestBase {

    SiteApiBL siteBL = SiteApiBL.getInstance();

    EdpApiRestActions restActions = EdpApiRestActions.getInstance(SiteApiBL.class);

    EdpPayload payload = EdpPayload.getInstance();

    /**
     * C288322 - Creates a site note.
     * @param site
     * @param notes
     */
    @Test(dataProviderClass = SiteNotesData.class, dataProvider = "saveNotesFieldForSiteAlerts", priority = 0)
    public void testSaveNotesFieldForSiteAlerts(Site site, Site notes) {

        //When request with valid siteKey
        LogUtil.log(Steps.START, "Save Notes Field for Site Alerts");
        restActions.url(SiteEnum.NOTES).pathParam(site).body(payload.createPayload(notes))
                .post().doAssert().is200().getObject("content")
                .isEqual("Content value is not updated in the DB", siteBL.validateNoteContent("noteId"));
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288323 - Gets Site Notes
     *
     * @param siteData
     */
    @Test(dataProviderClass = SiteNotesData.class, dataProvider = "getNotesFieldForSiteAlerts", priority = 1)
    public void testGetNotesFieldForSiteAlerts(Site siteData) {

       LogUtil.log(Steps.START, "Get Notes Field for Site Alerts");
       restActions.url(SiteEnum.NOTES).pathParam(siteData).get().doAssert().is200().getObject("[0].content")
               .isEqual("Content value is not updated in the DB", siteBL.validateNoteContent("[0].noteId"));
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288324 - Update one or more site notes
     *
     * @param site
     * @param notes
     */
    @Test(dataProviderClass = SiteNotesData.class, dataProvider = "editNotesFieldForSiteAlerts", priority = 2)
    public void testEditExistingNotesForSiteAlerts(Site site, Site notes) {

        //GET available notes and prepare payload
        LogUtil.log(Steps.START, "Get available note by site key");
        restActions.url(SiteEnum.NOTES).pathParam(site).get().doAssert().is200();

        //Edit notes
        LogUtil.log(Steps.START, "Edit existing Notes for Site Alerts");
        restActions.url(SiteEnum.NOTES).pathParam(site).body(siteBL.prepareNotesPayload(notes))
                .put().doAssert().is200().getObject("[0].content")
                .isEqual("Content value is not updated in the DB", siteBL.validateNoteContent("[0].noteId"));
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288350 - Returns the module layouts for the given site
     *
     * @param site
     */
    @Test(dataProviderClass = SiteV1Data.class, dataProvider = "moduleLayouts", priority = 3)
    public void testModuleLayoutsGivenSite(Site site) {

        LogUtil.log(Steps.START, "Returns the module layouts for the given site");
        restActions.url(SiteEnum.LAYOUT).pathParam(site).get().doAssert().is200();
    }

    /**
     * C288333 - Returns the module layouts for the given site
     *
     * @param sites
     */
    @Test(dataProviderClass = SiteV1Data.class, dataProvider = "reportSpecifiedRecipients", priority = 4)
    public void testSendsReportSpecifiedRecipients(List<Site> sites) {

        LogUtil.log(Steps.START, "Sends a report to specified recipients");
        restActions.url(SiteEnum.INSTANT_REPORT).pathParam(sites.get(0)).body(payload.createPayload(sites.get(1)))
                .post().doAssert().is200();
    }

    /**
     * C288360 - Returns the module layouts for the given site
     *
     * @param site
     */
    @Test(dataProviderClass = SiteV1Data.class, dataProvider = "savedFileLayout", priority = 5)
    public void testSignedURLToSavedFileLayout(Site site) {

        LogUtil.log(Steps.START, "Retrieves a signed URL from s3 to a saved file layout");
        restActions.url(SiteEnum.LAYOUT_FILE).pathParam(site).get().doAssert().is200();
    }
}
