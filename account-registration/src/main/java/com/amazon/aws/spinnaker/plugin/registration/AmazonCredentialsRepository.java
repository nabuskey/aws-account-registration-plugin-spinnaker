package com.amazon.aws.spinnaker.plugin.registration;

import com.netflix.spinnaker.credentials.Credentials;
import com.netflix.spinnaker.credentials.CredentialsLifecycleHandler;
import com.netflix.spinnaker.credentials.MapBackedCredentialsRepository;
import com.netflix.spinnaker.credentials.definition.AbstractCredentialsLoader;
import org.springframework.context.annotation.Lazy;

public class AmazonCredentialsRepository <T extends Credentials> extends MapBackedCredentialsRepository<T> {

    AbstractCredentialsLoader<T> credentialsLoader;

    AmazonCredentialsRepository(String type,
                                CredentialsLifecycleHandler<T> eventHandler,
                                @Lazy AbstractCredentialsLoader<T> credentialsLoader) {
        super(type, eventHandler);
        this.credentialsLoader = credentialsLoader;
    }

    @Override
    public T getOne(String key) {
        T creds = credentials.get(key);
        if (creds == null) {
            credentialsLoader.load();
            creds = credentials.get(key);
        }
        return creds;
    }
}
