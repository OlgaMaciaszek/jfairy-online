package io.codearte.jFairyOnline.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-01
 */
@RestController
public class HomeController {

	@RequestMapping("/")
	String home() {
		return "Welcome to jFairy Online!";
	}

}
