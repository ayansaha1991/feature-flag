package com.featureflags.poc.featureflags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppPropFeatureFlags implements FeatureFlag {

	@Value("${feature.rename}")
	private boolean renameEnabled;
	
	@Autowired
	Environment env;
	
	@Override
	public boolean isRenamingEnabled() {
		String renaming =  env.getProperty("feature.rename") ;
		return Boolean.valueOf(renaming);
	}
}
