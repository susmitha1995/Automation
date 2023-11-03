package com.sunpower.automation.edp.api.data;


import com.sunpower.automation.api.edp.entity.data.Search;
import com.sunpower.automation.core.manager.ConfigManager;
import com.sunpower.automation.core.utils.JsonUtil;
import org.testng.annotations.DataProvider;

public class SearchApiData extends AbstractDataProvider {

    /**
     * Preparing data for new Search Functionality
     * @return
     */
    @DataProvider(name = "newSearchFunctionality")
    public static Object[][] newSearchFunctionality() {

        Search search = JsonUtil.getObject(ConfigManager.getValue(
                "search.newSearchFunctionality"), Search.class);

        return new Object[][]{{search}};
    }

    /**
     * Preparing data for ability To Surface Meta data Users
     * @return
     */
    @DataProvider(name = "abilityToSurfaceMetadataUsers")
    public static Object[][] abilityToSurfaceMetadataUsers() {

        Search search = JsonUtil.getObject(ConfigManager.getValue(
                "search.abilityToSurfaceMetadataUsers"), Search.class);

        return new Object[][]{{search}};
    }

    /**
     * Preparing data for ability To Surface Meta data Users Two
     * @return
     */
    @DataProvider(name = "abilityToSurfaceMetadataUsersTwo")
    public static Object[][] abilityToSurfaceMetadataUsersTwo() {

        Search search = JsonUtil.getObject(ConfigManager.getValue(
                "search.abilityToSurfaceMetadataUsersTwo"), Search.class);

        return new Object[][]{{search}};
    }

    /**
     * Preparing data for proper Error Coding Search Site
     * @return
     */
    @DataProvider(name = "properErrorCodingSearchSite")
    public static Object[][] properErrorCodingSearchSite() {

        Search search = JsonUtil.getObject(ConfigManager.getValue(
                "search.properErrorCodingSearchSite"), Search.class);

        return new Object[][]{{search}};
    }

    /**
     * Preparing data for global Device Search
     * @return
     */
    @DataProvider(name = "globalDeviceSearch")
    public static Object[][] globalDeviceSearch() {

        Search search = JsonUtil.getObject(ConfigManager.getValue("search.globalDeviceSearch"), Search.class);

        return new Object[][]{{search}};
    }

    /**
     * Preparing data for global Site Address Search
     * @return
     */
    @DataProvider(name = "globalSiteAddsSearch")
    public static Object[][] globalSiteAddsSearch() {

        Search search = JsonUtil.getObject(ConfigManager.getValue("search.globalSiteAddsSearch"), Search.class);

        return new Object[][]{{search}};
    }

    /**
     * Preparing data for global User Search
     * @return
     */
    @DataProvider(name = "globalUserSearch")
    public static Object[][] globalUserSearch() {

        Search search = JsonUtil.getObject(ConfigManager.getValue("search.globalUserSearch"), Search.class);

        return new Object[][]{{search}};
    }

    /**
     * Preparing data for global Organization Search
     * @return
     */
    @DataProvider(name = "globalOrgSearch")
    public static Object[][] globalOrgSearch() {

        Search search = JsonUtil.getObject(ConfigManager.getValue("search.globalOrgSearch"), Search.class);

        return new Object[][]{{search}};
    }

    /**
     * Preparing data for index Device Search
     * @return
     */
    @DataProvider(name = "indexDeviceSearch")
    public static Object[][] indexDeviceSearch() {

        Search search = JsonUtil.getObject(ConfigManager.getValue("search.indexDeviceSearch"), Search.class);

        return new Object[][]{{search}};
    }

    /**
     * Preparing data for index User Search
     * @return
     */
    @DataProvider(name = "indexUserSearch")
    public static Object[][] indexUserSearch() {

        Search search = JsonUtil.getObject(ConfigManager.getValue("search.indexUserSearch"), Search.class);

        return new Object[][]{{search}};
    }

    /**
     * Preparing data for index Organization Search
     * @return
     */
    @DataProvider(name = "indexOrgSearch")
    public static Object[][] indexOrgSearch() {

        Search search = JsonUtil.getObject(ConfigManager.getValue("search.indexOrgSearch"), Search.class);

        return new Object[][]{{search}};
    }
}
