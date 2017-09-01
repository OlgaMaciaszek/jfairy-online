package io.codearte.jFairyOnline.repositories;

import java.util.Optional;

import io.codearte.jFairyOnline.model.DataPack;
import io.codearte.jFairyOnline.model.enums.DataType;
import io.codearte.jFairyOnline.model.enums.Language;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Olga Maciaszek-Sharma
 * @since 8/29/17
 */
public interface DataPackRepository extends MongoRepository<DataPack, String> {

	DataPack getOneById(String dataPackId);

	Optional<DataPack> findOneById(String dataPackId);

	Optional<DataPack> findFirstByLanguageAndDataTypeAndProcessedFalse(Language language, DataType dataType);

	Optional<DataPack> findFirstByLanguageAndProcessedFalse(Language language);

	Optional<DataPack> findFirstByDataTypeAndProcessedFalse(DataType dataType);

	Optional<DataPack> findFirstByProcessedFalse();
}
