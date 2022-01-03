package com.featureflags.poc.featureflags;

import org.springframework.stereotype.Component;

@Component
public class RenamingServiceDecider extends RenamingServiceFactory<Services> {
	
	public RenamingServiceDecider(FeatureFlag featureFlags) {

		super(new DefaultRenaming(), 
			new CustomerPrefferedRenaming(), 
			featureFlags::isRenamingEnabled, Services.class);
	}

}
