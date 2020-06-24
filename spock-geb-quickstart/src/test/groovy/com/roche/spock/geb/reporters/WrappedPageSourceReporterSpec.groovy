package com.roche.spock.geb.reporters


import spock.lang.Specification

class WrappedPageSourceReporterSpec extends Specification {

    WrappedPageSourceReporter wrappedPageSourceReporter = new WrappedPageSourceReporter()

    def "should change the page source file name"() {
        given:
        def expectedFilename = new File('parent', 'my_label.zip').toString()

        when:
        def file = wrappedPageSourceReporter.getFile(new File('parent'), 'my label', 'zip')

        then:
        file.toString() == expectedFilename
    }
}
