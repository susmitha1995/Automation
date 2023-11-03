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
import com.sunpower.automation.edp.api.data.site.SiteApiData;
import com.sunpower.automation.edp.api.enums.site.SiteEnum;
import com.sunpower.automation.edp.api.payload.EdpPayload;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

@Listeners({CustomReport.class, AutomationListener.class})
public class SiteApiTest extends EdpApiTestBase {

    SiteApiBL siteBL = SiteApiBL.getInstance();

    EdpApiRestActions restActions = EdpApiRestActions.getInstance(SiteApiBL.class);

    EdpPayload payload = EdpPayload.getInstance();

    /**
     * C288287 - Retrieves a list of active subscriptions for a given site
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "siteSubscriptions", priority = 0)
    public void retrievesAllActiveSubscriptionsForSpecificSite(Site site) {

        LogUtil.log(Steps.START, "Retrieves all active subscriptions for a specific site and validate");
        restActions.url(SiteEnum.SUBSCRIPTIONS).pathParam(site).get().doAssert().is200().getObject("[0]")
                .isEqualExcludingItems(siteBL.validateSubscriptionFields(), site.getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288313 - Find sites using a normalized address
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "lastDigitsAddress", priority = 1)
    public void testPVSLastDigitsAddress(Site site) {

        LogUtil.log(Steps.START, "New Endpoint: PVS last 4 digits + address");
        restActions.url(SiteEnum.ADDRESS).queryParam(site).get().doAssert().is200().getObject("[0]")
                .isEqualOnlyItems(siteBL.validateAddressFields("[0].siteKey"), site.getOnlyItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288358 - User is able to call the API with VIEW_LAYOUT_DIAGRAM permission
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "viewLayoutPermission", priority = 2)
    public void testUserAbleToAccessViewLayoutPermission(Site site) {

        LogUtil.log(Steps.START, "User is able to call the API with VIEW_LAYOUT_DIAGRAM permission ");
        restActions.url(SiteEnum.LAYOUT).pathParam(site).get().doAssert().is200()
                .getList("items.alertStatus").isListSizeNotZero("should not be empty");
    }

    /**
     * C288367 - Deletes a File image layout from a site and the metadata file associated with that image
     *
     * @param sites
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "deletePanelLayout", priority = 3)
    public void testDeletePanelLayoutImage(List<Site> sites) {

        //Send request with valid value.
        LogUtil.log(Steps.START, "API to Delete Panel Layout Image");
        restActions.url(SiteEnum.LAYOUT_FILE).pathParam(sites.get(0)).delete().doAssert()
                .is200().getList("Result.Deleted").isListSizeNotZero("Panel layout image should be deleted");

        //Send request with invalid value.
        LogUtil.log(Steps.START, "Send request with invalid value.");
        restActions.url(SiteEnum.LAYOUT_FILE).pathParam(sites.get(1)).delete().doAssert().is404();

        //Send request with invalid token.
        LogUtil.log(Steps.START, "Send request with invalid token.");
        restActions.url(SiteEnum.LAYOUT_FILE).pathParam(sites.get(0)).withInvalidToken().delete().doAssert().is401();
    }

    /**
     * C288351 -
     * Retrieves the service codes occurred during the last 60 days in any device in the site
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "siteServiceCodes", priority = 4)
    public void testSiteServiceCodes(Site site) {

        LogUtil.log(Steps.START, "Validate the service codes few device trees with different service codes");
        restActions.url(SiteEnum.SERVICE_CODES).pathParam(site).get().doAssert().is200();
        siteBL.validateErrorCodeField();
    }

    /**
     * C288045- Get power with invalid interval
     * response error message and code match the expected result
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "getDevicePowerWithInvalidInterval", priority = 5)
    public void testDevicePowerWithInvalidInterval(List<Site> site) {

        //When request with invalid interval
        LogUtil.log(Steps.START, "Ability to close alert with valid siteKey");
        restActions.url(SiteEnum.DEVICE_POWER).pathParam(site.get(0)).queryParam(site.get(1)).get().doAssert().is400();
    }

    /**
     * C288046- Get energy with invalid interval
     * response error message and code match the expected result
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "getDeviceEnergyWithInvalidInterval", priority = 6)
    public void testDeviceEnergyWithInvalidInterval(List<Site> site) {

        //When request with invalid interval
        LogUtil.log(Steps.START, "Ability to close alert with valid siteKey");
        restActions.url(SiteEnum.DEVICE_ENERGY).pathParam(site.get(0)).queryParam(site.get(1)).get().doAssert().is400();
    }

    /**
     * C288318 -Get a dealers information.
     *
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "dealerInformation", priority = 7)
    public void testDealerInformation(Site site) {

        LogUtil.log(Steps.START, "Get dealers information in relation to a site");
        restActions.url(SiteEnum.DEALERS_BY_ID).pathParam(site).get().doAssert().is200().getObject("")
                .isEqualOnlyItems(siteBL.validateSiteBySiteKey(site.getSiteKey()), site.getOnlyItems());
    }

    /**
     * C288303- Get all alerts for the given siteKey
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "getAlertsBySiteKey", priority = 8)
    public void testAlertStatusFieldInResponse(Site site) {

        LogUtil.log(Steps.START, "Get all alerts for the given siteKey");
        restActions.url(SiteEnum.ALL_ALERT).pathParam(site).get().doAssert().is200().getObject("")
                .isEqualOnlyItems(siteBL.validateAddressFields("items[0].siteKey"), site.getOnlyItems())
                .getObject("items[0].alertId").hasProperValue("alertId should not be null or empty")
                .getObject("items[0].alertType").hasProperValue("alertType should not be null or empty");
    }

    /**
     * C730475- Get stored utility and tariff for the given Site
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "storedUtilityAndTariff", priority = 9)
    public void testStoredUtilTariffForTheSite(Site site) {

        LogUtil.log(Steps.START, "Get stored utility and tariff for the given Site");
        restActions.url(SiteEnum.STORED_UTILITY_AND_TARIFF).pathParam(site).get().doAssert().is200()
                .getObject("items.find{it.siteKey=='"+site.getSiteKey()+"'}")
                .isEqualOnlyItems(siteBL.validateUtilityTariff("items[0].siteKey"), site.getOnlyItems());
    }

    /**
     * C730476 - Associates a utility tariff to a site
     * @param sites
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "updateStoredUtilityAndTariff", priority = 10)
    public void updateStoredUtilTariffForTheSite(List<Site> sites) {

        LogUtil.log(Steps.START, "Get stored utility and tariff for the given Site");
        restActions.url(SiteEnum.STORED_UTILITY_AND_TARIFF).pathParam(sites.get(0)).body(payload.createPayload(sites.get(1)))
                .patch().doAssert().is200().getObject("items.find{it.siteKey=='"+sites.get(0).getSiteKey()+"'}")
                .isEqualOnlyItems(siteBL.validateUtilityTariff("items[0].siteKey"), sites.get(0).getOnlyItems());
    }

    /**
     * C288331 - Get PDF file
     * @param sites
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "pdfFileValidation", priority = 11)
    public void testPdfFile(List<Site> sites) {

        LogUtil.log(Steps.START, "Send request with valid site_key.");
        restActions.url(SiteEnum.LAYOUT_FILE).pathParam(sites.get(0)).get().doAssert().is200()
                .getObject("contentType").hasProperValue("content type should not be null")
                .getObject("message").isEqual("", sites.get(0).getMessage());

        LogUtil.log(Steps.START, "Send request with invalid site_key.");
        restActions.url(SiteEnum.LAYOUT_FILE).pathParam(sites.get(1)).get().doAssert().is400()
                .getObject("").isEqualOnlyItems(GsonUtils.objectToMap(sites.get(1)), sites.get(1).getOnlyItems());

        LogUtil.log(Steps.START, "Send request with invalid token.");
        restActions.url(SiteEnum.LAYOUT_FILE).pathParam(sites.get(0)).withInvalidToken().get().doAssert().is401();
    }

    /**
     * C735421 - Get PDF file
     * @param site
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "searchForPlaceAddress", priority = 12)
    public void testSearchForPlaceAddress(Site site) {

        LogUtil.log(Steps.START, "Search for a place/address");
        restActions.url(SiteEnum.ADDRESS_PLACES).queryParam(site).get().doAssert().is200()
                .getObject("formatted_address").hasProperValue("'formatted_address' should not be null")
                .getObject("icon").hasProperValue("'icon' should not be null")
                .getObject("name").hasProperValue("'name' should not be null")
                .getObject("place_id").hasProperValue("'place_id' should not be null");
    }

    /**
     * C733869 - Retrieves active alerts occurred during the last 60 days in any device in the site
     * @param sites
     */
    @Test(dataProviderClass = SiteApiData.class, dataProvider = "retrievesLastDaysAnyDevice", priority = 13)
    public void retrievesActiveAlertsLastDaysAnyDevice(List<Site> sites) {

        LogUtil.log(Steps.START, "Retrieves active alerts occurred during the last 60 days in any device in the site");
        restActions.url(SiteEnum.STORAGE_ALERTS).pathParam(sites.get(0)).get().doAssert().is200()
                .getList("items.ComDvcSn").hasProperValue("'ComDvcSn' should not be null");

        LogUtil.log(Steps.START, "Request as include-resolved as false value.");
        restActions.url(SiteEnum.STORAGE_ALERTS).pathParam(sites.get(1)).queryParam(sites.get(1)).get().doAssert().is200()
                .getList("items.ComDvcSn").hasProperValue("'ComDvcSn' should not be null");
    }

    /**
     * C288347 - 200 Delete site assignment --System Size should update when Removing PVS
     * @param
     */
    @Test(priority = 14)
    public void testSystemSizeWhenRemovingPvs() {

        LogUtil.log(Steps.START, "Get SiteKey, SerialNumber for which 'sys_sz_w' is not null in site table");
        Site site = new Site();
        Map<String, String> data = siteBL.getDataSiteKeyAndSerialNumberForWhichSysSizeNotNull();
        site.setSiteKey(data.get("siteKey"));
        site.setLoggerSerialNumber(data.get("loggerSerialNumber"));

        LogUtil.log(Steps.START, "Request endpoint with valid SiteKey and datalogger serial number");
        restActions.url(SiteEnum.SITE_ASSIGNMENT).pathParam(site).delete().doAssert().is200()
                        .getObject("mds.rowsDeleted").isSizeNotZero("Rows are not deleted as per the api response")
                        .isValid(siteBL.checkSystemSizeIsNullInSiteTable(site.getSiteKey()),
                                "'System size' is not null in the site table'");
    }
}
