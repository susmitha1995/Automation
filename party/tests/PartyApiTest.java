package com.sunpower.automation.edp.api.party.tests;

import com.sunpower.automation.api.edp.entity.data.Party;
import com.sunpower.automation.core.enums.Steps;
import com.sunpower.automation.core.listeners.AutomationListener;
import com.sunpower.automation.core.testng.CustomReport;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.edp.api.base.EdpApiRestActions;
import com.sunpower.automation.edp.api.base.EdpApiTestBase;
import com.sunpower.automation.edp.api.businessflow.party.PartyApiBL;
import com.sunpower.automation.edp.api.data.PartyApiData;
import com.sunpower.automation.edp.api.enums.party.PartyEnum;
import com.sunpower.automation.edp.api.payload.EdpPayload;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners({CustomReport.class, AutomationListener.class})
public class PartyApiTest extends EdpApiTestBase {

    PartyApiBL partyBL = PartyApiBL.getInstance();

    EdpApiRestActions restActions = EdpApiRestActions.getInstance(PartyApiBL.class);

    EdpPayload payload = EdpPayload.getInstance();

    /**
     * C288124 - Get a party based on partyId - response body includes expected properties & values
     *
     * @param party
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "verifyPartyBasedOnPartyId", priority = 0)
    public void testPartyBasedOnPartyId(Party party) {

        //When verify party based on partyId
        LogUtil.log(Steps.START, "Get party based on PartyId");
        restActions.url(PartyEnum.V1_PARTY_BY_ID).pathParam(party).get().doAssert().is200().getObject("")
                .isEqualExcludingItems(partyBL.validatePartyFields("partyId"), party.getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288115 - Get organization - response body includes expected properties & values
     *
     * @param parties
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "verifyPartyOrganizationAlerts", priority = 1)
    public void testPartyOrganizationAlerts(List<Party> parties) {

        LogUtil.log(Steps.START, "Get party organization alerts");
        restActions.url(PartyEnum.ALERTS).queryParam(parties.get(1)).pathParam(parties.get(0)).get().doAssert().is200()
                .getObject("hasMore").hasProperValue("HasMore value should not be null or empty");
    }

    /**
     * C503528 - 200 Updates an organization party in MDS and optionally in SMS
     *
     * @param parties
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "updatesAnOrganizationParty", priority = 2)
    public void testUpdatesAnOrganizationParty(List<Party> parties) {

        //When update the organization party
        LogUtil.log(Steps.START, "Updates an organization party");
        restActions.url(PartyEnum.ORG_BY_PARTY_ID).pathParam(parties.get(1)).body(payload.createPayload(parties.get(0)))
                .patch().doAssert().is200().getObject("").isEqualExcludingItems(partyBL.validatePartyFields("partyId"),
                        parties.get(0).getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288150 - Updates a party
     *
     * @param parties
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "updatesParty", priority = 3)
    public void testUpdatesParty(List<Party> parties) {

        LogUtil.log(Steps.START, "Updates a party");
        restActions.url(PartyEnum.V1_PARTY_BY_ID).pathParam(parties.get(1)).body(payload.createPayload(parties.get(0)))
                .patch().doAssert().is200().getObject("").isEqualExcludingItems(partyBL.validatePartyFields("partyId"),
                        parties.get(2).getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288143 - Insert/update a mySunPower user config/preference item
     *
     * @param party
     * @param userConfig
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "updateUserConfig", priority = 4)
    public void testUpdateUserConfig(Party party, Party userConfig) {

        LogUtil.log(Steps.START, "Insert/update a mySunPower user config/preference item");
        restActions.url(PartyEnum.USER_CONFIG).pathParam(party).body(payload.payloadForUpdateUserConfig(userConfig))
                .post().doAssert().is200().getList("appRating")
                .isValid(partyBL.isUserConfCommentsUpdated(userConfig),
                        "Comments is not matching while checking in the response");
        // Need to validate data with DynamoDB is in progress
    }

    /**
     * C288159 - 200 Creates a group party under specified organization
     *
     * @param parties
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "createsGroupParty", priority = 5)
    public void testCreatesGroupParty(List<Party> parties) {

        LogUtil.log(Steps.START, "Creates a group party under specified organization");
        restActions.url(PartyEnum.GROUP).body(payload.payloadForPartyGroup(parties.get(0))).post()
                .doAssert().is200().getObject("")
                .isEqualExcludingItems(partyBL.validatePartyFields("partyId"), parties.get(1).getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C558893 - Gets the number of sites belonging to the organization of the party requesting
     * this endpoint
     *
     * @param party
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "numberOfSitesFromOrganizationParty", priority = 6)
    public void testNumberOfSitesFromOrganizationParty(Party party) {

        //Verify the number of sites
        LogUtil.log(Steps.START, "Get the number of sites from organization party");
        restActions.url(PartyEnum.COUNT).pathParam(party).get().doAssert().is200().getObject("")
                .isEqualExcludingItems(partyBL.validatePartyFields("partyId"), party.getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288112 - Edit Employee User
     *
     * @param parties
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "editEmployeeUser", priority = 7)
    public void testEditEmployeeUser(List<Party> parties) {

        LogUtil.log(Steps.START, "Edit Employee User");
        restActions.url(PartyEnum.INTERNAL_USER).body(payload.payloadForCreateOrUpdateInternalUser(parties.get(0)))
                .post().doAssert().is200().getObject("party")
                .isEqualOnlyItems(partyBL.validatePartyFields("party.partyId"), parties.get(1).getOnlyItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C503527 - 200 Creates an organization party at the specified system org
     *
     * @param parties
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "createOrgParty", priority = 8)
    public void createsOrgPartySpecifiedSystem(List<Party> parties) {

        LogUtil.log(Steps.START, "Creates an organization party at the specified system org");
        restActions.url(PartyEnum.PARTY_ORG).body(payload.payloadForCreateOrUpdateOrganizationParty(parties.get(0)))
                .post().doAssert().is200().getObject("").isEqualExcludingItems(partyBL.validatePartyFields("partyId"),
                        parties.get(1).getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288167 - 200 Sends an organization Alerts Report to specified recipients
     *
     * @param party
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "orgAlertsReport", priority = 9)
    public void testOrgAlertsReportSpecifiedRecipients(Party party) {

        LogUtil.log(Steps.START, "Sends an organization Alerts Report to specified recipients");
        restActions.url(PartyEnum.ORG_ALERTS_REPORT).body(payload.createPayload(party)).post()
                .doAssert().isValid(partyBL.validateOrgAlertsReport(), "Facing issue");
    }

    /**
     * C288160 - 200 Updates a group party in MDS and optionally in SMS
     *
     * @param parties
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "updatesGroupParty", priority = 10)
    public void testUpdateGroupParty(List<Party> parties) {

        LogUtil.log(Steps.START, "200 Updates a group party in MDS and optionally in SMS");
        restActions.url(PartyEnum.GROUP_BY_PARTY_ID).pathParam(parties.get(1))
                .body(payload.payloadForUpdateGroupParty(parties.get(0))).patch().doAssert().getObject("").
                isEqualExcludingItems(partyBL.validatePartyFields("partyId"), parties.get(2).getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288166 - 200 Updates a party in MDS, and Ping (SMS is not updated)
     *
     * @param parties
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "updatesPartyInMDS", priority = 11)
    public void updatesPartyInMDS(List<Party> parties) {

        LogUtil.log(Steps.START, "200 Updates a party in MDS, and Ping (SMS is not updated)");
        restActions.url(PartyEnum.V2_PARTY_BY_ID).pathParam(parties.get(1))
                .body(payload.payloadForUpdateParty(parties.get(0))).patch().doAssert().is200().getObject("")
                .isEqualExcludingItems(partyBL.validatePartyFields("partyId"), parties.get(2).getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C524500 - 200 Creates a Party (No SMS)
     *
     * @param parties
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "createsParty", priority = 12)
    public void createsParty(List<Party> parties) {

        LogUtil.log(Steps.START, "Create party V2");
        restActions.url(PartyEnum.V2_PARTY).body(payload.createPayload(parties.get(0))).post().doAssert().is200()
                .getObject("party").isEqualExcludingItems(partyBL.validatePartyFields("party.partyId"),
                        parties.get(1).getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288122 - Create a party site association.
     *
     * @param parties
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "createPartySiteAssociation", priority = 13)
    public void testPartySiteAssociation(List<Party> parties) {

        //When create a party site association
        LogUtil.log(Steps.START, "Create  party site Association");
        restActions.url(PartyEnum.V1_PARTY).body(payload.createPayload(parties.get(0))).post().doAssert().is200()
                .getObject("").isEqualExcludingItems(partyBL.validatePartyFields("party.partyId"),
                        parties.get(1).getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }
}
