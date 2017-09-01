package io.codearte.jFairyOnline.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/1/17
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	private static final String LOGIN = "login";

	@GetMapping
	public String login() {
		return LOGIN;
	}
}
