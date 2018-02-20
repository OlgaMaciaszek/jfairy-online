package io.codearte.jFairyOnline.config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/2/17
 */
@Component
class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	private static final String REALM = "JFO_REALM";

	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName(REALM);
		super.afterPropertiesSet();
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		super.commence(request, response, authException);
	}
}