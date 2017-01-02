package io.codearte.jFairyOnline.services;

import io.codearte.jFairyOnline.services.fairy.FairyProvider;
import io.codearte.jFairyOnline.services.validation.LimitValidator;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

	public Set<Company> getCompanies(String languageTag, int number) {
		limitValidator.validate(number);
		Fairy fairy = fairyProvider.getFairy(languageTag);
		return IntStream.range(0, number).mapToObj(i -> fairy.company()).collect(Collectors.toSet());
	}
}
