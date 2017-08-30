package io.codearte.jFairyOnline.config;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2016-12-27
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/persons", "/companies", "/text/**", "/rest/**",
						"/webjars/**", "/dandelion-assets/**", "/dandelion/**", "/data")
				.permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.and()
				.httpBasic();

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
		web.ignoring().antMatchers("/css/**", "/scripts/**");
	}
}
