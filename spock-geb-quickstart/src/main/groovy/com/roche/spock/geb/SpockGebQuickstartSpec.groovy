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
