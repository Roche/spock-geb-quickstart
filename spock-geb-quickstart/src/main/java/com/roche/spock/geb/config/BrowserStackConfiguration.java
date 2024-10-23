package com.roche.spock.geb.config;

import java.util.Map;

public class BrowserStackConfiguration {

    private final String username;
    private final String accessKey;
    private final String local;
    private final String localIdentifier;
    private final String build;
    private final String name;
    private final String browser = "chrome";
    private final String os = "Windows";
    private final String osVersion = "10";
    private final String browserVersion = "125";
    private final Map<String, Object> options;
    private final String browserName;


    public BrowserStackConfiguration(String username, String accessKey, String local, String localIdentifier, String build, String name, Map<String, Object> options, String browserName) {
        this.username = username;
        this.accessKey = accessKey;
        this.local = local;
        this.localIdentifier = localIdentifier;
        this.build = build;
        this.name = name;
        this.options = options;
        this.browserName = browserName;
    }

    public String getUsername() {
        return username;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String isLocal() {
        return local;
    }

    public String getLocalIdentifier() {
        return localIdentifier;
    }

    public String getBuild() {
        return build;
    }

    public String getName() {
        return name;
    }

    public String getBrowser() {
        return browser;
    }

    public String getOs() {
        return os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public Map<String, Object> getOptions() {
        return options;
    }

    public String getBrowserName() {
        return browserName;
    }
}
