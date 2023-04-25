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
package com.roche.spock.geb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties("com.roche.spock.geb")
public class SpockGebQuickstartConfiguration {

    private final String baseUrl;

    private final SpockGebQuickstartBrowserConfiguration browser;

    private final BrowserType browserType;

    @ConstructorBinding
    public SpockGebQuickstartConfiguration(String baseUrl, SpockGebQuickstartBrowserConfiguration browser,
                                           BrowserType browserType) {
        this.baseUrl = baseUrl;
        this.browser = browser;
        this.browserType = browserType == null ? BrowserType.local : browserType;
    }


    public String getBaseUrl() {
        return baseUrl;
    }

    public SpockGebQuickstartBrowserConfiguration getBrowser() {
        return browser;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }
}
