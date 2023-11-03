package com.sunpower.automation.edp.api.search.tests;

import com.sunpower.automation.api.edp.entity.dao.Device;
import com.sunpower.automation.api.edp.entity.dao.Party;
import com.sunpower.automation.api.edp.entity.dao.Site;
import com.sunpower.automation.api.edp.entity.data.Search;
import com.sunpower.automation.core.enums.Steps;
import com.sunpower.automation.core.utils.GsonUtils;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.edp.api.base.EdpApiRestActions;
import com.sunpower.automation.edp.api.base.EdpApiTestBase;
import com.sunpower.automation.edp.api.businessflow.search.SearchApiBL;
import com.sunpower.automation.edp.api.data.SearchApiData;
import com.sunpower.automation.edp.api.enums.search.SearchEnum;
import org.assertj.core.util.Lists;
import org.testng.annotations.Test;


public class SearchApiTest extends EdpApiTestBase {

    SearchApiBL searchBL = SearchApiBL.getInstance();

    EdpApiRestActions restActions = EdpApiRestActions.getInstance(SearchApiBL.class);

    /**
     * C288226 - New endpoint: new search/match functionality from manas' research
     *
     * @param search
     */
    @Test(dataProviderClass = SearchApiData.class, dataProvider = "newSearchFunctionality", priority = 0)
    public void newSearchFunctionality(Search search) {

        LogUtil.log(Steps.START, "New search/match functionality from manas' research");
        final String site = "items.hits._source.find { it.st_addr_lbl == \"" + search.getStreet() + "\"}";
        restActions.url(SearchEnum.SITES_ADDRESS).queryParam(search).get().doAssert().is200()
                .getObjectAndConvertToMap(site, Site.class).isEqualOnlyItems(searchBL
                                .validateAddressFields(site + ".site_key"), search.getOnlyItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288227 - Ability to surface metadata to users
     *
     * @param search
     */
    @Test(dataProviderClass = SearchApiData.class, dataProvider = "abilityToSurfaceMetadataUsers", priority = 1)
    public void testAbilityToSurfaceMetadataUsers(Search search) {

        LogUtil.log(Steps.START, "Ability to surface metadata to users");
        final String site = "items.hits._source.find { it.site_key == \"" + search.getListQ().get(0) + "\"}";
        search.setQ(search.getListQ().get(0));
        restActions.url(SearchEnum.INDEX_SITE_V1).queryParam(search).get().doAssert().is200()
                .getObject(site).containsKeys(Lists.list("sys_sz_w", "fin_ty_enum", "pvs_count"))
                .isEqualOnlyItems(searchBL.validateAddressFields(site + ".site_key"), search.getOnlyItems());

        LogUtil.log(Steps.START, "Verify that error message when site key is incorrect");
        search.setQ(search.getListQ().get(1));
        restActions.url(SearchEnum.INDEX_SITE_V1).queryParam(search).get().doAssert().is200().getObject("items.hits")
                .isListSizeEqualTo(0, "Size should be empty when invalid site key");

        LogUtil.log(Steps.START, "Verify that error message when site key is not provided");
        search.setQ(search.getListQ().get(2));
        restActions.url(SearchEnum.INDEX_SITE_V1).queryParam(search).get().doAssert().is400();
        LogUtil.log(Steps.END, "The API response matched with Database's data");

    }

    /**
     * C288228 - Ability to surface metadata to users (pt 2
     *
     * @param search
     */
    @Test(dataProviderClass = SearchApiData.class, dataProvider = "abilityToSurfaceMetadataUsersTwo", priority = 2)
    public void testAbilityToSurfaceMetadataUsersTwo(Search search) {

        LogUtil.log(Steps.START, "Ability to surface metadata to users (pt 2)");
        final String site = "items.hits._source.find { it.site_key == \"" + search.getListQ().get(0) + "\"}";
        search.setQ(search.getListQ().get(0));
        restActions.url(SearchEnum.INDEX_SITE_V1).queryParam(search).get().doAssert().is200()
                .getObject(site).containsKeys(Lists.list("strg_sys_sz_kwh", "dsgn_sys_sz_w"))
                .getObjectAndConvertToMap(site, Site.class).isEqualOnlyItems(searchBL
                        .validateAddressFields(site + ".site_key"), search.getOnlyItems());

        LogUtil.log(Steps.START, "Verify that error message when site key is incorrect");
        search.setQ(search.getListQ().get(1));
        restActions.url(SearchEnum.INDEX_SITE_V1).queryParam(search).get().doAssert().is200().getObject("items.hits")
                .isListSizeEqualTo(0, "Size should be empty when invalid site key");

        LogUtil.log(Steps.START, "Verify that error message when site key is not provided");
        search.setQ(search.getListQ().get(2));
        restActions.url(SearchEnum.INDEX_SITE_V1).queryParam(search).get().doAssert().is400();
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288229 - Proper error coding instead of "Site not Found" for Search Site - V2 Endpoint
     *
     * @param search
     */
    @Test(dataProviderClass = SearchApiData.class, dataProvider = "properErrorCodingSearchSite", priority = 3)
    public void testProperErrorCodingSearchSite(Search search) {

        LogUtil.log(Steps.START, "Proper error coding instead of \"Site not Found\" for Search Site - V2 Endpoint");
        final String site = "items.hits._source.find { it.site_key == \"" + search.getListQ().get(0) + "\"}";
        search.setQ(search.getListQ().get(0));
        restActions.url(SearchEnum.INDEX_SITE_V2).queryParam(search).get().doAssert().is200()
                .getObject(site).containsKeys(Lists.list("strg_sys_sz_kwh", "dsgn_sys_sz_w"))
                .getObjectAndConvertToMap(site, Site.class).isEqualOnlyItems(searchBL
                        .validateAddressFields(site + ".site_key"), search.getOnlyItems());

        LogUtil.log(Steps.START, "Verify that error message when site that doesn't exist in MDS.");
        search.setQ(search.getListQ().get(1));
        restActions.url(SearchEnum.INDEX_SITE_V2).queryParam(search).get().doAssert().is404();

        LogUtil.log(Steps.START, "Verify that error message when site key is not provided");
        search.setQ("");
        restActions.url(SearchEnum.INDEX_SITE_V2).queryParam(search).withInvalidToken().get().doAssert().is401();
        LogUtil.log(Steps.END, "The API response matched with Database's data");

    }

    /**
     * C288207 - Global Device Search - response body includes expected properties & values
     *
     * @param search
     */
    @Test(dataProviderClass = SearchApiData.class, dataProvider = "globalDeviceSearch", priority = 4)
    public void testGlobalDeviceSearch(Search search) {

        LogUtil.log(Steps.START, "Global Device Search - response body includes expected properties & values");
        final String device = "devices.hits._source.find { it.sn == \"" + search.getQ() + "\"}";
        restActions.url(SearchEnum.GLOBAL).queryParam(search).get().doAssert().is200()
                .getObjectAndConvertToMap(device, Device.class).isEqualExcludingItems(searchBL
                                .validateDeviceFields(device + ".sn"), search.getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288208 - Global Site Search - response body includes expected properties & values
     *
     * @param search
     */
    @Test(dataProviderClass = SearchApiData.class, dataProvider = "globalSiteAddsSearch", priority = 5)
    public void testGlobalSiteAddressSearch(Search search) {

        LogUtil.log(Steps.START, "Global Site Search - response body includes expected properties & values");
        final String site = "sites.hits._source.find { it.site_addr_nm == \"" + search.getQ() + "\"}";
        restActions.url(SearchEnum.GLOBAL).queryParam(search).get().doAssert().is200()
                .getObjectAndConvertToMap(site, Site.class).isEqualOnlyItems(searchBL
                                .validateAddressFields(site + ".site_key"), search.getOnlyItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288209 - Global User Search - response body includes expected properties & values
     *
     * @param search
     */
    @Test(dataProviderClass = SearchApiData.class, dataProvider = "globalUserSearch", priority = 6)
    public void testGlobalUserSearch(Search search) {

        LogUtil.log(Steps.START, "Global User Search - response body includes expected properties & values");
        final String user = "users.hits._source.find { it.eml == \"" + search.getQ() + "\"}";
        restActions.url(SearchEnum.GLOBAL).queryParam(search).get().doAssert().is200()
                .getObjectAndConvertToMap(user, Party.class).isEqualExcludingItems(searchBL
                                .validatePartyFields(user + ".party_id"), search.getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288210 - Global Orgs Search - response body includes expected properties & values
     *
     * @param search
     */
    @Test(dataProviderClass = SearchApiData.class, dataProvider = "globalOrgSearch", priority = 7)
    public void testGlobalOrgSearch(Search search) {

        LogUtil.log(Steps.START, "Global Orgs Search - response body includes expected properties & values");
        final String organizations = "organizations.hits._source.find { it.org_nm == \"" + search.getQ() + "\"}";
        restActions.url(SearchEnum.GLOBAL).queryParam(search).get().doAssert().is200()
                .getObjectAndConvertToMap(organizations, Party.class).isEqualExcludingItems(searchBL
                                .validateOrganizationTypeFields(organizations + ".org_nm"), search.getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288211 - Index Device Search - response body includes expected properties & values
     *
     * @param search
     */
    @Test(dataProviderClass = SearchApiData.class, dataProvider = "indexDeviceSearch", priority = 8)
    public void testIndexDeviceSearch(Search search) {

       LogUtil.log(Steps.START, "Index Device Search - response body includes expected properties & values");
        final String device = "items.hits._source.find { it.sn == \"" + search.getQ() + "\"}";
        restActions.url(SearchEnum.INDEX).pathParam(search).queryParam(search).get().doAssert().is200()
                .getObjectAndConvertToMap(device, Device.class).isEqualExcludingItems(searchBL
                                .validateDeviceFields(device + ".sn"), search.getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288212 - Index User Search - response body includes expected properties & values
     *
     * @param search
     */
    @Test(dataProviderClass = SearchApiData.class, dataProvider = "indexUserSearch", priority = 9)
    public void testIndexUserSearch(Search search) {

        LogUtil.log(Steps.START, "Index User Search - response body includes expected properties & values");
        final String party = "items.hits._source.find { it.eml == \"" + search.getQ() + "\"}";
        restActions.url(SearchEnum.INDEX).pathParam(search).queryParam(search).get().doAssert().is200()
                .getObjectAndConvertToMap(party, Party.class).isEqualExcludingItems(searchBL
                                .validatePartyFields(party + ".party_id"), search.getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288213 - Index Org Search - response body includes expected properties & values
     *
     * @param search
     */
    @Test(dataProviderClass = SearchApiData.class, dataProvider = "indexOrgSearch", priority = 10)
    public void testIndexOrgSearch(Search search) {

        LogUtil.log(Steps.START, "Index Organization Search - response body includes expected properties & values");
        final String organizations = "organizations.hits._source.find { it.org_nm == \"" + search.getQ() + "\"}";
        restActions.url(SearchEnum.GLOBAL).queryParam(search).get().doAssert().is200()
                .getObjectAndConvertToMap(organizations, Party.class).isEqualExcludingItems(searchBL
                                .validateOrganizationTypeFields(organizations + ".org_nm"), search.getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }
}
