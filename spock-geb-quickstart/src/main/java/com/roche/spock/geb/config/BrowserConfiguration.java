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

import geb.driver.BrowserStackDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.wait.strategy.HostPortWaitStrategy;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.containers.wait.strategy.WaitAllStrategy;
import org.testcontainers.containers.wait.strategy.WaitStrategy;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
@EnableConfigurationProperties(SpockGebQuickstartConfiguration.class)
public class BrowserConfiguration {

    private static ApplicationContext applicationContext;

    @Bean
    @Lazy(false)
    @SuppressWarnings("java:S2696")
    public Object injectContext(ApplicationContext applicationContext) {
        BrowserConfiguration.applicationContext = applicationContext;
        return new Object();
    }

    @Bean
    public WebDriverManager webDriverManager() {
        WebDriverManager webDriverManager = WebDriverManager.chromedriver();
        webDriverManager.setup();
        return webDriverManager;
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public RemoteWebDriver getRemoteWebDriver(WebDriverManager webDriverManager,
                                              SpockGebQuickstartConfiguration spockGebQuickstartConfiguration) {
        if (spockGebQuickstartConfiguration.getBrowserType() == BrowserType.docker) {
            return testContainersChromeDriver(webDriverManager, spockGebQuickstartConfiguration);
        } else if (spockGebQuickstartConfiguration.getBrowserType() == BrowserType.grid) {
            return gridChromeDriver(spockGebQuickstartConfiguration);
        } else if (spockGebQuickstartConfiguration.getBrowserType() == BrowserType.browserstack) {
            return browserStackChromeDriver(spockGebQuickstartConfiguration);
        } else {
            return chromeDriver(webDriverManager, spockGebQuickstartConfiguration);
        }
    }

    private RemoteWebDriver browserStackChromeDriver(SpockGebQuickstartConfiguration spockGebQuickstartConfiguration) {
        BrowserStackConfiguration browserStack = spockGebQuickstartConfiguration.getBrowser().getBrowserStack();
        Map<String, Object> capabilities = new HashMap<>();
        capabilities.put("browserName", browserStack.getBrowser());
        Map<String, Object> browserStackOptions = browserStack.getOptions();
        if (browserStackOptions != null) {
            capabilities.put("bstack:options", browserStackOptions);
        }
        return (RemoteWebDriver) new BrowserStackDriverFactory()
                .create(browserStack.getUsername(), browserStack.getAccessKey(), capabilities);
    }

    public RemoteWebDriver chromeDriver(WebDriverManager webDriverManager,
                                        SpockGebQuickstartConfiguration spockGebQuickstartConfiguration) {

        Objects.requireNonNull(webDriverManager, "WebDriverManager must not be null");

        ChromeOptions chromeOptions = new ChromeOptions();

        SpockGebQuickstartBrowserConfiguration browser = spockGebQuickstartConfiguration.getBrowser();

        if (browser != null) {
            chromeOptions.addArguments(browser.getArguments());

            if (browser.getBinary() != null) {
                if (!Files.exists(Path.of(browser.getBinary()))) {
                    throw new IllegalArgumentException("Browser binary not found: [" + browser.getBinary() + "]");
                }
                chromeOptions.setBinary(browser.getBinary());
            }
        }

        return new ChromeDriver(chromeOptions);
    }


    public RemoteWebDriver testContainersChromeDriver(WebDriverManager webDriverManager,
                                                      SpockGebQuickstartConfiguration spockGebQuickstartConfiguration) {

        Objects.requireNonNull(webDriverManager, "WebDriverManager must not be null");

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--disable-dev-shm-usage");

        if (spockGebQuickstartConfiguration.getBrowser() != null) {
            chromeOptions.addArguments(spockGebQuickstartConfiguration.getBrowser().getArguments());
        }

        BrowserWebDriverContainer<?> browserWebDriverContainer = getBrowserWebDriverContainer(spockGebQuickstartConfiguration)
                .withCapabilities(chromeOptions)
                .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.SKIP, null);

        browserWebDriverContainer.setWaitStrategy(getWaitStrategy());
        browserWebDriverContainer.start();
        RemoteWebDriver remoteWebDriver = browserWebDriverContainer.getWebDriver();
        remoteWebDriver.setFileDetector(new LocalFileDetector());
        return remoteWebDriver;
    }

    @NotNull
    private BrowserWebDriverContainer<?> getBrowserWebDriverContainer(SpockGebQuickstartConfiguration spockGebQuickstartConfiguration) {
        if (spockGebQuickstartConfiguration.getBrowser() == null) {
            return new BrowserWebDriverContainer<>();
        }
        return new BrowserWebDriverContainer<>(spockGebQuickstartConfiguration.getBrowser().getDockerImageName());
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

    public static RemoteWebDriver getChromeDriver() {
        return applicationContext.getBean(RemoteWebDriver.class);
    }

    private RemoteWebDriver gridChromeDriver(SpockGebQuickstartConfiguration spockGebQuickstartConfiguration) {
        ChromeOptions chromeOptions = new ChromeOptions();

        if (spockGebQuickstartConfiguration.getBrowser() != null) {
            chromeOptions.addArguments(spockGebQuickstartConfiguration.getBrowser().getArguments());
        }

        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(spockGebQuickstartConfiguration.getBrowser().getGridAddress(), chromeOptions);
        remoteWebDriver.setFileDetector(new LocalFileDetector());
        return remoteWebDriver;
    }
}