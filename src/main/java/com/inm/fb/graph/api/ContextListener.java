/**
 * 
 */
package com.inm.fb.graph.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.inm.fb.graph.api.config.AppProperties;

/**
 * Context intilialiser for log4j and properties
 * 
 * @author dkumar
 * 
 */
public class ContextListener implements ServletContextListener {

	private static final Logger LOGGER = Logger.getLogger(ContextListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// Initialise log4j
		ServletContext context = event.getServletContext();
		String log4jConfigFile = context.getInitParameter("log4jFile");
		String fullPath = context.getRealPath("") + File.separator
				+ log4jConfigFile;

		PropertyConfigurator.configure(fullPath);

		// Load Properties
		try {
			String fileName = context.getInitParameter("configFile");
			InputStream input = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(fileName);
			AppProperties m_properties = new AppProperties(input);
		} catch (IOException e) {
			LOGGER.error("Unable to load application properties.", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// do nothing
	}
}
