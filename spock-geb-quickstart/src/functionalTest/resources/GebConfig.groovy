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


import com.aoe.gebspockreports.GebReportingListener
import com.roche.spock.geb.config.BrowserConfiguration
import com.roche.spock.geb.reporters.BrowserLogsReporter
import com.roche.spock.geb.reporters.WrappedPageSourceReporter
import com.roche.spock.geb.reporters.WrappedScreenshotReporter
import geb.report.CompositeReporter

reporter = new CompositeReporter(new BrowserLogsReporter(), new WrappedScreenshotReporter(), new WrappedPageSourceReporter())

reportingListener = new GebReportingListener()
reportsDir = 'build/geb-spock-reports'
reportOnTestFailureOnly = false

// defaults
driver = { BrowserConfiguration.getChromeDriver() }

waiting {
    timeout = 6
    retryInterval = 0.5
    slow { timeout = 12 }
    reallySlow { timeout = 24 }
}
