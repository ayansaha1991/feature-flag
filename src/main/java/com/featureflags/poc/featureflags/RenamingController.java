package com.featureflags.poc.featureflags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RenamingController {

	@Autowired
	Services renameService; 
	
	@Autowired
	Environment env;
	
	@GetMapping
	public void name() {
		System.out.println(env.getProperty("feature.rename"));
		renameService.rename();
	}
}
