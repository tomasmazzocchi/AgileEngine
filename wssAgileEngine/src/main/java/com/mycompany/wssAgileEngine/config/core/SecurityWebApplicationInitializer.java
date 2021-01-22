package com.mycompany.wssAgileEngine.config.core;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import com.mycompany.wssAgileEngine.config.SpringSecurityConfig;

public class SecurityWebApplicationInitializer extends
		AbstractSecurityWebApplicationInitializer {

	public SecurityWebApplicationInitializer() {
		super(SpringSecurityConfig.class);
	}

}