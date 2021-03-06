package io.codearte.jFairyOnline.config;

import com.github.dandelion.core.web.DandelionFilter;
import com.github.dandelion.core.web.DandelionServlet;
import com.github.dandelion.datatables.core.web.filter.DatatablesFilter;
import com.github.dandelion.datatables.thymeleaf.dialect.DataTablesDialect;
import com.github.dandelion.thymeleaf.dialect.DandelionDialect;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Olga Maciaszek-Sharma
 */
@Configuration
public class WebConfig {

	@Bean
	public DandelionDialect dandelionDialect() {
		return new DandelionDialect();
	}

	@Bean
	public DataTablesDialect dataTablesDialect() {
		return new DataTablesDialect();
	}

	@Bean
	public FilterRegistrationBean dandelionFilterRegistration() {
		FilterRegistrationBean dandelionFilterRegistration = new FilterRegistrationBean();
		dandelionFilterRegistration.setFilter(new DandelionFilter());
		return dandelionFilterRegistration;
	}

	@Bean
	public FilterRegistrationBean datatablesFilterRegistration() {
		FilterRegistrationBean datatablesFilterRegistration = new FilterRegistrationBean();
		datatablesFilterRegistration.setFilter(new DatatablesFilter());
		datatablesFilterRegistration.addUrlPatterns("/*");
		datatablesFilterRegistration.setName("datatables");
		return datatablesFilterRegistration;
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setServlet(new DandelionServlet());
		servletRegistrationBean.addUrlMappings("/dandelion-assets/*");
		servletRegistrationBean.setName("dandelionServlet");
		return servletRegistrationBean;
	}
}
