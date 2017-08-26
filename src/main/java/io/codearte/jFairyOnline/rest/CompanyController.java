package io.codearte.jFairyOnline.rest;

import java.util.Set;

import io.codearte.jFairyOnline.services.CompanyService;
import io.codearte.jfairy.producer.company.Company;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-02
 */
@RestController("restCompanyController")
@RequestMapping("/rest/companies")
public class CompanyController {

	private final CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping
	Set<Company> companies(@RequestParam(value = "lang", defaultValue = "EN") String languageTag,
	                       @RequestParam(value = "count", defaultValue = "100") int count) {
		return companyService.getCompanies(languageTag, count);
	}

}
