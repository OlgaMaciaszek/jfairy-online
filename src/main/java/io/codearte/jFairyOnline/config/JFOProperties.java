package io.codearte.jFairyOnline.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Olga Maciaszek-Sharma
 * @since 6/25/2017
 */
@ConfigurationProperties(prefix = "jfo.app")
@Component
public class JFOProperties {

	private int limit = 3000;
	private String defaultLanguageTag = "EN";
	private int textLimit = 1000;
	private int randomStringLimit = 165;

	public int getLimit() {
		return limit;
	}

	public String getDefaultLanguageTag() {
		return defaultLanguageTag;
	}

	public int getTextLimit() {
		return textLimit;
	}

	public int getRandomStringLimit() {
		return randomStringLimit;
	}
}
