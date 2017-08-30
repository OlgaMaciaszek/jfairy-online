package io.codearte.jFairyOnline.services;

import java.util.Optional;

import io.codearte.jFairyOnline.model.CompanyName;
import io.codearte.jFairyOnline.model.enums.Language;
import io.codearte.jFairyOnline.repositories.CompanyNameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/30/17
 */
@Service
public class CompanyNameService {

	private static final Logger LOG = LoggerFactory.getLogger(CompanyNameService.class);

	private final CompanyNameRepository companyNameRepository;

	public CompanyNameService(CompanyNameRepository companyNameRepository) {
		this.companyNameRepository = companyNameRepository;
	}

	void process(String value, Language language) {
		Optional<CompanyName> companyNameOpt = companyNameRepository.findFirstByValueAndLanguage(value, language);
		if (companyNameOpt.isPresent()) {
			LOG.warn("Company name {} already exists for language: {}",
					value, language);
			return;
		}
		CompanyName companyName = new CompanyName(language, value);
		companyNameRepository.save(companyName);
	}
}
