/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2019-2020 Hoffmann-La Roche.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
