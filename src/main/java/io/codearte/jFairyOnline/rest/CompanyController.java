package io.codearte.jFairyOnline.rest;

import io.codearte.jFairyOnline.AppConstants;
import io.codearte.jFairyOnline.services.CompanyService;
import io.codearte.jfairy.producer.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-02
 */
@RestController
@RequestMapping("/rest/companies")
public class CompanyController {

	private final CompanyService companyService;

	@Autowired
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@RequestMapping
	Set<Company> companies(@RequestParam(value = "lang", defaultValue = "EN") String languageTag) {
		return companyService.getCompanies(languageTag, AppConstants.LIMIT);
	}

	@RequestMapping("/{number}")
	Set<Company> companies(@PathVariable int number, @RequestParam(value = "lang", defaultValue = "EN") String languageTag) {
		return companyService.getCompanies(languageTag, number);
	}

}
