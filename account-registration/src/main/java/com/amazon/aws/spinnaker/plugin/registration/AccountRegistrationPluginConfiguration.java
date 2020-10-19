package com.amazon.aws.spinnaker.plugin.registration;

import com.netflix.spinnaker.clouddriver.aws.AmazonCloudProvider;
import com.netflix.spinnaker.clouddriver.aws.security.NetflixAmazonCredentials;
import com.netflix.spinnaker.clouddriver.aws.security.config.CredentialsConfig;
import com.netflix.spinnaker.clouddriver.ecs.security.ECSCredentialsConfig;
import com.netflix.spinnaker.credentials.CredentialsLifecycleHandler;
import com.netflix.spinnaker.credentials.CredentialsRepository;
import com.netflix.spinnaker.credentials.definition.AbstractCredentialsLoader;
import com.netflix.spinnaker.credentials.definition.CredentialsDefinitionSource;
import com.netflix.spinnaker.kork.plugins.api.spring.ExposeToApp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AccountRegistrationPluginConfiguration {

    @Bean
    @ExposeToApp
    CredentialsDefinitionSource<CredentialsConfig.Account> ec2DefinitionSource(AccountsStatus accountsStatus) {
        return () -> {
            accountsStatus.getDesiredAccounts();
            return accountsStatus.getEC2AccountsAsList();
        };
    }

    @Bean
    @ExposeToApp
    CredentialsDefinitionSource<ECSCredentialsConfig.Account> ecsDefinitionSource(AccountsStatus accountsStatus) {
        return accountsStatus::getECSAccountsAsList;
    }

    @Bean
    @ExposeToApp
    CredentialsRepository<NetflixAmazonCredentials> amazonCredentialsRepository(
            @Lazy CredentialsLifecycleHandler<NetflixAmazonCredentials> handler,
            @Lazy AbstractCredentialsLoader<NetflixAmazonCredentials> loader
            ) {
        return new AmazonCredentialsRepository<>(
                AmazonCloudProvider.ID,
                handler,
                loader
        );
    }
}
