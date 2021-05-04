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

import geb.report.ReporterSupport;

import java.io.File;

abstract class WrappedReporterSupport extends ReporterSupport {

    /**
     * Rename all files with spaces (<code>file name.txt</code>) to files with underscores (<code>file_name.txt</code>).
     *
     * @param dir       report directory
     * @param name      file name
     * @param extension file extension
     * @return the report file location
     */
    @Override
    protected Object getFile(File dir, String name, String extension) {
        File file = (File) super.getFile(dir, name, extension);
        return new File(dir, file.getName().replaceAll("\\s", "_"));
    }

}
