package com.sunpower.automation.edp.api.party.tests;

import com.sunpower.automation.api.edp.entity.data.Party;
import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.enums.Steps;
import com.sunpower.automation.core.listeners.AutomationListener;
import com.sunpower.automation.core.testng.CustomReport;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.edp.api.base.EdpApiRestActions;
import com.sunpower.automation.edp.api.base.EdpApiTestBase;
import com.sunpower.automation.edp.api.businessflow.party.PartyApiBL;
import com.sunpower.automation.edp.api.data.PartyUserData;
import com.sunpower.automation.edp.api.enums.party.PartyEnum;
import com.sunpower.automation.edp.api.payload.EdpPayload;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;


@Listeners({CustomReport.class, AutomationListener.class})
public class PartyUserTest extends EdpApiTestBase {

    PartyApiBL partyBL = PartyApiBL.getInstance();

    EdpApiRestActions restActions = EdpApiRestActions.getInstance(PartyApiBL.class);

    EdpPayload payload = EdpPayload.getInstance();


    /**
     * C288125 - Get associated users - response body includes expected properties & values
     *
     * @param parties
     */
    @Test(dataProviderClass = PartyUserData.class, dataProvider = "verifyAssociatedUsers", priority = 0)
    public void testPartyUsers(List<Party> parties) {

        LogUtil.log(Steps.START, "Get associated users by parentId");
        restActions.url(PartyEnum.USER).pathParam(parties.get(0)).queryParam(parties.get(1)).get().doAssert()
                .is200().getObject("users[0]").isEqualExcludingItems(partyBL.validatePartyFields("users[0].partyId"),
                        parties.get(0).getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288151 - Creates or updates an employee in MDS, and Ping (no SMS).
     *
     * @param party
     */
    @Test(dataProviderClass = PartyUserData.class, dataProvider = "updatesInternalUser", priority = 1)
    public void createsOrUpdatesInternalUser(Party party) {

        LogUtil.log(Steps.START, "Creates or updates an employee");
        restActions.url(PartyEnum.INTERNAL_USER).body(payload.payloadForCreateOrUpdateInternalUser(party))
                .post().doAssert().is200().getObject("party")
                .isEqualOnlyItems(partyBL.validatePartyFields("party.partyId"), party.getOnlyItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288118 - Create user - response body includes expected properties & values
     *
     * @param parties
     */
    @Test(dataProviderClass = PartyUserData.class, dataProvider = "userData", priority = 2)
    public void testCreateUserData(List<Party> parties) {

        LogUtil.log(Steps.START, "Create user response details and validate");
        restActions.url(PartyEnum.PARTY_USER).body(payload.createPayload(parties.get(0))).post().doAssert().is200()
                .getObject("").isEqualExcludingItems(partyBL.validatePartyFields("partyId"),
                        parties.get(1).getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288128 - 200 Sends a feedback email
     *
     * @param party
     */
    @Test(dataProviderClass = PartyUserData.class, dataProvider = "verifyPartyFeedBack", priority = 3)
    public void testPartyFeedBack(Party party) {

        LogUtil.log(Steps.START, "Verify party feedback email");
        restActions.url(PartyEnum.FEEDBACK).body(payload.createPayload(party)).post().doAssert().is200()
                .getObject("subject").hasProperValue("Subject value should not be null or empty")
                .getObject("body").hasProperValue("body value should not be null or empty");
    }

    /**
     * C288156 - Creates a user (not Employee) in MDS, SMS (optionally), and Ping
     *
     * @param parties
     */
    @Test(dataProviderClass = PartyUserData.class, dataProvider = "createUser", priority = 4)
    public void createUserNotEmployee(List<Party> parties) {

        LogUtil.log(Steps.START, "Creates a user (not Employee) in MDS, SMS (optionally), and Ping");
        restActions.url(PartyEnum.PARTY_USER).body(payload.payloadForPartyGroup(parties.get(0))).post().doAssert()
                .is200().getObject("").isEqualExcludingItems(partyBL.validatePartyFields("partyId"),
                        parties.get(1).getExcludingItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288164 - Get a mySunPower user config/preference item
     *
     * @param partyData
     */
    @Test(dataProviderClass = PartyUserData.class, dataProvider = "getUserConfig", priority = 5)
    public void testMySunPowerUserConfig(List<Party> partyData) {

        //when get a mySunPower user config by partyId
        LogUtil.log(Steps.START, "Get a mySunPower user config/preference item");
        restActions.url(PartyEnum.USER_CONFIG).pathParam(partyData.get(0)).get().doAssert().is200();

        //when get a mySunPower user config by partyId & siteKey
        LogUtil.log(Steps.START, "Get a mySunPower user config/preference item");
        restActions.url(PartyEnum.USER_CONFIG).pathParam(partyData.get(0)).
                queryParam(partyData.get(1)).get().doAssert().is200();

        //when get a mySunPower user config by partyId & siteKey & appName
        LogUtil.log(Steps.START, "Get a mySunPower user config/preference item");
        restActions.url(PartyEnum.USER_CONFIG).pathParam(partyData.get(0)).
                queryParam(partyData.get(2)).get().doAssert().is200().getObject("[0]")
                .isEqualOnlyItems(partyBL.validatePartyFields("[0].partyId"), partyData.get(3).getOnlyItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C287995 - SPFM Feedback tool - API changes
     * @param partyData
     */
    @Test(dataProviderClass = PartyUserData.class, dataProvider = "createSpfmFeedbackProperties", priority = 6)
    public void testSPFMFeedbackTool(Site tool, List<Party> partyData) {

        LogUtil.log(Steps.START, "Post Subscription EndPoint");
        restActions.url(PartyEnum.USER_CONFIG).body(payload.createPayload(tool)).
                pathParam(partyData.get(0)).post().doAssert().is200();

        restActions.url(PartyEnum.USER_CONFIG).body(payload.createPayload(tool)).
                pathParam(partyData.get(0)).queryParam(partyData.get(1)).post().doAssert().is200().getObject("")
                .isEqualOnlyItems(partyBL.validatePartyFields("partyId"), partyData.get(2).getOnlyItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C288127 -200 Get all associated sites for a party by party id
     * @param partyData
     */
    @Test(dataProviderClass = PartyUserData.class, dataProvider = "verifyAssociatedSites", priority = 7)
    public void testAllAssociatedSites(List<Party> partyData) {

        LogUtil.log(Steps.START, "Verify all associated sites for a party by partyId");
        restActions.url(PartyEnum.PARTY_SITE).pathParam(partyData.get(0)).get().doAssert().is200().
                getObject("relations[0]").isEqualOnlyItems(partyBL.validateSiteFields("relations[0].siteKey"),
                        partyData.get(1).getOnlyItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }

    /**
     * C579977 -200 Creates a new notification configuration
     *
     * @param notificationParty
     */
    @Test(dataProviderClass = PartyUserData.class, dataProvider = "createNewNotificationConfiguration", priority = 8)
    public void testNotificationConfiguration(List<Party> notificationParty) {
        //When create a new notification configuration
        LogUtil.log(Steps.START, "Create a new notification configuration");
        restActions.url(PartyEnum.NOTIFICATION_CONFIG).
                body(payload.createPayload(notificationParty.get(0))).post().doAssert().is201().getObject("")
                .isEqualOnlyItems(partyBL.validatePartyFields("partyId"),
                        notificationParty.get(1).getOnlyItems());
        LogUtil.log(Steps.END, "The API response matched with Database's data");
    }
}
