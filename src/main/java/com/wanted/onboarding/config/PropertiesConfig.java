package com.wanted.onboarding.config;

import com.wanted.onboarding.support.Constants;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan(basePackages = Constants.BASE_PACKAGE)
public class PropertiesConfig {
}
