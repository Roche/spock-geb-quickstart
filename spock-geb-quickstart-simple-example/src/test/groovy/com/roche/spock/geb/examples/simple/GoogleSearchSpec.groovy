package com.roche.spock.geb.examples.simple

import com.roche.spock.geb.SpockGebQuickstartSpec
import org.openqa.selenium.Keys
import spock.lang.Stepwise

@Stepwise
class GoogleSearchSpec extends SpockGebQuickstartSpec {

    def "should open google page"() {
        when:
        to GoogleHomePage

        then:
        waitFor(3, { searchInput })
    }

    def "should find Geb in Google Search"() {
        given:
        at GoogleHomePage

        when:
        searchInput = "spock geb"
        searchInput << Keys.ENTER

        then:
        waitFor(3, { $("h3", text: "Geb - Very Groovy Browser Automation") })
    }

}
