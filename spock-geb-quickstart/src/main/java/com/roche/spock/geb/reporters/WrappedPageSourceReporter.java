package com.roche.spock.geb.reporters;

import geb.report.PageSourceReporter;

import java.io.File;

public class WrappedPageSourceReporter extends PageSourceReporter {

    @Override
    protected Object getFile(File dir, String name, String extension) {
        File file = (File) super.getFile(dir, name, extension);
        return new File(dir, file.getName().replaceAll("\\s", "_"));
    }

}
