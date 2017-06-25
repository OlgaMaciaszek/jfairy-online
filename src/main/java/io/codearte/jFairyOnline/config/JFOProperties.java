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

	public int getLimit() {
		return limit;
	}
}
