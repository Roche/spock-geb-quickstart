package com.roche.spock.geb.har;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import static net.lightbody.bmp.proxy.CaptureType.*;

public class BrowerMobProxyWrapper implements InitializingBean, DisposableBean {

    private BrowserMobProxy browserMobProxy;

    @Override
    public void destroy() throws Exception {
        browserMobProxy.stop();

    }

    public int getPort() {
        return browserMobProxy.getPort();
    }

    public BrowserMobProxy getBrowserMobProxy() {
        return browserMobProxy;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.browserMobProxy = new BrowserMobProxyServer();
        browserMobProxy.setTrustAllServers(true);
        browserMobProxy.enableHarCaptureTypes(REQUEST_HEADERS, RESPONSE_HEADERS, REQUEST_CONTENT, RESPONSE_CONTENT);
        browserMobProxy.start(0);
    }

}
