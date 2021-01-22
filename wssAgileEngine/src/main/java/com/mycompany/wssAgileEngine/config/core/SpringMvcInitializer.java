package com.mycompany.wssAgileEngine.config.core;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.mycompany.wssAgileEngine.config.MvcConfiguration;


public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

        @Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {};
	}

        @Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { MvcConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/api/*" };
	}

    }
