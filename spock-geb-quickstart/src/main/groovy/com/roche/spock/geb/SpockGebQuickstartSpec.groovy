package com.roche.spock.geb

import com.roche.spock.geb.config.BrowserConfiguration
import com.roche.spock.geb.config.SpockGebQuickstartConfiguration
import com.roche.spock.geb.har.BrowerMobProxyWrapper
import com.roche.spock.geb.har.MobProxyListener
import geb.spock.GebReportingSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestExecutionListeners

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

@SpringBootTest(webEnvironment = NONE, classes = BrowserConfiguration.class)
@TestExecutionListeners(
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
        listeners = MobProxyListener.class
)
abstract class SpockGebQuickstartSpec extends GebReportingSpec {

    @Autowired
    BrowerMobProxyWrapper browerMobProxyWrapper

    @Autowired
    SpockGebQuickstartConfiguration spockGebQuickstartConfiguration

    def setup() {
        browerMobProxyWrapper.getBrowserMobProxy().newHar()
        browser.baseUrl = spockGebQuickstartConfiguration.baseUrl
    }

}
