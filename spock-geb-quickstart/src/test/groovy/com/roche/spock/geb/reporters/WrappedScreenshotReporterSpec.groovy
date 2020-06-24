package com.roche.spock.geb.reporters


import spock.lang.Specification

class WrappedScreenshotReporterSpec extends Specification {

    WrappedScreenshotReporter wrappedScreenshotReporter = new WrappedScreenshotReporter()

    def "should change the page source file name"() {
        given:
        def expectedFilename = new File('parent', 'my_label.zip').toString()

        when:
        def file = wrappedScreenshotReporter.getFile(new File('parent'), 'my label', 'zip')

        then:
        file.toString() == expectedFilename
    }
}
