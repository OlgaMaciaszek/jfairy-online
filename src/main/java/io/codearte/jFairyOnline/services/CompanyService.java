package io.codearte.jFairyOnline.services;

import java.util.Set;

import io.codearte.jFairyOnline.services.fairy.FairyProvider;
import io.codearte.jFairyOnline.services.validation.CountProvider;
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
	private final CountProvider countProvider;

	@Autowired
	public CompanyService(FairyProvider fairyProvider, CountProvider countProvider) {
		this.fairyProvider = fairyProvider;
		this.countProvider = countProvider;
	}

	public Set<Company> getCompanies(String languageTag, int count) {
		Fairy fairy = fairyProvider.getFairy(languageTag);
		return range(0, countProvider.valid(count))
				.mapToObj(num -> fairy.company()).collect(toSet());
	}
}
