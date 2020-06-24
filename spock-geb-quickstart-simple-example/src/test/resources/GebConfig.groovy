import com.aoe.gebspockreports.GebReportingListener
import com.roche.spock.geb.config.BrowserConfiguration
import com.roche.spock.geb.reporters.BrowserLogsReporter
import com.roche.spock.geb.reporters.HarReporter
import com.roche.spock.geb.reporters.WrappedPageSourceReporter
import com.roche.spock.geb.reporters.WrappedScreenshotReporter
import geb.report.CompositeReporter
import io.github.bonigarcia.wdm.WebDriverManager

reporter = new CompositeReporter(new HarReporter(), new BrowserLogsReporter(), new WrappedScreenshotReporter(), new WrappedPageSourceReporter())

reportingListener = new GebReportingListener()
reportsDir = 'build/geb-spock-reports'
reportOnTestFailureOnly = false

// defaults
WebDriverManager.chromedriver().setup()

driver = { BrowserConfiguration.getChromeDriver() }

waiting {
    timeout = 6
    retryInterval = 0.5
    slow { timeout = 12 }
    reallySlow { timeout = 24 }
}
