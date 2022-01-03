package com.featureflags.poc.featureflags;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.core.env.PropertySource;

public class ReloadablePropertySource extends PropertySource {

	PropertiesConfiguration propertiesConfiguration;
	
	public ReloadablePropertySource(String name, PropertiesConfiguration propertyConfiguration) {
		super(name);
		this.propertiesConfiguration = propertyConfiguration;
	}
	
	public ReloadablePropertySource(String name, String path) {
		super(name);
		
		try {
            this.propertiesConfiguration = new PropertiesConfiguration(path);
            this.propertiesConfiguration.setReloadingStrategy(new FileChangedReloadingStrategy());
        } catch (Exception e) {
            //throw new PropertiesException(e);
        }
	}
	

	@Override
	public Object getProperty(String name) {
		return propertiesConfiguration.getProperty(name);
	}

}
