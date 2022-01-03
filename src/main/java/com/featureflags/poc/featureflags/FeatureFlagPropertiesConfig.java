package com.featureflags.poc.featureflags;

import java.io.File;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeatureFlagPropertiesConfig {

	@Bean
	public PropertiesConfiguration propertiesConfig(@Value("${spring.config.location}") String filepath) throws Exception {
		
		String filePath = new File(filepath.substring("file:".length())).getCanonicalPath();
		PropertiesConfiguration configuration = new PropertiesConfiguration(new File(filePath));
		configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
		
		return configuration;
	}
	
	
}
