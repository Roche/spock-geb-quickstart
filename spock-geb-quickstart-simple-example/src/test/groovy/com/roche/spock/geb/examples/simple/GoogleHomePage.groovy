package com.roche.spock.geb.examples.simple

import geb.Page

class GoogleHomePage extends Page {

    static url = "/"

    static at = { title == "Google" }

    static content = {
        searchInput { $("input", name: "q") }
        submitButton { $("div input", name: "btnK") }
        searchResults { $("div#search") }
    }

}
