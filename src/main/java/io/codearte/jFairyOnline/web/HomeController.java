package io.codearte.jFairyOnline.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-01
 */
@Controller
@RequestMapping("/")
public class HomeController {

	static final String HOME = "home";

	@GetMapping
	public String home() {
		return HOME;
	}

}
