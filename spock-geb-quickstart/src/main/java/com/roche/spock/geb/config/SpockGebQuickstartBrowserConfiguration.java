package com.roche.spock.geb.config;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpockGebQuickstartBrowserConfiguration {

    public static final String DEFAULT_DOCKER_CHROME_IMAGE = "selenium/standalone-chrome:97.0";

    private final List<String> arguments;

    private final String dockerImageName;

    private final String binary;

    public SpockGebQuickstartBrowserConfiguration(List<String> arguments, String dockerImageName, String binary) {

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
}
