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

import com.browserup.bup.client.ClientUtil;
import com.roche.spock.geb.har.BrowerUpProxyWrapper;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.BrowserWebDriverContainer;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
@EnableConfigurationProperties(SpockGebQuickstartConfiguration.class)
public class BrowserConfiguration {

    private static ApplicationContext applicationContext;

    @Bean
    @Lazy(false)
    public Object injectContext(ApplicationContext applicationContext) {
        BrowserConfiguration.applicationContext = applicationContext;
        return new Object();
    }

    @Bean
    @Profile("default")
    @Scope(SCOPE_PROTOTYPE)
    public RemoteWebDriver chromeDriver(BrowerUpProxyWrapper wrapper) {
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(wrapper.getBrowserUpProxy(), InetAddress.getLoopbackAddress());
        ChromeOptions chromeOptions = new ChromeOptions().setProxy(seleniumProxy).setAcceptInsecureCerts(true);
        return new ChromeDriver(chromeOptions);
    }

    @Bean
    @Profile("docker")
    @Scope(SCOPE_PROTOTYPE)
    public RemoteWebDriver testContainersChromeDriver(BrowerUpProxyWrapper wrapper) {

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(InetSocketAddress.createUnresolved("host.testcontainers.internal", wrapper.getPort()));

        ChromeOptions chromeOptions = new ChromeOptions().setProxy(seleniumProxy).setAcceptInsecureCerts(true);

        Testcontainers.exposeHostPorts(wrapper.getPort());

        BrowserWebDriverContainer browserWebDriverContainer = new BrowserWebDriverContainer()
                .withCapabilities(chromeOptions)
                .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.SKIP, null);
        browserWebDriverContainer.start();
        return browserWebDriverContainer.getWebDriver();
    }

    @Bean
    public BrowerUpProxyWrapper browerMobProxyWrapper() {
        return new BrowerUpProxyWrapper();
    }

    public static RemoteWebDriver getChromeDriver() {
        return applicationContext.getBean(RemoteWebDriver.class);
    }

    public static BrowerUpProxyWrapper getBrowerUpProxyWrapper() {
        return applicationContext.getBean(BrowerUpProxyWrapper.class);
    }

}
