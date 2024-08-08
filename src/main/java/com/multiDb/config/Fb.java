package com.multiDb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationProperties(prefix = "facebook")
@EnableConfigurationProperties(Fb.class)
public record Fb(String clientId, String clientSecret, String redirectUrl) {}
