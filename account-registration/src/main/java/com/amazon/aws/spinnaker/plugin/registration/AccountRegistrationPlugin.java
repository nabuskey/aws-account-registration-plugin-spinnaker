/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License").
 *   You may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.amazon.aws.spinnaker.plugin.registration;

import com.netflix.spinnaker.kork.plugins.api.spring.SpringLoaderPlugin;
import lombok.extern.slf4j.Slf4j;
import org.pf4j.PluginWrapper;

import java.util.Collections;
import java.util.List;

@Slf4j
public class AccountRegistrationPlugin extends SpringLoaderPlugin {

    public AccountRegistrationPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public List<String> getPackagesToScan() {
        return Collections.singletonList("com.amazon.aws.spinnaker.plugin.registration");
    }

    @Override
    public void start() {
        log.info("{} plugin started", this.getClass().getSimpleName());
    }

    @Override
    public void stop() {
        log.info("{} plugin stopped", this.getClass().getSimpleName());
    }
}