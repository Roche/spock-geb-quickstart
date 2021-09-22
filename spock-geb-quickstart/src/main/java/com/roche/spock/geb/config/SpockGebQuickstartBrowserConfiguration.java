package com.roche.spock.geb.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpockGebQuickstartBrowserConfiguration {

    private final List<String> arguments;

    public SpockGebQuickstartBrowserConfiguration(List<String> arguments) {
        if (arguments != null) {
            this.arguments = Collections.unmodifiableList(new ArrayList<>(arguments));
        } else {
            this.arguments = Collections.emptyList();
        }
    }

    public List<String> getArguments() {
        return arguments;
    }

}
