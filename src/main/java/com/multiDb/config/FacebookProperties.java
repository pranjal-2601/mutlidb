package com.multiDb.config;

import com.multiDb.services.ProtectedMembers;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@Getter
@ConfigurationProperties(prefix = "facebook")
@EnableConfigurationProperties(FacebookProperties.class)
final public class FacebookProperties {
    private final String clientId;
    private final String clientSecret;
    private final String redirectUrl;


    @ConstructorBinding
    public FacebookProperties(String clientId, String clientSecret, String redirectUrl) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUrl = redirectUrl;
    }

    public void test() {
        ProtectedMembers pm = new ProtectedMembers();
        pm.pubM1();

    }
}
