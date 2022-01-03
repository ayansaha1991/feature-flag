package com.featureflags.poc.featureflags;

public class CustomerPrefferedRenaming implements Services {

	@Override
	public void rename() {
		System.out.println("Renaming with customer pref");
	}

}
