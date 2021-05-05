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

import com.roche.spock.geb.har.BrowerMobProxyWrapper;
import net.lightbody.bmp.client.ClientUtil;
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
import org.testcontainers.containers.wait.strategy.HostPortWaitStrategy;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.containers.wait.strategy.WaitAllStrategy;
import org.testcontainers.containers.wait.strategy.WaitStrategy;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;
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
    public RemoteWebDriver chromeDriver(BrowerMobProxyWrapper wrapper) {
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(wrapper.getBrowserMobProxy(), InetAddress.getLoopbackAddress());
        ChromeOptions chromeOptions = (ChromeOptions) new ChromeOptions().setProxy(seleniumProxy).setAcceptInsecureCerts(true);
        return new ChromeDriver(chromeOptions);
    }

    @Bean
    @Profile("docker")
    @Scope(SCOPE_PROTOTYPE)
    public RemoteWebDriver testContainersChromeDriver(BrowerMobProxyWrapper wrapper, SpockGebQuickstartConfiguration spockGebQuickstartConfiguration) {

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(InetSocketAddress.createUnresolved("host.testcontainers.internal", wrapper.getPort()));

        ChromeOptions chromeOptions = (ChromeOptions) new ChromeOptions()
                .setProxy(seleniumProxy)
                .setAcceptInsecureCerts(true)
                .addArguments("--disable-dev-shm-usage");

        Testcontainers.exposeHostPorts(wrapper.getPort());

        BrowserWebDriverContainer<?> browserWebDriverContainer = new BrowserWebDriverContainer<>(spockGebQuickstartConfiguration.getDockerImageName())
                .withCapabilities(chromeOptions)
                .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.SKIP, null);
        browserWebDriverContainer.setWaitStrategy(getWaitStrategy());
        browserWebDriverContainer.start();
        return browserWebDriverContainer.getWebDriver();
    }

    private WaitStrategy getWaitStrategy() {
        final WaitStrategy logWaitStrategy = new LogMessageWaitStrategy()
                .withRegEx(".*(RemoteWebDriver instances should connect to|Selenium Server is up and running|Started Selenium Standalone).*\n")
                .withStartupTimeout(Duration.of(15, SECONDS));

        return new WaitAllStrategy()
                .withStrategy(logWaitStrategy)
                .withStrategy(new HostPortWaitStrategy())
                .withStartupTimeout(Duration.of(15, SECONDS));
    }

    @Bean
    public BrowerMobProxyWrapper browerMobProxyWrapper() {
        return new BrowerMobProxyWrapper();
    }

    public static RemoteWebDriver getChromeDriver() {
        return applicationContext.getBean(RemoteWebDriver.class);
    }

    public static BrowerMobProxyWrapper getBrowerMobProxyWrapper() {
        return applicationContext.getBean(BrowerMobProxyWrapper.class);
    }

}
