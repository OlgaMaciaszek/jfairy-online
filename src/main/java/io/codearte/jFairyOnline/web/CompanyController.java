package io.codearte.jFairyOnline.web;

import java.util.Set;

import io.codearte.jFairyOnline.services.CompanyService;
import io.codearte.jfairy.producer.company.Company;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/24/17
 */
@Controller
@RequestMapping("/companies")
public class CompanyController {

	private static final String COMPANIES = "companies";
	private static final String COMPANY_TABLE_CONFIG = "companyTableConfig";

	private final CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping
	public String companies(@RequestParam(value = "lang", defaultValue = "EN") String languageTag,
	                        @RequestParam(value = "count", defaultValue = "100") int count,
	                        @RequestParam(value = "companyTableConfig", defaultValue = "empty") String[] config,
	                        Model model) {
		Set<Company> companies = companyService.getCompanies(languageTag, count);
		model.addAttribute(COMPANIES, companies);
		model.addAttribute(COMPANY_TABLE_CONFIG, config);
		return COMPANIES;
	}
}
