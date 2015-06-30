package com.company.news;

import com.company.news.commons.util.ApplicationProperties;


public class ProjectProperties {
	private static final String IBASE_DEFAULT_PROPERTY_FILE = "classpath:conf.properties";

	private static ApplicationProperties oApplicationProperties = null;

	private ProjectProperties() {
		oApplicationProperties = new ApplicationProperties(
				IBASE_DEFAULT_PROPERTY_FILE);
	}

	private static void loadProperties() {
		oApplicationProperties = new ApplicationProperties(
				IBASE_DEFAULT_PROPERTY_FILE);
	}

	public static String getProperty(String key, String defaultValue) {
		if (oApplicationProperties == null)
			loadProperties();
		return oApplicationProperties.getPropertyAsString(key, defaultValue);
	}

	public static int getPropertyAsInt(String key, int defaultValue) {
		if (oApplicationProperties == null)
			loadProperties();
		return oApplicationProperties.getPropertyAsInt(key, defaultValue);
	}
}
