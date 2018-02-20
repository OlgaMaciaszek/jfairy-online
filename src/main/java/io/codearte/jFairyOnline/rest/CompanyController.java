package io.codearte.jFairyOnline.rest;

import java.util.Set;

import io.codearte.jFairyOnline.services.CompanyService;
import io.codearte.jfairy.producer.company.Company;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

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
	public ResponseEntity<Set<Company>> companies(@RequestParam(value = "lang", defaultValue = "EN") String languageTag,
	                                              @RequestParam(value = "count", defaultValue = "100") int count) {
		Set<Company> companies = companyService.getCompanies(languageTag, count);
		return ok(companies);
	}

}
