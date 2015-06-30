package com.company.news.commons.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderSupport;
import org.springframework.util.StringUtils;

public class ApplicationProperties extends PropertiesLoaderSupport {
	private static final String ENCODING_UTF_8 = "UTF-8";

	private static final String ENCODING_ISO_8859_1 = "ISO-8859-1";

	private String CONFIG_FILE_NAME = null;

	protected Properties oProperties = null;

	private String CONFIG_LOCATION_DELIMITERS = ",; \t\n";

	private static final String prefix_classpath = "classpath:";

	private static final String suffix_properties = ".properties";

	/**
	 * 
	 * 构造函数

	 * 
	 * @param resourceLocations
	 *            采用spring的ClasspathResource方式,例如：classpath:test-file.properties
	 * @author david.liu 2007-8-27
	 */
	public ApplicationProperties(String resourceLocations) {
		CONFIG_FILE_NAME = resourceLocations;

		if (StringUtils.isEmpty(CONFIG_FILE_NAME)) {
			throw new java.lang.IllegalArgumentException("the properties file:"
					+ CONFIG_FILE_NAME + " not live!");
		}

		init(resourceLocations);

	}

	private void init(String resourceLocations) {
		boolean localOverride = true;

		boolean ignoreResourceNotFound = true;

		super.setIgnoreResourceNotFound(ignoreResourceNotFound);
		super.setLocalOverride(localOverride);
		super.setFileEncoding(ENCODING_UTF_8);

		PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

		String[] resourceLocationArrayList = StringUtils.tokenizeToStringArray(
				resourceLocations, CONFIG_LOCATION_DELIMITERS);
		ArrayList resourceList = new ArrayList();
		for (int i = 0, j = resourceLocationArrayList.length; i < j; i++) {
			resourceLocationArrayList[i] = StringUtils
					.trimWhitespace(resourceLocationArrayList[i]);
			if (!resourceLocationArrayList[i].startsWith(prefix_classpath)) {
				resourceLocationArrayList[i] = prefix_classpath
						+ resourceLocationArrayList[i];
			}
			if (!resourceLocationArrayList[i].endsWith(suffix_properties)) {
				resourceLocationArrayList[i] = resourceLocationArrayList[i]
						+ suffix_properties;
			}
			resourceList.add(resourceResolver
					.getResource(resourceLocationArrayList[i]));
		}
		Resource[] resources = new Resource[resourceList.size()];
		resources = (Resource[]) resourceList.toArray(resources);
		setLocations(resources);
	}

	protected void loadProperties() {
		try {
			oProperties = mergeProperties();
		} catch (IOException e) {
			super.logger.warn("Could not load properties from "
					+ CONFIG_FILE_NAME + ": " + e.getMessage());
		}
	}
	
	/**
	 * 
	 * <p><code>getProperty</code></p>
	 * @return Properties by the resourceLocations path
	 * @author david.liu 2007-9-4
	 */
	public Properties getProperty() {
		if (oProperties == null)
			loadProperties();

		return oProperties;
	}

	public String getPropertyAsString(String key, String defalutValue) {
		// String result = null;
		// try {
		// if (oProperties == null)
		// loadProperties();
		// byte[] bytes = oProperties.getProperty(key, defalutValue).getBytes(
		// ENCODING_ISO_8859_1);
		// result = new String(bytes, ENCODING_UTF_8);
		// } catch (Exception exception) {
		// return defalutValue;
		// }
		//
		// return result;
		String result = null;
		try {
			if (oProperties == null)
				loadProperties();
			result = oProperties.getProperty(key, defalutValue);
		} catch (Exception exception) {
			return defalutValue;
		}

		return result;

	}

	public int getPropertyAsInt(String key, int defaultValue) {
		int resultInt;
		try {
			if (oProperties == null)
				loadProperties();
			String resultString = oProperties.getProperty(key);
			resultInt = Integer.parseInt(resultString);
		} catch (Exception exception) {
			return defaultValue;
		}
		return resultInt;
	}
}
