package com.roche.spock.geb.config;

import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpockGebQuickstartBrowserConfiguration {

    public static final String DEFAULT_DOCKER_CHROME_IMAGE = "selenium/standalone-chrome:97.0";

    private final List<String> arguments;

    private final String dockerImageName;

    private final String binary;

    private final URL gridAddress;

    private final BrowserStackConfiguration browserStack;

    public SpockGebQuickstartBrowserConfiguration(List<String> arguments, String dockerImageName, String binary, URL gridAddress, BrowserStackConfiguration browserStack) {
        this.gridAddress = gridAddress;
        this.browserStack = browserStack;

        if (arguments != null) {
            this.arguments = Collections.unmodifiableList(new ArrayList<>(arguments));
        } else {
            this.arguments = Collections.emptyList();
        }

        if (StringUtils.hasText(dockerImageName)) {
            this.dockerImageName = dockerImageName;
        } else {
            this.dockerImageName = DEFAULT_DOCKER_CHROME_IMAGE;
        }

        this.binary = binary;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public String getDockerImageName() {
        return dockerImageName;
    }

    public String getBinary() {
        return binary;
    }

    public URL getGridAddress() {
        return gridAddress;
    }

    public BrowserStackConfiguration getBrowserStack() {
        return browserStack;
    }

}
