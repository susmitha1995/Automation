package com.sunpower.automation.edp.api.payload;

import com.sunpower.automation.api.base.AbstractPayloadHelper;
import com.sunpower.automation.api.edp.entity.data.Device;
import com.sunpower.automation.api.edp.entity.data.Party;
import com.sunpower.automation.api.edp.entity.data.Site;
import com.sunpower.automation.core.utils.DateUtil;

public class EdpPayload extends AbstractPayloadHelper {

    private static EdpPayload edpPayload = null;

    private EdpPayload() {

    }

    /**
     * Get Instance of EdpPayload
     * @return
     */
    public static EdpPayload getInstance() {
        return edpPayload == null ? new EdpPayload() : edpPayload;
    }

    /**
     * Generate payload for create site
     *
     * @param site - payload data
     * @return String - Payload as a JSON String
     */
    public String payloadForCreateSite(Site site) {

        String localTime = DateUtil.getLocalTime().toString("SSS");
        site.setSiteName(site.getSiteName() + "_" + localTime);
        site.setAddress1(site.getAddress1() + "_" + localTime);

        return createPayload(site);
    }

    /**
     * Generate payload for update site
     *
     * @param site - payload data
     * @return String - Payload as a JSON String
     */
    public String payloadForUpdateSite(Site site) {

        String localTime = DateUtil.getLocalTime().toString("mmSSS");
        site.setSiteName(site.getSiteName() + "_updated_" + localTime);
        site.setAddress1(site.getAddress1() + "_updated_" + localTime);

        return createPayload(site);
    }

    /**
     * Generate payload for update device characteristics
     *
     * @param character - payload data
     * @return String - Payload as a JSON String
     */
    public String payloadForDeviceCharacteristics(Device character) {

        character.getItems().stream().forEach(data -> {
            String localTime = DateUtil.getLocalTime().toString("mmSSS");
            data.setValue(data.getValue() + "" + localTime);
        });
        return createPayload(character);
    }

    /**
     * Generate payload for update party
     *
     * @param party - payload data
     * @return String - Payload as a JSON String
     */
    public String payloadForUpdateParty(Party party) {

        String localTime = DateUtil.getLocalTime().toString("SSS");
        party.setDisplayName(party.getDisplayName() + "_" + localTime);

        return createPayload(party);
    }

    /**
     * Generate payload for update user configuration
     *
     * @param userConfig - payload data
     * @return String - Payload as a JSON String
     */
    public String payloadForUpdateUserConfig(Party userConfig) {

        String localTime = DateUtil.getLocalTime().toString("SSS");
        userConfig.getAppRating().setComment(userConfig.getAppRating().getComment() + "_" + localTime);

        return createPayload(userConfig);
    }

    /**
     * Generate payload for create or update Internal User
     *
     * @param userConfig - payload data
     * @return String - Payload as a JSON String
     */
    public String payloadForCreateOrUpdateInternalUser(Party userConfig) {

        String localTime = DateUtil.getLocalTime().toString("SSS");
        userConfig.setFirstName(userConfig.getFirstName() + localTime);
        userConfig.setLastName(userConfig.getLastName() + localTime);

        return createPayload(userConfig);
    }

    /**
     * Generate payload for Group
     *
     * @param group - payload data
     * @return String - Payload as a JSON String
     */
    public String payloadForPartyGroup(Party group) {

        String localTime = DateUtil.getLocalTime().toString("SSS");
        group.setDisplayName(group.getDisplayName() + "_" + localTime);
        group.setInternalName(group.getInternalName() + "_" + localTime);
        group.setOrgName(group.getOrgName() + "_" + DateUtil.getLocalTime().toString("mmSSS"));
        group.setEmail("auto_" + localTime + "@test.com");
        group.setFirstName(group.getFirstName() + "_" + localTime);
        group.setLastName(group.getLastName() + "_" + localTime);

        return createPayload(group);
    }

    /**
     * Generate payload for create or update Organization Party
     *
     * @param orgParty - payload data
     * @return String - Payload as a JSON String
     */
    public String payloadForCreateOrUpdateOrganizationParty(Party orgParty) {

        String localTime = DateUtil.getLocalTime().toString("SSS");
        orgParty.setDisplayName(orgParty.getDisplayName() + localTime);
        orgParty.setInternalName(orgParty.getInternalName() + localTime);
        orgParty.setOrgName(orgParty.getOrgName() + localTime);
        orgParty.setEmail(orgParty.getEmail().replace("@testAuto.com", "@testAuto" + localTime + ".com"));

        return createPayload(orgParty);
    }

    /**
     * Generate payload for Update Group Party
     *
     * @param group - payload data
     * @return String - Payload as a JSON String
     */
    public String payloadForUpdateGroupParty(Party group) {

        String localTime = DateUtil.getLocalTime().toString("SSS");
        group.setDisplayName(group.getDisplayName() + "_" + localTime);
        group.setInternalName(group.getInternalName() + "_" + localTime);
        group.setOrgName(group.getOrgName() + "_" + DateUtil.getLocalTime().toString("mmSSS"));
        group.setEmail("autoupdate_" + localTime + "@test.com");

        return createPayload(group);
    }

    /**
     * Generate payload for create user
     *
     * @param party - payload data
     * @return String - Payload as a JSON String
     */
    public String payloadForCreateUser(Party party) {

        String localTime = DateUtil.getLocalTime().toString("SSS");
        party.setDisplayName(party.getDisplayName() + "_" + localTime);
        party.setEmail("auto_" + localTime + "@test.com");
        party.setFirstName(party.getFirstName() + localTime);
        party.setLastName(party.getLastName() + localTime);
        return createPayload(party);
    }
}
