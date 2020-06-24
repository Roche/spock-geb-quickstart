package com.roche.spock.geb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("com.roche.spock.geb")
public class SpockGebQuickstartConfiguration {

    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
