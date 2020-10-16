package com.amazon.aws.spinnaker.plugin.registration;

import com.netflix.spinnaker.clouddriver.aws.security.config.CredentialsConfig;
import com.netflix.spinnaker.clouddriver.ecs.security.ECSCredentialsConfig;
import com.netflix.spinnaker.credentials.definition.CredentialsDefinitionSource;
import com.netflix.spinnaker.kork.plugins.api.spring.ExposeToApp;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class CredentialsDefinitions {

    @Bean
    @ExposeToApp
    CredentialsDefinitionSource<CredentialsConfig.Account> ec2DefinitionSource(AccountsStatus accountsStatus) {
        return new CredentialsDefinitionSource<CredentialsConfig.Account>() {
            @NotNull
            @Override
            public List<CredentialsConfig.Account> getCredentialsDefinitions() {
                if (accountsStatus.getDesiredAccounts()) {
                    return accountsStatus.getEC2AccountsAsList();
                }
                return Collections.emptyList();
            }
        };
    }

    @Bean
    @ExposeToApp
    CredentialsDefinitionSource<ECSCredentialsConfig.ECSAccount> ecsDefinitionSource(AccountsStatus accountsStatus) {
        return new CredentialsDefinitionSource<ECSCredentialsConfig.ECSAccount>() {
            @Override
            public List<ECSCredentialsConfig.ECSAccount> getCredentialsDefinitions() {
                return accountsStatus.getECSAccountsAsList();
            }
        };
    }
}
