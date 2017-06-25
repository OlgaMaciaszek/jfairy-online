package io.codearte.jFairyOnline.rest;

import java.util.Set;

import io.codearte.jFairyOnline.config.JFOProperties;
import io.codearte.jFairyOnline.services.CompanyService;
import io.codearte.jfairy.producer.company.Company;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-02
 */
@RestController
@RequestMapping("/rest/companies")
public class CompanyController {

	private final CompanyService companyService;
	private final JFOProperties properties;

	public CompanyController(CompanyService companyService, JFOProperties properties) {
		this.companyService = companyService;
		this.properties = properties;
	}

	@RequestMapping
	Set<Company> companies(@RequestParam(value = "lang", defaultValue = "EN") String languageTag) {
		return companyService.getCompanies(languageTag, properties.getLimit());
	}

	@RequestMapping("/{number}")
	Set<Company> companies(@PathVariable int number, @RequestParam(value = "lang", defaultValue = "EN") String languageTag) {
		return companyService.getCompanies(languageTag, number);
	}

}
