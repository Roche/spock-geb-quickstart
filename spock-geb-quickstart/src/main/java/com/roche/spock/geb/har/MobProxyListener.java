package com.roche.spock.geb.har;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

/**
 * Kept for reference on Geb.
 */
@Component
public class MobProxyListener implements TestExecutionListener {

    @Autowired
    BrowerMobProxyWrapper browerMobProxyWrapper;

    private Logger logger = LoggerFactory.getLogger(MobProxyListener.class);

    @Override
    public void beforeTestClass(TestContext testContext) {
    }

    @Override
    public void prepareTestInstance(TestContext testContext) {
    }

    @Override
    public void beforeTestMethod(TestContext testContext) {
    }

    @Override
    public void beforeTestExecution(TestContext testContext) {
    }

    @Override
    public void afterTestExecution(TestContext testContext) {
    }

    @Override
    public void afterTestMethod(TestContext testContext) {
    }

    @Override
    public void afterTestClass(TestContext testContext) {
    }
}
