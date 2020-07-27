/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2019-2020 Hoffmann-La Roche.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
