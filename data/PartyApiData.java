package com.sunpower.automation.edp.api.data;

import com.sunpower.automation.api.edp.entity.data.Party;
import com.sunpower.automation.core.manager.ConfigManager;
import com.sunpower.automation.core.utils.JsonUtil;
import org.testng.annotations.DataProvider;

import java.util.List;

public class PartyApiData extends AbstractDataProvider {

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
     * Preparing data for verify Party Based On PartyId
     * @return
     */
    @DataProvider(name = "verifyPartyBasedOnPartyId")
    public static Object[][] verifyPartyBasedOnPartyId() {

        Party party = JsonUtil.getObject(ConfigManager.getValue("party.verifyPartyBasedOnPartyId"), Party.class);
        return new Object[][]{{party}};
    }

    /**
     * Preparing data for verify Party Organization Alerts
     * @return
     */
    @DataProvider(name = "verifyPartyOrganizationAlerts")
    public static Object[][] verifyPartyOrganizationAlerts() {

        List<Party> party = JsonUtil.getListObject(ConfigManager.getValue(
                "party.verifyPartyOrganizationAlerts"), Party.class);
        return new Object[][]{{party}};
    }

    /**
     * Preparing data for verify Party Sites
     * @return
     */
    @DataProvider(name = "verifyPartySites")
    public static Object[][] verifyPartySites() {

        Party party = JsonUtil.getObject(ConfigManager.getValue("party.verifyPartySites"), Party.class);
        return new Object[][]{{party}};
    }

    /**
     * Preparing data for verify Associated Parties
     * @return
     */
    @DataProvider(name = "verifyAssociatedParties")
    public static Object[][] verifyAssociatedParties() {

        Party party = JsonUtil.getObject(ConfigManager.getValue("party.verifyPartySites"), Party.class);
        return new Object[][]{{party}};
    }

    /**
     * Preparing data for updates An Organization Party
     * @return
     */
    @DataProvider(name = "updatesAnOrganizationParty")
    public static Object[][] updatesAnOrganizationParty() {

        List<Party> updateParty = JsonUtil.getListObject(ConfigManager.getValue(
                "party.updatesAnOrganizationParty"), Party.class);
        return new Object[][]{{updateParty}};
    }

    /**
     * Preparing data for updates Party
     * @return
     */
    @DataProvider(name = "updatesParty")
    public static Object[][] updatesParty() {

        List<Party> party = JsonUtil.getListObject(ConfigManager.getValue("party.updatesPartyPayload"), Party.class);
        return new Object[][]{{party}};
    }

    /**
     * Preparing data for update User Config
     * @return
     */
    @DataProvider(name = "updateUserConfig")
    public static Object[][] updateUserConfig() {

        Party partyData = JsonUtil.getObject(ConfigManager.getValue("party.updateUserConfigData"), Party.class);
        Party userConfig = JsonUtil.getObject(ConfigManager.getValue("party.updateUserConfigPayload"), Party.class);

        return new Object[][]{{partyData, userConfig}};
    }


    /**
     * Preparing data for creates Group Party
     * @return
     */
    @DataProvider(name = "createsGroupParty")
    public static Object[][] createsGroupParty() {

        List<Party> internalUser = JsonUtil.getListObject(ConfigManager
                .getValue("party.createsGroupParty"), Party.class);
        return new Object[][]{{internalUser}};
    }

    /**
     * Preparing data for verify Number Of Sites From Organization Party
     * @return
     */
    @DataProvider(name = "numberOfSitesFromOrganizationParty")
    public static Object[][] verifyNumberOfSitesFromOrganizationParty() {

        Party party = JsonUtil.getObject(ConfigManager.getValue(
                "party.verifyNumberOfSitesFromOrganizationParty"), Party.class);
        return new Object[][]{{party}};
    }

    /**
     * Preparing data for creates An Organization Party
     * @return
     */
    @DataProvider(name = "createsAnOrganizationParty")
    public static Object[][] createsAnOrganizationParty() {

        Party createParty = JsonUtil.getObject(ConfigManager.getValue("party.createsAnOrganizationParty"), Party.class);
        return new Object[][]{{createParty}};
    }

    /**
     * Preparing data for edit Employee User
     * @return
     */
    @DataProvider(name = "editEmployeeUser")
    public static Object[][] editEmployeeUser() {

        List<Party> internalUser = JsonUtil.getListObject(ConfigManager
                .getValue("party.editEmployeeUser"), Party.class);

        return new Object[][]{{internalUser}};
    }

    /**
     * Preparing data for create organization Party
     * @return
     */
    @DataProvider(name = "createOrgParty")
    public static Object[][] createOrgParty() {

        List<Party> orgParty = JsonUtil.getListObject(ConfigManager.getValue("party.createOrgParty"), Party.class);

        return new Object[][]{{orgParty}};
    }

    /**
     * Preparing data for organization Alerts Report
     * @return
     */
    @DataProvider(name = "orgAlertsReport")
    public static Object[][] orgAlertsReport() {

        Party orgParty = JsonUtil.getObject(ConfigManager.getValue("party.orgAlertsReport"), Party.class);

        return new Object[][]{{orgParty}};
    }

    /**
     * Preparing data for updates Group Party
     * @return
     */
    @DataProvider(name = "updatesGroupParty")
    public static Object[][] updatesGroupParty() {

        List<Party> group = JsonUtil.getListObject(ConfigManager.getValue(
                "party.updatesGroupPartyPayload"), Party.class);

        return new Object[][]{{group}};
    }

    /**
     * Preparing data for updates Party In MDS
     * @return
     */
    @DataProvider(name = "updatesPartyInMDS")
    public static Object[][] updatesPartyInMDS() {

        List<Party> party = JsonUtil.getListObject(ConfigManager.getValue(
                "party.payloadForUpdatesPartyInMDS"), Party.class);

        return new Object[][]{{party}};
    }

    /**
     * Preparing data for create Party
     * @return
     */
    @DataProvider(name = "createsParty")
    public static Object[][] createsParty() {

        List<Party> party = JsonUtil.getListObject(ConfigManager.getValue("party.createsParty"), Party.class);

        return new Object[][]{{party}};
    }

    /**
     * Preparing data for create Party Site Association
     * @return
     */
    @DataProvider(name = "createPartySiteAssociation")
    public static Object[][] createPartySiteAssociation() {

        List<Party> party = JsonUtil.getListObject(ConfigManager
                .getValue("party.createPartySiteAssociation"), Party.class);

        return new Object[][] {{party}};
    }

    /**
     * Preparing data for updates Party
     * @return
     */
    @DataProvider(name = "updatesPartySite")
    public static Object[][] updatesPartySite() {

        List<Party> party = JsonUtil.getListObject(ConfigManager.getValue(
                "party.updatesPartySitePayload"), Party.class);

        return new Object[][]{{party}};
    }
}