package com.sunpower.automation.edp.api.data;

import com.sunpower.automation.api.edp.entity.data.Party;
import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.manager.ConfigManager;
import com.sunpower.automation.core.utils.JsonUtil;
import org.testng.annotations.DataProvider;
import java.util.List;

public class PartyUserData extends AbstractDataProvider {

    /**
     * Preparing data for verify Associated Users
     * @return
     */
    @DataProvider(name = "verifyAssociatedUsers")
    public static Object[][] verifyAssociatedUsers() {

        List<Party> party = JsonUtil.getListObject(ConfigManager.getValue("party.verifyAssociatedUsers"), Party.class);
        return new Object[][]{{party}};
    }

    /**
     * Preparing data for updates Internal User
     * @return
     */
    @DataProvider(name = "updatesInternalUser")
    public static Object[][] updatesInternalUser() {

        Party internalUser = JsonUtil.getObject(ConfigManager.getValue("party.updatesInternalUser"), Party.class);
        return new Object[][]{{internalUser}};
    }

    /**
     * Preparing data for get User Data
     * @return
     */
    @DataProvider(name = "userData")
    public static Object[][] userData() {

        List<Party> party = JsonUtil.getListObject(ConfigManager.getValue("party.userData"), Party.class);
        return new Object[][]{{party}};
    }

    /**
     * Preparing data for verify Party FeedBack
     * @return
     */
    @DataProvider(name = "verifyPartyFeedBack")
    public static Object[][] verifyPartyFeedBack() {

        Party party = JsonUtil.getObject(ConfigManager.getValue("party.verifyPartyFeedBack"), Party.class);
        return new Object[][]{{party}};
    }

    /**
     * Preparing data for createUser
     * @return
     */
    @DataProvider(name = "createUser")
    public static Object[][] createUser() {

        List<Party> createUser = JsonUtil.getListObject(ConfigManager.getValue("party.createUser"), Party.class);
        return new Object[][]{{createUser}};
    }

    /**
     * Preparing data for get User Config
     * @return
     */
    @DataProvider(name = "getUserConfig")
    public static Object[][] getUserConfig() {

        List<Party> partyData = JsonUtil.getListObject(ConfigManager.getValue("party.getUserConfigData"), Party.class);

        return new Object[][]{{partyData}};
    }

    /**
     * Preparing data for SPFM Feedback tool API
     * @return
     */
    @DataProvider(name = "createSpfmFeedbackProperties")
    public static Object[][] createSpfmFeedbackProperties() {

        Site tool = JsonUtil.getObject(ConfigManager.getValue("party.createSpfmFeedbackProperties"), Site.class);
        List<Party> partyData = JsonUtil.getListObject(ConfigManager.getValue("party.spfmPartyId"), Party.class);
        return new Object[][] {{tool, partyData}};
    }

    /**
     * Preparing data for verify associated Sites
     * @return
     */
    @DataProvider(name = "verifyAssociatedSites")
    public static Object[][] verifyAssociatedSites() {

        List<Party> party = JsonUtil.getListObject(ConfigManager.getValue("party.verifyAssociatedSites"), Party.class);
        return new Object[][]{{party}};
    }

    /**
     * Preparing data for create New Notification Configuration
     * @return
     */
    @DataProvider(name = "createNewNotificationConfiguration")
    public static Object[][] createNewNotificationConfiguration() {

        List<Party> party = JsonUtil.getListObject(ConfigManager.getValue(
                "party.createNewNotificationConfiguration"), Party.class);
        return new Object[][]{{party}};
    }
}