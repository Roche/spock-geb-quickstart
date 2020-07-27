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

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import static net.lightbody.bmp.proxy.CaptureType.*;

public class BrowerMobProxyWrapper implements InitializingBean, DisposableBean {

    private BrowserMobProxy browserMobProxy;

    @Override
    public void destroy() throws Exception {
        browserMobProxy.stop();

    }

    public int getPort() {
        return browserMobProxy.getPort();
    }

    public BrowserMobProxy getBrowserMobProxy() {
        return browserMobProxy;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.browserMobProxy = new BrowserMobProxyServer();
        browserMobProxy.setTrustAllServers(true);
        browserMobProxy.enableHarCaptureTypes(REQUEST_HEADERS, RESPONSE_HEADERS, REQUEST_CONTENT, RESPONSE_CONTENT);
        browserMobProxy.start(0);
    }

}
