/*
 * Copyright 2004-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.macula.uid.config;

import org.macula.uid.generator.impl.CachedUidGenerator;
import org.macula.uid.generator.impl.DefaultUidGenerator;
import org.macula.uid.support.service.DefaultWorkerIdAssigner;
import org.macula.uid.generator.worker.WorkerIdAssigner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * <b>UidConfiguration</b> UID配置
 * </p>
 *
 * @author Rain
 * @since 2019-03-05
 */

@Configuration
@ConditionalOnClass({DefaultUidGenerator.class, CachedUidGenerator.class})
@EnableConfigurationProperties(UidProperties.class)
public class UidConfiguration {

    @Bean(name = "defaultUidGenerator")
    @ConditionalOnMissingBean
    DefaultUidGenerator defaultUidGenerator(UidProperties uidProperties) {
        return new DefaultUidGenerator(uidProperties);
    }

    @Bean(name = "cachedUidGenerator")
    @ConditionalOnMissingBean
    CachedUidGenerator cachedUidGenerator(UidProperties uidProperties) {
        return new CachedUidGenerator(uidProperties);
    }

    @Bean
    WorkerIdAssigner workerIdAssigner() {
        return new DefaultWorkerIdAssigner();
    }

}
