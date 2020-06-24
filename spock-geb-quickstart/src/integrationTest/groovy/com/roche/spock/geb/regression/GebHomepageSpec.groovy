package com.roche.spock.geb.regression

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
