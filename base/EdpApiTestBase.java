
package com.sunpower.automation.edp.api.base;

import com.sunpower.automation.core.exceptions.ParamException;
import com.sunpower.automation.core.manager.ConfigManager;
import com.sunpower.automation.core.utils.TestCaseGridUtil;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;

public class EdpApiTestBase {

    /**
     * @param context
     * @throws ParamException
     */
    @BeforeSuite(alwaysRun = true)
    public void setups(final ITestContext context) throws ParamException {
        ConfigManager.loadConfig("testData.properties");
        TestCaseGridUtil.printAllTestcases(context);
        System.setProperty("screenshot", "false"); //To avoid taking screenshot in AutomationListener class.
    }
}
