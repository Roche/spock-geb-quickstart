package com.roche.spock.geb.reporters

import com.roche.spock.geb.config.BrowserConfiguration
import com.roche.spock.geb.har.BrowerMobProxyWrapper
import geb.report.ReportState
import net.lightbody.bmp.BrowserMobProxy
import net.lightbody.bmp.core.har.Har
import org.springframework.context.ApplicationContext
import spock.lang.Specification

import java.nio.file.Files

class HarReporterSpec extends Specification {

    HarReporter harReporter = new HarReporter()
    ApplicationContext applicationContext = Mock()
    BrowerMobProxyWrapper browerMobProxyWrapper = Mock()
    BrowserMobProxy browserMobProxy = Mock()
    Har har = Mock()


    def "should write the har file"() {
        given:
        def har = prepareHar()
        def reportDir = Files.createTempDirectory('spock-test').toFile()
        def reportState = new ReportState(null, 'my-label', reportDir)
        def expectedFile = new File(reportDir, 'my-label.har')

        when:
        harReporter.writeReport(reportState)

        then:
        1 * har.writeTo(expectedFile)
    }

    def prepareHar() {
        new BrowserConfiguration().injectContext(applicationContext)
        applicationContext.getBean(BrowerMobProxyWrapper) >> browerMobProxyWrapper
        browerMobProxyWrapper.getBrowserMobProxy() >> browserMobProxy
        browserMobProxy.getHar() >> har
        return har
    }
}
