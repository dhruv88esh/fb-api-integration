/**
 * 
 */
package com.inm.fb.graph.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class to contain application properties
 * 
 * @author dkumar
 *
 */
public class AppProperties {

	private static Properties m_properties = new Properties();

	public AppProperties(InputStream input) throws IOException {
		m_properties.load(input);
	}

	/**
	 * Get application properties
	 * 
	 * @return Properties
	 */
	public static Properties getProperties() {
		return m_properties;
	}
}
