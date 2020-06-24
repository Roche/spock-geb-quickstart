package com.roche.spock.geb.reporters;

import geb.report.ReporterSupport;

import java.io.File;

abstract class WrappedReporterSupport extends ReporterSupport {

    /**
     * Rename all files with spaces (<code>file name.txt</code>) to files with underscores (<code>file_name.txt</code>).
     *
     * @param dir
     * @param name
     * @param extension
     * @return
     */
    @Override
    protected Object getFile(File dir, String name, String extension) {
        File file = (File) super.getFile(dir, name, extension);
        return new File(dir, file.getName().replaceAll("\\s", "_"));
    }

}
