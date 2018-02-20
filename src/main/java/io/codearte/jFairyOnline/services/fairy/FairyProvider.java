package io.codearte.jFairyOnline.services.fairy;

import io.codearte.jfairy.Fairy;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2017-01-02
 */
public interface FairyProvider {

	Fairy getFairy(String languageTag);
}
