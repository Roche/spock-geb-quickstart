package com.roche.spock.geb.reporters;

import geb.report.ReportState;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Collections;
import java.util.List;

public class BrowserLogsReporter extends WrappedReporterSupport {

    @Override
    public void writeReport(ReportState reportState) {
        File file = (File) getFile(reportState.getOutputDir(), reportState.getLabel(), "txt");
        List<LogEntry> all = reportState.getBrowser().getDriver().manage().logs().get(LogType.BROWSER).getAll();

        try {
            FileUtils.writeLines(file, all);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        notifyListeners(reportState, Collections.singletonList(file));
    }

}
