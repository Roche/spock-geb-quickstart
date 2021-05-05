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

import geb.Browser
import geb.report.ReportState
import org.openqa.selenium.WebDriver
import org.openqa.selenium.logging.LogEntries
import org.openqa.selenium.logging.LogEntry
import org.openqa.selenium.logging.LogType
import org.openqa.selenium.logging.Logs
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Path
import java.util.logging.Level

class BrowserLogsReporterSpec extends Specification {

    BrowserLogsReporter browserLogsReporter = new BrowserLogsReporter()

    Browser browser = Mock()
    WebDriver webDriver = Mock()
    WebDriver.Options options = Mock()
    Logs logs = Mock()
    LogEntries logEntries = Mock()

    def "should create an empty file on an empty log list"() {
        given:
        def browser = getBrowserWithLogs([])
        def reportDir = Files.createTempDirectory('spock-test').toFile()
        def state = new ReportState(browser, 'my label', reportDir)

        when:
        browserLogsReporter.writeReport(state)
        def logFile = new File(reportDir, 'my_label.txt')

        then:
        logFile.exists()
        logFile.length() == 0
    }

    def "should add browser log files"() {
        given:
        def browser = getBrowserWithLogs([new LogEntry(Level.INFO, System.currentTimeMillis(), "my message")])
        def reportDir = Files.createTempDirectory('spock-test').toFile()
        def reportState = new ReportState(browser, 'my-label', reportDir)

        when:
        browserLogsReporter.writeReport(reportState)
        def logEntry = Files.readString(Path.of(reportDir.toString(), 'my-label.txt'))

        then:
        logEntry.replaceFirst('([^]]+]\\s)', '').replace('\r\n', '\n') == '[INFO] my message\n'
    }

    def getBrowserWithLogs(entries) {
        browser.getDriver() >> webDriver
        webDriver.manage() >> options
        options.logs() >> logs
        logs.get(LogType.BROWSER) >> logEntries
        logEntries.getAll() >> entries
        return browser
    }

}
