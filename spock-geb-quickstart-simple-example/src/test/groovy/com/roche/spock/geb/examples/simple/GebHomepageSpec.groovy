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
package com.roche.spock.geb.examples.simple

import com.roche.spock.geb.SpockGebQuickstartSpec
import spock.lang.Ignore
import spock.lang.Stepwise

@Stepwise
class GebHomepageSpec extends SpockGebQuickstartSpec {

    def "can access The Book of Geb via homepage"() {
        given:
        to GebHomePage

        when:
        manualsMenu.open()
        manualsMenu.links[0].click()

        then:
        at TheBookOfGebPage
    }

    def "should go to WebDriver implementation"() {
        given:
        at TheBookOfGebPage

        when:
        webDriverLink.click()

        then:
        at TheBookOfGebPage
    }

    @Ignore
    def "should fail"() {
        given:
        at GebHomePage

        when:
        webDriverLink.click()

        then:
        at TheBookOfGebPage
    }

}
