package com.featureflags.poc.featureflags;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

@Configuration
public class ReloadablePropertySourceConfig {

	private ConfigurableEnvironment env;
	
	public ReloadablePropertySourceConfig(@Autowired ConfigurableEnvironment env) {
		this.env = env;
	}
	
	@Bean
	public ReloadablePropertySource reloadableProperties(PropertiesConfiguration reloadbleSourceConfig) {
		
		ReloadablePropertySource reloadableSource = new ReloadablePropertySource("dynamic", reloadbleSourceConfig);
		
		MutablePropertySources propertySources = env.getPropertySources();
		propertySources.addFirst(reloadableSource);
		
		return reloadableSource;
	}
}
