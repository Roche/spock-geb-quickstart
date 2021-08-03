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
package com.roche.spock.geb.har;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.BrowserUpProxyServer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import static com.browserup.bup.proxy.CaptureType.REQUEST_CONTENT;
import static com.browserup.bup.proxy.CaptureType.REQUEST_HEADERS;
import static com.browserup.bup.proxy.CaptureType.RESPONSE_CONTENT;
import static com.browserup.bup.proxy.CaptureType.RESPONSE_HEADERS;

public class BrowerUpProxyWrapper implements InitializingBean, DisposableBean {

    private BrowserUpProxy browserUpProxy;

    @Override
    public void destroy() {
        browserUpProxy.stop();

    }

    public int getPort() {
        return browserUpProxy.getPort();
    }

    public BrowserUpProxy getBrowserUpProxy() {
        return browserUpProxy;
    }

    @Override
    public void afterPropertiesSet() {
        this.browserUpProxy = new BrowserUpProxyServer();
        browserUpProxy.setTrustAllServers(true);
        browserUpProxy.enableHarCaptureTypes(REQUEST_HEADERS, RESPONSE_HEADERS, REQUEST_CONTENT, RESPONSE_CONTENT);
        browserUpProxy.start(0);
    }

}
