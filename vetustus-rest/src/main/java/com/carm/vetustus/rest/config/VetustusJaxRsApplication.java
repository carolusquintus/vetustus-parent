package com.carm.vetustus.rest.config;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class VetustusJaxRsApplication extends ResourceConfig {
	
	public VetustusJaxRsApplication() {
		packages("com.carm.vetustus.rest");
 
		// register filters
		register(RequestContextFilter.class);
		register(LoggingFilter.class);
 
		// register features
		register(JacksonFeature.class);
		register(MultiPartFeature.class);		
	}

}
