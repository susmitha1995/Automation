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
import com.sunpower.automation.edp.api.data.site.SiteCharacteristicData;
import com.sunpower.automation.edp.api.enums.site.SiteEnum;
import com.sunpower.automation.edp.api.payload.EdpPayload;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners({CustomReport.class, AutomationListener.class})
public class SiteCharacteristicTest extends EdpApiTestBase {

    SiteApiBL siteApiBL = SiteApiBL.getInstance();

    EdpApiRestActions restActions = EdpApiRestActions.getInstance(SiteApiBL.class);

    EdpPayload payload = EdpPayload.getInstance();

    /**
     * C288290 - Get characteristics for site - response body includes expected properties & values
     *
     * @param site
     */
    @Test(dataProviderClass = SiteCharacteristicData.class, dataProvider = "siteCharacter", priority = 0)
    public void testCharacteristics(Site site) {

        LogUtil.log(Steps.START, "Get site characteristics and validate");
        restActions.url(SiteEnum.CHARACTERISTIC).pathParam(site).get().doAssert().is200().getObject("items[0]")
                .isEqualOnlyItems(siteApiBL.validateSiteCharacterFields("items[0]", "HOME_BACKUP"), site.getOnlyItems());
    }

    /**
     * C288375 - Retrieves the specified characteristic values for the given site
     *
     * @param site
     */
    @Test(dataProviderClass = SiteCharacteristicData.class, dataProvider = "verifyPCSCharacteristic", priority = 1)
    public void testPCSCharacteristic(List<Site> site) {

        //Get PCSCharacteristic by siteKey
        LogUtil.log(Steps.START, "Get siteKey for PCSCharacteristic");
        restActions.url(SiteEnum.CHARACTERISTIC).pathParam(site.get(0)).queryParam(site.get(1)).get().doAssert()
                .is200().getObject("items.label").hasProperValue("The response value should not be null or empty");
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288371 - Remove API limitation on Operational Mode that prevents BACKUP to BACKUP
     *
     * @param payloadData
     * @param param
     */
    @Test(dataProviderClass = SiteCharacteristicData.class, dataProvider = "verifySiteCharacteristics", priority = 2)
    public void testSiteCharacteristics(List<Site> payloadData, List<Site> param) {

        //When update the value is 5
        LogUtil.log(Steps.START, "Update site characteristics value to 5");
        restActions.url(SiteEnum.CHARACTERISTIC).pathParam(param.get(0))
                .body(payload.createPayload(payloadData.get(0))).patch().doAssert().is200();

        //When update the value is 10
        LogUtil.log(Steps.START, "Update site characteristics value to 10");
        restActions.url(SiteEnum.CHARACTERISTIC).pathParam(param.get(0))
                .body(payload.createPayload(payloadData.get(1))).patch().doAssert().is200();

        //Get response with siteKey and check with dataBase
        LogUtil.log(Steps.START, "Get site characteristics by siteKey");
        restActions.url(SiteEnum.CHARACTERISTIC).pathParam(param.get(0))
                .queryParam(param.get(1)).get().doAssert().is200().getObject("items[0]")
                .isEqualOnlyItems(siteApiBL.validateSiteCharacterFields("items[0]", "HOME_BACKUP"),
                        param.get(2).getOnlyItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288345 - Stores the specified characteristic values for the given site
     *
     * @param characteristics
     * @param site
     */
    @Test(dataProviderClass = SiteCharacteristicData.class, dataProvider = "updatePanelVisibility", priority = 3)
    public void testPanelVisibility(List<Site> characteristics, List<Site> site) {

        //When panel visibility is ON
        LogUtil.log(Steps.START, "Update panel characteristics to ON");
        String updatePayLoad = payload.createPayload(characteristics.get(0));
        restActions.url(SiteEnum.CHARACTERISTIC).pathParam(site.get(0)).body(updatePayLoad).patch()
                .doAssert().is200();

        //Get response with siteKey and PANEL VISIBILITY
        LogUtil.log(Steps.START, "Get characteristics and validate");
        restActions.url(SiteEnum.CHARACTERISTIC).pathParam(site.get(0)).queryParam(site.get(1)).get()
                .doAssert().is200();

        //When  panel visibility is OFF
        LogUtil.log(Steps.START, "Update panel characteristics to OFF");
        updatePayLoad = payload.createPayload(characteristics.get(1));
        restActions.url(SiteEnum.CHARACTERISTIC).pathParam(site.get(0)).body(updatePayLoad).patch()
                .doAssert().is200();

        //Get response with siteKey and check with database
        LogUtil.log(Steps.START, "Update site characteristics and check with dataBase");
        restActions.url(SiteEnum.CHARACTERISTIC).pathParam(site.get(0)).queryParam(site.get(1)).get()
                .doAssert().is200().getObject("items[0]")
                .isEqualOnlyItems(siteApiBL.validateSiteCharacterFields("items[0]", "HOME_BACKUP"),
                        Lists.list("siteKey", "label", "value", "statusIndicator"));

        //Verify that no item is displayed for empty panel visibility
        LogUtil.log(Steps.START, "Get characteristics and validate with different siteKey");
        restActions.url(SiteEnum.CHARACTERISTIC).pathParam(site.get(2)).queryParam(site.get(1)).get()
                .doAssert().is200();
        LogUtil.log(Steps.END, "Successfully completed");
    }

    /**
     * C288372 - 200 PATCH sitekey characteristic of higher reserve SOC
     *
     * @param sites
     */
    @Test(dataProviderClass = SiteCharacteristicData.class, dataProvider = "charHigherReserve", priority = 4)
    public void testCharacteristicHigherReserve(List<Site> sites) {

        //When panel visibility is ON
        LogUtil.log(Steps.START, "SiteKey characteristic of higher reserve SOC");
        String jsonKey = "items.find{it.label=='"+sites.get(0).getLabel()+"'}";
        restActions.url(SiteEnum.CHARACTERISTIC).pathParam(sites.get(0)).body(payload.createPayload(sites.get(1)))
                .patch().doAssert().is200().getObject(jsonKey)
                .isEqualOnlyItems(siteApiBL.validateSiteCharacterFields(jsonKey, sites.get(0).getLabel()), sites.get(0).getOnlyItems());
    }
}
