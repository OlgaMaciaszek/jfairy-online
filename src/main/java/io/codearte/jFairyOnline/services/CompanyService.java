package io.codearte.jFairyOnline.services;

import java.util.Set;

import io.codearte.jFairyOnline.services.fairy.FairyProvider;
import io.codearte.jFairyOnline.services.validation.LimitValidator;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-02
 */
@Service
public class CompanyService {

	private final FairyProvider fairyProvider;
	private final LimitValidator limitValidator;

	@Autowired
	public CompanyService(FairyProvider fairyProvider, LimitValidator limitValidator) {
		this.fairyProvider = fairyProvider;
		this.limitValidator = limitValidator;
	}

	public Set<Company> getCompanies(String languageTag, int count) {
		limitValidator.validate(count);
		Fairy fairy = fairyProvider.getFairy(languageTag);
		return range(0, count).mapToObj(num -> fairy.company()).collect(toSet());
	}
}
