package io.codearte.jFairyOnline.config;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

import io.codearte.jFairyOnline.services.MongoUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2016-12-27
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String REALM = "JFO_REALM";

	@Autowired
	private AuthenticationEntryPoint entryPoint;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		UserDetailsService userDetailsService = mongoUserDetails();
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	UserDetailsService mongoUserDetails() {
		return new MongoUserDetailsService();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/persons", "/companies", "/text/**", "/rest/**",
						"/webjars/**", "/dandelion-assets/**", "/dandelion/**", "/data", "/mgmt/**",
						"/v2/api-docs")
				.permitAll()
				.antMatchers("/admin/**", "/data/review/**", "/data/delete", "/data/process")
				.hasAuthority("admin")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
				.logout()
				.permitAll()
				.and()
				.httpBasic().realmName(REALM).authenticationEntryPoint(entryPoint);

		http.csrf().requireCsrfProtectionMatcher(new RequestMatcher() {
			private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
			private RegexRequestMatcher apiMatcher = new RegexRequestMatcher("/rest/.*", null);

			@Override
			public boolean matches(HttpServletRequest request) {
				if (allowedMethods.matcher(request.getMethod()).matches()) {
					return false;
				}
				if (apiMatcher.matches(request)) {
					return false;
				}
				return true;
			}
		});
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/scripts/**", "/swagger-ui**");
	}
}
