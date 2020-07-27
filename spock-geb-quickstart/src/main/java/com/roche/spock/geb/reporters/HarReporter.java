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
package com.roche.spock.geb.reporters;

import com.roche.spock.geb.config.BrowserConfiguration;
import geb.report.ReportState;
import net.lightbody.bmp.core.har.Har;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HarReporter extends WrappedReporterSupport {

    @Override
    public void writeReport(ReportState reportState) {
        File file = (File) getFile(reportState.getOutputDir(), reportState.getLabel(), "har");
        Har har = BrowserConfiguration.getBrowerMobProxyWrapper().getBrowserMobProxy().getHar();
        try {
            har.writeTo(file);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        notifyListeners(reportState, new ArrayList<File>(Arrays.asList(file)));
    }

}
