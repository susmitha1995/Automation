package com.sunpower.automation.edp.api.interfaces;

import com.sunpower.automation.core.enums.LogLevel;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.core.utils.ParamUtil;

public interface EdpBaseEnum {

    enum ServerURL {

        QA("https://test-edp-api.dev-edp.sunpower.com"),

        UAT("https://edp-api.dev-edp.sunpower.com"),

        PROD("https://edp-api.edp.sunpower.com");

        String serverURL;

        ServerURL(String serverURL) {
            this.serverURL = serverURL;
        }

        /**
         * getServerURL
         * @return
         */
        public String getServerURL() {
            return serverURL;
        }
    }

    public String V1 = "V1";

    public String V2 = "v2";

    /**
     * Get URL version one dynamic value
     * @param value
     * @return
     */
    public String getUrlV1(String... value);

    /**
     * Get URL version two with dynamic value
     * @param value
     * @return
     */
    public String getUrlV2(String... value);

    /**
     * Get url of version one
     * @return
     */
    public String getUrlV1();

    /**
     * Get url of version two
     * @return
     */
    public String getUrlV2();

    /**
     * Format the endpoint URL
     * @param url
     * @param v
     * @param dynamicValue
     * @return
     */
    public default String formatEndPointUrl(String url, String v, String... dynamicValue) {

        StringBuilder formatUrl = new StringBuilder();
        formatUrl.append(ServerURL.valueOf(ParamUtil.getTestEnv().toUpperCase()).getServerURL());
        formatUrl.append("/");
        formatUrl.append(v);
        formatUrl.append("/");
        formatUrl.append(String.format(url, dynamicValue[0]));

        LogUtil.log("URL: " + formatUrl.toString(), LogLevel.LOW);

        return formatUrl.toString();
    }
}
