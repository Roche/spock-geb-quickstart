package com.roche.spock.geb.regression

import geb.Module
import geb.Page

class GebHomePage extends Page {
    static url = "/"

    static at = { title == "Geb - Very Groovy Browser Automation" }

    static content = {
        manualsMenu { module(ManualsMenuModule) }
    }
}

class ManualsMenuModule extends Module {

    static content = {
        toggle { $("div.menu a.manuals") }
        linksContainer { $("#manuals-menu") }
        links { linksContainer.find("a") }
    }

    void open() {
        toggle.click()
        waitFor { !linksContainer.hasClass("animating") }
    }
}

class TheBookOfGebPage extends Page {
    static at = { title.startsWith("The Book Of Geb") }

    static content = {
        webDriverLink { $("div#toc.toc2 a[href='#driver']") }
    }
}
