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
public class PartyApiUnauthorizedTest extends EdpApiTestBase {

    EdpApiRestActions restActions = EdpApiRestActions.getInstance(PartyApiBL.class);

    EdpPayload payload = EdpPayload.getInstance();

    /**
     * C288183 - Get user - Status code matches the expected result
     *
     * @param parties
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "verifyAssociatedUsers", priority = 0)
    public void testUnauthorizedForPartyUsers(List<Party> parties) {

        LogUtil.log(Steps.START, "Verify associated users by invalid token");
        restActions.url(PartyEnum.USER).pathParam(parties.get(0)).queryParam(parties.get(1))
                .withInvalidToken().get().doAssert().is401();
    }

    /**
     * C288186 - Get party sites - Status code matches the expected result
     *
     * @param party
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "verifyPartySites", priority = 1)
    public void testUnauthorizedForPartySites(Party party) {

        LogUtil.log(Steps.START, "Verify party sites by invalid token");
        restActions.url(PartyEnum.V1_PARTY_BY_ID).pathParam(party).withInvalidToken().get().doAssert().is401();
    }

    /**
     * C288189 - Get associated parties - Status code matches the expected result
     *
     * @param party
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "verifyAssociatedParties", priority = 2)
    public void testUnauthorizedForAssociatedParties(Party party) {

        LogUtil.log(Steps.START, "Verify associated parties by invalid token");
        restActions.url(PartyEnum.PARTY_SITE).pathParam(party).withInvalidToken().get().doAssert().is401();
    }

    /**
     * C288176 - Update organization - Status code matches the expected result
     *
     * @param party
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "updatesAnOrganizationParty", priority = 3)
    public void testUnauthorizedForUpdatesOrganization(List<Party> party) {

        //When update the organization party
        LogUtil.log(Steps.START, "Updates an organization party");
        restActions.url(PartyEnum.ORG_BY_PARTY_ID).pathParam(party.get(1)).body(payload.createPayload(party.get(0)))
                .withInvalidToken().patch().doAssert().is401();
    }

    /**
     * C288184 - Create party site association - Status code matches the expected result
     *
     * @param party
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "createsAnOrganizationParty", priority = 4)
    public void testUnauthorizedForCreatesPartySites(Party party) {

        //When update the organization party
        LogUtil.log(Steps.START, "Create party site association");
        restActions.url(PartyEnum.V1_PARTY).body(payload.createPayload(party))
                .withInvalidToken().post().doAssert().is401();
    }

    /**
     * C288185 - Update party site
     *
     * @param parties
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "updatesPartySite", priority = 5)
    public void testUnauthorizedForUpdatesParty(List<Party> parties) {

        LogUtil.log(Steps.START, "Updates a party");
        restActions.url(PartyEnum.V1_PARTY_BY_ID).pathParam(parties.get(1))
                .body(payload.payloadForUpdateParty(parties.get(0))).withInvalidToken().patch().doAssert().is401();

        LogUtil.log(Steps.START, "Updates a party");
        restActions.url(PartyEnum.V2_PARTY_BY_ID).pathParam(parties.get(1))
                .body(payload.payloadForUpdateParty(parties.get(1))).withInvalidToken().patch().doAssert().is401();
    }

    /**
     * C288179 - Create user
     * @param party
     */
    @Test(dataProviderClass = PartyApiData.class, dataProvider = "getUserData", priority = 6)
    public void testUnauthorizedForCreateUser(Party party) {

        LogUtil.log(Steps.START, "Create user response details and validate");
        restActions.url(PartyEnum.PARTY_USER).body(payload.createPayload(party))
                .withInvalidToken().post().doAssert().is401();
    }
}
