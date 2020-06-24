package com.roche.spock.geb.reporters;

import com.roche.spock.geb.config.BrowserConfiguration;
import geb.report.ReportState;
import net.lightbody.bmp.core.har.Har;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HarReporter extends WrappedReporterSupport {

    @Override
    public void writeReport(ReportState reportState) {
        File file = (File) getFile(reportState.getOutputDir(), reportState.getLabel(), "har");
        Har har = BrowserConfiguration.getBrowerMobProxyWrapper().getBrowserMobProxy().getHar();
        try {
            har.writeTo(file);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        notifyListeners(reportState, new ArrayList<File>(Arrays.asList(file)));
    }

}
